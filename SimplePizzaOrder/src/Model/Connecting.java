package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Connecting {

	Connection con = null ;
	
	public Connection baglanDB() {
		
		try {
			
			this.con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/pizzadb?user=root&password=295017" );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return con;
		
	}
 
	
	
	
}