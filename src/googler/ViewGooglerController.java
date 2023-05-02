package googler;

import java.io.IOException;
import java.net.URL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import batchmanager.connecTtoSql;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import showall.BatchBean;
import googler.GoogleBean;

public class ViewGooglerController  {
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
    private ComboBox<String> Combocoll;

    @FXML
    private TableView<GoogleBean> tbleonscreen;

    @FXML
    void doSearch1(ActionEvent event) {
    	ObservableList<GoogleBean> ary=getAll1();
    	tbleonscreen.setItems(ary);
    }

    @FXML
    void doSearch2(ActionEvent event) {
    	ObservableList<GoogleBean> ary=getAll2();
    	tbleonscreen.setItems(ary);
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
    
    void doAddCols()
    {
    	TableColumn<GoogleBean, String> idcol=new TableColumn<GoogleBean, String>("Id");
    	idcol.setCellValueFactory(new PropertyValueFactory<>("id"));//same as bean property
    	idcol.setMinWidth(100);
    	
    	TableColumn<GoogleBean, String> nameCol=new TableColumn<GoogleBean, String>("Name");
    	nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));//same as bean property
    	nameCol.setMinWidth(100);
    	
    	TableColumn<GoogleBean, String> noCol=new TableColumn<GoogleBean, String>("Mobile-No.");
    	noCol.setCellValueFactory(new PropertyValueFactory<>("Mobile"));//same as bean property
    	noCol.setMinWidth(100);
    	
    	TableColumn<GoogleBean, String> CCol=new TableColumn<GoogleBean, String>("College");
    	CCol.setCellValueFactory(new PropertyValueFactory<>("College"));//same as bean property
    	CCol.setMinWidth(100);
    	
    	TableColumn<GoogleBean, String> smCol=new TableColumn<GoogleBean, String>("Semester");
    	smCol.setCellValueFactory(new PropertyValueFactory<>("Sem"));//same as bean property
    	smCol.setMinWidth(100);
    	
    	TableColumn<GoogleBean, String> BCol=new TableColumn<GoogleBean, String>("Branch");
    	BCol.setCellValueFactory(new PropertyValueFactory<>("branch"));//same as bean property
    	BCol.setMinWidth(100);
    	
    	TableColumn<GoogleBean, String> TeCol=new TableColumn<GoogleBean, String>("Technology");
    	TeCol.setCellValueFactory(new PropertyValueFactory<>("Technology"));//same as bean property
    	TeCol.setMinWidth(100);
    	
    	
    	TableColumn<GoogleBean, String> SCol=new TableColumn<GoogleBean, String>("Start-date");
    	SCol.setCellValueFactory(new PropertyValueFactory<>("Sdate"));//same as bean property
    	SCol.setMinWidth(100);
    	
    	TableColumn<GoogleBean, String> TCol=new TableColumn<GoogleBean, String>("Start-time");
    	TCol.setCellValueFactory(new PropertyValueFactory<>("Stime"));//same as bean property
    	TCol.setMinWidth(100);
    	
    	TableColumn<GoogleBean, Float> FCol=new TableColumn<GoogleBean, Float>("Fee");
    	FCol.setCellValueFactory(new PropertyValueFactory<>("Tfee"));//same as bean property
    	FCol.setMinWidth(100);
    	
    	TableColumn<GoogleBean, Float> adCol=new TableColumn<GoogleBean, Float>("Advance-Fee");
    	adCol.setCellValueFactory(new PropertyValueFactory<>("adv"));//same as bean property
    	adCol.setMinWidth(100);

    	
    	TableColumn<GoogleBean, Float> Bfee=new TableColumn<GoogleBean, Float>("Balance");
    	Bfee.setCellValueFactory(new PropertyValueFactory<>("bal"));//same as bean property
    	Bfee.setMinWidth(100);
    	
    	TableColumn<GoogleBean, String> DCol=new TableColumn<GoogleBean, String>("Date");
    	DCol.setCellValueFactory(new PropertyValueFactory<>("date"));//same as bean property
    	DCol.setMinWidth(100);

    	
    	tbleonscreen.getColumns().addAll(idcol,nameCol,noCol,CCol,smCol,TeCol,SCol,TCol,FCol,adCol,Bfee,DCol);
    	
    	
    }
    
    @FXML
    void doShowAll(ActionEvent event) {
    	ObservableList<GoogleBean> ary=fetchAll();
    	tbleonscreen.setItems(ary);
    }

    PreparedStatement  pst;
    Connection con;
    ObservableList<GoogleBean> fetchAll()
    {
    	ObservableList<GoogleBean> ary=FXCollections.observableArrayList();
    	try {
			
			PreparedStatement pst=con.prepareStatement("select * from  admission");
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
				GoogleBean obj = new GoogleBean(str,nme,mon,College,sem,brn,techn,sdate,stime,fee,adv,bal,doj.toString());
				ary.add(obj);
				System.out.println(str+"  "+nme+"  "+mon+"   "+College+"    "+sem+"    "+ brn+"   "+doj.toString());
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return ary;

    }
    
    
    ObservableList<GoogleBean> getAll2()
    {
    	ObservableList<GoogleBean> ary=FXCollections.observableArrayList();
    	try {
			
			PreparedStatement pst=con.prepareStatement("select * from  admission where Tech =?");
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
				GoogleBean obj = new GoogleBean(str,nme,mon,College,sem,brn,techn,sdate,stime,fee,adv,bal,doj.toString());
				ary.add(obj);
				System.out.println(str+"  "+nme+"  "+mon+"   "+College+"    "+sem+"    "+ brn+"   "+doj.toString());
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return ary;

    }
    
    
    ObservableList<GoogleBean> getAll1()
    {
    	ObservableList<GoogleBean> ary=FXCollections.observableArrayList();
    	try {
			
			PreparedStatement pst=con.prepareStatement("select * from  admission Where College=?");
			pst.setString(1, Combocoll.getSelectionModel().getSelectedItem());
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
				GoogleBean obj = new GoogleBean(str,nme,mon,College,sem,brn,techn,sdate,stime,fee,adv,bal,doj.toString());
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
    void initialize() {
       con = connecTtoSql.doConnect();
          
       try {
		pst= con.prepareStatement("select College from admission");
		ResultSet tbl = pst.executeQuery();
		
		while(tbl.next())
		{
			String coll = tbl.getString("College");
			System.out.println(coll);
			Combocoll.getItems().add(coll);
		}
	} 
       catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
       
       
       try {
		pst = con.prepareStatement("select  B_name from batchess");
         ResultSet tbl1 = pst.executeQuery();
         
		while(tbl1.next())
		{
			String tech = tbl1.getString("B_name");
			System.out.println(tech);
			ComboTech.getItems().add(tech);
		}    
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
       doAddCols();
    }

}

