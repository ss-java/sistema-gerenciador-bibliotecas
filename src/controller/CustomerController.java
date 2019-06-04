package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.DatabaseConnection;

public class CustomerController {
	/**
	 * Store a Singleton instance.
	 */
	private static CustomerController instance;
	
	/**
	 * Singleton method.
	 */
	public static CustomerController getInstance() {
		if (instance == null)
			instance = new CustomerController();
		
		return instance;
	}
	
	/**
	 * Create a customer.
	 */
	public void createCustomer(String name) {
		Connection connection = DatabaseConnection.getInstance().getConnection();
		
		try {
			Statement statement = connection.createStatement();
			
			/* Create table "customers" if it doesn't exist */
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS customers (id INTEGER PRIMARY KEY AUTOINCREMENT, name STRING)");
			
			/* Create customer */
			statement.executeUpdate("INSERT INTO customers(name) VALUES('" + name + "')");
		} catch (SQLException ex) {
			System.err.println("Couldn't get statement: " + ex.getMessage());
		}
	}
	
	
	/**
	 * Load customers.
	*/
	public ArrayList<String> loadCustomers() {
			Connection connection = DatabaseConnection.getInstance().getConnection();
			ArrayList<String> clientes = new ArrayList();
		
		try {
			Statement statement = connection.createStatement();
			
			/* Load customers */
			String sql = "SELECT name FROM customers";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				clientes.add(rs.getString("name").toString());
			};
			
			
		} catch (SQLException ex) {
			System.err.println("Couldn't get statement: " + ex.getMessage());
		}
		
		return clientes;
	}
	
}
