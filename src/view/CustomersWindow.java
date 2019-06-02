package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CustomersWindow extends JFrame {

	/**
	 * Window.
	 */
	private JPanel windowPanel;
	private JTextField textField;
	static JTable table = new JTable(1,1);;
	static Connection connection = DatabaseConnection.getInstance().getConnection();
	static ArrayList<String> colunaNomes = new ArrayList<>();
	static DefaultTableModel model = (DefaultTableModel) table.getModel();
	/**
	 * Open the window.
	 */
	public static void open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Statement statement = connection.createStatement();
					ResultSet rs = statement.executeQuery("Select name from customers");
										
					while(rs.next()) {				
						colunaNomes.add(rs.getString("name"));						
					}
					for(String nome : colunaNomes){
						model.addRow(new String[] {nome});						
					}				
					
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

		JScrollPane scroll = new JScrollPane();
		

		scroll.setViewportView(table);
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					
					
					if(textField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "O nome do Cliente não pode estar vazio");
						throw new Exception("O nome Cliente vazio");	
					}				
					else	{												
						new CustomerController().createCustomer(textField.getText());
						model.addRow(new String[] {textField.getText()});	
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
