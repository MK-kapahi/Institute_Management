package admission;

import java.io.IOException;
import java.net.URL;
import javafx.scene.layout.AnchorPane;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.scene.image.ImageView;
import batchmanager.connecTtoSql;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ViewAdmissionController {
	
	private Stage stage;
	private Scene scene;
   private Parent root;

   @FXML
   private TextField txtid;
   
   @FXML
   private Button btnret;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imgpic;
    
    @FXML
    private ComboBox<String> Combocollege;

    @FXML
    private ComboBox<String> Combotech;

    @FXML
    private TextField tctmob;

    @FXML
    private TextField txtadfee;

    @FXML
    private ComboBox<String> txtbranch;

    @FXML
    private TextField txtdate;

    @FXML
    private TextField txtfee;

    @FXML
    private TextField txtname;

    @FXML
    private ComboBox<String> txtsem;
    @FXML
    private AnchorPane anchor;

    @FXML
    private TextField txttime;

    String pic;
    @FXML
    void doBrowse(ActionEvent event) {
    	FileChooser fx = new FileChooser();
        fx.setTitle("Choose a file");
        Stage stg = (Stage)anchor.getScene().getWindow();
       
       java.io.File show = fx.showOpenDialog(stg);
       
       pic = show.getAbsolutePath();
       if(show!=null)
       {
      	 Image ymg = new javafx.scene.image.Image(show.toURI().toString());
      	 imgpic.setImage(ymg);
       } 
    }

    @FXML
    void doClear(ActionEvent event) {
    	txtname.clear();
    	tctmob.clear();
    	Combocollege.getEditor().clear();
    	

    }

    @FXML
    void doDelete(ActionEvent event) {
    	try {
    		String str = getid(); 
			pst = con.prepareStatement("delete from batchess where T_id=?");
			 pst.setString(1, str);
			 int c = pst.executeUpdate();
			 
			 showMsg(c+"Record Deleted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    String getid()
    {
    	Random rnd=new Random();
    	 
    		int r = rnd.nextInt(1000);
    	//System.out.println(r);
    	
    	String name = txtname.getText();
    	String name1 = name.substring(0, 3);
    	String num = tctmob.getText();
    	String num1 = num.substring(num.length()-2, num.length());
    	System.out.println(num1+"    "+name1);
    	
    	StringBuilder str  = new StringBuilder();
    	str.append(name1).append(String.valueOf(r)).append(num1);
    	
    	return str.toString();
    }
    
    void showMsg(String msg)
    {
    	Alert ms = new Alert(AlertType.CONFIRMATION);
    	ms.setTitle("Choises are : ");
    	ms.setContentText(msg);
    	ms.show();
    }
    
    @FXML
    void doSave(ActionEvent event) {
    	
    	String str = getid();
    	try {
			pst = con.prepareStatement("insert into admission values(?,?,?,?,?,?,?,?,?,?,?,?,?,current_date())");
			pst.setString(1,str);
			pst.setString(2, txtname.getText());
			pst.setString(3, tctmob.getText());
			pst.setString(4, Combocollege.getEditor().getText());
			pst.setString(5, txtsem.getSelectionModel().getSelectedItem());
			pst.setString(6, txtbranch.getSelectionModel().getSelectedItem());
			pst.setString(7,Combotech.getSelectionModel().getSelectedItem());
			pst.setString(8, txtdate.getText());
			pst.setString(9, txttime.getText());
			pst.setFloat(10, Float.parseFloat(txtfee.getText()));
			pst.setFloat(11, Float.parseFloat(txtadfee.getText()));
			Float bal =Float.parseFloat( txtfee.getText())-Float.parseFloat(txtadfee.getText());
			pst.setFloat(12, Float.parseFloat(String.valueOf(bal)));
			pst.setString(13, pic);
			pst.executeUpdate();
			showMsg("Data Submitted Succsessfulyy "+" Your id is"+str);
			txtid.setText(str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    @FXML
    void goback(ActionEvent event)  {
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
    void doSearch(ActionEvent event) {
    try {
		pst =  con.prepareStatement("select * from admission where T_id=?");
		pst.setString(1, txtid.getText());
		ResultSet tble = pst.executeQuery();
			
	    while(tble.next())
	    {
	    	String id = tble.getString("T_id");
	    	String nme = tble.getString("Name");
	    	String mob = tble.getString("Mobile_no");
	    	String coll = tble.getString("College");
	    	String sem = tble.getString("Semester");
	    	String brnch = tble.getString("Branch");
	    	String Tech = tble.getString("Tech");
	    	String dte = tble.getString("sdate");
	    	String tme = tble.getString("stime");
	    	Float fee = tble.getFloat("Fee");
	    	Float ad = tble.getFloat("adfee");
	    	Float bal = tble.getFloat("bfee");
	    	String pic2= tble.getString("pic");
	    	Date d = tble.getDate("Date");
	    	
	    	System.out.println(nme+"   "+mob);
	    	txtname.setText(nme);
	    	tctmob.setText(mob);
	    	Combocollege.setValue(coll);
	    	txtsem.setValue(sem);
	    	txtbranch.setValue(brnch);
	    	Combotech.setValue(Tech);
	    	txtdate.setText(dte);
	    	txttime.setText(tme);
	    	txtfee.setText(String.valueOf(fee));
	    	txtadfee.setText(String.valueOf(ad));
	    	
	    }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    }

    PreparedStatement pst;
    @FXML
    void showdetails(ActionEvent event) {
             try {
				pst = con.prepareStatement("select Sdate,Stime,Fee from batchess where B_name=?");
				pst.setString(1, Combotech.getSelectionModel().getSelectedItem());
				ResultSet tbl = pst.executeQuery();
				while(tbl.next())
				{
					String db = tbl.getString("Sdate");
					String s = tbl.getString("Stime");
					Float fee = tbl.getFloat("Fee");
					
					System.out.println("   "+db+"    "+s+"  "+fee);
					txtdate.setText(db);
					txttime.setText(s);
					txtfee.setText(String.valueOf(fee));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             
    }
    Connection con;
    @FXML
    void initialize() {
    	
    	ArrayList<String> ary1 = new ArrayList<String>(Arrays.asList("ECE","CSE","ME","CE"));
    	txtbranch.getItems().addAll(ary1);
        
    	ArrayList<String> ary = new ArrayList<String>(Arrays.asList("1","2","3","4","5","6","7","8","Beginneer","Passout"));
    	txtsem.getItems().addAll(ary);
      con=connecTtoSql.doConnect();
      try {
			pst = con.prepareStatement("select B_name from batchess");
			ResultSet tbl = pst.executeQuery();
			
			while(tbl.next())
			{
				String str = tbl.getString("B_name");
				System.out.println(str);
				Combotech.getItems().add(str);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}

