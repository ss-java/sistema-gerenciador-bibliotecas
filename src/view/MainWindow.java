package view;

import controller.BookController;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
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
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		setContentPane(contentPane);

		btnBooksLoan = new JButton("Empréstimos");
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
			System.out.println("Você clicou em: " + clickedButton.getName());

			if (clickedButton.getName() == "btnBooks") {
				System.out.println("Creating book");
				BookController.getInstance().createBook("Livro #1");
			}
		}
	}

}
