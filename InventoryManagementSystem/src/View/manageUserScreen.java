package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class manageUserScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manageUserScreen frame = new manageUserScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public manageUserScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,  900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 20, 60));
		panel.setForeground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 900, 91);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("INVETORY MANAGEMENT SYSTEM ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(200, 11, 476, 31);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("USER MANAGE");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(200, 43, 476, 31);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setForeground(new Color(220, 20, 60));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 150, 112, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setForeground(new Color(220, 20, 60));
		lblPassword.setBounds(10, 207, 112, 22);
		contentPane.add(lblPassword);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPhone.setForeground(new Color(220, 20, 60));
		lblPhone.setBounds(10, 261, 112, 22);
		contentPane.add(lblPhone);
		
		textField = new JTextField();
		textField.setForeground(new Color(220, 20, 60));
		textField.setFont(new Font("Consolas", Font.BOLD, 14));
		textField.setBounds(132, 140, 205, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setForeground(new Color(220, 20, 60));
		textField_1.setFont(new Font("Consolas", Font.BOLD, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(132, 196, 205, 32);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setForeground(new Color(220, 20, 60));
		textField_2.setFont(new Font("Consolas", Font.BOLD, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(132, 251, 205, 32);
		contentPane.add(textField_2);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(220, 20, 60));
		btnNewButton.setBounds(34, 324, 89, 32);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(220, 20, 60));
		btnNewButton_1.setBounds(149, 324, 89, 32);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBackground(new Color(220, 20, 60));
		btnNewButton_2.setBounds(259, 324, 89, 32);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Home");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mainScreen mScr = new mainScreen();
				
				setVisible(false);
				
				mScr.setVisible(true);
				
				
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBackground(new Color(220, 20, 60));
		btnNewButton_3.setBounds(149, 373, 89, 32);
		contentPane.add(btnNewButton_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(404, 140, 486, 406);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Consolas", Font.BOLD, 14));
		table.setForeground(new Color(220, 20, 60));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"User Name", "User Pass", "User Phone"
			}
		));
		table.setBackground(new Color(211, 211, 211));
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(220, 20, 60));
		panel_1.setBounds(0, 590, 900, 10);
		contentPane.add(panel_1);
		
		setUndecorated(true);
		this.setLocationRelativeTo(null);//ekranýn ortasýnda acýlmasýný saglar.
	}
}
