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
import Helper.*;//bunu kendi ellerimle ekliyorum ve helper içindeki tüm sýnýflara erisilir
import Model.*;

public class LoginSayfasý extends JFrame {

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
					LoginSayfasý frame = new LoginSayfasý();
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
	public LoginSayfasý() {
		setResizable(false);
		setTitle("Hastahane Otomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_penceresi = new JPanel();
		w_penceresi.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_penceresi);
		w_penceresi.setLayout(null);

		JLabel lbl_Logo = new JLabel(new ImageIcon(getClass().getResource("H_icon.png")));// logomuzu koyacagým
		// (getClass() ) : bu sýnýfý getir ve sýnýftaki þu kaynagý göruntule
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
		w_tabbpane.addTab("Hasta Giriþi", null, w_HastaLogin, null);
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

		JButton btnKayýtOl = new JButton("Kay\u0131t Ol");
		btnKayýtOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
						
						 //kayýt ekranýna gitmek istersede ise kayýtguý ye gec !

						HastaKayýtGUI kayýtol = new HastaKayýtGUI();
										 
						 kayýtol.setVisible( true ); // login penceresini açmak için
												 
						 dispose(); //pencereyi kapatmak içn
						
						
			}
		});
		btnKayýtOl.setBounds(37, 136, 113, 43);
		w_HastaLogin.add(btnKayýtOl);

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
									
									// þimdi giriþ yapan hastamýz için hastagui acýlmalýdýr
									// bunun için ilk önce hasta sýnýfý import edilir ve
									// bu sýnýftan bir nesne üretilir ve tablodaki tum býlgýlerý alýnýrak
									// bu hasta nesnesi olusturulur tek tek set yaparak veya
									// tek bir kez olarak new Hasta(); kýsmýndaki boþ constructor deðilde
									// parametrelerin hepsini alan constructor olarak kullanýlabilir örnek :
									// Hasta hasta = new Hasta(rs.getInt("id") , rs.getString("password") ,
									// rs.getString("tcno") , rs.getString("Username") , rs.getString("Usertype"));
									Hasta hasta = new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setPassword(rs.getString("password"));
									hasta.setTcno(rs.getString("tcno"));
									hasta.setName(rs.getString("Username"));
									hasta.setType(rs.getString("Usertype"));
									 
									//hastaGUý ekranýna hastanýn bilgilerini aktarmak için
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
						Helper.showMsg("Boyle bir hasta bulunamadý! Hasta kayýtlý degil! ");
					}
						
				}
					
			
			}
		});
		btnGirisYap.setBounds(300, 136, 113, 43);
		w_HastaLogin.add(btnGirisYap);

		PassFldHasta = new JPasswordField();
		PassFldHasta.setBounds(183, 65, 231, 30);
		w_HastaLogin.add(PassFldHasta);
			/* doktor için	*/
		JPanel w_DoctorLogin = new JPanel();
		w_tabbpane.addTab("Doctor Giriþ", null, w_DoctorLogin, null);
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

					// JOptionPane.showMessageDialog(null, "Lütfen tüm alanlarý doldurunuz !");
					// bunu her hata verecek yerde tek tek tanýmlamak yerine bir Helper sýnýfý
					// olustur
					// ve bu helpersýnýfý içinden nesne üretmeden static sýnýflardan bu mesajý veren
					// basit bir metod cagrýmý yaparak kullan heryerde ;
					// tabi bunun içinde bu sýnýfý package ile import et package.*; seklinde
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
									
									// þimdi giriþ yapan doktor bashekim mi ? ona bakacagýz
									//giriþ yapan bashekim ise bashekim gui acýlmalý
									// bunun için ilk önce bashekim sýnýfý import eilir ve
									// bu sýnýftan bir nesne üretilir ve tablodaki tum býlgýlerý alýnýrak
									// bu bashekim nesnesi olusturulur tek tek set yaparak veya
									// tek bir kez olarak new Bashekim(); kýsmýndaki boþ constructor deðilde
									// parametrelerin hepsini alan constructor olarak kullanýlabilir örnek :
									// BasHekim bHekim = new BasHekim(rs.getInt("id") , rs.getString("password") ,
									// rs.getString("tcno") , rs.getString("Username") , rs.getString("Usertype"));
									BasHekim bHekim = new BasHekim();
									bHekim.setId(rs.getInt("id"));
									bHekim.setPassword(rs.getString("password"));
									bHekim.setTcno(rs.getString("tcno"));
									bHekim.setName(rs.getString("Username"));
									bHekim.setType(rs.getString("Usertype"));
									System.out.println(bHekim.getName());// bashekimin ismini ekrana bas

									BashekimSayfasý bGUI = new BashekimSayfasý(bHekim);
									bGUI.setVisible(true);
									dispose();
									
								}
							// burasýný doctorguý den sonra yazdýk bunu 
										
								if( rs.getString("Usertype").equals("Doktor") ) {
									
									// þimdi giriþ yapan doktor sa  ona bakacagýz
									// giriþ yapan doctor ise doctor gui acýlmalý
									// bunun için ilk önce doctor sýnýfý import eilir ve
									// bu sýnýftan bir nesne üretilir ve tablodaki tum býlgýlerý alýnýrak
									// bu doctor nesnesi olusturulur tek tek set yaparak veya
									// tek bir kez olarak new Doctor(); kýsmýndaki boþ constructor deðilde
									// parametrelerin hepsini alan constructor olarak kullanýlabilir örnek :
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
