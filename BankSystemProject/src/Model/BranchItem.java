package Model;

public class BranchItem {
	
	private int key_branch_id;
	private String value_branch_name;
	
	
	 
	public BranchItem(int key_branch_id, String value_branch_name) {
		super();
		this.key_branch_id = key_branch_id;
		this.value_branch_name = value_branch_name;
	}

	
	public int getKey_branch_id() {
		return key_branch_id;
	}




	public void setKey_branch_id(int key_branch_id) {
		this.key_branch_id = key_branch_id;
	}




	public String getValue_branch_name() {
		return value_branch_name;
	}




	public void setValue_branch_name(String value_branch_name) {
		this.value_branch_name = value_branch_name;
	}





	// bunu unutursan COMBOBOX içinde hep rastgele karakter yazar

	@Override
	 public String toString(){
		 return this.value_branch_name;
	 }

}
