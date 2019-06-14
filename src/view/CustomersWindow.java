package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.CustomerController;
import controller.SampleController;
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

import com.sun.java.swing.plaf.windows.resources.windows;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CustomersWindow extends JFrame {

	/**
	 * Window.
	 */
	private JPanel windowPanel;
	private JTextField textField;
	private JTable table;
	static Connection connection = DatabaseConnection.getInstance().getConnection();
	static DefaultTableModel tableModel = new DefaultTableModel();
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
		buildWindowContainer();
		buildFormArea();
		buildButtonsArea();
		buildDataTable();
		populateDataTable();
	}
	
	private void buildWindowContainer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 920, 600);
		windowPanel = new JPanel();
		windowPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(windowPanel);
		windowPanel.setLayout(null);
	}

	private void buildFormArea() {
		JPanel formPanel = new JPanel();
		formPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		formPanel.setBounds(12, 12, 370, 494);
		formPanel.setLayout(null);
		
		JLabel lblSample = new JLabel("Nome do Cliente");
		lblSample.setBounds(12, 12, 107, 15);
		formPanel.add(lblSample);
	
		textField = new JTextField();
		textField.setBounds(12, 39, 306, 19);
		textField.setColumns(10);
		
		windowPanel.add(formPanel);
		formPanel.add(textField);
	}
	
	private void buildButtonsArea() {
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		buttonsPanel.setBounds(12, 518, 370, 40);
		buttonsPanel.setLayout(new FlowLayout());
		
		JButton btnSave = new JButton("Salvar");
		JButton btnAdd = new JButton("Adicionar");
		JButton btnEdit = new JButton("Editar");
		JButton btnDelete = new JButton("Excluir");
		
		buttonsPanel.add(btnSave);
		buttonsPanel.add(btnAdd);
		buttonsPanel.add(btnEdit);
		buttonsPanel.add(btnDelete);
		windowPanel.add(buttonsPanel);
		
		btnSave.addActionListener(new SaveButtonListener());
		//btnAdd.addActionListener(arg0);
		//btnEdit.addActionListener(l);
		//btnDelete.addActionListener(l);
		
	}
	
	private void buildDataTable() {
		table = new JTable(tableModel);
		
		tableModel.addColumn("#");
		tableModel.addColumn("Nome");
		
		JScrollPane scrlDataTable = new JScrollPane(table);
		scrlDataTable.setBounds(394, 12, 512, 546);
		scrlDataTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		windowPanel.add(scrlDataTable);
		
	}
	
	private void populateDataTable() {
		ResultSet data = CustomerController.getInstance().getAllSamples();
		try {
			tableModel.setRowCount(0);
			while (data.next()) {
				Object[] model = new Object[] { data.getInt("id"), data.getString("name") };
				tableModel.addRow(model);
			}
			tableModel.setColumnCount(2);
		} catch(SQLException e) {}		
	}

	private class SaveButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String name = textField.getText();
			
			if (name.isEmpty())
				return;
			
			CustomerController.getInstance().createCustomer(name);
			
			textField.setText(null);	
			
			new CustomersWindow().populateDataTable();
		}
	}
}
