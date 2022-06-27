package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CustomerSystem {

	private int id, phone;
	private String cust_id, first_name, last_name, street, city, branch;

	public CustomerSystem(int id, int phone, String cust_id, String first_name, String last_name, String street,
			String city, String branch) {
		this.id = id;
		this.phone = phone;
		this.cust_id = cust_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.street = street;
		this.city = city;
		this.branch = branch;
	}

	public CustomerSystem() {
	}

	Connecting bag = new Connecting();
	Connection conn = bag.baglaDB();

	Statement st = null;
	PreparedStatement prSt = null;
	ResultSet rs = null;

	public String autoID() throws SQLException {

		String str = null; 

		try {

			st = conn.createStatement();
			rs = st.executeQuery("select Max(cust_id) from customer ");

			rs.next();
			rs.getString("Max(cust_id)");

			if (rs.getString("Max(cust_id)") != null) {

				// DB teki cust_id = CS...  þeklinde bunun 2 nolu indexinden itibaren al inetegera cevir bunu arttýr
				int count = Integer.parseInt( rs.getString("Max(cust_id)").substring( 2, rs.getString("Max(cust_id)").length() )   );
				count++;
				
				str  = "CS" + String.format("%03d", count );
		 	
				

			} else {
				str = "CS001";
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;

	}

	 
	
	
	public ArrayList branchItem() {

		ArrayList liste = new ArrayList<>();

		try {

			st = conn.createStatement();
			rs = st.executeQuery("select * from branch ");

			while (rs.next()) {

				liste.add(rs.getString("branch_name"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return liste;

	}

	public boolean saved(String cust_id, String first_name, String last_name, String street, String city, String branch,
			int phone) {

		String query = "INSERT INTO customer ( cust_id , first_name , last_name , street , city , branch , phone ) VALUES ( ? , ? , ? , ? , ? , ? , ? )";

		int key = 0;
		int count = 0;

		try {

			while (rs.next()) {
				count += 1;
				JFrame pencere = new JFrame();
				JOptionPane.showMessageDialog(pencere, "aynýsý var", "dikkat", JOptionPane.INFORMATION_MESSAGE);

			}

			if (count == 0) {

				st = conn.createStatement();
				prSt = conn.prepareStatement(query); 
				prSt.setString(1, cust_id);
				prSt.setString(2, first_name);
				prSt.setString(3, last_name);
				prSt.setString(4, street);
				prSt.setString(5, city);
				prSt.setString(6, branch);
				prSt.setInt(7, phone);
				prSt.executeUpdate();

				key = 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (key == 1) return true;
		else return false;
		
	}

}
