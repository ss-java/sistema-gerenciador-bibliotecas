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

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CustomersWindow extends JFrame {

	/**
	 * Window.
	 */
	private JPanel windowPanel;
	private JTextField textField;
	private JTable table;
	static DefaultTableModel tableModel = new DefaultTableModel();

	public CustomerController controller = CustomerController.getInstance();
	public CustomersWindow instance;
	public WindowCustomersState state = new WindowCustomersState();

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
		instance = this;
	}

	private void buildWindowContainer() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 920, 600);
		windowPanel = new JPanel();
		windowPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(windowPanel);
		windowPanel.setLayout(null);
	}

	private void buildFormArea() {
		BorderFacade border = new BorderFacade();

		JLabel lblSample = new JLabel("Nome do Cliente");
		lblSample.setBounds(12, 12, 107, 15);
		border.add(lblSample);

		textField = new JTextField();
		textField.setBounds(12, 39, 306, 19);
		textField.setColumns(10);

		windowPanel.add(border.getPanel());
		border.add(textField);
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
		btnAdd.addActionListener(new AddButtonListener());
		btnEdit.addActionListener(new EditButtonListener());
		// btnDelete.addActionListener(l);

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
		ResultSet data = controller.getAllCustomers();
		try {
			tableModel.setRowCount(0);
			while (data.next()) {
				Object[] model = new Object[] { data.getInt("id"), data.getString("name") };
				tableModel.addRow(model);
			}
			tableModel.setColumnCount(2);
		} catch (SQLException e) {
		}
	}

	public void createCustomer() {
		String name = textField.getText();

		if (name.isEmpty())
			return;

		controller.createCustomer(name);
		System.out.println("Cliente: " + name + " cadastrado com sucesso");

		textField.setText(null);

		new CustomersWindow().populateDataTable();
	}

	public void editCustomer() {
		String name = textField.getText();
		
		if (name.isEmpty())
			return;
		
		System.out.println("Cliente: " + name + " editado com sucesso");

	}

	private class SaveButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			state.onSave(instance);
		}
	}

	private class AddButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			state.setState(new CustomersCreateWindowState());

			state.onSave(instance);
		}
	}
		
	private class EditButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			state.setState(new CustomersEditWindowState());

			state.onSave(instance);
		}
	}
	
}
