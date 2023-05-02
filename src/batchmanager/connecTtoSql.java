package batchmanager;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class connecTtoSql {
	
	public static  Connection doConnect()
	{
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
				con = 	DriverManager.getConnection("jdbc:mysql://localhost/institute","root","");
				System.out.println("DataBase Connected Successfulyyyy...............");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void main(String args[])
	{
		doConnect();
	}

}
