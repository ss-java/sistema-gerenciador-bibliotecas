package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Book;
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
		Book book = new Book();
		book.setName(name);
		book.save();
	}
	
	/**
	 * 
	 * Checks if the book you want to insert into the database is already registered.
	 */
	
	public boolean checkBook(ArrayList<Book> l, String nameBook) {
		for(Book book : l) {
			if(book.getName().equals(nameBook)) {
				System.out.println("Livro j� cadastrado");
				return false;
			}
		}
		return true;
	}
	
	
	/** 
	 * A method that assists in the insertion of new books. 
	 */
	
	public Book addBooks(int id, String name) {
		Book book = new Book();
	
		book.setId(id);
		book.setName(name);
	
		return book;
	}

	public ResultSet getAllBooks() {
		Connection connection = DatabaseConnection.getInstance().getConnection();

		try {
			Statement statement = connection.createStatement();

			/* Create table "books" if it doesn't exist */
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS books (id INTEGER PRIMARY KEY AUTOINCREMENT, name STRING)");

			/* Carrega todas as informacoes do banco de dados */
			return statement.executeQuery("SELECT * FROM books");
		} catch (SQLException ex) {
			System.err.println("Couldn't get statement: " + ex.getMessage());
		}

		return null;
	}
}
