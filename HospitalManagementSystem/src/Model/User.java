package Model;

import Helper.DBConnection;

public class User {
	
	private int id;
	String tcno,name,password,type;
	DBConnection conn = new DBConnection(); // Bunu BashekimSAyfasý 'nda  DB teki doktorlarý cekmek için tanýmlýyoruz aslýnda bunu BasHekim.java da yapacaktýk
										// Lakin Userdan miras aldýgý için inheritance iþlei gerçekleþtirmek için burda tanýmladýk 
								// ve biz zaten baðlantý kurma sýnýfý olusturmustuk Helper package içinde burada da o sýnýfý çagýrdýk kullandýk yeniden
								// baðlantý kurmak yerine bu sýnýfý çagýrarak bunun üstünden baðlantý saðladýk
	
	public User(int id, String tcno, String password,String name,  String type) {
		super();
		this.id = id;
		this.tcno = tcno;
		this.name = name;
		this.password = password;
		this.type = type;
	}
	
	public User() {} // boþ constructor
	
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
