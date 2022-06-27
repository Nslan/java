package Model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connecting {
	
	Connection con = null;
	
	public Connecting() {}
	
	public Connection baglaDB() {
	
		try {
			
			this.con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/newbankdb?user=root&password=295017" );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	

}
