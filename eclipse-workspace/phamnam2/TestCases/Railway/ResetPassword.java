package Railway;

import org.testng.annotations.Test;

import Common.Utilities;
import Constant.PageMenu;

public class ResetPassword extends TestBase{
	
	@Test
	public void TC10() {
		System.out.println("TC10: Verify that reset password shows error if the new password is same as current");
		System.out.println("Pre-condition: an actived account is existing");
		
		HomePage homePage = new HomePage();
		homePage.open();
		
		String registerEmail = Utilities.generateRandomEmail();
		String registerPassword = Utilities.generateRandomPassword();
		String registerPip = Utilities.generateRandomPIP();
		RegisterAccount account = new RegisterAccount(registerEmail, registerPassword, registerPip);
		
		account = PreconditionHelper.createActivedAccount(account, false, "", "");
		
		System.out.println("Step 1: Navigate to QA Railway Login page");
		homePage.open();
		homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		
		System.out.println("Step 2: Click on \"Forgot Password page\" link");
		System.out.println("Step 3: Enter the email address of the activated account"); 
		System.out.println("Step 4: Click on \"Send Instructions\" button"); 
		System.out.println("Step 5: Login to the mailbox (the same mailbox when creating account) "); 
		System.out.println("Step 6: Open email with subject contaning \"Please reset your password\" and the email of the account at step 3"); 
		System.out.println("Step 7: Click on reset link"); 
		System.out.println("Step 8: Input same password into 2 fields  \"new password\" and \"confirm password\""); 
		System.out.println("Step 9: Click Reset Password");
		String expectedMsg = "The new password cannot be the same with the current password";
		LoginPage loginPage = new LoginPage();
		loginPage.forgotPassword(account.getEmail(), account.getPassword(), true, expectedMsg);
	}
	 


}
