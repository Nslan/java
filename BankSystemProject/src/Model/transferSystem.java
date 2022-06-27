package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class transferSystem {

	public transferSystem() {
		// TODO Auto-generated constructor stub
	}

	Connecting conn = new Connecting();
	Connection bag = conn.baglaDB();

	Statement st = null;
	PreparedStatement prSt = null;
	ResultSet rs = null;

	
	//gonderici bilgileri
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

				listem.add(0, rs.getString("a.balance"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listem;

	}

	public boolean saved(String f_acc , String to_acc , int Amount ) throws SQLException {

		String query = "INSERT INTO transfer( f_account , to_account , amount ) VALUES ( ? ,  ? , ? ) ";

		int key = 0;
		try {

			st = bag.createStatement();
			prSt = bag.prepareStatement(query);
			prSt.setString(1, f_acc  );
			prSt.setString(2, to_acc );
			prSt.setInt( 3, Amount );
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

	//Gonderenin balance degerini guncelleemek için
	public boolean updateSender(String f_acc, int amount) {

		// account tablosunudaki acc_id ye sahip olan balance ý þu þekilde update et :
		// balance = balance + amount
		String query = "UPDATE account set balance = balance-  ? WHERE account_id = ? ";
		int key = 0;

		try {

			st = bag.createStatement();
			prSt = bag.prepareStatement(query);
			prSt.setInt(1, amount);// 1. soru iþsareti amount
			prSt.setString(2, f_acc); // 2. soru iþareti accno yu alsýn
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

	//gonderici bilgileri
	public boolean findReceiver(String acc_no) throws SQLException {
 

		String query = "SELECT c.cust_id   FROM customer c , account a WHERE c.cust_id = a.customer_id AND a.account_id = ? ";

		int key = 0;
		
		try {

			st = bag.createStatement();
			prSt = bag.prepareStatement(query);
			prSt.setString(1, acc_no);// soru iþareti yerine dýsardan gelen acc_id yi koy
			rs = prSt.executeQuery();// resultset nesnesi ve preparedStatemeneti birlikte kullanmak

			if (rs.next() == false) {
				JOptionPane.showMessageDialog(null, "Account not found!");
				
				key = 0 ;
				
			} else {

				JOptionPane.showMessageDialog(null, acc_no +  " Account found!");
				 key = 1 ;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if ( key == 0 ) return false;
		else return true; 

	}
	 
	//Alan kisinin balance degerini guncelleemek için
		public boolean updateReceiver( String toAccount , int amount ) {

			// account tablosunudaki acc_id ye sahip olan balance ý þu þekilde update et :
			// balance = balance + amount
			String query = "UPDATE account set balance = balance+  ? WHERE account_id = ? ";
			int key = 0;

			try {

				st = bag.createStatement();
				prSt = bag.prepareStatement(query);
				prSt.setInt(1, amount );// 1. soru iþsareti amount
				prSt.setString(2, toAccount ); // 2. soru iþareti accno yu alsýn
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
	 
	
}
