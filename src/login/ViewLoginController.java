package login;

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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewLoginController {

	private Stage stage;
	private Scene scene;
   private Parent root;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchory;

    @FXML
    private Button btnlog;

    @FXML
    private Button btnsign;

    @FXML
    private TextField txtpass;

    @FXML
    private TextField txtusername;

    @FXML
    void doSignup(ActionEvent event) {

    }

    void showMsg(String msg)
    {
    	Alert ms = new Alert(AlertType.CONFIRMATION);
    	ms.setTitle("Choises are : ");
    	ms.setContentText(msg);
    	ms.show();
    }
    
    void errorMsg(String msg)
    {
    	Alert ms = new Alert(AlertType.ERROR);
    	ms.setTitle("Choises are : ");
    	ms.setContentText(msg);
    	ms.show();
    }
    
    @FXML
    void dologin(ActionEvent event) {
           
    	try {
			pst = con.prepareStatement("Select * from user");
			ResultSet res = pst.executeQuery();
			
			while(res.next())
			{
				String use = res.getString("username");
				String pass = res.getString("Password");
				System.out.println(use+"   "+pass);
				
				String user = txtusername.getText();
				String pawd = txtpass.getText();
				
				if(use.equals(user)&& pass.equals(pawd))
				{
				
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
					
					showMsg("Welcome"+user);
				}
				
				else
				{
					errorMsg("Wrong Password");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    Connection con;
    PreparedStatement pst;

    @FXML
    void initialize() {
        con = connecTtoSql.doConnect();

    }

}
