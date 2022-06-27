package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Doctor extends User { //user ýn bilgilerini kullanacagýmýz için

	public Doctor(int id, String tcno, String password, String name, String type) {
		super(id, tcno, password, name, type);
		// TODO Auto-generated constructor stub
	}

	public Doctor() {
		super();
	}

	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement prSt = null;

	public boolean addDoctorWorkHour(int doctor_id, String doctor_name, String workhourdate) throws SQLException {

		int key = 0;
		int count = 0;

		String query = "INSERT INTO doctor_workhour (doctor_id, doctor_name , workhourdate) VALUES  ( ?,?,? ) ";

		try {
					
			st = con.createStatement();  
			rs = st.executeQuery("SELECT * FROM doctor_workhour WHERE status = 'aktif' AND doctor_id = " + doctor_id + " AND workhourdate = '" + workhourdate + "' ");
			// secilen doctorun o tarihte
																						// aktif mi pasif mi yani o
																						// tarih te randevu alýnabilirmi
																						// ? alýnmaz mý?
			while (rs.next()) {
				count += 1;
				JFrame pencere = new JFrame();
				JOptionPane.showMessageDialog(pencere, "ayný tarih", "dikkat", JOptionPane.INFORMATION_MESSAGE);
				 
			}

			if (count == 0) {

				prSt = con.prepareStatement(query);
				prSt.setInt(1, doctor_id);
				prSt.setString(2, doctor_name);
				prSt.setString(3, workhourdate);
				prSt.executeUpdate();
				key = 1;
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (key == 1) return true;
		else return false;

	}
	
	// work hour sýnýfýndan nesne almalýyýzki DB teki doctor_workhour bilgilerini bu nesneye atayarak cekelim
	public ArrayList< WorkHour > getWorkHourList( int doctor_id ) throws SQLException{
		
		ArrayList< WorkHour > liste = new ArrayList<>();
		
		WorkHour whnesnesi;
		
		try {
			
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM doctor_workhour WHERE status ='Aktif' AND doctor_id = " + doctor_id ); 
			while( rs.next() ) {
				
				whnesnesi = new WorkHour( rs.getInt( "id" ) , rs.getInt("doctor_id") , rs.getString("doctor_name")  , rs.getString("workhourdate") , rs.getString("status" ));
				
				liste.add( whnesnesi );
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return liste;
	}
	
	
	// jtabledakileri silmek için
	
	public boolean deleteWhour( int id ) throws SQLException {

		String query = " DELETE FROM doctor_workhour WHERE id = ? ";
		boolean key = false;
		try {

			st = con.createStatement();
			prSt = con.prepareStatement(query);// sorgu yu veriyoruz
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
	
	//DB te ki randevu table da randevularý iptal etmek için
	public boolean iptalRandevu( int ID ) throws SQLException{
		
		int key = 0;
		
		String query = "DELETE FROM randevu WHERE id = ? ";
		
		try {
			
			prSt = con.prepareStatement(query);
			prSt.setInt( 1 , ID);
			prSt.executeUpdate();
			
			key = 1 ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if( key == 1 ) {
			return true;
		}else {
			return false;
		}
		
	}
	
	
	
	
	
	
}
