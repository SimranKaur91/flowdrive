package com.flowdrive.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectivity {

	String query;
	Connection connection;
	Statement st;
	ResultSet rs;
	static int testcaseCount=0;
	
	public DatabaseConnectivity(String database){

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/"+database,"root","root");
			st=connection.createStatement();		
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public ResultSet getData(String query){
		try {
			rs=st.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public void sendData(String query){
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
