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

public class BashekimSayfasý extends JFrame {

	static BasHekim bashekimnesnesi = new BasHekim(); // BasHekim sýnýfýndaki metodlarý kulanabilmek için bunu
														// tanýmlamak zorundayýz
	static Clinic kliniknesnesi = new Clinic(); // clinic sýnýfýnda ki metodlarýmý cagýrýp kullanabilmek için
	private JPanel contentPane;
	private JTextField fld_name;
	private JTextField fld_tc;
	private JTextField fld_sifre;
	private JTextField fld_id;
	private JTable table_doctor;

	/*
	 * artýk panelimizde doktorlarýmýzý liste þeklinde görüntüleyebileceðiz ama þuan
	 * sadece listeyi döndürdük görüntüleme kýsmýný henüz yapmadýk bunu için
	 * BashekimSayfasý na gelerek yani görüntülemenin yapýlacagý yere gelinir biz
	 * java.swing derslerinde table lara modeller sayesinde veri ekleyebilmeyi
	 * biliyorduk þimdi o yapýlarý kullanarak bu iþlemi gerçekleþtirelim : ilk önce
	 * bir DEfaultTableModel sýnýfýndan bir nesne üretilir ve balangýcta null a
	 * setlenir (bir tabloya veri eklemek için modellerden yarar) sonra bir Object[]
	 * sýnýfýndan nesne üretilir ve buda nul a setlenir buda datalarý, içine
	 * atacaðýmýz verileri alacak
	 */

	private DefaultTableModel doctorModel = null;// bir tabloya veri eklemek için modellerden yarar
	private Object[] doctorData = null; // buda datalarý, içine atacaðýmýz verileri alacak

	// CLÝNÝC için
	private JTextField fld_clinic_name;
	private JTable table_clinic;
	private DefaultTableModel clinicModel = null; // Poliklinik sayfa içini bu olusturur
	private Object[] clinicData = null; // clinicteki bilgileri içine atacagýmýz bilgileri tutacak
	private JPopupMenu clinicMenu;
	private JTable table_worker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimSayfasý frame = new BashekimSayfasý(bashekimnesnesi); // artýk yazabildik nesnemizi
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
	public BashekimSayfasý(BasHekim bashekimnesnesi) throws SQLException {

		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];// biz doktorlarý belirlerken DB den bütün bilgileri çekmek zorundayýz
												// lakin görüntülerken
		// zaten sadece doktorlar görüntülenecegi için bir daha type ý görüntülemeye
		// gerek yok (id,tcno,name,password) bunun için boyut 4 olur

		// þimdi hangi indiste hangi bilgi var ? onu belirtelim yani görüntüleme
		// sýralamasýný belirleyelim
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Tc No";
		colDoctorName[2] = "Sifre";
		colDoctorName[3] = "Ad Soyad";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for (int i = 0; i < bashekimnesnesi.getDoctorList().size(); i++) {

			doctorData[0] = bashekimnesnesi.getDoctorList().get(i).getId();// bashekim clasý userdan extends sedildigi için get() metodlarý taa userdan gelir
			doctorData[1] = bashekimnesnesi.getDoctorList().get(i).getTcno();
			doctorData[2] = bashekimnesnesi.getDoctorList().get(i).getPassword();
			doctorData[3] = bashekimnesnesi.getDoctorList().get(i).getName();
			doctorModel.addRow(doctorData);
		}

		setBackground(Color.WHITE);
		setTitle("Hastahane Yönetim Sistemi");
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

