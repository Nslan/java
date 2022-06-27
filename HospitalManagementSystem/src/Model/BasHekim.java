package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BasHekim extends User {

	Connection con = conn.connDb(); // User ' a ekledi�imiz DBConnection s�n�f�n�n nesnesini (conn) kullanarak
	// DBConnection s�n�f�ndaki metodu �a��rd�k bu metod ise bize ba�lant�y�
	// sa�layacak

	Statement st = null;
	ResultSet rs = null;

	PreparedStatement prSt = null;

	public BasHekim(int id, String tcno, String password, String name, String type) {
		super(id, tcno, password, name, type);//bu user clas�ndan miras ald�g�ndan biz eger getID getName vs kullanmak istersek bir daha burada tan�mlamaya gerek yok bunlar yukardan gelir
	}

	// bos contructor

	public BasHekim() {
		// TODO Auto-generated constructor stub
	}

	// bu bir ArrayList tipide de�er d�nd�ren metod ve bu ArrayList User tipinde
	// nesne tutacak
	// yani ArrayListten olu�turulan bir liste ile geriye User tipte verileri tutan
	// liste d�nd�r�lecek
	public ArrayList<User> getDoctorList() throws SQLException {

		ArrayList<User> liste = new ArrayList<>();

		User obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE Usertype = 'Doktor' "); // DB deki t�r� Doctor olanlar� getir

			while (rs.next()) {
				obj = new User(rs.getInt("id"), rs.getString("tcno"), rs.getString("password"),
						rs.getString("Username"), rs.getString("Usertype"));
				liste.add(obj);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return liste;

	}

	// DB doktor ekleme yapmak i�in
	public boolean addDoctor(String tcno, String password, String Username) throws SQLException {

		boolean key = false;
		try {
			String query = "INSERT INTO user " + "(tcno,password,Username,Usertype) VALUES" + "(?,?,?,?)";
			st = con.createStatement();
			prSt = con.prepareStatement(query);
			prSt.setString(1, tcno);
			prSt.setString(2, password);
			prSt.setString(3, Username);
			prSt.setString(4, "Doktor");
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

	// DB doktor silme yapmak i�in
	public boolean deleteDoctor(int id) throws SQLException {
		String query = "DELETE FROM user  WHERE  id = ? ";
		boolean key = false;
		try {

			st = con.createStatement();
			prSt = con.prepareStatement(query);
			prSt.setInt(1, id);
			prSt.executeUpdate();
			key = true;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		if (key) {
			return true;
		} else {
			return false;
		}

	}

	// Jtable da herhangi bir sat�r sutun daki bir bilgiyi g�ncellemek i�in

	public boolean updateDoctor(int id, String tcno, String password, String Username) throws SQLException {
		String query = "UPDATE user SET   tcno = ?  , password = ? , Username = ?  WHERE id = ? ";
		boolean key = false;
		try {

			st = con.createStatement();
			prSt = con.prepareStatement(query);
			prSt.setString(1, tcno);
			prSt.setString(2, password);
			prSt.setString(3, Username);
			prSt.setInt(4, id);
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

	// polikliniklere doktor eklemek i�in bu metod kullan�cag�z
	// id si verilen poliklini�e id si verilen doctor u ekle
	public boolean addWorker(int doctor_id, int clinic_id) throws SQLException {

		String query = " INSERT INTO worker " + "( doctor_id , clinic_id ) VALUES " + "( ? , ? )";
		boolean key = false;

		int count = 0; // ayn� cal�san� bir den cok kez ekleme yapmamak i�in 

		try {

			st = con.createStatement();
			// e�er bu id ler worker tablosunda daha �nceden varsa count artt�r ve ekleme
			// yapma
			rs = st.executeQuery("SELECT * FROM worker WHERE clinic_id =" + clinic_id + " AND doctor_id =" + doctor_id);

			while (rs.next()) {
				count++;
			}
			if (count == 0) {
				// count = 0 sa demek ki id ler worker tablosunda daha �nceden yok o halde
				// worker a ekle
				prSt = con.prepareStatement(query);
				prSt.setInt(1, doctor_id);
				prSt.setInt(2, clinic_id);
				prSt.executeUpdate();

			}
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
	
	// clinikteki doktorlar� listeler
	public ArrayList<User> getClinicDoctorList( int clinic_id ) throws SQLException {

		ArrayList<User> liste = new ArrayList<>();

		User obj;
		try {
			st = con.createStatement();
	// yani �u clinic_id ye sahip olan ve w tablosunda doctor_id si u daki id ile ayn� olanlar�n u da ki id , tc , name , type bilgisini getir
			//boylece geriye  doctor_id , user_tc , user_name , user_type verilerini donduren liste doner
			rs = st.executeQuery("SELECT u.id , u.tcno , u.password , u.Username, u.Usertype FROM worker w LEFT JOIN user u ON  w.doctor_id = u.id WHERE clinic_id = " + clinic_id); 
//�imdi worker tablosundaki polikliniklere ekli olan doktor bilgilerini �ekebiliyoruz
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
