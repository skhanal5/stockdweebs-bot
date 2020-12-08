package me.skhanal.StockDweebs;

import java.sql.*;

public class SQL {

	public static void main(String[]args) {
		
		try {
			//1. get a connection to the database
			Connection myConn = DriverManager.getConnection("jdbc:mysql://locallhost:3306/demo", "student", "student");
			
			//2. create a statement
			
			//3. execute a SQL query
			
			//4. Process the result set
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
