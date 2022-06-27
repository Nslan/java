package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Doctor;
import Model.Hasta;
import Model.Randevu;
import Model.WorkHour;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

import Helper.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class DoctorGUI extends JFrame {

	private JPanel doctor_pane;
	private static Doctor doctor = new Doctor();// modelimizdeki doktor bilgilerimiz
	private DefaultTableModel whour_model;
	private Object[] whourData = null;
	private JTable table;
	
	// randevular için
	private DefaultTableModel randevuModel;
	private Object[] randevuData = null;
	private static Randevu randevularým = new Randevu();
	private JTable table_randevularým;
	private JPopupMenu menum;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGUI frame = new DoctorGUI(doctor);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public DoctorGUI(Doctor doctor) throws SQLException {

		whour_model = new DefaultTableModel();
		Object[] colWhour = new Object[2];// doktor jtabla da sadece calýsma saatini ve statu bilgisini gormesi yeterli

		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whour_model.setColumnIdentifiers(colWhour);
		whourData = new Object[2];

		// giriþ yapan doktorun( doctor.getId()) çalýsma saatlerinin listesini
		// (dcotor.getWorkHourList( ) ) getir :
		// doctor.getWorkHourList(doctor.getId())
		for (int i = 0; i < doctor.getWorkHourList(doctor.getId()).size(); i++) {

			whourData[0] = doctor.getWorkHourList(doctor.getId()).get(i).getId();
			whourData[1] = doctor.getWorkHourList(doctor.getId()).get(i).getWorkhour_date();
			whour_model.addRow(whourData);

		}

		
		// doctor randevularý için
		randevuModel = new DefaultTableModel();
		Object[] randevuCol = new Object[3];
		
		randevuCol[0] = "ID";
		randevuCol[1] = "HASTA ISMI";
		randevuCol[2] = "RANDEVU TARIHI";
		randevuModel.setColumnIdentifiers(randevuCol);		
		randevuData = new  Object[3];
		
		for( int i=0; i < randevularým.getRandevuDoctorList( doctor.getId() ).size(); i++ ) {
		
			randevuData[0] = randevularým.getRandevuDoctorList( doctor.getId() ).get(i).getId();
			randevuData[1] = randevularým.getRandevuDoctorList( doctor.getId() ).get(i).getHastaName();
			randevuData[2] = randevularým.getRandevuDoctorList( doctor.getId() ).get(i).getRandevuDate();
			randevuModel.addRow(randevuData);
			
		}		
			
		setTitle("Hastahane Y\u00F6netim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		doctor_pane = new JPanel();
		doctor_pane.setBackground(Color.WHITE);
		doctor_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(doctor_pane);
		doctor_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hosgeldiniz Sayýn " + doctor.getName());// artýk doktor ismi ekranda
																					// gosterilir
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 21, 225, 25);
		doctor_pane.add(lblNewLabel);

		JButton btnCýkýsYap = new JButton("çýkýþ yap");
		btnCýkýsYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// cýkýs yap deyýnce logýn sayfasýna geri donelim
				LoginSayfasý baslangýc = new LoginSayfasý();
				baslangýc.setVisible(true);
				dispose();

			}
		});
		btnCýkýsYap.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		btnCýkýsYap.setBounds(630, 24, 89, 23);
		doctor_pane.add(btnCýkýsYap);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(10, 66, 714, 384);
		doctor_pane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Çalýþma Saatleri", null, panel, null);
		panel.setLayout(null);

		JDateChooser select_tarih = new JDateChooser();
		select_tarih.setBounds(10, 11, 136, 26);
		panel.add(select_tarih);

		JComboBox select_time = new JComboBox();
		select_time.setModel(new DefaultComboBoxModel(new String[] { "09:00", "09:30", "10:00", "10:30", "11:00",
				"11:30", "12:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00" }));
		select_time.setBounds(168, 11, 136, 26);
		panel.add(select_time);

		JButton btn_ekleWHour = new JButton("Ekle");
		btn_ekleWHour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// kullanacagýmýz tarih formatýmýzý belirleyelim
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				// tarihimizi yukarda belirttiðimiz format þeklinde alalým
				String date = "";

				try {

					date = sdf.format(select_tarih.getDate());

				} catch (Exception e2) {
					e2.printStackTrace();
				}

				// þimdi de tarih secildiginden emin olalým
				if (date.length() == 0) {

					Helper.showMsg("Lütfen gecerli bir tarih giriniz !");

				} else {

					// comboboxttan secilen saatimizi bulalým , bu date ve time lar DB te boyle
					// tutuluyor
					String time = " " + select_time.getSelectedItem().toString() + ":00";

					// þimdi bu tarih ve saatimizi birleþtirelim
					String selectDate = date + time;

					try {

						boolean control = doctor.addDoctorWorkHour(doctor.getId(), doctor.getName(), selectDate);

						if (control) {
							Helper.showMsg("success");
							updateWhourModel(doctor);
						} else {
							Helper.showMsg("error");
						}
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}

				}

			}
		});
		btn_ekleWHour.setBounds(314, 11, 136, 26);
		panel.add(btn_ekleWHour);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 689, 297);
		panel.add(scrollPane);

		table = new JTable(whour_model);
		scrollPane.setViewportView(table);

		JButton btn_deleteWHour_1 = new JButton("Sil");
		btn_deleteWHour_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selRow = table.getSelectedRow();

				if (selRow >= 0) {

					String selectRow = table.getModel().getValueAt(selRow, 0).toString();

					int selID = Integer.parseInt(selectRow);

					boolean control;

					try {

						control = doctor.deleteWhour(selID);

						if (control) {

							Helper.showMsg("success");
							updateWhourModel(doctor);

						} else {
							Helper.showMsg("error");
						}

					} catch (Exception e2) {
						// TODO: handle exception
					}
				}

			}
		});
		btn_deleteWHour_1.setBounds(563, 11, 136, 26);
		panel.add(btn_deleteWHour_1);

		
		menum = new JPopupMenu();
		JMenuItem iptalItem = new JMenuItem("iptal et");
		menum.add( iptalItem );
		
		JPanel randevular_paneli = new JPanel();
		tabbedPane.addTab("Randevularým", null, randevular_paneli, null);
		randevular_paneli.setLayout(null);
		
		JScrollPane scrollPane_randevular = new JScrollPane();
		scrollPane_randevular.setBounds(10, 11, 689, 334);
		randevular_paneli.add(scrollPane_randevular);
		
		table_randevularým = new JTable( randevuModel );
		table_randevularým.setComponentPopupMenu(menum); // popup menumuzu jtable ý mýza ekleyelim 
		scrollPane_randevular.setViewportView(table_randevularým);
		
		//item a týklandýgýný algýlayalým
		iptalItem.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//secilen satýr bizim secilen id mizdir
				int selectId = Integer.parseInt( table_randevularým.getValueAt( table_randevularým.getSelectedRow() , 0 ).toString());
				
				try {
					
					boolean control = doctor.iptalRandevu( selectId ); 
					
					if( control ) {
						
						Helper.showMsg("success");
						
						String Rtarih = table_randevularým.getValueAt( table_randevularým.getSelectedRow() , 2 ).toString();
						
						System.out.println(" ilgili hasta cep telefonuna " +  Rtarih +" tarihli randevunun iptal edildiði mesajý iletildi!");
						
						updateRandevuModel( selectId );
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				
			}
			
		}); 
		
		
	}

	
	
	
	// bu update metodu doctor sýnýfýndan bir nesne almalý cunki!
	// giriþ yapan doktor bilgisini içerde almamýz gerkiyor = doctor.getId();
	// burada buna parametre olarak doctor nesnesini vermeliyiz
	// video 5 50. dk
	public void updateWhourModel(Doctor doctor) throws SQLException {

		DefaultTableModel clearModel = (DefaultTableModel) table.getModel();
		clearModel.setRowCount(0);

			for (int i = 0; i < doctor.getWorkHourList(doctor.getId()).size(); i++) {

				whourData[0] = doctor.getWorkHourList(doctor.getId()).get(i).getId();
				whourData[1] = doctor.getWorkHourList(doctor.getId()).get(i).getWorkhour_date();
				whour_model.addRow(whourData);

			}
	}
	
	//randevular table ýný guncelemek için
	public void updateRandevuModel( int doctorID) throws SQLException{
		
		DefaultTableModel cleearModel = new  DefaultTableModel();
		cleearModel.setRowCount( 0 );
		
		for( int i=0; i< randevularým.getRandevuDoctorList( doctorID ).size(); i++ ) {
			
			randevuData[0] = randevularým.getRandevuDoctorList(doctorID).get(i).getId();
			randevuData[1] = randevularým.getRandevuDoctorList(doctorID).get(i).getHastaName();
			randevuData[2] = randevularým.getRandevuDoctorList(doctorID).get(i).getRandevuDate();
			randevuModel.addRow(randevuData);
		}
	}
	
	
}
