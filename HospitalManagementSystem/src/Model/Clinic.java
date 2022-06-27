package Model;

import java.sql.*;
import java.util.ArrayList;

import Helper.DBConnection;

public class Clinic {

	private int id;
	String name;

	public Clinic(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Clinic() {
		// TODO Auto-generated constructor stub
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	DBConnection con = new DBConnection();// sýnýftan bir nesne üretidiki içindeki metodu cagrýalbilsin
	Connection conn = con.connDb();

	Statement st = null;
	ResultSet rs = null;

	public ArrayList<Clinic> getClinicList() throws SQLException {

		ArrayList<Clinic> clinic_list = new ArrayList<>();

		Clinic klinikOBJ;// bu sýnýftan bir nesne tanýmladýk

		try {
			String query = " SELECT * FROM clinic  ";
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {

				klinikOBJ = new Clinic(rs.getInt("clinic_id"), rs.getString("clinic_name")); // tanýmladýgýmýz nesnemizi
																								// olsuturalým

				// veya
				/*
				 * klinikOBJ = new Clinic( ); klinikOBJ.setId(rs.getInt("clinic_id"));
				 * klinikOBJ.setName( rs.getString("clinic_name"));
				 */

				clinic_list.add(klinikOBJ);// listemize bu nesnelerimi ekledik

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return clinic_list;// array list imizi geri döndürdük
	}

	PreparedStatement prSt = null;

	// clinic ekleme metodu
	public boolean addClinic(String name) throws SQLException {

		String query = " INSERT INTO clinic " + " (clinic_name) VALUES " + "(?)";
		boolean key = false;
		try {

			st = conn.createStatement();
			prSt = conn.prepareStatement(query);// sorgu yu veriyoruz
			prSt.setString(1, name);
			prSt.executeUpdate();
			key = true;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;

	}

	// clinic silme metodu
	public boolean deleteClinic( int id ) throws SQLException {

		String query = " DELETE FROM clinic WHERE clinic_id = ? ";
		boolean key = false;
		try {

			st = conn.createStatement();
			prSt = conn.prepareStatement(query);// sorgu yu veriyoruz
			prSt.setInt(1, id);
			prSt.executeUpdate();
			key = true;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;

	}

	// secilen satýrdaki bilgiyi guncellemek için
	public boolean updateClinic(int id, String name) throws SQLException {

		String query = " UPDATE clinic SET clinic_name = ? WHERE clinic_id = ? ";
		boolean key = false;
		try {

			st = conn.createStatement();
			prSt = conn.prepareStatement(query);
			prSt.setString(1, name);
			prSt.setInt(2, id);
			prSt.executeUpdate();
			key = true;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;

	}

	public Clinic updateClinicNameSelectedRow(int id) {

		Clinic klinik = new Clinic();

		try {

			st = conn.createStatement();
			rs = st.executeQuery(" SELECT * FROM clinic WHERE clinic_id =" + id);
			while (rs.next()) {

				// klinik = new Clinic( rs.getInt("clinic_id") , rs.getString("clinic_name") );
				klinik.setId(rs.getInt("clinic_id"));
				klinik.setName(rs.getString("clinic_name"));

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return klinik;

	}


	// clinikteki doktorlarý listeler
	public ArrayList<User> getClinicDoctorList( int clinic_id ) throws SQLException {

		ArrayList<User> liste = new ArrayList<>();

		User obj;
		try {
			st = conn.createStatement();
	// yani þu clinic_id ye sahip olan ve w tablosunda doctor_id si u daki id ile ayný olanlarýn u da ki id , tc , name , type bilgisini getir
			//boylece geriye  doctor_id , user_tc , user_name , user_type verilerini donduren liste doner
			rs = st.executeQuery("SELECT u.id , u.tcno , u.password , u.Username, u.Usertype FROM worker w LEFT JOIN user u ON  w.doctor_id = u.id WHERE clinic_id = " + clinic_id); 
//þimdi worker tablosundaki polikliniklere ekli olan doktor bilgilerini çekebiliyoruz
			while (rs.next()) {
				obj = new User(rs.getInt("u.id"), rs.getString("u.tcno"), rs.getString("u.password"),
						rs.getString("u.Username"), rs.getString("u.Usertype"));
				liste.add(obj);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return liste;

	}
	
	
}
