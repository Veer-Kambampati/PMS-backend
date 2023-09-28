package com.OPTSApplication.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConfig {
	
	private static String dbhost = "jdbc:mysql://localhost:3306/";
	private static String username = "root";
	private static String password = "";
	private static Connection conn;
	
	@SuppressWarnings("finally")
	public static Connection createNewconnection() {
		try  {	
			conn = DriverManager.getConnection(
					dbhost, username, password);	
		} catch (SQLException e) {
			System.out.println("Cannot create database connection");
			e.printStackTrace();
		} finally {
			return conn;	
		}		
	}

}
