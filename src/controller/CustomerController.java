package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public ResultSet getAllCustomers() {

		Connection connection = DatabaseConnection.getInstance().getConnection();
		
		try {
			Statement statement = connection.createStatement();
			/* Create table "customers" if it doesn't exist */
			statement.executeUpdate(""
				+ "CREATE TABLE IF NOT EXISTS CUSTOMERS ("
				+ "	id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "	name STRING "
				+ ")"
			);
			/* Carrega todas as informacoes do banco de dados */
			return statement.executeQuery("SELECT * FROM CUSTOMERS");
		} catch (SQLException ex) {
			System.err.println("Couldn't get statement: " + ex.getMessage());
		}
		
		return null;
	}
	
	public void deleteCustomers(int id) {
		Connection connection = DatabaseConnection.getInstance().getConnection();
		
		try {
			Statement statement = connection.createStatement();
			
			statement.executeUpdate("DELETE FROM CUSTOMERS WHERE ID = " + id);
			
		} catch (SQLException ex) {
			System.err.println("Couldn't get statement: " + ex.getMessage());
		}
	}
	
	public void editCustomers(int id, String name) {
		Connection connection = DatabaseConnection.getInstance().getConnection();
		
		try {
			Statement statement = connection.createStatement();			
			
			statement.executeUpdate("UPDATE CUSTOMERS SET NAME = " + name + "WHERE ID = " + id);
			
		}catch(SQLException ex) {
			System.err.println("Couldn't get statement: " + ex.getMessage());			
		}
	}
}
