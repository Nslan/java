package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class orderScreen extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					orderScreen frame = new orderScreen();
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
	public orderScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 20, 60));
		panel.setForeground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1000, 85);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("INVETORY MANAGEMENT SYSTEM ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(200, 11, 476, 31);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("MANAGE ORDERS");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(200, 43, 476, 31);
		panel.add(lblNewLabel_2_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 158, 361, 193);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Consolas", Font.BOLD, 14));
		table.setForeground(new Color(220, 20, 60));
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "CUSTID", "CUSTNAME", "CUSTPHONE" }));
		scrollPane.setViewportView(table);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(522, 158, 468, 193);
		contentPane.add(scrollPane_1);

		table_1 = new JTable();
		table_1.setForeground(new Color(220, 20, 60));
		table_1.setFont(new Font("Consolas", Font.BOLD, 14));
		table_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "PRODID", "PRODNAME", "PRODQTY", "PRODDESC", "PRODCAT" }));
		scrollPane_1.setViewportView(table_1);

		JLabel lblNewLabel = new JLabel("CUSTOMER LIST");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(220, 20, 60));
		lblNewLabel.setBounds(112, 130, 150, 23);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("PRODUCT LIST");
		lblNewLabel_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(654, 130, 204, 23);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setForeground(new Color(220, 20, 60));
		textField.setFont(new Font("Consolas", Font.BOLD, 16));
		textField.setBounds(102, 369, 204, 30);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Order Id");
		lblNewLabel_3.setForeground(new Color(220, 20, 60));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(20, 366, 72, 33);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("Price");
		lblNewLabel_3_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(363, 366, 44, 33);
		contentPane.add(lblNewLabel_3_1);

		textField_1 = new JTextField();
		textField_1.setForeground(new Color(220, 20, 60));
		textField_1.setFont(new Font("Consolas", Font.BOLD, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(406, 369, 173, 30);
		contentPane.add(textField_1);

		JLabel lblNewLabel_3_2 = new JLabel("Quantity");
		lblNewLabel_3_2.setForeground(new Color(220, 20, 60));
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_2.setBounds(589, 366, 67, 33);
		contentPane.add(lblNewLabel_3_2);

		textField_2 = new JTextField();
		textField_2.setForeground(new Color(220, 20, 60));
		textField_2.setFont(new Font("Consolas", Font.BOLD, 16));
		textField_2.setColumns(10);
		textField_2.setBounds(657, 369, 173, 30);
		contentPane.add(textField_2);

		JButton btnNewButton = new JButton("AddToOrder");
		btnNewButton.setBackground(new Color(220, 20, 60));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(840, 369, 126, 30);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_4 = new JLabel("CustomerName");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setForeground(new Color(220, 20, 60));
		lblNewLabel_4.setBounds(20, 433, 121, 30);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("CustName");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setForeground(new Color(220, 20, 60));
		lblNewLabel_5.setBounds(162, 433, 96, 30);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_4_1 = new JLabel("Date");
		lblNewLabel_4_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1.setBounds(20, 493, 92, 30);
		contentPane.add(lblNewLabel_4_1);

		JLabel lblNewLabel_5_1 = new JLabel("2020/11/12");
		lblNewLabel_5_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5_1.setBounds(162, 493, 96, 30);
		contentPane.add(lblNewLabel_5_1);

		JButton btnNewButton_1 = new JButton("Add Order");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBackground(new Color(220, 20, 60));
		btnNewButton_1.setBounds(30, 534, 119, 30);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("View Orders");
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBackground(new Color(220, 20, 60));
		btnNewButton_2.setBounds(185, 534, 119, 30);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_1_1 = new JButton("Home");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);

				mainScreen mainScr = new mainScreen();

				mainScr.setVisible(true);

			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1_1.setBackground(new Color(220, 20, 60));
		btnNewButton_1_1.setBounds(112, 579, 109, 30);
		contentPane.add(btnNewButton_1_1);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(522, 429, 468, 180);
		contentPane.add(scrollPane_2);

		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Num", "Product", "Quantity", "UPrice", "Total" }));
		table_2.setForeground(new Color(0, 0, 0));
		table_2.setBackground(new Color(112, 128, 144));
		scrollPane_2.setViewportView(table_2);

		JButton btnNewButton_3 = new JButton("Print");
		btnNewButton_3.setBackground(new Color(220, 20, 60));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3.setBounds(789, 632, 89, 30);
		contentPane.add(btnNewButton_3);

		JLabel lblNewLabel_6 = new JLabel("Rs Amount");
		lblNewLabel_6.setForeground(new Color(220, 20, 60));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(675, 631, 96, 32);
		contentPane.add(lblNewLabel_6);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(220, 20, 60));
		panel_1.setBounds(0, 690, 1000, 10);
		contentPane.add(panel_1);

		setUndecorated(true);
		this.setLocationRelativeTo(null);// ekranýn ortasýnda acýlmasýný saglar.
	}
}
