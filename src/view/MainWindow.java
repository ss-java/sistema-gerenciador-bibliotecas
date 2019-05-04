package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.BookController;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	private JPanel contentPane;

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
		
		JButton btnBooksLoan = new JButton("Empréstimos");
		btnBooksLoan.setName("btnBooksLoan");
		JButton btnBooks = new JButton("Livros");
		btnBooks.setName("btnBooks");
		JButton btnNewButton_2 = new JButton("Clientes");
		
		btnBooksLoan.addActionListener(new HandleButtonClick());
		btnBooks.addActionListener(new HandleButtonClick());
		btnNewButton_2.addActionListener(new HandleButtonClick());
		
		contentPane.add(btnBooksLoan);
		contentPane.add(btnBooks);
		contentPane.add(btnNewButton_2);
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
