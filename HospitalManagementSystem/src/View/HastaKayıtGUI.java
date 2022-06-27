package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import Helper.*;
import Model.Hasta;

public class HastaKayýtGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_name;
	private JTextField fld_tc;
	private JTextField fld_password;
	
	private static Hasta hasta = new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaKayýtGUI frame = new HastaKayýtGUI();
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
	 public HastaKayýtGUI() {
		 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 235, 300);
		setResizable(false);
		setTitle("Hasta Kayýt Ekraný");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 11, 198, 20);
		contentPane.add(lblNewLabel_1);
		
		fld_name = new JTextField();
		fld_name.setColumns(10);
		fld_name.setBounds(10, 30, 198, 30);
		contentPane.add(fld_name);
		
		JLabel lblNewLabel_1_1 = new JLabel("T.C. No");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 59, 198, 20);
		contentPane.add(lblNewLabel_1_1);
		
		fld_tc = new JTextField();
		fld_tc.setColumns(10);
		fld_tc.setBounds(10, 81, 198, 30);
		contentPane.add(fld_tc);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\u015Eifre");
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(10, 111, 198, 20);
		contentPane.add(lblNewLabel_1_1_1);
		
		fld_password = new JTextField();
		fld_password.setColumns(10);
		fld_password.setBounds(10, 133, 198, 30);
		contentPane.add(fld_password);
		
		JButton btn_kayýtOl = new JButton("Kay\u0131t Ol");
		btn_kayýtOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 if( fld_name.getText().length() == 0 || fld_password.getText().length() == 0 || fld_tc.getText().length() == 0 ) {
					 
					 Helper.showMsg("fill");
					 
				 }else {
					 
					 try {
					
						 boolean control = hasta.kayýt( fld_tc.getText() , fld_password.getText() , fld_name.getText() );
					
						 if( control ) {
							 Helper.showMsg("success");
							 
							 //eðer basarýlý ise loginSayfasýna gec !
							 LoginSayfasý giriþyap = new LoginSayfasý();
							 
							 giriþyap.setVisible( true ); // login penceresini açmak için
							 
							 dispose(); //pencereyi kapatmak içn
							 
							 
						 }
						 
					 } catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					 
					 
				 }
			}
		});
		btn_kayýtOl.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btn_kayýtOl.setBounds(10, 170, 198, 32);
		contentPane.add(btn_kayýtOl);
		
		JButton btn_back = new JButton("Geri D\u00F6n");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 //geri donmek istersede ise loginSayfasýna gec !
				
				 LoginSayfasý giriþyap = new LoginSayfasý();
								 
				 giriþyap.setVisible( true ); // login penceresini açmak için
										 
				 dispose(); //pencereyi kapatmak içn
				
			}
		});
		btn_back.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btn_back.setBounds(10, 206, 198, 32);
		contentPane.add(btn_back);
	}
}
