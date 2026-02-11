package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.PageMenu;
import Guerrilla.GuerrillaHomePage;

public class ResetPassword extends TestBase{
	
	@Test
	public void TC10() {
		HomePage homePage = new HomePage();
		LoginPage loginPage = new LoginPage();
		GuerrillaHomePage guerrillaHomePage = new GuerrillaHomePage();
		System.out.println("TC10: Verify that reset password shows error if the new password is same as current");
		System.out.println("Pre-condition: an actived account is existing");
		homePage.open();
		RegisterAccount account = PreconditionHelper.generateRandomRegisterAccount();
		account = PreconditionHelper.createAnAccount(account);
		PreconditionHelper.activateAccountViaEmail(account);

		System.out.println("Step 1: Navigate to QA Railway Login page");
		homePage.open();
		loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		
		System.out.println("Step 2: Click on \"Forgot Password page\" link");
		System.out.println("Step 3: Enter the email address of the activated account"); 
		System.out.println("Step 4: Click on \"Send Instructions\" button"); 
		System.out.println("Step 5: Login to the mailbox (the same mailbox when creating account) "); 
		System.out.println("Step 6: Open email with subject contaning \"Please reset your password\" and the email of the account at step 3"); 
		System.out.println("Step 7: Click on reset link"); 
		loginPage = loginPage.forgotPassword(account);
		guerrillaHomePage = guerrillaHomePage.open();
		loginPage = guerrillaHomePage.confirmForgotPasswordEmail(account.getEmail());
		
		System.out.println("VP: Redirect to Railways page and Form \"Password Change Form\" is shown with the reset password token");
		Assert.assertTrue(loginPage.isResetPasswordTokenDisplayed(), "\"Password Change Form\" is not shown with the reset password token");
		
		System.out.println("Step 8: Input same password into 2 fields \"new password\" and \"confirm password\""); 
		System.out.println("Step 9: Click Reset Password");
		loginPage = loginPage.enterResetPassword(account, account.getPassword());
		
		System.out.println("VP: The new password cannot be the same with the current password\\\" is shown");
		String expectedGenralMsg = "The new password cannot be the same with the current password";
		String actualMsg = loginPage.getForgotPasswordGeneralMsg();
		Assert.assertEquals(actualMsg, expectedGenralMsg, "The message is not displayed as expected");
		
	}
	
	@Test
	public void TC11() {
		HomePage homePage = new HomePage();
		LoginPage loginPage = new LoginPage();
		GuerrillaHomePage guerrillaHomePage = new GuerrillaHomePage();
		String confirmPassword = Utilities.generateRandomPassword(5);
		
		System.out.println("TC11: Verify that reset password shows error if the new password and confirm password doesn't match");
		System.out.println("Pre-condition: an actived account is existing");
		homePage.open();
		RegisterAccount account = PreconditionHelper.generateRandomRegisterAccount();
		account = PreconditionHelper.createAnAccount(account);
		PreconditionHelper.activateAccountViaEmail(account);
		
		System.out.println("Step 1: Navigate to QA Railway Login page");
		homePage.open();
		homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		
		System.out.println("Step 2: Click on \"Forgot Password page\" link");
		System.out.println("Step 3: Enter the email address of the activated account"); 
		System.out.println("Step 4: Click on \"Send Instructions\" button"); 
		System.out.println("Step 5: Login to the mailbox (the same mailbox when creating account)"); 
		System.out.println("Step 6: Open email with subject contaning \"Please reset your password\" and the email of the account at step 3"); 
		System.out.println("Step 7: Click on reset link"); 
		loginPage = loginPage.forgotPassword(account);
		guerrillaHomePage = guerrillaHomePage.open();
		loginPage = guerrillaHomePage.confirmForgotPasswordEmail(account.getEmail());
		
		System.out.println("VP: Redirect to Railways page and Form \"Password Change Form\" is shown with the reset password token");  
		Assert.assertTrue(loginPage.isResetPasswordTokenDisplayed(), "\"Password Change Form\" is not shown with the reset password token");
		
		System.out.println("Step 8: Input different input into 2 fields  \"new password\" and \"confirm password\""); 
		System.out.println("Step 9: Click Reset Password");
		loginPage = loginPage.enterResetPassword(account, confirmPassword);
		
		System.out.println("VP: Error message \"Could not reset password. Please correct the errors and try again.\" displays above the form.\r\n"
				+ "Error message \"The password confirmation did not match the new password.\" displays next to the confirm password field.");
		String expectedGenralMsg = "Could not reset password. Please correct the errors and try again.";
		String expectedPasswordErrorMsg = "The password confirmation did not match the new password.";
		String actualMsgGenralMsg = loginPage.getForgotPasswordGeneralMsg();
		String actualPasswordErrorMsg = loginPage.getForgotConfirmPasswordMsg();
		String message = "The message is not displayed as expected";
		
		Assert.assertEquals(actualMsgGenralMsg, expectedGenralMsg, message);
		Assert.assertEquals(actualPasswordErrorMsg, expectedPasswordErrorMsg, message);
	}
	 


}
