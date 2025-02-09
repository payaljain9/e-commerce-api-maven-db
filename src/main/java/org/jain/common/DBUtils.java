package org.jain.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
	private static String url="jdbc:mysql://localhost:3306/managment";
	private static String username="root";
	private static String password="Payal@j9067";
	
	private static Connection con;
	
	static 
	{
		try
		{
			con=DriverManager.getConnection(url, username, password);
		}
		catch(SQLException e)
		{
			System.out.println("Got error while establishing db connection..!");
			e.printStackTrace();
		}
	}
	
	public static ResultSet executeSelectQuery(String query)
	{
		Statement stmt;
		ResultSet rs=null;
		try 
		{
			stmt = con.createStatement();
			rs=stmt.executeQuery(query);
		} 
		catch (SQLException e) 
		{
			System.out.println("Got error while executing query..!");
			e.printStackTrace();
		}
		
		return rs;
	}
	
	
	public static int executeDMLQuery(String query)
	{
		Statement stmt;
		int c=0;
		try 
		{
			stmt = con.createStatement();
			c=stmt.executeUpdate(query);
		} 
		catch (SQLException e) 
		{
			System.out.println("Got error while executing query..!");
			e.printStackTrace();
		}
		
		return c;
	}
	
	
}
