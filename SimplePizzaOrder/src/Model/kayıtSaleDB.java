package Model; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class kayýtSaleDB {
	
	//bunlar sale tablosunun
	private int id;
	private String subtotal , balance , payment ;

	//bunlar sale_product tablosunun
	private int ID ;
	private int sales_id; // bunun oldugu yerlerde yukardaki id yi kullanmalýyým 
	private String product_name;
	private int price;
	private int quantity;
	private int total = 0 ;
	
	Connecting bag = new Connecting();//conecting sýnýfýndan bir nesne uretelim
	Connection conn = bag.baglanDB(); // ve bu nesne ile bu sýnýftaki baglanDB() metodunu cagýralým
	// boylece artýk DB ye baglanabiliyoruz  bunu da yeni bir Connection nesnesine atayalým 
	
	  
	
	public kayýtSaleDB(int id, String subtotal, String balance, String payment, int ID, int sales_id,
			String product_name, int price, int quantity, int total) { 
		this.id = id;
		this.subtotal = subtotal;
		this.balance = balance;
		this.payment = payment;
		this.ID = ID;
		this.sales_id = sales_id;  
		this.product_name = product_name;
		this.price = price;
		this.quantity = quantity;
		this.total = total;
	}
	
	public kayýtSaleDB( ) { }
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getSales_id() {
		return sales_id;
	}

	public void setSales_id(int sales_id) {
		this.sales_id = sales_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}




	Statement st = null;
	
 
	PreparedStatement prSt = null;
	
	public int addSatýsKayýt(String total , String paym , String balan ) throws SQLException{
		
		String query = "INSERT INTO sales (subtotal , payment , balance ) VALUES  ( ? , ? , ? ) ";
		int last_id = 0;
		
		try {
			
			st = conn.createStatement(); 
			prSt = conn.prepareStatement(query ,  Statement.RETURN_GENERATED_KEYS );//keyleri geri dondurebilmek için boyle tanýmlanýr
			prSt.setString( 1 , total );
			prSt.setString( 2 , paym  );
			prSt.setString( 3, balan    );
			prSt.executeUpdate();
			
			ResultSet rs  = prSt.getGeneratedKeys(); // bunu da  UNUTMA YAZ BURAYA
			
			// burada extra bir iþ yaparak dB e kayýt edilen siparisin id si nee?  bunu bulmak istedik
			while( rs.next() ) {
				
				last_id = rs.getInt( 1 );
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return last_id;
		
	}
	 
	public void addSaleProductKayýt( int sales_id , String product_name , int price , int quantity , int total ) {
		
		String query = "INSERT INTO sales_product (sales_id , product_name , price , quantity , total ) VALUES ( ? , ? , ? , ? , ? )  ";
		
		try {
			
			st = conn.createStatement();
			prSt = conn.prepareStatement(query);
			prSt.setInt(1, sales_id);
			prSt.setString(2, product_name);
			prSt.setInt(3, price);
			prSt.setInt(4, quantity);
			prSt.setInt(5, total);
			prSt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
 
	
	
}




