package Railway;

import Common.Utilities;
import Constant.Constant;
import Constant.PageMenu;
import Guerrilla.GuerrillaHomePage;

public class PreconditionHelper {
	
	public static RegisterPage createActivedAccount(String newEmail, String newPassword, String newPip) {
		GuerrillaHomePage guerrillaHomePage = new GuerrillaHomePage();
		
		HomePage homePage = new HomePage();
		homePage.open();
		
		
		
		RegisterPage registerPage = new RegisterPage();
		registerPage.gotoPage(PageMenu.REGISTER, RegisterPage.class);
		
		
		registerPage = registerPage.registerNewAccount(newEmail, newPassword, newPip);
		
		guerrillaHomePage.confirmNewEmail(newEmail);
		
		return new RegisterPage();
	}
	
}
