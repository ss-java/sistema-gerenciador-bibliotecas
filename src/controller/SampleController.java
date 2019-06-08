package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Book;
import model.Customer;
import model.DatabaseConnection;

public class SampleController {
	/**
	 * Store a Singleton instance.
	 */
	private static SampleController instance;
	
	/**
	 * Singleton method.
	 */
	public static SampleController getInstance() {
		if (instance == null)
			instance = new SampleController();
		
		return instance;
	}
	
	/**
	 * Cria um registro no banco de dados.
	 */
	public void createSample(String name) {
		Connection connection = DatabaseConnection.getInstance().getConnection();
		
		try {
			Statement statement = connection.createStatement();
			
			/* Create sample record and add it into the "samples" table */
			statement.executeUpdate(""
				+ "INSERT INTO samples(name)"
				+ "VALUES('" + name + "')"
			);
		} catch (SQLException ex) {
			System.err.println("Couldn't get statement: " + ex.getMessage());
		}
	}
	
	/**
	 * 
	 */
	public ResultSet getAllSamples() {
		Connection connection = DatabaseConnection.getInstance().getConnection();
		
		try {
			Statement statement = connection.createStatement();
			
			/* Create table "samples" if it doesn't exist */
			statement.executeUpdate(""
				+ "CREATE TABLE IF NOT EXISTS samples ("
				+ "	id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "	name STRING "
				+ ")"
			);
			
			/* Carrega todas as informacoes do banco de dados */
			return statement.executeQuery("SELECT * FROM samples");
		} catch (SQLException ex) {
			System.err.println("Couldn't get statement: " + ex.getMessage());
		}
		
		return null;
	}
}
