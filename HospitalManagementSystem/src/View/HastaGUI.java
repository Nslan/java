package View;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Item;
import Helper.Helper;
import Model.Clinic;
import Model.Hasta;
import Model.Randevu;
import Model.WorkHour;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;

import javax.swing.JComboBox;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;

public class HastaGUI extends JFrame {

	private JPanel contentPane;
	private static Hasta hastalar = new Hasta();
	private static Clinic klinikler = new Clinic();
	private DefaultTableModel doctorModel;
	private Object[] doctorData = null;
	private JTable table;

	private WorkHour wHour = new WorkHour();
	private DefaultTableModel wHourModel;
	private Object[] wHourData = null;

	private JTable table_wh;

	// bunlar� doctor bilgilerini tekrar bulmaktan kurtulmak i�in yapt�m
	private int selectDctID = 0;
	private String selectDctName = null;

	// randevu i�lemleri i�in
	private JTable table_randevular;
	private DefaultTableModel randevuModel;
	private Object[] randevuData = null;
	private static Randevu randevular�m = new Randevu();
	private JPopupMenu menum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hastalar);
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
	public HastaGUI(Hasta hastalar) throws SQLException {

		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[2];

		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[2];

		// cal�sma saatleri i�in
		wHourModel = new DefaultTableModel();
		Object[] colWHour = new Object[2];

		colWHour[0] = "ID";
		colWHour[1] = "Tarih";
		wHourModel.setColumnIdentifiers(colWHour);
		wHourData = new Object[2];

		// hasta randevular� i�in
		randevuModel = new DefaultTableModel();
		Object[] colRandevu = new Object[3];

		colRandevu[0] = "ID";
		colRandevu[1] = "Doktor";
		colRandevu[2] = "Tarih";
		randevuModel.setColumnIdentifiers(colRandevu);
		randevuData = new Object[3];

		for (int i = 0; i < randevular�m.getRandevuHastaList(hastalar.getId()).size(); i++) {

			randevuData[0] = randevular�m.getRandevuHastaList(hastalar.getId()).get(i).getId();
			randevuData[1] = randevular�m.getRandevuHastaList(hastalar.getId()).get(i).getDoctorName();
			randevuData[2] = randevular�m.getRandevuHastaList(hastalar.getId()).get(i).getRandevuDate();
			randevuModel.addRow(randevuData);
		}

		setResizable(false);
		setTitle("Hastahane Y�netim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel doctor_pane = new JPanel();
		doctor_pane.setLayout(null);
		doctor_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		doctor_pane.setBackground(Color.WHITE);
		doctor_pane.setBounds(10, 11, 729, 449);
		contentPane.add(doctor_pane);

		JLabel lblNewLabel = new JLabel("Hosgeldiniz Say�n " + hastalar.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 21, 225, 25);
		doctor_pane.add(lblNewLabel);

		JButton btnC�k�sYap = new JButton("\u00E7\u0131k\u0131\u015F yap");
		btnC�k�sYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// c�k�s yap dey�nce log�n sayfas�na geri donelim
				LoginSayfas� baslang�c = new LoginSayfas�();
				baslang�c.setVisible(true);
				dispose();
			}
		});
		btnC�k�sYap.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		btnC�k�sYap.setBounds(630, 24, 89, 23);
		doctor_pane.add(btnC�k�sYap);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(10, 66, 704, 372);
		doctor_pane.add(tabbedPane);

		JPanel randevu_paneli = new JPanel();
		randevu_paneli.setBackground(Color.WHITE);
		tabbedPane.addTab("Randevu Sistemi", null, randevu_paneli, null);
		randevu_paneli.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("                      Doktor Listesi");
		lblNewLabel_1.setBounds(10, 11, 262, 20);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		randevu_paneli.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("         Poliklinikler");
		lblNewLabel_1_1.setBounds(282, 11, 142, 20);
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		randevu_paneli.add(lblNewLabel_1_1);

		JComboBox select_clinic = new JComboBox();
		select_clinic.setBounds(284, 30, 142, 31);
		select_clinic.addItem("--Poliklinik Se� --");
		for (int i = 0; i < klinikler.getClinicList().size(); i++) {

			select_clinic.addItem(
					new Item(klinikler.getClinicList().get(i).getId(), klinikler.getClinicList().get(i).getName()));

		}

		select_clinic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (select_clinic.getSelectedIndex() != 0) { // 0. indiste bizim Poliklink se� yaz�yodu onu secmed�yse

					JComboBox combo = (JComboBox) e.getSource(); // combobox i�indeki degerleri getir key-value
																	// degerlerini

					Item selectItem = (Item) combo.getSelectedItem(); // secilen �tem hangisi onu bul

					System.out.println(selectItem.getKey() + " - " + selectItem.getValue()); // terminal ekranda gorelim

					// secilen polklinikteki doktorlar� model i�ine ekle yani jtable da goruntule

					DefaultTableModel clearModel = (DefaultTableModel) table.getModel();
					clearModel.setRowCount(0);

					try {
						for (int i = 0; i < klinikler.getClinicDoctorList(selectItem.getKey()).size(); i++) {

							doctorData[0] = klinikler.getClinicDoctorList(selectItem.getKey()).get(i).getId();
							doctorData[1] = klinikler.getClinicDoctorList(selectItem.getKey()).get(i).getName();
							doctorModel.addRow(doctorData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});

		randevu_paneli.add(select_clinic);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 30, 265, 303);
		randevu_paneli.add(scrollPane_1);

		table = new JTable(doctorModel);
		scrollPane_1.setViewportView(table);

		JLabel lblNewLabel_1_2 = new JLabel("                    Cal�sma Saatleri");
		lblNewLabel_1_2.setBounds(427, 11, 262, 20);
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		randevu_paneli.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_1_1 = new JLabel("         Doctor Se�");
		lblNewLabel_1_1_1.setBounds(282, 107, 142, 20);
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		randevu_paneli.add(lblNewLabel_1_1_1);

		JButton btnSelectDoctor = new JButton("Se�");
		btnSelectDoctor.setBounds(282, 126, 142, 23);
		btnSelectDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// System.out.println("girildi");
				// sat�r secildi mi ? e�er secim yap�lmad�ysa zaten i�lem yap�lmaz onu
				// anlamal�y�z
				int row = table.getSelectedRow(); // doctorlar�n listelendigi jtabla da secilen sat�r� getir

				// System.out.println(row);

				if (row >= 0) { // e�er sat�r se�ilmisse

					// bu se�ilen sat�r�n 0. indisindeki degeri al cunku bu deger bize secilen
					// sat�rdaki doctor id sini verir!
					int selectId = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());

					// �imdi model i�ini olustural�m

					DefaultTableModel clearModel = (DefaultTableModel) table_wh.getModel();
					clearModel.setRowCount(0);

					// System.out.println("inildi");
					// �imdi secilen doctorun DB deki doctor_workhour table�nda ki id ve �al�sma
					// saatlerini modele ekle
					// bunun i�in secilen doctor kullan�l�r yani doctorun id si
					try {
						// workhour.java clas�ndaki getDoctorWorkHourList() metodunu cag�rd�g�m�zda
						// bu metod parametre olarak secilen doktorun id sini al�r ve geriye doktor�n
						// cal�sma saatlerini verir

						for (int i = 0; i < wHour.getDoctorWorkHourList(selectId).size(); i++) {

							wHourData[0] = wHour.getDoctorWorkHourList(selectId).get(i).getId();
							wHourData[1] = wHour.getDoctorWorkHourList(selectId).get(i).getWorkhour_date();
							wHourModel.addRow(wHourData);

						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					table_wh.setModel(wHourModel);

					// global olarak tan�mlad�g�m�z doctor bilgilerine bunlar� burada atayal�m ki
					// doctor biliglerimize itiyac duydugumuz yerde bir daha bunlar� belirlemek i�in
					// ugrasmayal�m
					selectDctID = selectId;
					selectDctName = table.getModel().getValueAt(row, 1).toString();

				} else {

					Helper.showMsg("Lutfen bir doctor seciniz");

				}

			}
		});
		randevu_paneli.add(btnSelectDoctor);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(434, 30, 255, 303);
		randevu_paneli.add(scrollPane);

		table_wh = new JTable(wHourModel);
		scrollPane.setViewportView(table_wh);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("       Randevu Kay\u0131t");
		lblNewLabel_1_1_1_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		lblNewLabel_1_1_1_1.setBounds(284, 187, 142, 20);
		randevu_paneli.add(lblNewLabel_1_1_1_1);

		JButton btnRandevuAl = new JButton("Randevu Al");
		btnRandevuAl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// sat�r secildi mi ? e�er secim yap�lmad�ysa zaten i�lem yap�lmaz onu
				// anlamal�y�z
				int selRow = table_wh.getSelectedRow();

				if (selRow >= 0) {// sat�r secildiyse i�lem yap�labilir demektir

					// sat�r secildi ise table_wh dan secilen cal�sma saatini alal�m
					String tarih = table_wh.getModel().getValueAt(selRow, 1).toString();
					try {

						// �imdi randevu olsutumak i�in randevu ekle metodumuzu cag�ral�m
						// metodumuzun parametrelerini verelim
						boolean control = hastalar.ekleRandevu(selectDctID, hastalar.getId(), selectDctName,
								hastalar.getName(), tarih);

						if (control) {

							Helper.showMsg("success");
							// secilen tarihi art�k pasif hale getirmeliyiz
							hastalar.updateWHourStatus(selectDctID, tarih);
							// suan DB te doctor_workhour table da bu tarih guncellendi
							// birde ekranda yani jtabla da bunu guncelleyelim
							// secti�imiz doctorun secilen tarihi art�k pasif oldu bunu doctor wrok hour
							// listesinden art�k kald�r der
							updateWHourModel(selectDctID);

							// randevu al�n�nca randevular panelinde bunlar� guncel olarak goruntulemek
							// i�in;
							updateRandevuModel(hastalar.getId());

						}

					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}

				} else {
					Helper.showMsg("Lutfen i�lem yapmak i�in tarih secim yap�n�z!");
				}

			}
		});
		btnRandevuAl.setBounds(284, 206, 142, 23);
		randevu_paneli.add(btnRandevuAl);

		table_wh.getColumnModel().getColumn(0).setPreferredWidth(5);// id colonumuz 5 birim geni�li�ine sahip olsun

		JPanel randevular_paneli = new JPanel();
		tabbedPane.addTab("Randevular�m", null, randevular_paneli, null);
		randevular_paneli.setLayout(null);

		// randevular�m panelindeki randevuyu �ptal etmek i�in popup menu olusturduk
		menum = new JPopupMenu();
		JMenuItem deleteItem = new JMenuItem("Iptal Et");
		menum.add(deleteItem);

		JScrollPane scrollPane_randevular = new JScrollPane();
		scrollPane_randevular.setBounds(10, 11, 679, 322);
		randevular_paneli.add(scrollPane_randevular);

		table_randevular = new JTable(randevuModel);
		table_randevular.setComponentPopupMenu(menum); // popup menumuzu ekledik
		scrollPane_randevular.setViewportView(table_randevular);

		// randevular�m paenlinde jtable da nereye t�kland� onu bulal�m

		table_randevular.addMouseListener(new MouseAdapter() {

			// mouse dugmes�ne bas�ld�g�n� alg�lamak i�in
			@Override
			public void mousePressed(MouseEvent e) {

				// �imdi bu jtable �zerinde mousu a t�klamay� alg�layabiliyoruz
				// mouse a t�klan�ld�g� noktay� alg�layal�m bakal�m ki mouse jtable �n hangi
				// sat�r�na t�klam�s
				Point t�klan�lanNokta = e.getPoint();

				// jtable da �zerine t�klan�lan sat�r = mouse un t�klad�g� noktad�r
				int selectedRow = table_randevular.rowAtPoint(t�klan�lanNokta);

				// jtable da uzer�ne t�kla�lan sat�r = secilen sat�rd�r
				table_randevular.setRowSelectionInterval(selectedRow, selectedRow);

			}

		});

		// �imdi de randevular�m panelinde bu item �m�za t�kland�g�nda i�lemlerini
		// ger�ekle�ebilemesini sa�layal�m
		deleteItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// burada secti�imiz sat�r�n id sini bulal�m
				// secilen sat�r , 0. sutundaki degeri string olarak getir ve bunu integer a
				// cevir
				int selectRow = Integer
						.parseInt(table_randevular.getValueAt(table_randevular.getSelectedRow(), 0).toString());

				int selectID = selectRow;
				try {

					// secilen sat�r�n id sini vererek buradaki doctorun id sini bulabilmek i�in
					int doctorID = hastalar.bul(selectID);
					System.out.println(" doctor �d :" + doctorID);

					// �imdi de iptal edilecek olan randevuyu DB teki randevular tablosundan
					// silmeliyiz
					boolean control = hastalar.iptalRandevu(selectID);
					if (control) {
						Helper.showMsg("success");

						// ard�ndan doctor_workhour tablosunda bu iptal edilen randevu tarihini Aktif
						// yapal�m
						// bunun i�in bize doctor id ve randevu tarihi gerek
						// �nce doctor_id yi belirlemi�tik �imdi de se�ilen tarihi belirleyelim : bu da
						// table_randevular da ki 2. sutun idi
						String randevuTarih = table_randevular.getModel().getValueAt(table_randevular.getSelectedRow(), 2).toString();
						System.out.println(" tarihimiz :" + randevuTarih);

						try {
							// �imdiye kadar randevular�m panel�nde secilen sat�rdaki randevuyu
							// secilen sat�r�n id si yani raandevunun id si ile dB teki randevu table da
							// sildik
							// �imdi bu sat�r�n id si kullanarak db teki randevu table da bu id ye sahip
							// olan
							// doctorun id si ve randevu tarihini bulduk
							// art�k bu doctor id ve tarihi kullanarak bu tarihi doctor_workhour table da
							// Aktif yapal�m

							boolean kontrol = hastalar.iptalWHourStatus(doctorID, randevuTarih);

							if (kontrol) {
								Helper.showMsg("success");
								// sistemdeki hastam�z�n id sini parametre olarak verelim
								updateRandevuModel(hastalar.getId());
							}
						} catch (Exception e2) {
							// TODO: handle exception
							e2.printStackTrace();
						}

					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

	}

	// secti�imiz doctorun secilen tarihi art�k pasif oldu bunu doctor wrok hour
	// listesinden art�k kald�r der
	public void updateWHourModel(int hastaId) throws SQLException {

		DefaultTableModel clearModel = (DefaultTableModel) table_wh.getModel();
		clearModel.setRowCount(0);

		for (int i = 0; i < wHour.getDoctorWorkHourList(hastaId).size(); i++) {

			wHourData[0] = wHour.getDoctorWorkHourList(hastaId).get(i).getId();
			wHourData[1] = wHour.getDoctorWorkHourList(hastaId).get(i).getWorkhour_date();
			wHourModel.addRow(wHourData);

		}
	}

	// secti�imiz hastan�n randevular�n�n goruntulend�g� ekran� guncelle
	public void updateRandevuModel(int hastaId) throws SQLException {

		DefaultTableModel clearModel = (DefaultTableModel) table_randevular.getModel();
		clearModel.setRowCount(0);

		for (int i = 0; i < randevular�m.getRandevuHastaList(hastaId).size(); i++) {

			randevuData[0] = randevular�m.getRandevuHastaList(hastaId).get(i).getId();
			randevuData[1] = randevular�m.getRandevuHastaList(hastaId).get(i).getDoctorName();
			randevuData[2] = randevular�m.getRandevuHastaList(hastaId).get(i).getRandevuDate();
			randevuModel.addRow(randevuData);
		}
	}
}
