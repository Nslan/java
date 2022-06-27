package Model;

import java.sql.*;
import java.util.ArrayList;

import Helper.DBConnection;

public class Randevu {
	
	private int id , doctorID , hastaID ;
	private String doctorName , hastaName , randevuDate ;
	
	DBConnection conn = new DBConnection();
	Statement st = null ;
	ResultSet rs = null ;
	PreparedStatement prSt = null ;
	
	public Randevu(int id, int doctorID, int hastaID, String doctorName, String hastaName, String randevuDate) {
		super();
		this.id = id;
		this.doctorID = doctorID;
		this.hastaID = hastaID;
		this.doctorName = doctorName;
		this.hastaName = hastaName;
		this.randevuDate = randevuDate;
	}
	
	public Randevu() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

	public int getHastaID() {
		return hastaID;
	}

	public void setHastaID(int hastaID) {
		this.hastaID = hastaID;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getHastaName() {
		return hastaName;
	}

	public void setHastaName(String hastaName) {
		this.hastaName = hastaName;
	}

	public String getRandevuDate() {
		return randevuDate;
	}

	public void setRandevuDate(String randevuDate) {
		this.randevuDate = randevuDate;
	}
	
	// hastalarýn randevularýný getirmek için
	public ArrayList< Randevu > getRandevuHastaList( int hasta_id ) throws SQLException {

		ArrayList<Randevu> Randevu_list = new ArrayList<>();

		Randevu randevular;// bu sýnýftan bir nesne tanýmladýk
		
		Connection con = conn.connDb();

		try {
			String query = " SELECT * FROM randevu WHERE hasta_id = " + hasta_id ;
			st = con.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {

				randevular = new Randevu(rs.getInt("id"), rs.getInt("doctor_id"),  rs.getInt("hasta_id") , rs.getString("doctor_name")  , rs.getString("hasta_name") , rs.getString("randevu_date")); 
				// tanýmladýgýmýz nesnemizi  olsuturalým

				// veya
				/*
				 * randevular = new Randevu( ); randevular.setId(rs.getInt("Randevu_id"));
				 * randevular.setHastaName( rs.getString("hasta_name"));
				 */

				Randevu_list.add(randevular);// listemize bu nesnelerimi ekledik

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return Randevu_list;// array list imizi geri döndürdük
	}
 
	// doctorlarýn randevularýný getirmek için
		public ArrayList< Randevu > getRandevuDoctorList( int doctorId ) throws SQLException {

			ArrayList<Randevu> Randevu_list = new ArrayList<>();

			Randevu randevular;// bu sýnýftan bir nesne tanýmladýk
			
			Connection con = conn.connDb();

			try {
				String query = " SELECT * FROM randevu WHERE doctor_id = " + doctorId ;
				st = con.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {

					randevular = new Randevu(rs.getInt("id"), rs.getInt("doctor_id"),  rs.getInt("hasta_id") , rs.getString("doctor_name")  , rs.getString("hasta_name") , rs.getString("randevu_date")); 
					// tanýmladýgýmýz nesnemizi  olsuturalým

					// veya
					/*
					 * randevular = new Randevu( ); randevular.setId(rs.getInt("Randevu_id"));
					 * randevular.setHastaName( rs.getString("hasta_name"));
					 */

					Randevu_list.add(randevular);// listemize bu nesnelerimi ekledik

				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			return Randevu_list;// array list imizi geri döndürdük
		}

	
	
}
