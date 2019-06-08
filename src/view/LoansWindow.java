package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.BookController;
import controller.CustomerController;
import controller.LoanController;
import controller.SampleController;
import model.Book;
import model.Customer;

import javax.swing.JComboBox;

public class LoansWindow extends JFrame {
	
	/**
	 * Janela
	 */
	private JPanel contentPane;
	
	/**
	 * Tabela de informacoes
	 */
	private JTable tblData;
	
	JComboBox cbLivros = new JComboBox();
	JComboBox cbClientes = new JComboBox();
	
	ArrayList<Book> books;
	ArrayList<Customer> customers;
	
	/**
	 * Os dados da tabela. A classe "DefaultTableModel" 
	 * eh uma especie de "espelho". Sempre que ela for 
	 * alterada, automaticamente atualizara a tabela de 
	 * dados.
	 */
	private DefaultTableModel records = new DefaultTableModel();
	
	public static void open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					(new LoansWindow()).setVisible(true);
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
	public LoansWindow() {
		buildWindowContainer();
		buildFormArea();
		buildButtonsArea();
		buildDataTable();
		populateDataTable();
	}
	
	private void buildWindowContainer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 920, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	/**
	 * Cria a area com o formulario.
	 */
	private void buildFormArea() {
		// Cria o painel com as bordas para separar 
		// o conteudo visualmente
		JPanel formPanel = new JPanel();
		formPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		formPanel.setBounds(12, 12, 370, 494);
		formPanel.setLayout(null);
		
		// Cria o label em cima do campo de texto
		JLabel lblLivro = new JLabel("Livro");
		lblLivro.setBounds(12, 12, 107, 15);
		formPanel.add(lblLivro);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(12, 70, 107, 15);
		formPanel.add(lblCliente);
		
		// Adiciona os elementos na tela. So chamar 
		// "new" nao eh o suficiente. Precisamos incluir 
		// os elementos que criamos na janela chamando 
		// "#add()".
		contentPane.add(formPanel);
		
		
		cbLivros.setBounds(12, 38, 314, 20);
		formPanel.add(cbLivros);
		ResultSet data = BookController.getInstance().getAllBooks();
		try {
			while (data.next()) {
				cbLivros.addItem(data.getString("name"));
			}
		} catch(SQLException e) {}
		
		cbClientes.setBounds(12, 91, 314, 20);
		formPanel.add(cbClientes);
		data = CustomerController.getInstance().getAllCustomers();
		try {
			while (data.next()) {
				cbClientes.addItem(data.getString("name"));
			}
		} catch(SQLException e) {}
		
	}
	
	/**
	 * Cria a area com os botoes.
	 */
	private void buildButtonsArea() {
		// Cria o painel com as bordas para separar 
		// os botoes visualmente na janela.
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		buttonsPanel.setBounds(12, 518, 370, 40);
		buttonsPanel.setLayout(new FlowLayout());
		
		// Cria os botoes.
		JButton btnLoan = new JButton("Emprestar");
		JButton btnAdd = new JButton("Adicionar");
		JButton btnEdit = new JButton("Editar");
		JButton btnDelete = new JButton("Excluir");
		
		// Add os botoes na janela.
		buttonsPanel.add(btnLoan);
		buttonsPanel.add(btnAdd);
		buttonsPanel.add(btnEdit);
		buttonsPanel.add(btnDelete);
		contentPane.add(buttonsPanel);
		
		// Aqui, informamos qual classe vai cuidar do 
		// comportamento de cada botao.
		btnLoan.addActionListener(new LoanButtonListener());
	}
	
	/**
	 * Cria a tabela de informacoes e adiciona os valores 
	 * nela.
	 */
	private void buildDataTable() {
		// Aqui nos criamos uma JTable e, como parametro, 
		// passamos o nosso "espelho".
		// Com isso o JTable cria uma relacao com o espelho.
		// Sempre que o modelo eh atualizado, o JTable tambem 
		// eh.
		tblData = new JTable(records);
		
		// Adicionamos as colunas da tabela. Perceba que 
		// as colunas sao adicionadas no espelho e nao na 
		// instancia do JTable em si.
		records.addColumn("Livro");
		records.addColumn("Cliente");
		
		// Aqui nos criamos um "JScrollPane". Diferente de 
		// aplicacoes Web, onde a barra de rolagem eh geralmente 
		// criada automaticamente, no Java eh diferente. Voce 
		// precisa, manualmente, dizer que quer criar uma barra de 
		// rolagem para um determinado componente.
		JScrollPane scrlDataTable = new JScrollPane(tblData);
		scrlDataTable.setBounds(394, 12, 512, 546);
		scrlDataTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		contentPane.add(scrlDataTable);
	}
	
	/**
	 * Carrega as informacoes (usando o Controller) e entao 
	 * monta todas elas na tabela de dados.
	 */
	private void populateDataTable() {
		ResultSet data = LoanController.getInstance().getAllLoans();
		try {
			while (data.next()) {
				Integer bookId = data.getInt("bookId");
				String bookName = data.getString("bookName");
				
				Integer customerId = data.getInt("customerId");
				String customerName = data.getString("customerName");
				
				
				Object[] record = new Object[] { bookName, customerName };
				records.addRow(record);
				
				Book book = new Book();
				book.setId(bookId);
				book.setName(bookName);
				
				Customer customer = new Customer();
				customer.setId(customerId);
				customer.setName(customerName);
				
				books.add(book);
				customers.add(customer);
				
			}
		} catch(SQLException e) {}
	}
	

	private class LoanButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			Book book = books.get(cbLivros.getSelectedIndex());
			Customer customer = customers.get(cbClientes.getSelectedIndex());
			if (books.isEmpty())
				return;
			
			
			LoanController.getInstance().createLoan(book, customer);

		}
	}
}
