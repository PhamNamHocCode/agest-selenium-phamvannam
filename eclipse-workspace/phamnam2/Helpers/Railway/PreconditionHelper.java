package Railway;

import Common.Utilities;
import Constant.Constant;
import Constant.PageMenu;
import Guerrilla.GuerrillaHomePage;

public class PreconditionHelper {
	
	public static RegisterAccount createActivedAccount(RegisterAccount account, Boolean isCheckLabel, String expectedMsgThankyou, String expectedMsgConfirmed) {
		HomePage homePage = new HomePage();
		RegisterPage registerPage = new RegisterPage();
		GuerrillaHomePage guerrillaHomePage = new GuerrillaHomePage();
		String railwayHanlde = Constant.WEBDRIVER.getWindowHandle();
		
		Utilities.openUrlInNewTab(Constant.GUERRILLA_URL);
		account.setEmail(guerrillaHomePage.createNewEmail(account.getEmail()));
		
		Constant.WEBDRIVER.switchTo().window(railwayHanlde);
		homePage.getLinkCreateAccount().click();
		registerPage = registerPage.registerNewAccount(account.getEmail(), account.getPassword(), account.getPip(), isCheckLabel, expectedMsgThankyou);
		
		guerrillaHomePage.open();
		registerPage = guerrillaHomePage.confirmRegistrationEmail(account.getEmail(), isCheckLabel, expectedMsgConfirmed);
		
		return account;
	}
	
	public static RegisterAccount createUnactiveAccout(RegisterAccount account, Boolean isCheck, String expectedMsgThankyou) {
		RegisterPage registerPage = new RegisterPage();
		String railwayHanlde = Constant.WEBDRIVER.getWindowHandle();
		
		Utilities.openUrlInNewTab(Constant.GUERRILLA_URL);
		GuerrillaHomePage guerrillaHomePage = new GuerrillaHomePage();
		account.setEmail(guerrillaHomePage.createNewEmail(account.getEmail()));

		Constant.WEBDRIVER.switchTo().window(railwayHanlde);
		registerPage.gotoPage(PageMenu.REGISTER, RegisterPage.class);
		registerPage = registerPage.registerNewAccount(account.getEmail(), account.getPassword(), account.getPip(), isCheck, expectedMsgThankyou);
		
		return account;
	}
	
	public static RegisterAccount createRandomAccount() {
		return new RegisterAccount(
			Utilities.generateRandomEmail(5),
			Utilities.generateRandomPassword(5),
			Utilities.generateRandomPIP(5)
		);
	}
	
	
	
}
