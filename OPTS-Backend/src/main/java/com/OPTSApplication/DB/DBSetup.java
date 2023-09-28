package com.OPTSApplication.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBSetup {
	
	public static void main(String[] args) {
		try(Connection conn = createConnection()){
			createSchemaAndTables(conn);
			System.out.println("Tables and schema created");
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	private static String dbhost = "jdbc:mysql://localhost:3306";
	private static String username = "root";
	private static String password = "";
	private static Connection conn;
	
	@SuppressWarnings("finally")
	public static Connection createConnection() {
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
	
	private static void createSchemaAndTables(Connection connection) {
		try {
			connection.prepareStatement("DROP SCHEMA if exists ProjectManagementSystem").execute();
			PreparedStatement statement = connection.prepareStatement("CREATE DATABASE ProjectManagementSystem"); 
			statement.execute();
			connection.setSchema("ProjectManagementSystem");
			String createUser = "CREATE TABLE ProjectManagementSystem.user ("+ 
					"					user_id MEDIUMINT NOT NULL AUTO_INCREMENT,"+ 
					"					user_name varchar(150)," +
					"					password varchar(300)," +
					"					email varchar(150)," +
					"					phone_number varchar(150)," +
					"					user_role varchar(150)," +
					"					Constraint PK Primary Key(user_id)"+ 
					"					)";
			String createProject = "CREATE TABLE ProjectManagementSystem.project (" + 
					"					project_id MEDIUMINT NOT NULL AUTO_INCREMENT,"+
					"					user_id MEDIUMINT NOT NULL,"+
					"					project_name varchar(150)," +
					"					license varchar(150)," +
					"					url varchar(150)," +
					"					description varchar(15000)," +
					"					state varchar(150)," +
					"					last_updated varchar(150)," +
					"					Constraint PK Primary Key(project_id),"+
					" 	 				Constraint project_fk FOREIGN KEY (user_id) REFERENCES user(user_id)"+	
					")";
			
			
			connection.prepareStatement(createUser).execute();
			connection.prepareStatement(createProject).execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
