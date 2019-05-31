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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class CustomersWindow extends JFrame {

	/**
	 * Window.
	 */
	private JPanel windowPanel;
	private JTextField textField;
	private JTable table;
	
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
		setBounds(100, 100, 800, 600);
		windowPanel = new JPanel();
		windowPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(windowPanel);
				
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nome do Cliente:");
		
		JButton btnExcluir = new JButton("Excluir");
		
		JButton btnEditar = new JButton("Editar");
		
		JButton btnAdicionar = new JButton("Adicionar");
		
		JButton btnSalvar = new JButton("Salvar");
		
		table = new JTable();
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "O nome do Cliente não pode estar vazio");
						throw new Exception("O nome Cliente vazio");	
					}				
					else	{												
						new CustomerController().createCustomer(textField.getText());  
						Connection connection = DatabaseConnection.getInstance().getConnection();
						Statement statement = connection.createStatement();
						ResultSet rs = statement.executeQuery("Select name from customers");
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
		table.setToolTipText("");
		GroupLayout gl_windowPanel = new GroupLayout(windowPanel);
		gl_windowPanel.setHorizontalGroup(
			gl_windowPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_windowPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_windowPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_windowPanel.createSequentialGroup()
							.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE))
		);
		gl_windowPanel.setVerticalGroup(
			gl_windowPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_windowPanel.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 467, Short.MAX_VALUE)
					.addGroup(gl_windowPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
				.addGroup(Alignment.LEADING, gl_windowPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE))
		);
		windowPanel.setLayout(gl_windowPanel);
	}
}
