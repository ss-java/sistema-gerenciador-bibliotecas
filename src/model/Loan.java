package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Loan {
	private int id;
	private int book_id;
	private int customerId;
	
	private Object[] record;
	
	private Statement statement;
	
	/* Getter e Setter id Loan */
	public int getId() {
		return this.id;
	}
	
	public void setId(int newId) {
		this.id = newId; 
	}
	
	/* Getter e Setter book_id  */
	public int getBookId() {
		return this.book_id;
	}
	
	public void setBookId(int newBookId) {
		this.book_id = newBookId;
	}
	
	/* Getter e Setter Customer */
	public int getCustomerId() {
		return this.customerId;
	}
	
	public void setCustomerId(int newCustomerId) {
		this.customerId = newCustomerId;
	}
	
	public Object[] getRecord() {
		return this.record;
	}
	
	public void setRecord(Object[] newRecord) {
		this.record = newRecord;
	}
	
	private void createTable() {
		Connection connection = DatabaseConnection.getInstance().getConnection();
		
		try {
			this.statement = connection.createStatement();
			
			/* Create table "loans" if it doesn't exist */
			this.statement.executeUpdate(""
				+ "CREATE TABLE IF NOT EXISTS loans ("
				+ "	id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "	book_id INTEGER, "
				+ "	customer_id INTEGER, "
				+ "	FOREIGN KEY(book_id) REFERENCES books(id), "
				+ "	FOREIGN KEY(customer_id) REFERENCES customers(id)"
				+ ")"
			);
		} catch (SQLException ex) {
			System.err.println("Couldn't get statement: " + ex.getMessage());
		}	
	}
	
	private boolean selectBookId(boolean sucessCreate) {
		try {
			String sql = "SELECT book_id FROM loans WHERE book_id = " + this.book_id;
			
			ResultSet rs = this.statement.executeQuery(sql);
			
			if(rs.next()) {
				System.out.println("Livro já emprestado!");
				return false;

			} else {
				/* Create loan */
				insertLoan();
				System.out.println(sucessCreate);
				return true;
			}		
			
		}
		
		catch(SQLException ex) {
			System.err.println("Couldn't get statement: " + ex.getMessage());
		}
		return true;	
	}
	
	private void insertLoan() {
		try {
			/* Create loan */
			this.statement.executeUpdate(""
					+ "INSERT INTO loans(book_id, customer_id)"
					+ "VALUES(" + this.book_id + ", " + this.customerId + ")"
			);
			
			this.record = new Object[] { this.book_id, this.customerId };
		}
		
		catch(SQLException ex) {
			System.err.println("Couldn't get statement: " + ex.getMessage());
		}	
	}
	
	public boolean save(boolean sucess) {
		createTable();
		sucess = selectBookId(sucess);
		return sucess;
	}
}
