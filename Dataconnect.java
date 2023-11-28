package com.training.connect;

import java.sql.Connection;
import java.sql.DriverManager;

	public class Dataconnect {
	private static Connection con;
		
		public static Connection getConnect() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Onlineshop","root","password123");
				System.out.println("connection established");
				
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
			return con;
		}	
	}
