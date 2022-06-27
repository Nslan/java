package bank;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.border.TitledBorder;
 
import Model.BranchItem;
import Model.CustomerSystem;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Customer extends JInternalFrame {
	
	private static CustomerSystem objCust = new CustomerSystem();
	
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer frame = new Customer(  );
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
	public Customer(  ) {
		setBounds(1, 24 , 720, 593);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Customer", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setToolTipText("");
		panel.setBounds(57, 65, 604, 464);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Customer No");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(45, 50, 75, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("First Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(45, 106, 66, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Last Name");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(45, 164, 66, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Street");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(45, 215, 66, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("City");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(45, 268, 66, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Branch");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(45, 321, 66, 14);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Phone");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_7.setBounds(45, 376, 66, 14);
		panel.add(lblNewLabel_7);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(191, 104, 211, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(191, 162, 211, 20);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(191, 213, 211, 20);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(191, 266, 211, 20);
		panel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(191, 370, 211, 20);
		panel.add(textField_5);
		
		JLabel lblNewLabel_8 = new JLabel("JLabel");
		
		try {
			
			lblNewLabel_8.setText( objCust.autoID()  ); // artýk Customer panelindeki jlabel alanýnda musteri no gosterilecek
			
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		
		lblNewLabel_8.setForeground(new Color(0, 0, 205));
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_8.setBounds(191, 51, 211, 14);
		panel.add(lblNewLabel_8);
		
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(191, 313, 211, 22);
		//comboBox.removeAllItems();
		
		for ( int i=0; i< objCust.branchItem().size() ; i++  ) {
			
			 
				comboBox.addItem(  new BranchItem( i , objCust.branchItem().get(i).toString()  ) ); // branch itemýný elde ettik
				
			
		}
		

		// bu metodu kurdugumuz key - value gercekten calýsýyomu diye kontrol etmek için
		// yazdýk bir onemý yok!
		comboBox.addActionListener(e -> { // bu iþaret pointer dýr bu yapý ise lamda yapýsýdýr

			JComboBox c = (JComboBox) e.getSource();
			BranchItem item = (BranchItem) c.getSelectedItem();
			System.out.println(item.getKey_branch_id() + " : " + item.getValue_branch_name());

		});
		
		panel.add(comboBox);
		
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String customer_id = lblNewLabel_8.getText();
				String firstname = textField_1.getText();
				String lastname  = textField_2.getText();
				String street    = textField_3.getText();
				String city      = textField_4.getText();
		        String branch    = comboBox.getSelectedItem().toString();
				String phone     = textField_5.getText();
				int phone_no = Integer.parseInt( phone );
				
				try {
					
					 boolean record = objCust.saved(customer_id, firstname, lastname, street, city, branch , phone_no ); 
					
					if( record == true ) {
						
						JOptionPane.showMessageDialog(null, "Record Added! " , "Mesaj" , JOptionPane.INFORMATION_MESSAGE );
						
						
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						textField_4.setText("");
						textField_5.setText("");
						lblNewLabel_8.setText( objCust.autoID() );
						comboBox.setSelectedIndex(-1);
						textField_1.requestFocus();//textfieldlarý bosalttýktan sonra dikkati first name ustune cektik
						
						
					}else {
						JOptionPane.showMessageDialog(null, "Record can not add  " , "Mesaj" , JOptionPane.INFORMATION_MESSAGE );
					}
					
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(461, 154, 98, 75);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false); 
				dispose();
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.setBounds(461, 265, 98, 70);
		panel.add(btnNewButton_1);
		
		
		JLabel lblNewLabel = new JLabel("Customer");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(47, 24, 131, 29);
		getContentPane().add(lblNewLabel);

	}
}
