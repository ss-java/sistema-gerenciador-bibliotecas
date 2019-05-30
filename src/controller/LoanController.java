package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import model.Book;
import model.Customer;
import model.DatabaseConnection;

public class LoanController {
	/**
	 * Store a Singleton instance.
	 */
	private static LoanController instance;
	
	/**
	 * Singleton method.
	 */
	public static LoanController getInstance() {
		if (instance == null)
			instance = new LoanController();
		
		return instance;
	}
	
	/**
	 * Create a loan.
	*/
	public void createLoan(Book book, Customer customer) {
		Connection connection = DatabaseConnection.getInstance().getConnection();
		
		try {
			Statement statement = connection.createStatement();
			
			/* Create table "loans" if it doesn't exist */
			statement.executeUpdate(""
				+ "CREATE TABLE IF NOT EXISTS loans ("
				+ "	id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "	book_id INTEGER, "
				+ "	customer_id INTEGER, "
				+ "	FOREIGN KEY(book_id) REFERENCES books(id), "
				+ "	FOREIGN KEY(customer_id) REFERENCES customers(id)"
				+ ")"
			);
			
			/* Create loan */
			statement.executeUpdate(""
				+ "INSERT INTO loans(book_id, customer_id)"
				+ "VALUES(" + book.getId() + ", " + customer.getId() + ")"
			);
		} catch (SQLException ex) {
			System.err.println("Couldn't get statement: " + ex.getMessage());
		}
	}
	
	/**
	 * Load a loan.
	*/
	public void loadLoan() {
			Connection connection = DatabaseConnection.getInstance().getConnection();
		
		try {
			Statement statement = connection.createStatement();
			
			/* Load loan */
			String sql = "SELECT count(*) FROM books";
			ResultSet rs = statement.executeQuery(sql);
			int idCount = Integer.parseInt(rs.getString(1));
			System.out.println(idCount);

			
			
		} catch (SQLException ex) {
			System.err.println("Couldn't get statement: " + ex.getMessage());
		}
	}
}
