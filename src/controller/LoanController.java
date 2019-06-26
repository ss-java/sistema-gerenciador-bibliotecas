package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Book;
import model.Customer;
import model.DatabaseConnection;
import model.Loan;

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
	 * Retorno deve ser avaliado.
	 * Está retornando um boolean para verificar
	 * se livro foi emprestado ou não.
	 */
	public boolean createLoan(Book book, Customer customer, boolean sucess) {
		Loan loan = new Loan();
		loan.setBookId(book.getId());
		loan.setCustomerId(customer.getId());
		boolean sucesso = sucess;
		sucess = loan.save(sucesso);
		return sucess;
	}
	
	 public ResultSet getAllLoan() {
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
				
				/* Carrega todas as informacoes do banco de dados */
				return statement.executeQuery("SELECT * FROM loans");
			} catch (SQLException ex) {
				System.err.println("Couldn't get statement: " + ex.getMessage());
			}
			
			return null;
	}
	
	public ResultSet getAllLoans() {
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
			
			/* Carrega todas as informacoes do banco de dados */
			return statement.executeQuery("SELECT books.name as bookName, customers.name as customerName"
					+ " FROM books, customers, loans"
					+ " WHERE loans.book_id = books.id and loans.customer_id = customers.id");
		} catch (SQLException ex) {
			System.err.println("Couldn't get statement: " + ex.getMessage());
		}
		
		return null;
	}
}
