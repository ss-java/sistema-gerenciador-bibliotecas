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
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;
import javax.swing.JEditorPane;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JLayeredPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JList;
import java.awt.Panel;
import java.awt.Choice;
import java.awt.Font;
import java.awt.List;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Label;
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.TextArea;
import javax.swing.JComboBox;
import javax.swing.AbstractListModel;

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
		setBounds(100, 100, 727, 528);
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
		
		btnExit = new JButton("Close application");
		btnExit.setName("btnExit");

		btnBooksLoan.addActionListener(new HandleButtonClick());
		btnBooks.addActionListener(new HandleButtonClick());
		btnCustomers.addActionListener(new HandleButtonClick());
		btnExit.addActionListener(new HandleButtonClick());

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
			System.out.println("Você clicou em: " + clickedButton.getName());
			
			if (clickedButton.getName() == "btnBooks") {
				BooksWindow.open();
			}
			
			else if (clickedButton.getName() == "btnCustomers") {
				CustomersWindow.open();
			}
			
			else if (clickedButton.getName() == "btnBooksLoan") {
				LoansWindow.open();
			}
			
			else if(clickedButton.getName() == "btnExit") {
				System.exit(0);
			}
		}
	}

}
