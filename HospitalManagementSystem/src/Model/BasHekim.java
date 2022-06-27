package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BasHekim extends User {

	Connection con = conn.connDb(); // User ' a eklediðimiz DBConnection sýnýfýnýn nesnesini (conn) kullanarak
	// DBConnection sýnýfýndaki metodu çaðýrdýk bu metod ise bize baðlantýyý
	// saðlayacak

	Statement st = null;
	ResultSet rs = null;

	PreparedStatement prSt = null;

	public BasHekim(int id, String tcno, String password, String name, String type) {
		super(id, tcno, password, name, type);//bu user clasýndan miras aldýgýndan biz eger getID getName vs kullanmak istersek bir daha burada tanýmlamaya gerek yok bunlar yukardan gelir
	}

	// bos contructor

	public BasHekim() {
		// TODO Auto-generated constructor stub
	}

	// bu bir ArrayList tipide deðer döndüren metod ve bu ArrayList User tipinde
	// nesne tutacak
	// yani ArrayListten oluþturulan bir liste ile geriye User tipte verileri tutan
	// liste döndürülecek
	public ArrayList<User> getDoctorList() throws SQLException {

		ArrayList<User> liste = new ArrayList<>();

		User obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE Usertype = 'Doktor' "); // DB deki türü Doctor olanlarý getir

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

	// DB doktor ekleme yapmak için
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

	// DB doktor silme yapmak için
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

	// Jtable da herhangi bir satýr sutun daki bir bilgiyi güncellemek için

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

	// polikliniklere doktor eklemek için bu metod kullanýcagýz
	// id si verilen polikliniðe id si verilen doctor u ekle
	public boolean addWorker(int doctor_id, int clinic_id) throws SQLException {

		String query = " INSERT INTO worker " + "( doctor_id , clinic_id ) VALUES " + "( ? , ? )";
		boolean key = false;

		int count = 0; // ayný calýsaný bir den cok kez ekleme yapmamak için 

		try {

			st = con.createStatement();
			// eðer bu id ler worker tablosunda daha önceden varsa count arttýr ve ekleme
			// yapma
			rs = st.executeQuery("SELECT * FROM worker WHERE clinic_id =" + clinic_id + " AND doctor_id =" + doctor_id);

			while (rs.next()) {
				count++;
			}
			if (count == 0) {
				// count = 0 sa demek ki id ler worker tablosunda daha önceden yok o halde
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
	
	// clinikteki doktorlarý listeler
	public ArrayList<User> getClinicDoctorList( int clinic_id ) throws SQLException {

		ArrayList<User> liste = new ArrayList<>();

		User obj;
		try {
			st = con.createStatement();
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
