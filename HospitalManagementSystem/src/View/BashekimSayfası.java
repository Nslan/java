package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Helper.DoctorIdName;
import Helper.Helper;
import Model.BasHekim;
import Model.Clinic;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JComboBox;

public class BashekimSayfas� extends JFrame {

	static BasHekim bashekimnesnesi = new BasHekim(); // BasHekim s�n�f�ndaki metodlar� kulanabilmek i�in bunu
														// tan�mlamak zorunday�z
	static Clinic kliniknesnesi = new Clinic(); // clinic s�n�f�nda ki metodlar�m� cag�r�p kullanabilmek i�in
	private JPanel contentPane;
	private JTextField fld_name;
	private JTextField fld_tc;
	private JTextField fld_sifre;
	private JTextField fld_id;
	private JTable table_doctor;

	/*
	 * art�k panelimizde doktorlar�m�z� liste �eklinde g�r�nt�leyebilece�iz ama �uan
	 * sadece listeyi d�nd�rd�k g�r�nt�leme k�sm�n� hen�z yapmad�k bunu i�in
	 * BashekimSayfas� na gelerek yani g�r�nt�lemenin yap�lacag� yere gelinir biz
	 * java.swing derslerinde table lara modeller sayesinde veri ekleyebilmeyi
	 * biliyorduk �imdi o yap�lar� kullanarak bu i�lemi ger�ekle�tirelim : ilk �nce
	 * bir DEfaultTableModel s�n�f�ndan bir nesne �retilir ve balang�cta null a
	 * setlenir (bir tabloya veri eklemek i�in modellerden yarar) sonra bir Object[]
	 * s�n�f�ndan nesne �retilir ve buda nul a setlenir buda datalar�, i�ine
	 * ataca��m�z verileri alacak
	 */

	private DefaultTableModel doctorModel = null;// bir tabloya veri eklemek i�in modellerden yarar
	private Object[] doctorData = null; // buda datalar�, i�ine ataca��m�z verileri alacak

	// CL�N�C i�in
	private JTextField fld_clinic_name;
	private JTable table_clinic;
	private DefaultTableModel clinicModel = null; // Poliklinik sayfa i�ini bu olusturur
	private Object[] clinicData = null; // clinicteki bilgileri i�ine atacag�m�z bilgileri tutacak
	private JPopupMenu clinicMenu;
	private JTable table_worker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimSayfas� frame = new BashekimSayfas�(bashekimnesnesi); // art�k yazabildik nesnemizi
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
	public BashekimSayfas�(BasHekim bashekimnesnesi) throws SQLException {

		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];// biz doktorlar� belirlerken DB den b�t�n bilgileri �ekmek zorunday�z
												// lakin g�r�nt�lerken
		// zaten sadece doktorlar g�r�nt�lenecegi i�in bir daha type � g�r�nt�lemeye
		// gerek yok (id,tcno,name,password) bunun i�in boyut 4 olur

		// �imdi hangi indiste hangi bilgi var ? onu belirtelim yani g�r�nt�leme
		// s�ralamas�n� belirleyelim
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Tc No";
		colDoctorName[2] = "Sifre";
		colDoctorName[3] = "Ad Soyad";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for (int i = 0; i < bashekimnesnesi.getDoctorList().size(); i++) {

			doctorData[0] = bashekimnesnesi.getDoctorList().get(i).getId();// bashekim clas� userdan extends sedildigi i�in get() metodlar� taa userdan gelir
			doctorData[1] = bashekimnesnesi.getDoctorList().get(i).getTcno();
			doctorData[2] = bashekimnesnesi.getDoctorList().get(i).getPassword();
			doctorData[3] = bashekimnesnesi.getDoctorList().get(i).getName();
			doctorModel.addRow(doctorData);
		}

