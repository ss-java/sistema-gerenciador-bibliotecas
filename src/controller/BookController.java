package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.DatabaseConnection;

public class BookController {
	/**
	 * Store a Singleton instance.
	 */
	private static BookController instance;
	
	/**
	 * Singleton method.
	 */
	public static BookController getInstance() {
		if (instance == null)
			instance = new BookController();
		
		return instance;
	}
	
	/**
	 * Create a book.
	 */
	public void createBook(String name) {
		Connection connection = DatabaseConnection.getInstance().getConnection();
		
		try {
			Statement statement = connection.createStatement();
			
			/* Create table "books" if it doesn't exist */
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS books (id INTEGER PRIMARY KEY AUTOINCREMENT, name STRING)");
			
			/* Create book */
			statement.executeUpdate("INSERT INTO books(name) VALUES('" + name + "')");
		} catch (SQLException ex) {
			System.err.println("Couldn't get statement: " + ex.getMessage());
		}
	}
	
	/**
	 * Load books.
	*/
	public ArrayList<String> loadBooks() {
			Connection connection = DatabaseConnection.getInstance().getConnection();
			ArrayList<String> livros = new ArrayList();
		
		try {
			Statement statement = connection.createStatement();
			
			/* Load books */
			String sql = "SELECT name FROM books";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				livros.add(rs.getString("name").toString());
			};
			
			
		} catch (SQLException ex) {
			System.err.println("Couldn't get statement: " + ex.getMessage());
		}
		
		return livros;
	}
	
}