		JButton btnCýkýsYap = new JButton("\u00C7\u0131k\u0131\u015F yap");
		btnCýkýsYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//cýkýs yap deyýnce logýn sayfasýna geri donelim
				LoginSayfasý baslangýc = new LoginSayfasý();
				baslangýc.setVisible(true);
				dispose();
			}
		});
		btnCýkýsYap.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		btnCýkýsYap.setBounds(635, 14, 89, 23);
		contentPane.add(btnCýkýsYap);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(10, 66, 714, 384);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Doktor Yönetimi", null, panel, null);
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
							updateDoctorModel();// bu ekraný güncellemek için asagýda sonradan yazdýgýmýz metoddur
												// bunuda burda çagýrýrýz
							// ki DB e ekleme yapýnca DB f5 ile güncellenebiliyordu lakin ekran ancak böyle
							// güncellenir
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
					Helper.showMsg("Lutfen kaydý silinecek doktor seciniz!");
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

		// JTable da bir doktor sec ve secilen bu satýrýn id sini görüntüle
		// getSelectionModel : seçme iþleminin yapýlmasýný saglar
		// addListSelectionListener : hangi satýra týklandý onu algýlar
		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				// getValueAt(row,col) : satýr sutunundaki degeri getir
				// getSelectedRow() : seçilen satýr numarasýný döndürür
				try {
					fld_id.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());

				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});

		// bunu enson yapýyorumm silme ekleme güncelleme iþlemleriinden sonra
		// amac Jtable da herhengi bir satýr sutundaki bilgi deðiþtirilmiþse DB tede
		// deðiþsin
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
		colClinic[1] = "Poiklinik Adý";
		clinicModel.setColumnIdentifiers(colClinic);
		clinicData = new Object[2];
		for (int i = 0; i < kliniknesnesi.getClinicList().size(); i++) {

			clinicData[0] = kliniknesnesi.getClinicList().get(i).getId();
			clinicData[1] = kliniknesnesi.getClinicList().get(i).getName();
			clinicModel.addRow(clinicData);
		}

		// clinic panelimizi olsuturalým
		JPanel w_clinic_panel = new JPanel();
		w_clinic_panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Poliklinikler", null, w_clinic_panel, null);
		w_clinic_panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 257, 334);
		w_clinic_panel.add(scrollPane);

		clinicMenu = new JPopupMenu(); // popup menu muzu olusturduk
		JMenuItem updateMenu = new JMenuItem("Güncelle");// itemlarýmýz olusturduk
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);// ýtemlar popup menuye eklendi

		// þimdi bu ýtemlara týklandýgýný algýlayabilelim
		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// secilen satýrýn ýd sini bulalým
				int setID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());

				// þimdi secilen satýrdaki clinic bilgilerini dondurelim bunun için metodumuzu
				// cagýralým
				// ve bunu yenibir nesneye atayalým
				Clinic selectClinic = kliniknesnesi.updateClinicNameSelectedRow(setID);

				// þimdi sectiðimiz ogrenci bilgilerini diðer guý mize aktaralým ki orada görüp
				// düzenleyebilelim
				updateClinicGUI updateGuý = new updateClinicGUI(selectClinic);

				updateGuý.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // guý de exite basýnca kapamasýný saglar
				updateGuý.setVisible(true); // guý mizin içeriðini görüntulemek için

				// þimdi de Jtable içini güncellemek için bu guý ekranýnýn kapandýgýný algýlamak
				// lazým
				// bu guý penceresini algýlamak için de pencere iþlemlerini algýlayan bir
				// windowListener sýnýf kulllanalým

				updateGuý.addWindowListener(new WindowAdapter() {

					// pencerenin kapanmasýný algýlayan bu metod
					@Override
					public void windowClosed(WindowEvent e) {

						/*
						 * þimdi burada daha önceden olusturdugumuz Jtable içini guncelleyen metodumuzu
						 * cagýrýsak artýk bu guý miz kapanýnca JTable içerisi hemen guncellenecek
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

		// sil itemý iþlemleri
		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (Helper.confirm("sure")) {
					// silmek istedigimiz secilen satýrýn id si belirlenmelidir
					int setId = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());

					try {

						if (kliniknesnesi.deleteClinic(setId)) {
							Helper.showMsg("success");
							updateClinicModel(); // sildikten sonra Jtable guncellensýn
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
		table_clinic.setComponentPopupMenu(clinicMenu);// popup menuyu table a ekledýk
		// Jtable da mause hangi satýrda týklandý onu algýlayalým
		table_clinic.addMouseListener(new MouseAdapter() {
			/*
			 * Mouse listener için de MousePressed metodunu cagýrýyoruz ve bunu Override
			 * ediyoruz bunun nedeni JTable mouse nereye týkladý o noktanýn koordinatýný
			 * alarak orada hangi satýr hangi id var onu bulmak istiyoruz
			 */
			@Override
			public void mousePressed(MouseEvent e) {

				Point týklanýlan_nokta = e.getPoint();// noktayý bulmak içinse Point sýnýfý kulanýlýr
				/*
				 * þimdi bu table uzerinde nereye yani hangi satýra týklandýgýný algýlayan bir
				 * rowAtPoint metodu var ve bu metod mouse un týkladýgý noktayý parametre olarak
				 * alýr boylece biz bu metodu ve bu noktayý kullanarak seçilen satýrýmýzý yani
				 * üzerine týklanýlan satýrýmýzý bulabiliriz
				 */
				int selectedRow = table_clinic.rowAtPoint(týklanýlan_nokta); // üzerine týklanýlan satýr budur dedik
//þimdi de bu uzerine týklanýlan satýr aynýzamanda bizim seçtiðimiz satýr olmasý için setRowSelectionInterval 
//				metodunu çagýrýrýz bu metod parametre olarak iki satýr alýr

				table_clinic.setRowSelectionInterval(selectedRow, selectedRow); // artýk üzeirne týkladýgýmýz satýrýmýz
																				// sectiðimi satýrmýz oldu

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

		JLabel lbl_poliklinikAdý = new JLabel("Poliklinik Ad\u0131");
		lbl_poliklinikAdý.setBounds(277, 16, 155, 21);
		w_clinic_panel.add(lbl_poliklinikAdý);

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
		// doktorlar combobox içinde gosterileceði için bunlarý cek
		// doktorlarý DB ten ceken metod basHekim sýnýfýnda idi
		for (int i = 0; i < bashekimnesnesi.getDoctorList().size(); i++) {

			//// doktorlarý comboboxa ekle
			// comboBox.addItem( bashekimnesnesi.getDoctorList().get(i).getName() );// lakin
			//// ayný isimden bir suru doktor olabilir ayýrt edebilmek için id de lazým
			// ne yazýk ki bu metod 2 tane parametre almýyor
			// bunu saðlamak için yeni bir yapý kurmak gerekir

			// doktorlarýn id sini bilmek için içine nesne alan ve bu nesne de id - name
			// deðerleri bulunmalý
			// bunun için DoctorIdName metodumzu kulanalým
			comboBox.addItem(new DoctorIdName(bashekimnesnesi.getDoctorList().get(i).getId(),
					bashekimnesnesi.getDoctorList().get(i).getName()));

		}

		// bu metodu kurdugumuz key - value gercekten calýsýyomu diye kontrol etmek için
		// yazdýk bir onemý yok!
		comboBox.addActionListener(e -> { // bu iþaret pointer dýr bu yapý ise lamda yapýsýdýr

			JComboBox c = (JComboBox) e.getSource();
			DoctorIdName item = (DoctorIdName) c.getSelectedItem();
			System.out.println(item.getKey_doctorId() + " : " + item.getValue_doctorName());

		});

		w_clinic_panel.add(comboBox);
		
		JLabel lbl_poliklinikAdý_1 = new JLabel("Poliklinik Ad\u0131");
		lbl_poliklinikAdý_1.setBounds(277, 166, 155, 21);
		w_clinic_panel.add(lbl_poliklinikAdý_1);

		// worker model
		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "ID";
		colWorker[1] = "Poliklinik Adý";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];
		/*
		 * þimdi bunun içini burada doldurmayalým eðer burada doldurursak tum worker
		 * tablosunu soldaki jtable da gostermiþ oluruz biz soldaki jtable da sadece sað
		 * tarafta sectiðimiz polk. teki calýsan doktorlarý goruntulemek istiyoruz yani
		 * biz bir polk. secip sec butonuna basýnca bu jtable içi o polk. teki
		 * doctorlarý goruntuleyecek bunun için seç butonunun action listenerý
		 * içerisinde bu modelin içini dolduralým
		 */

		JButton btn_select_clinic = new JButton("Se\u00E7");
		btn_select_clinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// jtable da polklinik seçsin
				int selectRow = table_clinic.getSelectedRow();
				if (selectRow >= 0) {
					// secilen kliniðin id si ni bul
					int selectClinicId = Integer.parseInt(table_clinic.getValueAt(selectRow, 0).toString());
					/*
					 * ardýndan tanýmladýgýmýz modelimizin içini olusturalým oncelikle her yeni polk
					 * secildiðinde bu model içi deðiþecegi için ve bizde model içini deðiþtirirken
					 * guncellerken model içini önce bosaltýyorduk burada da oyle yapalým ki her
					 * yeni polk secildiðinde model içi de ona gore guncellensin
					 */
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0); // satýrlarý sildik yani içini boþalttýk

					try {
// biz bu model içinde poliklinklerdeki doktorlarý tutacagýz bunu da liste olarak donduren bir metod olarak tanýmlamýstýk
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
					Helper.showMsg("lutfen poliklinik seçin !");
				}

			}
		});

		btn_select_clinic.setBounds(277, 185, 155, 33);
		w_clinic_panel.add(btn_select_clinic);

		// worker tablosunu olusturur yani polkilinklere doktor eklemek için
		JButton btn_addWorker = new JButton("Ekle");
		btn_addWorker.setBounds(277, 296, 155, 33);
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// jtable da polklinik seçsin
				int selectRow = table_clinic.getSelectedRow();
				if (selectRow >= 0) {
					// secilen kliniðin id si ni bul
					int selectClinicId = Integer.parseInt(table_clinic.getModel().getValueAt(selectRow, 0).toString());
					// veya
					// int selectClinicId = Integer.parseInt(table_clinic.getValueAt(selectRow,
					// 0).toString()) ;

					// secilen doktorun id si ve name ini key-value seklinde getirmek için
					// DoctorIdName sýnýfýný kullanýyoruk
					// bu sýnýftan bir nesne üretelim
					DoctorIdName doctorItem = (DoctorIdName) comboBox.getSelectedItem();

					// artýk hem pol id si hemde doctor id-name ini biliyoruz þimdi polikliniðe
					// doktoru ekleyebiliriz
					// BasHekim sýnýfýndaki polk niðe doctor ekleme metodumuzu yani addWorker
					// cagýrýrsak
					try {

						boolean control = bashekimnesnesi.addWorker(doctorItem.getKey_doctorId(), selectClinicId);
						
						if (control) {
							Helper.showMsg("success");
							
							// eðer polk.niðe doktor ekleme basarýlý ise
							//model olusturarak model içini guncelleyelim ki 
							//polk secip comboboxta doctor ekle deyince jtable da bu poliklinik içindeki doktorrlara bunuda eklediðini ve göstersin  
							//yukarda ki modelimizi ve workerData objectimizi kullanalým
							
							//herseferinde model içini guncelleyeceðimiz için model içini bosaltýp tekar olusturmak gerek
							DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
							clearModel.setColumnCount(0);
							
							// ekleme yapýlacak polk içindeki doctorlarý tutan metodumuzu cagýralým
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

		// table içini boþalt ve yeniden olustur
		DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);

		// bunu soylede yazabiliriz
		// ((DefaultTableModel) table.getModel()).setRowCount(0);// tablomuzu getir
		// içini boþalt

		for (int i = 0; i < bashekimnesnesi.getDoctorList().size(); i++) {

			doctorData[0] = bashekimnesnesi.getDoctorList().get(i).getId();
			doctorData[1] = bashekimnesnesi.getDoctorList().get(i).getTcno();
			doctorData[2] = bashekimnesnesi.getDoctorList().get(i).getPassword();
			doctorData[3] = bashekimnesnesi.getDoctorList().get(i).getName();
			doctorModel.addRow(doctorData);

		}
	}
}
