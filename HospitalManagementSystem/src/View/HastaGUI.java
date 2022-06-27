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

	// bunlarý doctor bilgilerini tekrar bulmaktan kurtulmak için yaptým
	private int selectDctID = 0;
	private String selectDctName = null;

	// randevu iþlemleri için
	private JTable table_randevular;
	private DefaultTableModel randevuModel;
	private Object[] randevuData = null;
	private static Randevu randevularým = new Randevu();
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

		// calýsma saatleri için
		wHourModel = new DefaultTableModel();
		Object[] colWHour = new Object[2];

		colWHour[0] = "ID";
		colWHour[1] = "Tarih";
		wHourModel.setColumnIdentifiers(colWHour);
		wHourData = new Object[2];

		// hasta randevularý için
		randevuModel = new DefaultTableModel();
		Object[] colRandevu = new Object[3];

		colRandevu[0] = "ID";
		colRandevu[1] = "Doktor";
		colRandevu[2] = "Tarih";
		randevuModel.setColumnIdentifiers(colRandevu);
		randevuData = new Object[3];

		for (int i = 0; i < randevularým.getRandevuHastaList(hastalar.getId()).size(); i++) {

			randevuData[0] = randevularým.getRandevuHastaList(hastalar.getId()).get(i).getId();
			randevuData[1] = randevularým.getRandevuHastaList(hastalar.getId()).get(i).getDoctorName();
			randevuData[2] = randevularým.getRandevuHastaList(hastalar.getId()).get(i).getRandevuDate();
			randevuModel.addRow(randevuData);
		}

		setResizable(false);
		setTitle("Hastahane Yönetim Sistemi");
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

		JLabel lblNewLabel = new JLabel("Hosgeldiniz Sayýn " + hastalar.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 21, 225, 25);
		doctor_pane.add(lblNewLabel);

		JButton btnCýkýsYap = new JButton("\u00E7\u0131k\u0131\u015F yap");
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
		select_clinic.addItem("--Poliklinik Seç --");
		for (int i = 0; i < klinikler.getClinicList().size(); i++) {

			select_clinic.addItem(
					new Item(klinikler.getClinicList().get(i).getId(), klinikler.getClinicList().get(i).getName()));

		}

		select_clinic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (select_clinic.getSelectedIndex() != 0) { // 0. indiste bizim Poliklink seç yazýyodu onu secmedýyse

					JComboBox combo = (JComboBox) e.getSource(); // combobox içindeki degerleri getir key-value
																	// degerlerini

					Item selectItem = (Item) combo.getSelectedItem(); // secilen ýtem hangisi onu bul

					System.out.println(selectItem.getKey() + " - " + selectItem.getValue()); // terminal ekranda gorelim

					// secilen polklinikteki doktorlarý model içine ekle yani jtable da goruntule

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

		JLabel lblNewLabel_1_2 = new JLabel("                    Calýsma Saatleri");
		lblNewLabel_1_2.setBounds(427, 11, 262, 20);
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		randevu_paneli.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_1_1 = new JLabel("         Doctor Seç");
		lblNewLabel_1_1_1.setBounds(282, 107, 142, 20);
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		randevu_paneli.add(lblNewLabel_1_1_1);

		JButton btnSelectDoctor = new JButton("Seç");
		btnSelectDoctor.setBounds(282, 126, 142, 23);
		btnSelectDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// System.out.println("girildi");
				// satýr secildi mi ? eðer secim yapýlmadýysa zaten iþlem yapýlmaz onu
				// anlamalýyýz
				int row = table.getSelectedRow(); // doctorlarýn listelendigi jtabla da secilen satýrý getir

				// System.out.println(row);

				if (row >= 0) { // eðer satýr seçilmisse

					// bu seçilen satýrýn 0. indisindeki degeri al cunku bu deger bize secilen
					// satýrdaki doctor id sini verir!
					int selectId = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());

					// þimdi model içini olusturalým

					DefaultTableModel clearModel = (DefaultTableModel) table_wh.getModel();
					clearModel.setRowCount(0);

					// System.out.println("inildi");
					// þimdi secilen doctorun DB deki doctor_workhour tableýnda ki id ve çalýsma
					// saatlerini modele ekle
					// bunun için secilen doctor kullanýlýr yani doctorun id si
					try {
						// workhour.java clasýndaki getDoctorWorkHourList() metodunu cagýrdýgýmýzda
						// bu metod parametre olarak secilen doktorun id sini alýr ve geriye doktorýn
						// calýsma saatlerini verir

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

					// global olarak tanýmladýgýmýz doctor bilgilerine bunlarý burada atayalým ki
					// doctor biliglerimize itiyac duydugumuz yerde bir daha bunlarý belirlemek için
					// ugrasmayalým
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

				// satýr secildi mi ? eðer secim yapýlmadýysa zaten iþlem yapýlmaz onu
				// anlamalýyýz
				int selRow = table_wh.getSelectedRow();

				if (selRow >= 0) {// satýr secildiyse iþlem yapýlabilir demektir

					// satýr secildi ise table_wh dan secilen calýsma saatini alalým
					String tarih = table_wh.getModel().getValueAt(selRow, 1).toString();
					try {

						// þimdi randevu olsutumak için randevu ekle metodumuzu cagýralým
						// metodumuzun parametrelerini verelim
						boolean control = hastalar.ekleRandevu(selectDctID, hastalar.getId(), selectDctName,
								hastalar.getName(), tarih);

						if (control) {

							Helper.showMsg("success");
							// secilen tarihi artýk pasif hale getirmeliyiz
							hastalar.updateWHourStatus(selectDctID, tarih);
							// suan DB te doctor_workhour table da bu tarih guncellendi
							// birde ekranda yani jtabla da bunu guncelleyelim
							// sectiðimiz doctorun secilen tarihi artýk pasif oldu bunu doctor wrok hour
							// listesinden artýk kaldýr der
							updateWHourModel(selectDctID);

							// randevu alýnýnca randevular panelinde bunlarý guncel olarak goruntulemek
							// için;
							updateRandevuModel(hastalar.getId());

						}

					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}

				} else {
					Helper.showMsg("Lutfen iþlem yapmak için tarih secim yapýnýz!");
				}

			}
		});
		btnRandevuAl.setBounds(284, 206, 142, 23);
		randevu_paneli.add(btnRandevuAl);

		table_wh.getColumnModel().getColumn(0).setPreferredWidth(5);// id colonumuz 5 birim geniþliðine sahip olsun

		JPanel randevular_paneli = new JPanel();
		tabbedPane.addTab("Randevularým", null, randevular_paneli, null);
		randevular_paneli.setLayout(null);

		// randevularým panelindeki randevuyu ýptal etmek için popup menu olusturduk
		menum = new JPopupMenu();
		JMenuItem deleteItem = new JMenuItem("Iptal Et");
		menum.add(deleteItem);

		JScrollPane scrollPane_randevular = new JScrollPane();
		scrollPane_randevular.setBounds(10, 11, 679, 322);
		randevular_paneli.add(scrollPane_randevular);

		table_randevular = new JTable(randevuModel);
		table_randevular.setComponentPopupMenu(menum); // popup menumuzu ekledik
		scrollPane_randevular.setViewportView(table_randevular);

		// randevularým paenlinde jtable da nereye týklandý onu bulalým

		table_randevular.addMouseListener(new MouseAdapter() {

			// mouse dugmesýne basýldýgýný algýlamak için
			@Override
			public void mousePressed(MouseEvent e) {

				// þimdi bu jtable üzerinde mousu a týklamayý algýlayabiliyoruz
				// mouse a týklanýldýgý noktayý algýlayalým bakalým ki mouse jtable ýn hangi
				// satýrýna týklamýs
				Point týklanýlanNokta = e.getPoint();

				// jtable da üzerine týklanýlan satýr = mouse un týkladýgý noktadýr
				int selectedRow = table_randevular.rowAtPoint(týklanýlanNokta);

				// jtable da uzerýne týklaýlan satýr = secilen satýrdýr
				table_randevular.setRowSelectionInterval(selectedRow, selectedRow);

			}

		});

		// þimdi de randevularým panelinde bu item ýmýza týklandýgýnda iþlemlerini
		// gerçekleþebilemesini saðlayalým
		deleteItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// burada sectiðimiz satýrýn id sini bulalým
				// secilen satýr , 0. sutundaki degeri string olarak getir ve bunu integer a
				// cevir
				int selectRow = Integer
						.parseInt(table_randevular.getValueAt(table_randevular.getSelectedRow(), 0).toString());

				int selectID = selectRow;
				try {

					// secilen satýrýn id sini vererek buradaki doctorun id sini bulabilmek için
					int doctorID = hastalar.bul(selectID);
					System.out.println(" doctor ýd :" + doctorID);

					// þimdi de iptal edilecek olan randevuyu DB teki randevular tablosundan
					// silmeliyiz
					boolean control = hastalar.iptalRandevu(selectID);
					if (control) {
						Helper.showMsg("success");

						// ardýndan doctor_workhour tablosunda bu iptal edilen randevu tarihini Aktif
						// yapalým
						// bunun için bize doctor id ve randevu tarihi gerek
						// önce doctor_id yi belirlemiþtik þimdi de seçilen tarihi belirleyelim : bu da
						// table_randevular da ki 2. sutun idi
						String randevuTarih = table_randevular.getModel().getValueAt(table_randevular.getSelectedRow(), 2).toString();
						System.out.println(" tarihimiz :" + randevuTarih);

						try {
							// þimdiye kadar randevularým panelýnde secilen satýrdaki randevuyu
							// secilen satýrýn id si yani raandevunun id si ile dB teki randevu table da
							// sildik
							// þimdi bu satýrýn id si kullanarak db teki randevu table da bu id ye sahip
							// olan
							// doctorun id si ve randevu tarihini bulduk
							// artýk bu doctor id ve tarihi kullanarak bu tarihi doctor_workhour table da
							// Aktif yapalým

							boolean kontrol = hastalar.iptalWHourStatus(doctorID, randevuTarih);

							if (kontrol) {
								Helper.showMsg("success");
								// sistemdeki hastamýzýn id sini parametre olarak verelim
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

	// sectiðimiz doctorun secilen tarihi artýk pasif oldu bunu doctor wrok hour
	// listesinden artýk kaldýr der
	public void updateWHourModel(int hastaId) throws SQLException {

		DefaultTableModel clearModel = (DefaultTableModel) table_wh.getModel();
		clearModel.setRowCount(0);

		for (int i = 0; i < wHour.getDoctorWorkHourList(hastaId).size(); i++) {

			wHourData[0] = wHour.getDoctorWorkHourList(hastaId).get(i).getId();
			wHourData[1] = wHour.getDoctorWorkHourList(hastaId).get(i).getWorkhour_date();
			wHourModel.addRow(wHourData);

		}
	}

	// sectiðimiz hastanýn randevularýnýn goruntulendýgý ekraný guncelle
	public void updateRandevuModel(int hastaId) throws SQLException {

		DefaultTableModel clearModel = (DefaultTableModel) table_randevular.getModel();
		clearModel.setRowCount(0);

		for (int i = 0; i < randevularým.getRandevuHastaList(hastaId).size(); i++) {

			randevuData[0] = randevularým.getRandevuHastaList(hastaId).get(i).getId();
			randevuData[1] = randevularým.getRandevuHastaList(hastaId).get(i).getDoctorName();
			randevuData[2] = randevularým.getRandevuHastaList(hastaId).get(i).getRandevuDate();
			randevuModel.addRow(randevuData);
		}
	}
}
