package com.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DbConnect{
	String sql;
	public static Connection getconn(){
		Connection connect=null;
		String user   = "root";
		String passwd = "";
		String url = "jdbc:mysql://localhost:3306/shopping_management_system";
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 connect = DriverManager.getConnection(url,user,passwd);		
	    }
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return connect;
	}
	
}
