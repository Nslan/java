package Helper;
import java.sql.*;

public class DBConnection {

	/*
	 burada Conenction sýnýnfýdan bir nesne uretilir ve baslangýcta bu nesne null'a setlenir
ve bir tane DB baðlantýsý saðlayan fonksýyon olusturulur ve bu fonsiyon bir Connection nesnesi dondurur

	 */
	
	Connection c = null; //connectio nesnesi
	
	public DBConnection() {}; // bu sýnýfýn boþ constructor'ý dýr
	
	
	//DB baðlantýsý saðlayan fonksýyon ve bu fonsiyon bir Connection nesnesi dondurur
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
