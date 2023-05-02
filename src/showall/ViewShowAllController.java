package showall;

import java.io.IOException;
import java.net.URL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import showall.BatchBean;


public class ViewShowAllController {

	private Stage stage;
	private Scene scene;
   private Parent root;
   
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button doShowAll;

    @FXML
    private TableView<BatchBean> tblView;
    
    
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
    void doShowAll(ActionEvent event) {
    	ObservableList<BatchBean> ary=fetchAll();
    	tblView.setItems(ary);
    }
    
    void doAddCols()
    {
    	TableColumn<BatchBean, String> rollCol=new TableColumn<BatchBean, String>("batch");
    	rollCol.setCellValueFactory(new PropertyValueFactory<>("batch"));//same as bean property
    	rollCol.setMinWidth(100);
    	
    	TableColumn<BatchBean, String> nameCol=new TableColumn<BatchBean, String>("date");
    	nameCol.setCellValueFactory(new PropertyValueFactory<>("date"));//same as bean property
    	nameCol.setMinWidth(100);
    	
    	TableColumn<BatchBean, String> perCol=new TableColumn<BatchBean, String>("Per kuch");
    	perCol.setCellValueFactory(new PropertyValueFactory<>("time"));//same as bean property
    	perCol.setMinWidth(100);
    	
    	TableColumn<BatchBean, Integer> TsCol=new TableColumn<BatchBean, Integer>("seat");
    	TsCol.setCellValueFactory(new PropertyValueFactory<>("tseats"));//same as bean property
    	TsCol.setMinWidth(100);
    	
    	TableColumn<BatchBean, Integer> BksCol=new TableColumn<BatchBean, Integer>("bseat");
    	BksCol.setCellValueFactory(new PropertyValueFactory<>("bseats"));//same as bean property
    	BksCol.setMinWidth(100);
    	
    	TableColumn<BatchBean, Float> Bfee=new TableColumn<BatchBean, Float>("feee");
    	Bfee.setCellValueFactory(new PropertyValueFactory<>("fee"));//same as bean property
    	Bfee.setMinWidth(100);
    	
    	tblView.getColumns().addAll(rollCol,nameCol,perCol,TsCol,BksCol,Bfee);
    	
    	
    }

    Connection con;
    ObservableList<BatchBean> fetchAll()
    {
    	ObservableList<BatchBean> ary=FXCollections.observableArrayList();
    	try {
			
			PreparedStatement pst=con.prepareStatement("select * from  batchess");
			ResultSet tableInMem= pst.executeQuery();
			
			while(tableInMem.next())
			{
				String str= tableInMem.getString("B_name");
				Date doj=tableInMem.getDate("Sdate");
				String time=tableInMem.getString("Stime");
				int seat = tableInMem.getInt("Tseats");
			    int bokseat = tableInMem.getInt("Booked");
				Float fee=tableInMem.getFloat("Fee");
				BatchBean obj = new BatchBean(str,doj.toString(),time,seat,bokseat,fee);
				ary.add(obj);
				System.out.println(str+"  "+time+"  "+seat+"   "+doj.toString());
				
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
       doAddCols();
    }

}

