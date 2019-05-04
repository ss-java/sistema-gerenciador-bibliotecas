package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import model.DatabaseConnection;

public class BookController {
	/**
	 * Stores a Singleton instance.
	 */
	private static BookController instance;
	
	/**
	 * Constructor.
	 */
	private BookController() {}
	
	/**
	 * Singleton method.
	 */
	public static BookController getInstance() {
		if (instance == null)
			instance = new BookController();
		
		return instance;
	}
	
	public void createBook(String name) {
		Connection connection = DatabaseConnection.getInstance().getConnection();
		
		try {
			Statement statement = connection.createStatement();
			
			/* Creates the table "books" if it doesn't exist */
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS books (id INTEGER PRIMARY KEY AUTOINCREMENT, name STRING)");
			
			/* Creates the book */
			statement.executeUpdate("INSERT INTO books(name) VALUES('" + name + "')");
		} catch (SQLException ex) {
			System.err.println("Couldn't get statement: " + ex.getMessage());
		}
	}
}
