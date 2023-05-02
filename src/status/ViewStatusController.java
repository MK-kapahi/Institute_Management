package status;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import batchmanager.connecTtoSql;
import googler.GoogleBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import status.StatusBean;

public class ViewStatusController {
	private Stage stage;
	private Scene scene;
   private Parent root;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> ComboTech;

    @FXML
    private RadioButton radiobal;

    @FXML
    private RadioButton radiofull;

    @FXML
    private TableView<StatusBean> table;

    void doAddCols()
    {
    	TableColumn<StatusBean, String> idcol=new TableColumn<StatusBean, String>("Id");
    	idcol.setCellValueFactory(new PropertyValueFactory<>("id"));//same as bean property
    	idcol.setMinWidth(100);
    	
    	TableColumn<StatusBean, String> nameCol=new TableColumn<StatusBean, String>("Name");
    	nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));//same as bean property
    	nameCol.setMinWidth(100);
    	
    	TableColumn<StatusBean, String> noCol=new TableColumn<StatusBean, String>("Mobile-No.");
    	noCol.setCellValueFactory(new PropertyValueFactory<>("Mobile"));//same as bean property
    	noCol.setMinWidth(100);
    	
    	TableColumn<StatusBean, String> CCol=new TableColumn<StatusBean, String>("College");
    	CCol.setCellValueFactory(new PropertyValueFactory<>("College"));//same as bean property
    	CCol.setMinWidth(100);
    	
    	TableColumn<StatusBean, String> smCol=new TableColumn<StatusBean, String>("Semester");
    	smCol.setCellValueFactory(new PropertyValueFactory<>("Sem"));//same as bean property
    	smCol.setMinWidth(100);
    	
    	TableColumn<StatusBean, String> BCol=new TableColumn<StatusBean, String>("Branch");
    	BCol.setCellValueFactory(new PropertyValueFactory<>("branch"));//same as bean property
    	BCol.setMinWidth(100);
    	
    	TableColumn<StatusBean, String> TeCol=new TableColumn<StatusBean, String>("Technology");
    	TeCol.setCellValueFactory(new PropertyValueFactory<>("tech"));//same as bean property
    	TeCol.setMinWidth(100);
    	
    	
    	TableColumn<StatusBean, String> SCol=new TableColumn<StatusBean, String>("Start-date");
    	SCol.setCellValueFactory(new PropertyValueFactory<>("Date"));//same as bean property
    	SCol.setMinWidth(100);
    	
    	TableColumn<StatusBean, String> TCol=new TableColumn<StatusBean, String>("Start-time");
    	TCol.setCellValueFactory(new PropertyValueFactory<>("Stime"));//same as bean property
    	TCol.setMinWidth(100);
    	
    	TableColumn<StatusBean, Float> FCol=new TableColumn<StatusBean, Float>("Fee");
    	FCol.setCellValueFactory(new PropertyValueFactory<>("Total"));//same as bean property
    	FCol.setMinWidth(100);
    	
    	TableColumn<StatusBean, Float> adCol=new TableColumn<StatusBean, Float>("Pid");
    	adCol.setCellValueFactory(new PropertyValueFactory<>("Paid"));//same as bean property
    	adCol.setMinWidth(100);

    	
    	TableColumn<StatusBean, Float> Bfee=new TableColumn<StatusBean, Float>("Balance");
    	Bfee.setCellValueFactory(new PropertyValueFactory<>("bal"));//same as bean property
    	Bfee.setMinWidth(100);
    	
    	TableColumn<StatusBean, String> DCol=new TableColumn<StatusBean, String>("Date");
    	DCol.setCellValueFactory(new PropertyValueFactory<>("date"));//same as bean property
    	DCol.setMinWidth(100);

    	
    	table.getColumns().addAll(idcol,nameCol,noCol,CCol,smCol,TeCol,SCol,TCol,FCol,adCol,Bfee,DCol);
    	
    	
    }
    
    
    ObservableList<StatusBean> fetchfull()
    {
    	ObservableList<StatusBean> ary=FXCollections.observableArrayList();
    	try {
			
			PreparedStatement pst=con.prepareStatement("select * from admission where bfee=0 and Tech=?");
			pst.setString(1, ComboTech.getSelectionModel().getSelectedItem());
			ResultSet tableInMem= pst.executeQuery();
			
			while(tableInMem.next())
			{ 
				String str= tableInMem.getString("T_id");
				String nme=tableInMem.getString("Name");
				String mon=tableInMem.getString("Mobile_no");
				String College = tableInMem.getString("College");
				String sem = tableInMem.getString("Semester");
				String brn = tableInMem.getString("Branch");
				String techn = tableInMem.getString("Tech");
			    String sdate = tableInMem.getString("sdate");
			    String stime = tableInMem.getString("stime");
				Float fee=tableInMem.getFloat("Fee");
				Float adv=tableInMem.getFloat("adfee");
				Float bal=tableInMem.getFloat("bfee");
				Date doj = tableInMem.getDate("Date");
				StatusBean obj = new StatusBean(str,nme,mon,College,sem,brn,techn,sdate,stime,fee,adv,bal,doj.toString());
				ary.add(obj);
				System.out.println(str+"  "+nme+"  "+mon+"   "+College+"    "+sem+"    "+ brn+"   "+doj.toString());
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return ary;

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
    ObservableList<StatusBean> fetchbal()
    {
    	ObservableList<StatusBean> ary=FXCollections.observableArrayList();
    	try {
			
			PreparedStatement pst=con.prepareStatement("select * from admission where Tech=? and not bfee=0");
			pst.setString(1, ComboTech.getSelectionModel().getSelectedItem());
			ResultSet tableInMem= pst.executeQuery();
			
			while(tableInMem.next())
			{ 
				String str= tableInMem.getString("T_id");
				String nme=tableInMem.getString("Name");
				String mon=tableInMem.getString("Mobile_no");
				String College = tableInMem.getString("College");
				String sem = tableInMem.getString("Semester");
				String brn = tableInMem.getString("Branch");
				String techn = tableInMem.getString("Tech");
			    String sdate = tableInMem.getString("sdate");
			    String stime = tableInMem.getString("stime");
				Float fee=tableInMem.getFloat("Fee");
				Float adv=tableInMem.getFloat("adfee");
				Float bal=tableInMem.getFloat("bfee");
				Date doj = tableInMem.getDate("Date");
				StatusBean obj = new StatusBean(str,nme,mon,College,sem,brn,techn,sdate,stime,fee,adv,bal,doj.toString());
				ary.add(obj);
				System.out.println(str+"  "+nme+"  "+mon+"   "+College+"    "+sem+"    "+ brn+"   "+doj.toString());
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return ary;

    }
    @FXML
    void doSearch(ActionEvent event) {
ObservableList<StatusBean> objary=FXCollections.observableArrayList();
    	
    	if(radiofull.isSelected()==true)
    	{
    		objary=fetchfull();
    		table.getItems().clear();
    		table.setItems(objary);
    	}
    		
    	
    	else
    		if(radiobal.isSelected()==true)
    	{
    		objary=fetchbal();
    		table.getItems().clear();
    		table.setItems(objary);
    	}
    	else
    		getMsg("Please Make a Choice");   	
    	
    }
    void getMsg(String msg)
    {
    	Alert ms = new Alert(AlertType.WARNING);
    	ms.setTitle("Choises are : ");
    	ms.setContentText(msg);
    	ms.show();
    }
    
    Connection con;
    PreparedStatement pst;
    
    @FXML
    void initialize() {
    	con=connecTtoSql.doConnect();
        try {
  			pst = con.prepareStatement("select B_name from batchess");
  			ResultSet tbl = pst.executeQuery();
  			
  			while(tbl.next())
  			{
  				String str = tbl.getString("B_name");
  				System.out.println(str);
  				ComboTech.getItems().add(str);
  			}
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
        
        doAddCols();
    }

}
