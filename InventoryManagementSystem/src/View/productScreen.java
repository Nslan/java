package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class productScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					productScreen frame = new productScreen();
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
	public productScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
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
		
		JLabel lblNewLabel_2_1 = new JLabel("MANAGE PRODUCT");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(200, 43, 476, 31);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel = new JLabel("Product ID");
		lblNewLabel.setForeground(new Color(220, 20, 60));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 144, 116, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(new Color(220, 20, 60));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblName.setBounds(10, 197, 116, 24);
		contentPane.add(lblName);
		
		JLabel lblDescription = new JLabel("Quantity");
		lblDescription.setForeground(new Color(220, 20, 60));
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDescription.setBounds(10, 249, 116, 24);
		contentPane.add(lblDescription);
		
		JLabel lblDescription_1 = new JLabel("Description");
		lblDescription_1.setForeground(new Color(220, 20, 60));
		lblDescription_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDescription_1.setBounds(10, 306, 116, 24);
		contentPane.add(lblDescription_1);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setForeground(new Color(220, 20, 60));
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCategory.setBounds(10, 362, 116, 24);
		contentPane.add(lblCategory);
		
		textField = new JTextField();
		textField.setFont(new Font("Consolas", Font.BOLD, 14));
		textField.setForeground(new Color(220, 20, 60));
		textField.setBounds(136, 144, 198, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Consolas", Font.BOLD, 14));
		textField_1.setForeground(new Color(220, 20, 60));
		textField_1.setColumns(10);
		textField_1.setBounds(136, 197, 198, 24);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Consolas", Font.BOLD, 14));
		textField_2.setForeground(new Color(220, 20, 60));
		textField_2.setColumns(10);
		textField_2.setBounds(136, 249, 198, 24);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Consolas", Font.BOLD, 14));
		textField_3.setForeground(new Color(220, 20, 60));
		textField_3.setColumns(10);
		textField_3.setBounds(136, 306, 198, 24);
		contentPane.add(textField_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Consolas", Font.BOLD, 14));
		comboBox.setForeground(new Color(220, 20, 60));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Phone", "Computer", "Lamp", "TV", "Mouse"}));
		comboBox.setBounds(136, 362, 198, 24);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBackground(new Color(220, 20, 60));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(21, 422, 89, 35);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.setBackground(new Color(220, 20, 60));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBounds(148, 422, 89, 35);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setBackground(new Color(220, 20, 60));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBounds(272, 422, 89, 35);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Home");
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
		btnNewButton_3.setBounds(148, 470, 89, 35);
		contentPane.add(btnNewButton_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(405, 144, 485, 361);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Consolas", Font.BOLD, 14));
		table.setForeground(new Color(220, 20, 60));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"PRODID", "PRODNAME", "PRODQTY", "PRODDESC", "PRODCAT"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("PRODUCT LIST");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(594, 102, 198, 24);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(220, 20, 60));
		panel_1.setBounds(0, 584, 900, 16);
		contentPane.add(panel_1);
		

		setUndecorated(true);
		this.setLocationRelativeTo(null);//ekranýn ortasýnda acýlmasýný saglar.
	}
}
