package Helper;
import java.sql.*;

public class DBConnection {

	/*
	 burada Conenction s�n�nf�dan bir nesne uretilir ve baslang�cta bu nesne null'a setlenir
ve bir tane DB ba�lant�s� sa�layan fonks�yon olusturulur ve bu fonsiyon bir Connection nesnesi dondurur

	 */
	
	Connection c = null; //connectio nesnesi
	
	public DBConnection() {}; // bu s�n�f�n bo� constructor'� d�r
	
	
	//DB ba�lant�s� sa�layan fonks�yon ve bu fonsiyon bir Connection nesnesi dondurur
	public Connection connDb() {
		try {
			this.c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/hospitaldb?user=root&password=295017");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c ;
	}
	
}
