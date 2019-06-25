package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.BookController;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class BooksWindow extends JFrame {

	/**
	 * Janela
	 */
	private JPanel contentPane;
	
	/**
	 * Campo de texto para o nome
	 */
	private JTextField txtBooksName;
	
	/**
	 * Tabela de informacoes
	 */
	private JTable tblData;
	
	/**
	 * Os dados da tabela. A classe "DefaultTableModel" 
	 * eh uma especie de "espelho". Sempre que ela for 
	 * alterada, automaticamente atualizara a tabela de 
	 * dados.
	 */
	private DefaultTableModel records = new DefaultTableModel();
	
	/**
	 * Objetos para o State Pattern
	 */
	WindowState currentState;
	BooksWindow instance;
	/**
	 * Metodo chamado por outras classes sempre que 
	 * elas desejam abrir essa janela.
	 */
	public static void open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					(new BooksWindow()).setVisible(true);
				} catch (Exception e) {
					System.err.println("Couldn't open the " + getClass().getName());
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Construtor. Chama todos os metodos 
	 * necessarios para criar a estrutura 
	 * inicial da janela (botoes, campos e etc).
	 */
	public BooksWindow() {
		buildWindowContainer();
		buildFormArea();
		buildButtonsArea();
		buildDataTable();
		populateDataTable();
		
		currentState = new BooksCreateWindowState();
		instance = this;
	}
	
	/**
	 * Cria a janela em si.
	 */
	private void buildWindowContainer() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        BorderFacade border = new BorderFacade();
		
		// Cria o label em cima do campo de texto
		JLabel lblSample = new JLabel("Nome do Livro: ");
		lblSample.setBounds(12, 12, 107, 15);
                border.add(lblSample);
		
		// Cria o campo de texto
		txtBooksName = new JTextField();
		txtBooksName.setBounds(12, 39, 306, 19);
		txtBooksName.setColumns(10);
		
		// Adiciona os elementos na tela. So chamar 
		// "new" nao eh o suficiente. Precisamos incluir 
		// os elementos que criamos na janela chamando 
		// "#add()".
		contentPane.add(border.getPanel());
		border.add(txtBooksName);
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
		JButton btnSave = new JButton("Salvar");
		JButton btnAdd = new JButton("Adicionar");
		JButton btnEdit = new JButton("Editar");
		JButton btnDelete = new JButton("Excluir");
		
		// Add os botoes na janela.
		buttonsPanel.add(btnSave);
		buttonsPanel.add(btnAdd);
		buttonsPanel.add(btnEdit);
		buttonsPanel.add(btnDelete);
		contentPane.add(buttonsPanel);
		
		// Aqui, informamos qual classe vai cuidar do 
		// comportamento de cada botao.
		btnSave.addActionListener(new SaveButtonListener());
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
		records.addColumn("#");
		records.addColumn("Nome");
		
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
		ResultSet data = BookController.getInstance().getAllBooks();
		try {
			while (data.next()) {
				Object[] record = new Object[] { data.getInt("id"), data.getString("name") };
				records.addRow(record);
			}
		} catch(SQLException e) {}
	}
	
	
	public void createBook() {
		// Pega o texto inserido no campo de texto "txtSampleName"
		String name = txtBooksName.getText();
		
		// Se nenhum texto for digitado, simplesmente nao fazemos nada.
		// No futuro, podemos mostrar uma mensagem de erro dizendo que 
		// o campo eh obrigatorio.
		if (name.isEmpty())
			return;
		
		BookController.getInstance().createBook(name);
		
		// Limpa todos os campos
		txtBooksName.setText(null);
		
		new BooksWindow().populateDataTable();
	}
	
	/*
	 * State Pattern
	 */
	private class SaveButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			currentState.onSave(instance);
			
		}
	}
}
