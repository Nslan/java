package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	//option pane butonlar�n� turkce olarak yazma bunu yukardaki iki metod i�inde �ag�rmak yeterli
	public static void changeNameButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "iptal");
		UIManager.put("OptionPane.noButtonText", "Hay�r");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
		
	}
	
	public static void showMsg(String str) {
		
		String msg;
		changeNameButtonText();//bunu bu metoddaki butonlar� turkceye cevirdik
		switch(str) {
			case "fill":
				msg = "L�tfen t�m alanlar� doldurunuz !" ;
				break;
			case "success":
				msg = "i�lem ba�ar�l� !";
				break;
			case "error":
				msg = "Bir hata ger�ekle�ti";
				break;
			default:
				msg = str;
		}
		
		JOptionPane.showMessageDialog(null, msg , "Mesaj" , JOptionPane.INFORMATION_MESSAGE );
		
	}
	
	//bu metodu silme i�lemini onaylay�p onaylamad�g�n� izin vermek i�in yapt�k
	public static boolean confirm( String str) {
		String msg;
		changeNameButtonText();//bu metodtaki butonlar� turkceye cevirdik
		switch( str ) {
		case "sure": 
				msg = "Bu i�elemi ger�eklestirmek istiyormusun ? "; 
			break;
		 default:
			 msg = str ;
			 break;
		}
	  
		int result = JOptionPane.showConfirmDialog(null, msg , "Dikkat !", JOptionPane.YES_NO_OPTION);
	
		if(result == 0) return true;
		else return false;
	}
	
	

}