		setBackground(Color.WHITE);
		setTitle("Hastahane Y�netim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ho\u015Fgeldiniz Say\u0131n" + bashekimnesnesi.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(15, 11, 225, 25);
		contentPane.add(lblNewLabel);

		JButton btnC�k�sYap = new JButton("\u00C7\u0131k\u0131\u015F yap");
		btnC�k�sYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//c�k�s yap dey�nce log�n sayfas�na geri donelim
				LoginSayfas� baslang�c = new LoginSayfas�();
				baslang�c.setVisible(true);
				dispose();
			}
		});
		btnC�k�sYap.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		btnC�k�sYap.setBounds(635, 14, 89, 23);
		contentPane.add(btnC�k�sYap);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(10, 66, 714, 384);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Doktor Y�netimi", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		lblNewLabel_1.setBounds(501, 21, 198, 20);
		panel.add(lblNewLabel_1);

		fld_name = new JTextField();
		fld_name.setBounds(501, 40, 198, 30);
		panel.add(fld_name);
		fld_name.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("T.C. No");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(501, 69, 198, 20);
		panel.add(lblNewLabel_1_1);

		fld_tc = new JTextField();
		fld_tc.setColumns(10);
		fld_tc.setBounds(501, 91, 198, 30);
		panel.add(fld_tc);

		JLabel lblNewLabel_1_1_1 = new JLabel("\u015Eifre");
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(501, 121, 198, 20);
		panel.add(lblNewLabel_1_1_1);

		fld_sifre = new JTextField();
		fld_sifre.setColumns(10);
		fld_sifre.setBounds(501, 143, 198, 30);
		panel.add(fld_sifre);

		JButton btn_ekleDoctor = new JButton("Ekle");
		btn_ekleDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_name.getText().length() == 0 || fld_sifre.getText().length() == 0
						|| fld_tc.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = bashekimnesnesi.addDoctor(fld_tc.getText(), fld_sifre.getText(),
								fld_name.getText());
						if (control = true) {
							Helper.showMsg("success");
							fld_name.setText(null);
							fld_tc.setText(null);
							fld_sifre.setText(null);
							updateDoctorModel();// bu ekran� g�ncellemek i�in asag�da sonradan yazd�g�m�z metoddur
												// bunuda burda �ag�r�r�z
							// ki DB e ekleme yap�nca DB f5 ile g�ncellenebiliyordu lakin ekran ancak b�yle
							// g�ncellenir
						}
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				}
			}
		});
		btn_ekleDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btn_ekleDoctor.setBounds(501, 180, 198, 32);
		panel.add(btn_ekleDoctor);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Kullan\u0131c\u0131 ID");
		lblNewLabel_1_1_1_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		lblNewLabel_1_1_1_1.setBounds(501, 232, 198, 20);
		panel.add(lblNewLabel_1_1_1_1);

		fld_id = new JTextField();
		fld_id.setBackground(Color.LIGHT_GRAY);
		fld_id.setColumns(10);
		fld_id.setBounds(501, 254, 198, 30);
		panel.add(fld_id);

		JButton btn_silDoctor = new JButton("Sil");
		btn_silDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (fld_id.getText().length() == 0) {
					Helper.showMsg("Lutfen kayd� silinecek doktor seciniz!");
				} else {
					if (Helper.confirm("sure")) {

						int selectID = Integer.parseInt(fld_id.getText()); // bizim text fieldlardaki degerimiz
																			// stringtir bunu int e cevir

						try {

							boolean control = bashekimnesnesi.deleteDoctor(selectID);

							if (control) {
								Helper.showMsg("success");
								fld_id.setText(null);
								updateDoctorModel();
							}

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn_silDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btn_silDoctor.setBounds(501, 299, 198, 32);
		panel.add(btn_silDoctor);

		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 11, 478, 334);
		panel.add(w_scrollDoctor);

		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);

		// JTable da bir doktor sec ve secilen bu sat�r�n id sini g�r�nt�le
		// getSelectionModel : se�me i�leminin yap�lmas�n� saglar
		// addListSelectionListener : hangi sat�ra t�kland� onu alg�lar
		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				// getValueAt(row,col) : sat�r sutunundaki degeri getir
				// getSelectedRow() : se�ilen sat�r numaras�n� d�nd�r�r
				try {
					fld_id.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());

				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});

		// bunu enson yap�yorumm silme ekleme g�ncelleme i�lemleriinden sonra
		// amac Jtable da herhengi bir sat�r sutundaki bilgi de�i�tirilmi�se DB tede
		// de�i�sin
		table_doctor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub

				if (e.getType() == TableModelEvent.UPDATE) {

					int selectID = Integer.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
					String selectTC = table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
					String selectPassWord = table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
					String selectUserName = table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();

					try {

						if (Helper.confirm("sure")) {
							boolean control = bashekimnesnesi.updateDoctor(selectID, selectTC, selectPassWord,
									selectUserName);
							if (control) {
								Helper.showMsg("success");
							}
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});

		// Clinic model
		clinicModel = new DefaultTableModel();
		Object[] colClinic = new Object[2];
		colClinic[0] = "ID";
		colClinic[1] = "Poiklinik Ad�";
		clinicModel.setColumnIdentifiers(colClinic);
		clinicData = new Object[2];
		for (int i = 0; i < kliniknesnesi.getClinicList().size(); i++) {

			clinicData[0] = kliniknesnesi.getClinicList().get(i).getId();
			clinicData[1] = kliniknesnesi.getClinicList().get(i).getName();
			clinicModel.addRow(clinicData);
		}

		// clinic panelimizi olsutural�m
		JPanel w_clinic_panel = new JPanel();
		w_clinic_panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Poliklinikler", null, w_clinic_panel, null);
		w_clinic_panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 257, 334);
		w_clinic_panel.add(scrollPane);

		clinicMenu = new JPopupMenu(); // popup menu muzu olusturduk
		JMenuItem updateMenu = new JMenuItem("G�ncelle");// itemlar�m�z olusturduk
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);// �temlar popup menuye eklendi

		// �imdi bu �temlara t�kland�g�n� alg�layabilelim
		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// secilen sat�r�n �d sini bulal�m
				int setID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());

				// �imdi secilen sat�rdaki clinic bilgilerini dondurelim bunun i�in metodumuzu
				// cag�ral�m
				// ve bunu yenibir nesneye atayal�m
				Clinic selectClinic = kliniknesnesi.updateClinicNameSelectedRow(setID);

				// �imdi secti�imiz ogrenci bilgilerini di�er gu� mize aktaral�m ki orada g�r�p
				// d�zenleyebilelim
				updateClinicGUI updateGu� = new updateClinicGUI(selectClinic);

				updateGu�.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // gu� de exite bas�nca kapamas�n� saglar
				updateGu�.setVisible(true); // gu� mizin i�eri�ini g�r�ntulemek i�in

				// �imdi de Jtable i�ini g�ncellemek i�in bu gu� ekran�n�n kapand�g�n� alg�lamak
				// laz�m
				// bu gu� penceresini alg�lamak i�in de pencere i�lemlerini alg�layan bir
				// windowListener s�n�f kulllanal�m

				updateGu�.addWindowListener(new WindowAdapter() {

					// pencerenin kapanmas�n� alg�layan bu metod
					@Override
					public void windowClosed(WindowEvent e) {

						/*
						 * �imdi burada daha �nceden olusturdugumuz Jtable i�ini guncelleyen metodumuzu
						 * cag�r�sak art�k bu gu� miz kapan�nca JTable i�erisi hemen guncellenecek
						 */

						try {

							updateClinicModel();

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}

				});

			}
		});

		// sil item� i�lemleri
		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (Helper.confirm("sure")) {
					// silmek istedigimiz secilen sat�r�n id si belirlenmelidir
					int setId = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());

					try {

						if (kliniknesnesi.deleteClinic(setId)) {
							Helper.showMsg("success");
							updateClinicModel(); // sildikten sonra Jtable guncellens�n
						} else {
							Helper.showMsg("error");
						}

					} catch (Exception e2) {
						e2.printStackTrace();
					}

				}

			}
		});

		table_clinic = new JTable(clinicModel);
		table_clinic.setComponentPopupMenu(clinicMenu);// popup menuyu table a ekled�k
		// Jtable da mause hangi sat�rda t�kland� onu alg�layal�m
		table_clinic.addMouseListener(new MouseAdapter() {
			/*
			 * Mouse listener i�in de MousePressed metodunu cag�r�yoruz ve bunu Override
			 * ediyoruz bunun nedeni JTable mouse nereye t�klad� o noktan�n koordinat�n�
			 * alarak orada hangi sat�r hangi id var onu bulmak istiyoruz
			 */
			@Override
			public void mousePressed(MouseEvent e) {

				Point t�klan�lan_nokta = e.getPoint();// noktay� bulmak i�inse Point s�n�f� kulan�l�r
				/*
				 * �imdi bu table uzerinde nereye yani hangi sat�ra t�kland�g�n� alg�layan bir
				 * rowAtPoint metodu var ve bu metod mouse un t�klad�g� noktay� parametre olarak
				 * al�r boylece biz bu metodu ve bu noktay� kullanarak se�ilen sat�r�m�z� yani
				 * �zerine t�klan�lan sat�r�m�z� bulabiliriz
				 */
				int selectedRow = table_clinic.rowAtPoint(t�klan�lan_nokta); // �zerine t�klan�lan sat�r budur dedik
//�imdi de bu uzerine t�klan�lan sat�r ayn�zamanda bizim se�ti�imiz sat�r olmas� i�in setRowSelectionInterval 
//				metodunu �ag�r�r�z bu metod parametre olarak iki sat�r al�r

				table_clinic.setRowSelectionInterval(selectedRow, selectedRow); // art�k �zeirne t�klad�g�m�z sat�r�m�z
																				// secti�imi sat�rm�z oldu

			}

		});

		scrollPane.setViewportView(table_clinic);

		fld_clinic_name = new JTextField();
		fld_clinic_name.setBounds(277, 41, 155, 33);
		w_clinic_panel.add(fld_clinic_name);
		fld_clinic_name.setColumns(10);

		JScrollPane scrollPane_worker = new JScrollPane();
		scrollPane_worker.setBounds(442, 11, 257, 334);
		w_clinic_panel.add(scrollPane_worker);

		table_worker = new JTable();
		scrollPane_worker.setViewportView(table_worker);

		JLabel lbl_poliklinikAd� = new JLabel("Poliklinik Ad\u0131");
		lbl_poliklinikAd�.setBounds(277, 16, 155, 21);
		w_clinic_panel.add(lbl_poliklinikAd�);

		JButton btn_clinic_add = new JButton("Ekle");
		btn_clinic_add.setBounds(277, 85, 155, 33);
		btn_clinic_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (fld_clinic_name.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {

					try {
						boolean control = kliniknesnesi.addClinic(fld_clinic_name.getText());

						if (control = true) {

							Helper.showMsg("success");
							fld_clinic_name.setText(null);
							updateClinicModel();
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		w_clinic_panel.add(btn_clinic_add);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(277, 252, 155, 33);
		// doktorlar combobox i�inde gosterilece�i i�in bunlar� cek
		// doktorlar� DB ten ceken metod basHekim s�n�f�nda idi
		for (int i = 0; i < bashekimnesnesi.getDoctorList().size(); i++) {

			//// doktorlar� comboboxa ekle
			// comboBox.addItem( bashekimnesnesi.getDoctorList().get(i).getName() );// lakin
			//// ayn� isimden bir suru doktor olabilir ay�rt edebilmek i�in id de laz�m
			// ne yaz�k ki bu metod 2 tane parametre alm�yor
			// bunu sa�lamak i�in yeni bir yap� kurmak gerekir

			// doktorlar�n id sini bilmek i�in i�ine nesne alan ve bu nesne de id - name
			// de�erleri bulunmal�
			// bunun i�in DoctorIdName metodumzu kulanal�m
			comboBox.addItem(new DoctorIdName(bashekimnesnesi.getDoctorList().get(i).getId(),
					bashekimnesnesi.getDoctorList().get(i).getName()));

		}

		// bu metodu kurdugumuz key - value gercekten cal�s�yomu diye kontrol etmek i�in
		// yazd�k bir onem� yok!
		comboBox.addActionListener(e -> { // bu i�aret pointer d�r bu yap� ise lamda yap�s�d�r

			JComboBox c = (JComboBox) e.getSource();
			DoctorIdName item = (DoctorIdName) c.getSelectedItem();
			System.out.println(item.getKey_doctorId() + " : " + item.getValue_doctorName());

		});

		w_clinic_panel.add(comboBox);
		
		JLabel lbl_poliklinikAd�_1 = new JLabel("Poliklinik Ad\u0131");
		lbl_poliklinikAd�_1.setBounds(277, 166, 155, 21);
		w_clinic_panel.add(lbl_poliklinikAd�_1);

		// worker model
		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "ID";
		colWorker[1] = "Poliklinik Ad�";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];
		/*
		 * �imdi bunun i�ini burada doldurmayal�m e�er burada doldurursak tum worker
		 * tablosunu soldaki jtable da gostermi� oluruz biz soldaki jtable da sadece sa�
		 * tarafta secti�imiz polk. teki cal�san doktorlar� goruntulemek istiyoruz yani
		 * biz bir polk. secip sec butonuna bas�nca bu jtable i�i o polk. teki
		 * doctorlar� goruntuleyecek bunun i�in se� butonunun action listener�
		 * i�erisinde bu modelin i�ini doldural�m
		 */

		JButton btn_select_clinic = new JButton("Se\u00E7");
		btn_select_clinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// jtable da polklinik se�sin
				int selectRow = table_clinic.getSelectedRow();
				if (selectRow >= 0) {
					// secilen klini�in id si ni bul
					int selectClinicId = Integer.parseInt(table_clinic.getValueAt(selectRow, 0).toString());
					/*
					 * ard�ndan tan�mlad�g�m�z modelimizin i�ini olustural�m oncelikle her yeni polk
					 * secildi�inde bu model i�i de�i�ecegi i�in ve bizde model i�ini de�i�tirirken
					 * guncellerken model i�ini �nce bosalt�yorduk burada da oyle yapal�m ki her
					 * yeni polk secildi�inde model i�i de ona gore guncellensin
					 */
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0); // sat�rlar� sildik yani i�ini bo�altt�k

					try {
// biz bu model i�inde poliklinklerdeki doktorlar� tutacag�z bunu da liste olarak donduren bir metod olarak tan�mlam�st�k
						for (int i = 0; i < bashekimnesnesi.getClinicDoctorList(selectClinicId).size(); i++) {

							workerData[0] = bashekimnesnesi.getClinicDoctorList(selectClinicId).get(i).getId();
							workerData[1] = bashekimnesnesi.getClinicDoctorList(selectClinicId).get(i).getName();
							workerModel.addRow(workerData);
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					
					table_worker.setModel(workerModel); // modeli table a eklemeyi unutma
					
				} else {
					Helper.showMsg("lutfen poliklinik se�in !");
				}

			}
		});

		btn_select_clinic.setBounds(277, 185, 155, 33);
		w_clinic_panel.add(btn_select_clinic);

		// worker tablosunu olusturur yani polkilinklere doktor eklemek i�in
		JButton btn_addWorker = new JButton("Ekle");
		btn_addWorker.setBounds(277, 296, 155, 33);
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// jtable da polklinik se�sin
				int selectRow = table_clinic.getSelectedRow();
				if (selectRow >= 0) {
					// secilen klini�in id si ni bul
					int selectClinicId = Integer.parseInt(table_clinic.getModel().getValueAt(selectRow, 0).toString());
					// veya
					// int selectClinicId = Integer.parseInt(table_clinic.getValueAt(selectRow,
					// 0).toString()) ;

					// secilen doktorun id si ve name ini key-value seklinde getirmek i�in
					// DoctorIdName s�n�f�n� kullan�yoruk
					// bu s�n�ftan bir nesne �retelim
					DoctorIdName doctorItem = (DoctorIdName) comboBox.getSelectedItem();

					// art�k hem pol id si hemde doctor id-name ini biliyoruz �imdi poliklini�e
					// doktoru ekleyebiliriz
					// BasHekim s�n�f�ndaki polk ni�e doctor ekleme metodumuzu yani addWorker
					// cag�r�rsak
					try {

						boolean control = bashekimnesnesi.addWorker(doctorItem.getKey_doctorId(), selectClinicId);
						
						if (control) {
							Helper.showMsg("success");
							
							// e�er polk.ni�e doktor ekleme basar�l� ise
							//model olusturarak model i�ini guncelleyelim ki 
							//polk secip comboboxta doctor ekle deyince jtable da bu poliklinik i�indeki doktorrlara bunuda ekledi�ini ve g�stersin  
							//yukarda ki modelimizi ve workerData objectimizi kullanal�m
							
							//herseferinde model i�ini guncelleyece�imiz i�in model i�ini bosalt�p tekar olusturmak gerek
							DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
							clearModel.setColumnCount(0);
							
							// ekleme yap�lacak polk i�indeki doctorlar� tutan metodumuzu cag�ral�m
							for(int i=0; i< bashekimnesnesi.getClinicDoctorList(selectClinicId).size(); i++ ) {
								workerData[0] = bashekimnesnesi.getClinicDoctorList(selectClinicId).get(i).getId();
								workerData[1] = bashekimnesnesi.getClinicDoctorList(selectClinicId).get(i).getName();
								workerModel.addRow(workerData);
							}
							table_worker.setModel(workerModel);
						} else {
							Helper.showMsg("error");

						}

					} catch (Exception e2) {
						e2.printStackTrace();
					}

				} else {
					Helper.showMsg("Lutfen bir poliklinik seciniz !");
				}

			}
		});
		w_clinic_panel.add(btn_addWorker);

		

	}

	public void updateClinicModel() throws SQLException {

		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);

		// veya
		// ( (DefaultTableModel) table_clinic.getModel()).setRowCount(0);

		for (int i = 0; i < kliniknesnesi.getClinicList().size(); i++) {

			clinicData[0] = kliniknesnesi.getClinicList().get(i).getId();
			clinicData[1] = kliniknesnesi.getClinicList().get(i).getName();
			clinicModel.addRow(clinicData);

		}

	}

	public void updateDoctorModel() throws SQLException {

		// table i�ini bo�alt ve yeniden olustur
		DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);

		// bunu soylede yazabiliriz
		// ((DefaultTableModel) table.getModel()).setRowCount(0);// tablomuzu getir
		// i�ini bo�alt

		for (int i = 0; i < bashekimnesnesi.getDoctorList().size(); i++) {

			doctorData[0] = bashekimnesnesi.getDoctorList().get(i).getId();
			doctorData[1] = bashekimnesnesi.getDoctorList().get(i).getTcno();
			doctorData[2] = bashekimnesnesi.getDoctorList().get(i).getPassword();
			doctorData[3] = bashekimnesnesi.getDoctorList().get(i).getName();
			doctorModel.addRow(doctorData);

		}
	}
}
