package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ConcurrentModificationException;

import javax.swing.JOptionPane;

public class accountSystem {
	
	private int id , balance ;
	private String account_id , customer_id , account_type ;
	
	public accountSystem(int id, int balance, String account_id, String customer_id, String account_type) {
		super();
		this.id = id;
		this.balance = balance;
		this.account_id = account_id;
		this.customer_id = customer_id;
		this.account_type = account_type;
	}

	public accountSystem() {
		// TODO Auto-generated constructor stub
	}
	
	  
	Connecting BAG = new Connecting();
	Connection conn = BAG.baglaDB();
	
	Statement st = null;
	PreparedStatement prSt = null;
	ResultSet rs = null ;
	
	public String find( String cust_id ) {
		
		String query = "SELECT * FROM customer WHERE cust_id = ?";
		
		String firstname = null;
		
		try {
			
			st = conn.createStatement();
			prSt = conn.prepareStatement(query);
			prSt.setString( 1 , cust_id );
			rs = prSt.executeQuery();// resultset nesnesi ve preparedStatemeneti birlikte kullanmak
			
			if( rs.next() == false ) {
				
				JOptionPane.showMessageDialog( null , "Customer no not found" ,  "Mesaj" , JOptionPane.INFORMATION_MESSAGE );
				
		   }else {
			   
			   firstname = rs.getString("first_name");
			   
		   }
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return firstname;
	}

	
	
	public String autoID() throws SQLException {

		String str = null; 

		try {

			st = conn.createStatement();
			rs = st.executeQuery("select Max(account_id) from account ");

			rs.next();
			rs.getString("Max(account_id)");

			if (rs.getString("Max(account_id)") != null) {

				// DB teki cust_id = CS...  þeklinde bunun 2 nolu indexinden itibaren al inetegera cevir bunu arttýr
				int count = Integer.parseInt( rs.getString("Max(account_id)").substring( 2, rs.getString("Max(account_id)").length() )   );
				count++;
				
				str  = "A" + String.format("%04d", count );
		 	
				

			} else {
				str = "A0001";
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;

	}
	
	
	
	
	
	public boolean saved( String account_id , String customer_id , String account_type , int balance ) {
		
		String query = "INSERT INTO account ( account_id , customer_id , account_type , balance ) VALUES ( ?,?,?,? )";
		
		int key = 0;
		
		try {
			
			st = conn.createStatement();
			prSt = conn.prepareStatement(query);
			prSt.setString(1, account_id );
			prSt.setString(2, customer_id );
			prSt.setString(3, account_type );
			prSt.setInt(4, balance);
			prSt.executeUpdate();
			key = 1 ;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if( key == 1)  return true;
		else  return false;
		 
		
	}
	
	
	
	
}
