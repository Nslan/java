package bank;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Model.transferSystem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Transfer extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	private static transferSystem objTra = new transferSystem();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Transfer frame = new Transfer();
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
	public Transfer() {
		setBounds(1, 24, 690, 478);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Account", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(48, 35, 392, 378);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("FromAccount");
		lblNewLabel.setBounds(38, 46, 89, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Balance");
		lblNewLabel_1.setBounds(38, 153, 89, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("To Account");
		lblNewLabel_2.setBounds(38, 228, 89, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Amount");
		lblNewLabel_3.setBounds(38, 301, 46, 14);
		panel.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(168, 43, 186, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(168, 150, 186, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(168, 225, 186, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(168, 298, 186, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("Find");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String proood = textField.getText();
				
				try {
					
					String balance = objTra.find(proood).get(0).toString();
					
					textField_1.setText( balance );
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(265, 84, 89, 44);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Transfer");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String f_account = textField.getText();
				String balance = textField_1.getText();
				String t_account = textField_2.getText();
				int amount = Integer.parseInt(  textField_3.getText().toString() );
				
				
				if( textField.getText() != ""  ) {
					
					 boolean state ;
					 boolean state1 ;
					 boolean state2 ;
					 boolean state3 ;
					 
						try {
						
							
							state1 = objTra.findReceiver(t_account); // boyle bir account var mý?
							
							if( state1 != false ) { // account varsa para gonder
								
								// para gönderil di mi? gonderilebildiyse gondrenin parasýný guncelle
							state = objTra.updateSender(f_account, amount); 
									
									// para alýndýmý ? alýndýysa alýcýnýn parasýný guncelle
							state2 = objTra.updateReceiver(t_account, amount);
							
							if( state != false || state2 != false ) {
								
								state3 = objTra.saved( f_account, t_account ,amount);
								
								if( state3 != false ) {
									
									JOptionPane.showInputDialog(this , "Amount Transferd......!!!!" );
									
								
							}
							
						
								}
								
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
		btnNewButton_1.setBounds(450, 118, 112, 68);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Cancel");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				dispose();
				
			}
		});
		btnNewButton_2.setBounds(450, 214, 112, 68);
		getContentPane().add(btnNewButton_2);

	}
}
