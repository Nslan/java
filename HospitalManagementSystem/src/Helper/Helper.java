package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	//option pane butonlarýný turkce olarak yazma bunu yukardaki iki metod içinde çagýrmak yeterli
	public static void changeNameButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "iptal");
		UIManager.put("OptionPane.noButtonText", "Hayýr");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
		
	}
	
	public static void showMsg(String str) {
		
		String msg;
		changeNameButtonText();//bunu bu metoddaki butonlarý turkceye cevirdik
		switch(str) {
			case "fill":
				msg = "Lütfen tüm alanlarý doldurunuz !" ;
				break;
			case "success":
				msg = "iþlem baþarýlý !";
				break;
			case "error":
				msg = "Bir hata gerçekleþti";
				break;
			default:
				msg = str;
		}
		
		JOptionPane.showMessageDialog(null, msg , "Mesaj" , JOptionPane.INFORMATION_MESSAGE );
		
	}
	
	//bu metodu silme iþlemini onaylayýp onaylamadýgýný izin vermek için yaptýk
	public static boolean confirm( String str) {
		String msg;
		changeNameButtonText();//bu metodtaki butonlarý turkceye cevirdik
		switch( str ) {
		case "sure": 
				msg = "Bu iþelemi gerçeklestirmek istiyormusun ? "; 
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