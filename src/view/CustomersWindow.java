package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CustomerController;
import model.DatabaseConnection;

import javax.swing.JTextField;
import javax.swing.JLabel;

public class CustomersWindow extends JFrame {

	/**
	 * Window.
	 */
	private JPanel windowPanel;
	private JTextField textField;
	
	/**
	 * Open the window.
	 */
	public static void open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					(new CustomersWindow()).setVisible(true);
					
				} catch (Exception e) {
					System.err.println("Couldn't open the " + getClass().getName());
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomersWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 643, 530);
		windowPanel = new JPanel();
		windowPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(windowPanel);
				
		textField = new JTextField();
		textField.setBounds(10, 54, 447, 20);
		windowPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setBounds(269, 438, 161, 38);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().isEmpty() || textField.getText().startsWith(" ")) {
						JOptionPane.showMessageDialog(null, "O nome do Cliente não pode estar vazio");
						throw new Exception("O nome Cliente vazio");	
					}				
					else	{
						
						
						new CustomerController().createCustomer(textField.getText());  
						Connection connection = DatabaseConnection.getInstance().getConnection();
						Statement statement = connection.createStatement();
						ResultSet rs = statement.executeQuery("Select * from customers");
						while(rs.next()) {
							String t = rs.getString("name");
							System.out.println(t);
						}
						System.out.println(statement.getResultSet().toString());
						JOptionPane.showMessageDialog(null, "Cliente Cadastrado com sucesso\nCliente: " + textField.getText());
					}
					
				}catch(Exception ex) {
					System.err.println(ex.getMessage());
				}

			}
		});
		windowPanel.setLayout(null);
		windowPanel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Nome Cliente:");
		lblNewLabel.setBounds(10, 35, 92, 14);
		windowPanel.add(lblNewLabel);
		
		
	}
}
