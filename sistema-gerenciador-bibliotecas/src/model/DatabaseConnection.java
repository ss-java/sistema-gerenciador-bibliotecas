package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	/**
	 * Stores the connection with the SQL database.
	 */
	private Connection connection;
	
	/**
	 * Stores a Singleton instance.
	 */
	private static DatabaseConnection instance;
	
	/**
	 * Constructor.
	 */
	private DatabaseConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			this.connection = DriverManager.getConnection("jdbc:sqlite:biblioteca.db");
		} catch (SQLException ex) {
			System.err.println("Couldn't connect to SQL: " + ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.err.println("Couldn't find the SQLite driver: " + ex.getMessage());
		}
	}
	
	/**
	 * Singleton method.
	 */
	public static DatabaseConnection getInstance() {
		if (instance == null)
			instance = new DatabaseConnection();
		
		return instance;
	}
	
	public Connection getConnection() {
		return this.connection;
	}
}
