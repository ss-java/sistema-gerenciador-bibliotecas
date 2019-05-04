package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

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
		
		JButton btnNewButton = new JButton("Empréstimos");
		JButton btnNewButton_1 = new JButton("Livros");
		JButton btnNewButton_2 = new JButton("Clientes");
		
		btnNewButton.addActionListener(new HandleButtonClick());
		btnNewButton_1.addActionListener(new HandleButtonClick());
		btnNewButton_2.addActionListener(new HandleButtonClick());
		
		contentPane.add(btnNewButton);
		contentPane.add(btnNewButton_1);
		contentPane.add(btnNewButton_2);
	}
	
	/**
	 * This class handles each click on each of the main buttons.
	 */
	private class HandleButtonClick implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JButton clickedButton = ((JButton) event.getSource());
			System.out.println("Você clicou em: " + clickedButton.getText());
		}
	}

}
