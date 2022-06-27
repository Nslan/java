package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Contoller.myButton;

public class customerScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customerScreen frame = new customerScreen();
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
	public customerScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 20, 60));
		panel.setForeground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 900, 85);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("INVETORY MANAGEMENT SYSTEM ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(200, 11, 476, 31);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("CUSTOMER PRODUCT");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(200, 43, 476, 31);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel = new JLabel("Customer ID");
		lblNewLabel.setForeground(new Color(220, 20, 60));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 144, 116, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Customer Name");
		lblName.setForeground(new Color(220, 20, 60));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblName.setBounds(10, 197, 133, 24);
		contentPane.add(lblName);
		
		textField = new JTextField();
		textField.setForeground(new Color(220, 20, 60));
		textField.setFont(new Font("Consolas", Font.BOLD, 14));
		textField.setBounds(153, 144, 198, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setForeground(new Color(220, 20, 60));
		textField_1.setFont(new Font("Consolas", Font.BOLD, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(153, 197, 198, 24);
		contentPane.add(textField_1);
		
		myButton btnNewButton = new myButton();
		btnNewButton.setText("Add");
		btnNewButton.setRadius(20);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBackground(new Color(220, 20, 60));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(25, 316, 89, 35);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setBackground(new Color(220, 20, 60));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBounds(152, 316, 89, 35);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setBackground(new Color(220, 20, 60));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBounds(276, 316, 89, 35);
		contentPane.add(btnNewButton_2);
		
		myButton btnNewButton_3 = new myButton();
		btnNewButton_3.setText("Home");
		btnNewButton_3.setRadius(20);
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setFocusPainted(false);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				
				mainScreen mainScr = new mainScreen();
				
				mainScr.setVisible(true);
				
				
			}
		});
		btnNewButton_3.setBackground(new Color(220, 20, 60));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBounds(152, 364, 89, 35);
		contentPane.add(btnNewButton_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(405, 144, 485, 361);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setForeground(new Color(220, 20, 60));
		table.setFont(new Font("Consolas", Font.BOLD, 16));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CUSTID", "CUSTNAME", "CUSTPHONE"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("CUSTOMERS LIST");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(594, 102, 198, 24);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(220, 20, 60));
		panel_1.setBounds(0, 684, 900, 16);
		contentPane.add(panel_1);
		
		JLabel lblCustomerPhone = new JLabel("Customer Phone");
		lblCustomerPhone.setForeground(new Color(220, 20, 60));
		lblCustomerPhone.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCustomerPhone.setBounds(10, 248, 143, 24);
		contentPane.add(lblCustomerPhone);
		
		textField_2 = new JTextField();
		textField_2.setForeground(new Color(220, 20, 60));
		textField_2.setFont(new Font("Consolas", Font.BOLD, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(153, 248, 198, 24);
		contentPane.add(textField_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 0, 255));
		panel_2.setBounds(172, 526, 224, 126);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("X");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(77, 52, 70, 41);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("ORDER NUMBER");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(0, 0, 224, 29);
		panel_2.add(lblNewLabel_4);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(new Color(255, 0, 0));
		panel_2_1.setBounds(525, 526, 224, 126);
		contentPane.add(panel_2_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("X");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel_3_1.setBounds(77, 52, 70, 41);
		panel_2_1.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("ORDER AMOUNT");
		lblNewLabel_4_1.setForeground(Color.WHITE);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4_1.setBounds(0, 0, 224, 29);
		panel_2_1.add(lblNewLabel_4_1);
		

		setUndecorated(true);
		this.setLocationRelativeTo(null);//ekranýn ortasýnda acýlmasýný saglar.
	}
}
