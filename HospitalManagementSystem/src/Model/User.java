package Model;

import Helper.DBConnection;

public class User {
	
	private int id;
	String tcno,name,password,type;
	DBConnection conn = new DBConnection(); // Bunu BashekimSAyfas� 'nda  DB teki doktorlar� cekmek i�in tan�ml�yoruz asl�nda bunu BasHekim.java da yapacakt�k
										// Lakin Userdan miras ald�g� i�in inheritance i�lei ger�ekle�tirmek i�in burda tan�mlad�k 
								// ve biz zaten ba�lant� kurma s�n�f� olusturmustuk Helper package i�inde burada da o s�n�f� �ag�rd�k kulland�k yeniden
								// ba�lant� kurmak yerine bu s�n�f� �ag�rarak bunun �st�nden ba�lant� sa�lad�k
	
	public User(int id, String tcno, String password,String name,  String type) {
		super();
		this.id = id;
		this.tcno = tcno;
		this.name = name;
		this.password = password;
		this.type = type;
	}
	
	public User() {} // bo� constructor
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTcno() {
		return tcno;
	}
	public void setTcno(String tcno) {
		this.tcno = tcno;
	}
	
	public void setName(String name) {
		this.name = name;
		
	}public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	 
	
	
}
