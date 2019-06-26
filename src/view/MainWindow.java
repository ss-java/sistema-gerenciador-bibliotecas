package view;

import controller.BookController;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

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
	private JButton btnSampleWindow;
	
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
		setBounds(1000, 500, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		setContentPane(contentPane);

		btnBooksLoan = new JButton("Emprestimos");

		btnBooksLoan.setName("btnBooksLoan");
		
		btnBooks = new JButton("Livros");
		btnBooks.setName("btnBooks");
		
		btnCustomers = new JButton("Clientes");
		btnCustomers.setName("btnCustomers");

		btnBooksLoan.addActionListener(new HandleButtonClick());
		btnBooks.addActionListener(new HandleButtonClick());
		btnCustomers.addActionListener(new HandleButtonClick());

		contentPane.add(btnBooksLoan);
		contentPane.add(btnBooks);
		contentPane.add(btnCustomers);
	}

	/**
	 * This class handles each click on each of the main buttons.
	 */
	private class HandleButtonClick implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JButton clickedButton = ((JButton) event.getSource());
			System.out.println("Voce clicou em: " + clickedButton.getName());
			
			if (clickedButton.getName() == "btnBooks") {
				BooksWindow.open();
			}
			
			else if (clickedButton.getName() == "btnCustomers") {
				CustomersWindow.open();
			}
			
			else if (clickedButton.getName() == "btnBooksLoan") {
				LoansWindow.open();
			}
		}
	}
	
}
