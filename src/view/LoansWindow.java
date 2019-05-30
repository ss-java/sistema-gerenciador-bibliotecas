package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.LoanController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class LoansWindow extends JFrame {

	/**
	 * Window.
	 */
	private JPanel windowPanel;

	/**
	 * Open the window.
	 */
	public static void open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					(new LoansWindow()).setVisible(true);
					new LoanController();
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1174, 842);
		windowPanel = new JPanel();
		windowPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		windowPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(windowPanel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(21, 616, 127, 44);
		windowPanel.add(panel_3);
		panel_3.setLayout(null);

		JPanel panel = new JPanel();
		panel_3.add(panel);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 389, 520);
		panel.setLayout(null);
				
		JLabel lblLivros = new JLabel("Livro");
		lblLivros.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLivros.setBounds(10, 11, 63, 18);
		panel.add(lblLivros);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCliente.setBounds(10, 78, 63, 14);
		panel.add(lblCliente);

		JComboBox cbLivro = new JComboBox();
		cbLivro.setBackground(Color.WHITE);
		cbLivro.setBounds(10, 33, 346, 23);
		panel.add(cbLivro);

		JComboBox cbCliente = new JComboBox();
		cbCliente.setBounds(10, 94, 346, 23);
		panel.add(cbCliente);

		JButton button = new JButton("Salvar");
		button.setBounds(37, 624, 107, 22);
		panel_3.add(button);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(23, 610, 134, 46);
		panel_3.add(panel_4);

		JPanel panel_1 = new JPanel();
		panel_3.add(panel_1);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(171, 558, 228, 44);
		panel_1.setLayout(null);

		JButton btnDevolver = new JButton("Devolver");
		btnDevolver.setBounds(10, 11, 89, 25);
		panel_1.add(btnDevolver);
		
		JButton btnEmprestar = new JButton("Emprestar");
		btnEmprestar.setBounds(119, 12, 99, 23);
		panel_1.add(btnEmprestar);

		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(171, 612, 228, 44);
		panel_2.setLayout(null);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(118, 11, 100, 23);
		panel_2.add(btnExcluir);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(10, 11, 89, 23);
		panel_2.add(btnEditar);
		
		
		
		
		
		
		
	}
}
