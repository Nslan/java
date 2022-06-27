package Model;

import java.sql.*;
import java.util.ArrayList;

import Helper.DBConnection;

public class WorkHour {

	private int id , doctor_id ;
	private String doctor_name , workhour_date , status ;

	DBConnection con = new DBConnection();// sýnýftan bir nesne üretidiki içindeki metodu cagrýalbilsin
	Statement st = null ;
	ResultSet rs = null ;
	PreparedStatement prSt = null;
	
	
	
	public WorkHour(int id, int doctor_id, String doctor_name, String workhour_date, String status) {
		this.id = id;
		this.doctor_id = doctor_id;
		this.doctor_name = doctor_name;
		this.workhour_date = workhour_date;
		this.status = status;
	}
	
	
	public WorkHour() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getDoctor_id() {
		return doctor_id;
	}


	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}


	public String getDoctor_name() {
		return doctor_name;
	}


	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}


	public String getWorkhour_date() {
		return this.workhour_date;
	}


	public void setWorkhour_date(String workhour_date) {
		this.workhour_date = workhour_date;
	}


	public String getStatus() {
		return this.status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
 
	 
	// hasta sistmee girip doktor secince doctorlarýn calýsma saatlerini göruntulemek için bu saatleri geri dondurelim
	public ArrayList< WorkHour > getDoctorWorkHourList( int doctor_id ) throws SQLException {
		
		String query = "SELECT * FROM doctor_workhour WHERE status ='Aktif' AND doctor_id = " + doctor_id ; 
		
		ArrayList< WorkHour >  workHourlist = new ArrayList<>(); 
		
		WorkHour doktor;
		
		try {
			
			Connection conn = con.connDb();
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
			while( rs.next()  ) {
				
				doktor = new WorkHour( rs.getInt("id") , rs.getInt("doctor_id") , rs.getString("doctor_name") , rs.getString("workhourdate") ,  rs.getString("status") );
				workHourlist.add(doktor);
			}
				
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		return workHourlist;
		
	}
	
	
 
	
 
}




