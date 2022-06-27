package Helper;

import javax.swing.JComboBox;

public class DoctorIdName {
	
	private int key_doctorId;
	private String value_doctorName;
	
	
	public DoctorIdName(int key_doctorId, String value_doctorName) {
		super();
		this.key_doctorId = key_doctorId;
		this.value_doctorName = value_doctorName;
	}


	public int getKey_doctorId() {
		return key_doctorId;
	}


	public String getValue_doctorName() {
		return value_doctorName;
	}


	public void setKey_doctorId(int key_doctorId) {
		this.key_doctorId = key_doctorId;
	}


	public void setValue_doctorName(String value_doctorName) {
		this.value_doctorName = value_doctorName;
	}
	// bunu unutursan COMBOBOX içinde hep rastgele karakter yazar
	@Override
	 public String toString(){
		 return this.value_doctorName;
	 }

  
	
	
}
