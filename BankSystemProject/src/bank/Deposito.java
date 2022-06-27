package bank;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Model.depositSystem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class Deposito extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;

	private static depositSystem objDepo = new depositSystem();
	private JTextField textField_2;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deposito frame = new Deposito();
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
	public Deposito() {
		setBounds(1, 24, 720, 503);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Account No", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(21, 101, 254, 183);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Enter the Account No");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(23, 51, 200, 20);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(23, 89, 200, 31);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Customer No");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(21, 320, 91, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Firstname");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(21, 370, 91, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("LastName");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(21, 420, 91, 14);
		getContentPane().add(lblNewLabel_3);


		JLabel lblNewLabel_4 = new JLabel("Date");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(21, 46, 91, 14);
		getContentPane().add(lblNewLabel_4);

		
		JLabel lblNewLabel_5 = new JLabel("New label5");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(185, 320, 79, 14);
		getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("New label6");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(185, 370, 79, 14);
		getContentPane().add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("New label7");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_7.setBounds(185, 420, 79, 14);
		getContentPane().add(lblNewLabel_7);


		JLabel lblNewLabel_8 = new JLabel("New label8");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_8.setBounds(109, 46, 155, 14);
		getContentPane().add(lblNewLabel_8);
		// tarihi eklemek için
		DateTimeFormatter dtd = DateTimeFormatter.ofPattern("yyyy/MM/dd"); 
		LocalDateTime now = LocalDateTime.now();
		String date = dtd.format( now );
		lblNewLabel_8.setText(date);
		
		
		JLabel lblNewLabel_9 = new JLabel("Balance");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_9.setBounds(474, 46, 79, 27);
		getContentPane().add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("New label10");
		lblNewLabel_10.setForeground(new Color(0, 0, 255));
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_10.setBounds(462, 103, 114, 27);
		getContentPane().add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("Deposit");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setBounds(474, 250, 79, 26);
		getContentPane().add(lblNewLabel_11);

		textField_1 = new JTextField();
		textField_1.setForeground(new Color(255, 255, 255));
		textField_1.setBackground(new Color(128, 0, 0));
		textField_1.setBounds(421, 296, 203, 42);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		
		JButton btnNewButton_2 = new JButton("Find");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String acc_no = textField.getText();

				try {
					
					
					String id = objDepo.find(acc_no).get(0).toString();
					String firstname = objDepo.find(acc_no).get(1).toString();
					String lastname = objDepo.find(acc_no).get(2).toString();
					String balance = objDepo.find(acc_no).get(3).toString();
					
					
					lblNewLabel_5.setText( id.trim() );
					lblNewLabel_6.setText( firstname.trim() );
					lblNewLabel_7.setText( lastname.trim() );
					lblNewLabel_10.setText( balance.trim() );
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_2.setBounds(153, 131, 70, 31);
		panel.add(btnNewButton_2);

		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				 String accno = textField.getText();
				 String cust_id =lblNewLabel_5.getText();
				 String firstname = lblNewLabel_6.getText();
				 String lastname = lblNewLabel_7.getText();
				 String date = lblNewLabel_8.getText();
				 int balance =  Integer.parseInt(  lblNewLabel_10.getText().toString() );
				 int amount = Integer.parseInt( textField_1.getText().toString() );
				 
				 //////
				 
				if( lblNewLabel_10.getText() != ""  ) {
					
					 boolean state ;
					 boolean state1 ;
					 
						try {
						
							state = objDepo.saved(accno, cust_id, date, balance, amount);
							
							 if( state == true ) {
								 
								 JOptionPane.showMessageDialog( null , "deposit saveed!" );
								 
								 
								 state1 = objDepo.updateSaved( accno , amount );
								 if( state1 == true ) {
									 
									 JOptionPane.showMessageDialog( null, "deposit update!");
									 
									 lblNewLabel_10.setText( objDepo.getUpdateBalanceValue(accno) );
									 
									 textField_1.setText("");
									 
									 
								 }else {
									 
									 JOptionPane.showMessageDialog( null, "deposit not update!");
								 }
									 
								 
							 }else {
								
								 JOptionPane.showMessageDialog( null , "deposit not saved!" );
								 
							 }
						
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	 
						 	
				}else {
					
					JOptionPane.showMessageDialog( null , "deposit is null", "dikkat", JOptionPane.INFORMATION_MESSAGE);
					
				}
				 

			}
		});
		btnNewButton.setBounds(421, 362, 89, 34);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				dispose();

			}
		});
		btnNewButton_1.setBounds(535, 362, 89, 34);
		getContentPane().add(btnNewButton_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(421, 167, 203, 42);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		

		
	}
}
