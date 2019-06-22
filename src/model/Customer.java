package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Customer {
	/**
	 * Identifier.
	 */
	private int id;

	/**
	 * Name.
	 */
	private String name;

	/**
	 * Get the identifier.
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Set the identifier.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set the name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	public void save() {
		Connection connection = DatabaseConnection.getInstance().getConnection();
		try {
			Statement statement = connection.createStatement();

			/* Create table "customers" if it doesn't exist */
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS customers (id INTEGER PRIMARY KEY AUTOINCREMENT, name STRING)");

			/* Create customer */
			statement.executeUpdate("INSERT INTO customers(name) VALUES('" + name + "')");
		} catch (SQLException ex) {
			System.err.println("Couldn't get statement: " + ex.getMessage());
		}
	}
}
