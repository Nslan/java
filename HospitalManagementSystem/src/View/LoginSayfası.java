package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;//bunu kendi ellerimle ekliyorum ve helper i�indeki t�m s�n�flara erisilir
import Model.*;

public class LoginSayfas� extends JFrame {

	private JPanel w_penceresi;
	private JTextField fld_hastaTc;
	private JTextField fld_doctorTc;
	private JPasswordField PassfldDoctor;
	private JPasswordField PassFldHasta;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginSayfas� frame = new LoginSayfas�();
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
	public LoginSayfas�() {
		setResizable(false);
		setTitle("Hastahane Otomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_penceresi = new JPanel();
		w_penceresi.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_penceresi);
		w_penceresi.setLayout(null);

		JLabel lbl_Logo = new JLabel(new ImageIcon(getClass().getResource("H_icon.png")));// logomuzu koyacag�m
		// (getClass() ) : bu s�n�f� getir ve s�n�ftaki �u kaynag� g�runtule
		// (getResource() ) der
		lbl_Logo.setBounds(200, 25, 100, 55);
		w_penceresi.add(lbl_Logo);

		JLabel lblNewLabel = new JLabel("Hastahane Y\u00F6netim Sistemine Ho\u015Fgeldiniz!");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		lblNewLabel.setBounds(31, 71, 430, 30);
		w_penceresi.add(lblNewLabel);

		JTabbedPane w_tabbpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabbpane.setBounds(10, 113, 463, 240);
		w_penceresi.add(w_tabbpane);

		JPanel w_HastaLogin = new JPanel();
		w_HastaLogin.setBackground(Color.WHITE);
		w_tabbpane.addTab("Hasta Giri�i", null, w_HastaLogin, null);
		w_HastaLogin.setLayout(null);

		JLabel lblTcNumara = new JLabel("T.C. Numaran\u0131z :");
		lblTcNumara.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lblTcNumara.setBounds(37, 24, 140, 30);
		w_HastaLogin.add(lblTcNumara);

		JLabel lblPassword = new JLabel("\u015Eifre :");
		lblPassword.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lblPassword.setBounds(37, 65, 132, 30);
		w_HastaLogin.add(lblPassword);

		fld_hastaTc = new JTextField();
		fld_hastaTc.setBounds(183, 24, 231, 30);
		w_HastaLogin.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);

		JButton btnKay�tOl = new JButton("Kay\u0131t Ol");
		btnKay�tOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
						
						 //kay�t ekran�na gitmek istersede ise kay�tgu� ye gec !

						HastaKay�tGUI kay�tol = new HastaKay�tGUI();
										 
						 kay�tol.setVisible( true ); // login penceresini a�mak i�in
												 
