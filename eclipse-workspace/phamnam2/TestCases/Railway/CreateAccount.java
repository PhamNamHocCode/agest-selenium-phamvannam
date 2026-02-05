package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import Constant.PageMenu;
import Guerrilla.GuerrillaHomePage;

public class CreateAccount extends TestBase{
	
	@Test
	public void TC07() {
		GuerrillaHomePage guerrillaHomePage = new GuerrillaHomePage();
		guerrillaHomePage.open();
		
		String registerEmail = guerrillaHomePage.createNewEmail();
		String registerPassword = Utilities.generateRandomPassword();
		String registerPip = Utilities.generateRandomPIP();
		
		RegisterAccount account = new RegisterAccount(registerEmail, registerPassword, registerPip);
		
		System.out.println("TC06: Verify that user is redirected to Home page after logging out ");
		System.out.println("Pre-condition: an actived account is existing");
		RegisterPage registerPage = PreconditionHelper.createActivedAccount(account.email, account.password, account.pip);
		
		HomePage homePage = new HomePage();
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2:  Click on \"Register\" tab");
		homePage.gotoPage(PageMenu.REGISTER, RegisterPage.class);
		
		System.out.println("Step 3: Enter information of the created account in Pre-condition");
		System.out.println("Step 4: Click on \"Register\" button");
		registerPage.registerNewAccount(account.email, account.password, account.pip);
	
	}
}
