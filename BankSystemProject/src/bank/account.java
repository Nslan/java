package bank;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Model.BranchItem;
import Model.accountSystem;

import javax.swing.border.EtchedBorder;
import javax.swing.DefaultComboBoxModel;

public class account extends JInternalFrame {
	
	private static accountSystem objAcc  = new accountSystem();

	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_5;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					account frame = new account();
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
	public account() {
		setBounds(1, 24 , 720, 593);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Account", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setToolTipText("");
		panel.setBounds(57, 65, 604, 420);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Account No");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(45, 50, 75, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Customer ID");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(45, 106, 114, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Customer Name");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(45, 174, 114, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_6 = new JLabel("Account type");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(45, 246, 97, 14);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Balance");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_7.setBounds(45, 321, 66, 14);
		panel.add(lblNewLabel_7);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(191, 104, 211, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(191, 174, 211, 20);
		panel.add(textField_2);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(191, 319, 211, 20);
		panel.add(textField_5);
		
		JLabel lblNewLabel_8 = new JLabel("JLabel");
		try {
			lblNewLabel_8.setText( objAcc.autoID() );
		} catch (Exception e) {
			// TODO: handle exception
		}
		lblNewLabel_8.setForeground(new Color(0, 0, 205));
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_8.setBounds(191, 51, 211, 14);
		panel.add(lblNewLabel_8);
		
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Saving", "Fix", "Current"}));
		comboBox.setBounds(191, 244, 211, 22); 
		panel.add(comboBox);
		
		
		JButton btnNewButton = new JButton("Add"); 
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String acc_no = lblNewLabel_8.getText();
				String cust_id = textField_1.getText();
				String cust_name = textField_2.getText();
				String acc_type = comboBox.getSelectedItem().toString();
				String bal = textField_5.getText();
				int balance = Integer.parseInt( bal );
				
				try {
					//saved( String account_id , String customer_id , String account_type , int balance )
					boolean record = objAcc.saved( acc_no ,  cust_id , acc_type,  balance );
					
					if( record == true ) {
						
						JOptionPane.showMessageDialog(null, "Account Created! " , "Mesaj" , JOptionPane.INFORMATION_MESSAGE );
						
						textField_1.setText("");
						textField_2.setText(""); 
						textField_5.setText("");
						lblNewLabel_8.setText( objAcc.autoID() );
						comboBox.setSelectedIndex(-1);
						textField_1.requestFocus();//textfieldlarý bosalttýktan sonra dikkati first name ustune cektik
						
						
					}else {
						JOptionPane.showMessageDialog(null, "Account can not Created!  " , "Mesaj" , JOptionPane.INFORMATION_MESSAGE );
					}
					
					
					
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(438, 193, 120, 61);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false); 
				dispose();
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.setBounds(438, 278, 120, 61);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Find");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cust_id = textField_1.getText();
				
				textField_2.setText( objAcc.find(cust_id) );
				
			}
		});
		btnNewButton_2.setBounds(438, 105, 120, 61);
		panel.add(btnNewButton_2);
		
		
		JLabel lblNewLabel = new JLabel("Account");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(47, 24, 131, 29);
		getContentPane().add(lblNewLabel);


	}
}