						 dispose(); //pencereyi kapatmak i�n
						
						
			}
		});
		btnKay�tOl.setBounds(37, 136, 113, 43);
		w_HastaLogin.add(btnKay�tOl);

		JButton btnGirisYap = new JButton("Giri\u015F Yap");
		btnGirisYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if( fld_hastaTc.getText().length() == 0 || PassFldHasta.getText().length() == 0 ) {
					Helper.showMsg("fill");
				}else {
							boolean key = true;
					try {	
						
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM User");
						
				
						while (rs.next()) {
							if (fld_hastaTc.getText().equals(rs.getString("tcno"))
									&& PassFldHasta.getText().equals(rs.getString("password"))) {

								if( rs.getString( "Usertype" ).equals("Hasta") ) { //giris yapan hasta ise
									
									// �imdi giri� yapan hastam�z i�in hastagui ac�lmal�d�r
									// bunun i�in ilk �nce hasta s�n�f� import edilir ve
									// bu s�n�ftan bir nesne �retilir ve tablodaki tum b�lg�ler� al�n�rak
									// bu hasta nesnesi olusturulur tek tek set yaparak veya
									// tek bir kez olarak new Hasta(); k�sm�ndaki bo� constructor de�ilde
									// parametrelerin hepsini alan constructor olarak kullan�labilir �rnek :
									// Hasta hasta = new Hasta(rs.getInt("id") , rs.getString("password") ,
									// rs.getString("tcno") , rs.getString("Username") , rs.getString("Usertype"));
									Hasta hasta = new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setPassword(rs.getString("password"));
									hasta.setTcno(rs.getString("tcno"));
									hasta.setName(rs.getString("Username"));
									hasta.setType(rs.getString("Usertype"));
									 
									//hastaGU� ekran�na hastan�n bilgilerini aktarmak i�in
									HastaGUI hGUI = new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose();
									key = false;
								}
							 
								
							}

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if ( key  ) {
						Helper.showMsg("Boyle bir hasta bulunamad�! Hasta kay�tl� degil! ");
					}
						
				}
					
			
			}
		});
		btnGirisYap.setBounds(300, 136, 113, 43);
		w_HastaLogin.add(btnGirisYap);

		PassFldHasta = new JPasswordField();
		PassFldHasta.setBounds(183, 65, 231, 30);
		w_HastaLogin.add(PassFldHasta);
			/* doktor i�in	*/
		JPanel w_DoctorLogin = new JPanel();
		w_tabbpane.addTab("Doctor Giri�", null, w_DoctorLogin, null);
		w_DoctorLogin.setLayout(null);

		JLabel lblTcNumara2 = new JLabel("T.C. Numaran\u0131z :");
		lblTcNumara2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lblTcNumara2.setBounds(28, 23, 140, 30);
		w_DoctorLogin.add(lblTcNumara2);

		JLabel lblPassword2 = new JLabel("\u015Eifre :");
		lblPassword2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lblPassword2.setBounds(28, 64, 132, 30);
		w_DoctorLogin.add(lblPassword2);

		fld_doctorTc = new JTextField();
		fld_doctorTc.setColumns(10);
		fld_doctorTc.setBounds(174, 23, 231, 30);
		w_DoctorLogin.add(fld_doctorTc);

		JButton btnDoctorGirisYap = new JButton("Giri\u015F Yap");
		btnDoctorGirisYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorTc.getText().length() == 0 || PassfldDoctor.getText().length() == 0) {

					// JOptionPane.showMessageDialog(null, "L�tfen t�m alanlar� doldurunuz !");
					// bunu her hata verecek yerde tek tek tan�mlamak yerine bir Helper s�n�f�
					// olustur
					// ve bu helpers�n�f� i�inden nesne �retmeden static s�n�flardan bu mesaj� veren
					// basit bir metod cagr�m� yaparak kullan heryerde ;
					// tabi bunun i�inde bu s�n�f� package ile import et package.*; seklinde
					Helper.showMsg("fill");
				} else {
					Connection con = conn.connDb();
					try {
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM User");
						while (rs.next()) {
							if (fld_doctorTc.getText().equals(rs.getString("tcno"))
									&& PassfldDoctor.getText().equals(rs.getString("password"))) {

								if( rs.getString( "Usertype" ).equals("BasHekim") ) { 
									
									// �imdi giri� yapan doktor bashekim mi ? ona bakacag�z
									//giri� yapan bashekim ise bashekim gui ac�lmal�
									// bunun i�in ilk �nce bashekim s�n�f� import eilir ve
									// bu s�n�ftan bir nesne �retilir ve tablodaki tum b�lg�ler� al�n�rak
									// bu bashekim nesnesi olusturulur tek tek set yaparak veya
									// tek bir kez olarak new Bashekim(); k�sm�ndaki bo� constructor de�ilde
									// parametrelerin hepsini alan constructor olarak kullan�labilir �rnek :
									// BasHekim bHekim = new BasHekim(rs.getInt("id") , rs.getString("password") ,
									// rs.getString("tcno") , rs.getString("Username") , rs.getString("Usertype"));
									BasHekim bHekim = new BasHekim();
									bHekim.setId(rs.getInt("id"));
									bHekim.setPassword(rs.getString("password"));
									bHekim.setTcno(rs.getString("tcno"));
									bHekim.setName(rs.getString("Username"));
									bHekim.setType(rs.getString("Usertype"));
									System.out.println(bHekim.getName());// bashekimin ismini ekrana bas

									BashekimSayfas� bGUI = new BashekimSayfas�(bHekim);
									bGUI.setVisible(true);
									dispose();
									
								}
							// buras�n� doctorgu� den sonra yazd�k bunu 
										
								if( rs.getString("Usertype").equals("Doktor") ) {
									
									// �imdi giri� yapan doktor sa  ona bakacag�z
									// giri� yapan doctor ise doctor gui ac�lmal�
									// bunun i�in ilk �nce doctor s�n�f� import eilir ve
									// bu s�n�ftan bir nesne �retilir ve tablodaki tum b�lg�ler� al�n�rak
									// bu doctor nesnesi olusturulur tek tek set yaparak veya
									// tek bir kez olarak new Doctor(); k�sm�ndaki bo� constructor de�ilde
									// parametrelerin hepsini alan constructor olarak kullan�labilir �rnek :
									// Doctor dct = new Doctor(rs.getInt("id") , rs.getString("password") ,
									// rs.getString("tcno") , rs.getString("Username") , rs.getString("Usertype"));
									Doctor dct = new Doctor();
									dct.setId(rs.getInt("id"));
									dct.setPassword(rs.getString("password"));
									dct.setTcno(rs.getString("tcno"));
									dct.setName(rs.getString("Username"));
									dct.setType(rs.getString("Usertype"));
									System.out.println(dct.getName());// doctorun ismini ekrana bas

									DoctorGUI dctGUI = new DoctorGUI( dct );
									dctGUI.setVisible(true);
									dispose();
									
								}
								
								
							}

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btnDoctorGirisYap.setBounds(28, 135, 376, 43);
		w_DoctorLogin.add(btnDoctorGirisYap);

		PassfldDoctor = new JPasswordField();
		PassfldDoctor.setBounds(174, 64, 231, 30);
		w_DoctorLogin.add(PassfldDoctor);
	}
}
