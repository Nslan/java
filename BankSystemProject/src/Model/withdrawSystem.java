package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class withdrawSystem {

	public withdrawSystem() {
		// TODO Auto-generated constructor stub
	}

	Connecting conn = new Connecting();
	Connection bag = conn.baglaDB();

	Statement st = null;
	PreparedStatement prSt = null;
	ResultSet rs = null;

	public boolean saved(String accId, String custId, String Date, int Balance, int Withdraw) throws SQLException {

		String query = "INSERT INTO withdraw( acc_id , cust_id , date , balance , withdraw ) VALUES ( ? , ? , ? , ? , ? ) ";

		int key = 0;
		try {

			st = bag.createStatement();
			prSt = bag.prepareStatement(query);
			prSt.setString(1, accId);
			prSt.setString(2, custId);
			prSt.setString(3, Date);
			prSt.setInt(4, Balance);
			prSt.setInt(5, Withdraw);
			prSt.executeUpdate();

			key = 1;

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (key == 1)
			return true;
		else
			return false;

	}

	public ArrayList find(String acc_no) throws SQLException {

		ArrayList listem = new ArrayList<>();

		String query = "SELECT c.cust_id  , c.first_name , c.last_name , a.balance FROM customer c , account a WHERE c.cust_id = a.customer_id AND a.account_id = ? ";

		try {

			st = bag.createStatement();
			prSt = bag.prepareStatement(query);
			prSt.setString(1, acc_no);// soru iþareti yerine dýsardan gelen acc_id yi koy
			rs = prSt.executeQuery();// resultset nesnesi ve preparedStatemeneti birlikte kullanmak

			if (rs.next() == false) {
				JOptionPane.showMessageDialog(null, "Account not found!");
			} else {

				listem.add(0, rs.getString("c.cust_id"));
				listem.add(1, rs.getString("c.first_name"));
				listem.add(2, rs.getString("c.last_name"));
				listem.add(3, rs.getString("a.balance"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listem;

	}

	//balance degerini guncelleemek için
	public boolean updateSaved( String accno , int amount ) {
		
		//account tablosunudaki acc_id ye sahip olan balance ý þu þekilde update et : balance = balance - amount 
		String query = "UPDATE account set balance = balance-  ? WHERE account_id = ? ";
		int key = 0 ;
		
		try {
			
			st = bag.createStatement();
			prSt = bag.prepareStatement(query);
			prSt.setInt( 1 , amount );//1. soru iþsareti amount
			prSt.setString( 2 , accno ); // 2. soru iþareti accno yu alsýn
			prSt.executeUpdate();
			key = 1 ; 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if( key == 1 ) return true;
		else return false;
	}
	
	//update edilmiþ balance degerini getirmek için
	public String getUpdateBalanceValue( String accno ) {
		
		String query = "SELECT balance FROM account  WHERE account_id = ? ";
		String str = null;
		
		try {
			
			st = bag.createStatement();
			prSt = bag.prepareStatement(query);
			prSt.setString( 1 , accno ); // 1. soru iþareti accno yu alsýn
			
			rs = prSt.executeQuery();
			
			rs.next();
			str = rs.getString("balance");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return str;
	}
	
	
}
