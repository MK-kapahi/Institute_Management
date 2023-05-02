package batchmanager;

import java.io.IOException;
import java.net.URL;



import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Date;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ViewBatchManagerController {
	
	private Stage stage;
	private Scene scene;
   private Parent root;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> Combobatch;

    @FXML
    private AnchorPane anchor;

    @FXML
    private ImageView imgpic;

    @FXML
    private TextField txtbokseats;

    @FXML
    private DatePicker txtdate;

    @FXML
    private TextField txtfee;

    @FXML
    private TextField txtseats;

    @FXML
    private ComboBox<String> txttime;

    @FXML
    void doBrowse(ActionEvent event) {
    	FileChooser fx = new FileChooser();
        fx.setTitle("Choose a file");
        Stage stg = (Stage)anchor.getScene().getWindow();
//       fx.setInitialFileName("icon.png");
       
       java.io.File show = fx.showOpenDialog(stg);
       
       if(show!=null)
       {
      	 Image ymg = new javafx.scene.image.Image(show.toURI().toString());
      	 imgpic.setImage(ymg);
       } 
    }

    @FXML
    void doDelete(ActionEvent event) {
    	 try {
				pst = con.prepareStatement("delete from batchess where B_name =?");
				 pst.setString(1, Combobatch.getEditor().getText());
				 int c = pst.executeUpdate();
				 
				 showMsg(c+"Record Deleted");
					doFill();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    }
    void showMsg(String msg)
    {
    	Alert ms = new Alert(AlertType.CONFIRMATION);
    	ms.setTitle("Choises are : ");
    	ms.setContentText(msg);
    	ms.show();
    }
    PreparedStatement pst;
     Connection con;
    @FXML
    void doSave(ActionEvent event) {
    	doFill();
    	try
    	{
    	pst = con.prepareStatement("insert into batchess values (?,?,?,?,?,?)");
		pst.setString(1,Combobatch.getEditor().getText());
		LocalDate lcl = txtdate.getValue();
		pst.setDate(2, java.sql.Date.valueOf(lcl));
		pst.setString(3, txttime.getEditor().getText());
		pst.setInt(4, Integer.parseInt(txtseats.getText()));
		pst.setInt(5, Integer.parseInt(txtbokseats.getText()));
		pst.setFloat(6, Float.parseFloat(txtfee.getText()));
		pst.executeUpdate();
		showMsg("Data Submitted Succsessfulyy ");
    	}
	 catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }

    @FXML
    void doSearch(ActionEvent event) {
    	try {
			pst = con.prepareStatement("select  * from batchess where B_name=?");
			pst.setString(1, Combobatch.getEditor().getText());
			ResultSet tbl = pst.executeQuery();
			
			boolean j = false;
			while(tbl.next())
			{
				j= true;
				String roll = tbl.getString("B_name");
				Date db = tbl.getDate("Sdate");
				String s = tbl.getString("Stime");
				int Seat = tbl.getInt("Tseats");
				int Bseat = tbl.getInt("Booked");
				Float fee = tbl.getFloat("Fee");
				
				System.out.println(roll+"   "+db.toString()+"    "+s+"   "+Seat+"  "+Bseat+"  "+fee);
				txtdate.setValue(db.toLocalDate());
				txttime.setValue(s);
				txtseats.setText(String.valueOf(Seat));
				txtbokseats.setText(String.valueOf(Bseat));
				txtfee.setText(String.valueOf(fee));
			
				txtdate.setValue(db.toLocalDate());
			}
			
			if(j==false)
			{
				showMsg("Not found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void doUpdate(ActionEvent event) {
    	 try {
 			pst = con.prepareStatement("update batchess set Sdate=? ,Stime=?, Tseats=?,Booked=?, Fee=? where B_name=?   ");
 			pst.setString(6, Combobatch.getEditor().getText());
 			LocalDate lcl = txtdate.getValue();
 			pst.setDate(1, java.sql.Date.valueOf(lcl));
 			pst.setString(2,  txttime.getEditor().getText());
 			pst.setInt(3, Integer.parseInt(txtseats.getText()));
            pst.setInt(4, Integer.parseInt(txtbokseats.getText()));
            pst.setFloat(5, Float.parseFloat(txtfee.getText()));
 			pst.executeUpdate();
 			showMsg("Data Updated Succsessfulyy");
 			doFill();
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    }

    void doFill()
    {
    	Combobatch.getItems().clear();
    	txttime.getItems().clear();
    	
    	try {
			pst = con.prepareStatement("select B_name,Stime from batchess");
			ResultSet tbl= pst.executeQuery();
			
			while(tbl.next())
			{
				String str = tbl.getString("B_name");
				String time = tbl.getString("Stime");
				Combobatch.getItems().add(str);
				txttime.getItems().add(time);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void doGoback(ActionEvent event) {
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
    void doNew(ActionEvent event) {
    	Combobatch.getEditor().clear();
       txtdate.setValue(null);
       txttime.getEditor().clear();
       txtfee.clear();
       txtseats.clear();
       txtbokseats.clear();
    }
    
    @FXML
    void initialize() {
       con = connecTtoSql.doConnect();
       doFill();
       

    }

}
