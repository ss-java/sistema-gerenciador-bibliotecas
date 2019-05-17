package view;

import controller.BookController;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {

	/**
	 * Window.
	 */
	private JPanel contentPane;
	
	/**
	 * Button that leads to Loans screen.
	 */
	private JButton btnBooksLoan;
	
	/**
	 * Button that leads to Books screen.
	 */
	private JButton btnBooks;
	
	/**
	 * Button that leads to Customers screen.
	 */
	private JButton btnCustomers;
	
	/**
	 * Button that close the application.
	 */
	private JButton btnExit;
	
	/**
	 * Open the window.
	 */
	public static void open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					(new MainWindow()).setVisible(true);
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
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		btnBooksLoan = new JButton("Empr\u00E9stimos");
		btnBooksLoan.setBounds(12, 10, 115, 36);
		btnBooksLoan.setName("btnBooksLoan");
		
		btnBooks = new JButton("Livros");
		btnBooks.setBounds(150, 10, 84, 36);
		btnBooks.setName("btnBooks");
		
		btnCustomers = new JButton("Clientes");
		btnCustomers.setBounds(271, 10, 92, 36);
		btnCustomers.setName("btnCustomers");
		
		btnExit = new JButton("Close Application");
		btnExit.setBounds(401, 10, 175, 36);
		btnExit.setName("btnExit");
				
		btnBooksLoan.addActionListener(new HandleButtonClick());
		btnBooks.addActionListener(new HandleButtonClick());
		btnCustomers.addActionListener(new HandleButtonClick());
		btnExit.addActionListener(new HandleButtonClick());
		contentPane.setLayout(null);

		contentPane.add(btnBooksLoan);
		contentPane.add(btnBooks);
		contentPane.add(btnCustomers);
		contentPane.add(btnExit);
	}

	/**
	 * This class handles each click on each of the main buttons.
	 */
	private class HandleButtonClick implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JButton clickedButton = ((JButton) event.getSource());
			System.out.println("Voc� clicou em: " + clickedButton.getName());
			
			if (clickedButton.getName() == "btnBooks") {
				BooksWindow.open();
			}
			
			else if (clickedButton.getName() == "btnCustomers") {
				CustomersWindow.open();
			}
			
			else if (clickedButton.getName() == "btnBooksLoan") {
				LoansWindow.open();
			}
			
			else if (clickedButton.getName() == "btnExit") {
				System.exit(0);
			}
		}
	}
}
