package feecollector;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import batchmanager.connecTtoSql;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ViewFeeController {
	
	private Stage stage;
	private Scene scene;
   private Parent root;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtad;

    @FXML
    private TextField txtamt;

    @FXML
    private TextField txtbal;

    @FXML
    private TextField txtfee;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txttech;

    @FXML
    private TextField txttime;

    @FXML
    void doSearch(ActionEvent event) {
      try {
		pst = con.prepareStatement("select Tech,stime,fee,adfee,bfee from admission where T_id=?");
		pst.setString(1, txtid.getText());
		ResultSet res = pst.executeQuery();
		
		while(res.next())
		{
			String Tech = res.getString("Tech");
			String time = res.getString("stime");
			Float total = res.getFloat("fee");
			Float advance = res.getFloat("adfee");
			Float balance = res.getFloat("bfee");
			
			System.out.println(time+"   "+Tech+"   "+total+"   "+advance+"   "+balance+"    ");
			txttech.setText(Tech);
			txttime.setText(time);
			txtfee.setText(String.valueOf(total));
			txtad.setText(String.valueOf(advance));
			txtbal.setText(String.valueOf(balance));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
    }

    @FXML
    void doUpdate(ActionEvent event) {

    	try {
			pst = con.prepareStatement("update admission set adfee=adfee+? , bfee=bfee-? where T_id=?");
			pst.setFloat(1,Float.parseFloat(txtamt.getText()));
			pst.setFloat(2,Float.parseFloat(txtamt.getText()));
			pst.setString(3,txtid.getText());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	showMsg("Data-Updated Successfulyyyyy");
    	
    }
    void showMsg(String msg)
    {
    	Alert ms = new Alert(AlertType.CONFIRMATION);
    	ms.setTitle("Choises are : ");
    	ms.setContentText(msg);
    	ms.show();
    }
    
    @FXML
    void goback(ActionEvent event) {
    	try {
			root = FXMLLoader.load(getClass().getResource("/dashboard/DashView.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void doClear(ActionEvent event) {
           txtid.clear();
           txttech.clear();
           txttime.clear();
           txtfee.clear();
           txtad.clear();
           txtbal.clear();
           txtamt.clear();
    }

    Connection con;
    PreparedStatement pst;
    @FXML
    void initialize() {
        con = connecTtoSql.doConnect();
    }

}
