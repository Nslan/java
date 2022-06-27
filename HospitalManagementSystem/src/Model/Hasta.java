package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.Statement;

import Helper.*;

public class Hasta extends User {

	Statement st = null;
	ResultSet rs = null;
	Connection con = conn.connDb();
	PreparedStatement prSt = null;

	public Hasta(int id, String tcno, String password, String name, String type) {
		super(id, tcno, password, name, type);
	}

	public Hasta() {
		// TODO Auto-generated constructor stub
	}

	public boolean kayýt(String tcno, String password, String Username) throws SQLException   {

		int key = 0;

		boolean duplicate = false;

		String query = " INSERT INTO user  ( tcno , password , Username , Usertype ) VALUES  ( ? , ? , ? , ?  ) ";

		try {

			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE tcno = '" + tcno + "'");

			while (rs.next()) {

				duplicate = true;

				Helper.showMsg("Bu T.C Numarasýna ait bir Kayýt bulunmaktadýr");

				break;

			}

			if (!duplicate) {

				prSt = con.prepareStatement(query);
				prSt.setString(1, tcno);
				prSt.setString(2, password);
				prSt.setString(3, Username);
				prSt.setString(4, "hasta");
				prSt.executeUpdate();

				key = 1;
			}
		} catch (SQLException E) {
			E.printStackTrace();
		}

		if (key == 1)
			return true;
		else
			return false;

	}

	public boolean ekleRandevu(int doctorId, int hastaId, String doctorName, String hastaName, String randevuTarih) throws SQLException  {

		int key = 0;
 
		String query = " INSERT INTO randevu  ( doctor_id , doctor_name , hasta_id , hasta_name , randevu_date ) VALUES  ( ? , ? , ? , ? , ?  ) ";

		try {

			prSt = con.prepareStatement(query);
			prSt.setInt(1, doctorId );
			prSt.setString(2, doctorName );
			prSt.setInt(3, hastaId );
			prSt.setString(4, hastaName );
			prSt.setString( 5, randevuTarih );
			prSt.executeUpdate();

			key = 1;

		} catch (SQLException E) {
			E.printStackTrace();
		}

		if (key == 1)
			return true;
		else
			return false;

	}
	
	
	//secilen calýsma saatlerini pasif yap
	public boolean updateWHourStatus( int doctorId , String whourDate ) throws SQLException {

		int key = 0;

		//doctor id si ve workhourdate i þu olan satýrýn statusunu guncelle der
		String query = " UPDATE  doctor_workhour  SET status = ? WHERE doctor_id = ? AND workhourdate = ? ";

		try {

			prSt = con.prepareStatement(query);
			prSt.setString(1, "Pasif" );
			prSt.setInt(2, doctorId );
			prSt.setString(3, whourDate );
			prSt.executeUpdate();

			key = 1;

		} catch (SQLException E) {
			E.printStackTrace();
		}

		if (key == 1)
			return true;
		else
			return false;

	}
	
	//randevu iptal etmek için : DB teki randevu tablosundan secilen randevularý siler
	public boolean iptalRandevu(int ID ) throws SQLException {

		int key = 0;
 
		String query = " DELETE FROM randevu  WHERE id = ? ";

		try {

			prSt = con.prepareStatement(query);
			prSt.setInt(1, ID );
			prSt.executeUpdate();

			key = 1;

		} catch (SQLException E) {
			E.printStackTrace();
		}

		if (key == 1)
			return true;
		else
			return false;

	}
	
	//randevu tablosunda secilen doctorun bilgilerini getir
	public int bul(int ID) throws SQLException {
 
		int doctorID = 0 ;
		try {
			
			String query = " SELECT * FROM randevu WHERE id = '" + ID + "'";
			st = con.createStatement();
			rs = st.executeQuery(query);

			 while( rs.next() ) {
				 doctorID = rs.getInt("doctor_id");
			 }

		} catch (SQLException E) {
			E.printStackTrace();
		}

		 return doctorID;

	}
	
	//secilen calýsma saatlerini aktif yap
		public boolean iptalWHourStatus( int doctorId , String whourDate ) throws SQLException {

			int key = 0;
			//doctor id si ve workhourdate i þu olan satýrýn statusunu guncelle der
			String query = " UPDATE  doctor_workhour  SET status = ? WHERE doctor_id = ? AND workhourdate = ? ";

			try {

				prSt = con.prepareStatement(query);
				prSt.setString(1, "Aktif" );
				prSt.setInt(2, doctorId );
				prSt.setString(3, whourDate );
				prSt.executeUpdate();

				key = 1;

			} catch (SQLException E) {
				E.printStackTrace();
			}

			if (key == 1)
				return true;
			else
				return false;

		}
	

}
