package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.PageMenu;
import Constant.Constant;

public class CreateAccount extends TestBase{
	
	@Test
	public void TC07() {
		System.out.println("TC07: Verify that user is redirected to Home page after logging out ");
		System.out.println("Pre-condition: an actived account is existing");
		homePage.open();
		
		RegisterAccount account = PreconditionHelper.createRandomAccount();
		account = PreconditionHelper.createActivedAccount(account, false, null, null);
		
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2:  Click on \"Register\" tab");
		registerPage = homePage.gotoPage(PageMenu.REGISTER, RegisterPage.class);
		
		System.out.println("Step 3: Enter information of the created account in Pre-condition");
		System.out.println("Step 4: Click on \"Register\" button");
		registerPage.registerNewAccount(account.getEmail(), account.getPassword(), account.getPip(), false, null);
		
		String actualMsg = registerPage.getTextLblMsgGeneralError();
		String expectedMsg = "This email address is already in use.";
		
		System.out.println("VP: Error message \"This email address is already in use.\" displays above the form.");
		Assert.assertEquals(actualMsg, expectedMsg.trim(), "Error message is not displayed as expected");
	}
	
	@Test
	public void TC08() {
		System.out.println("TC08: Verify that user can't create account while password and PID fields are empty");
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2:  Click on \"Register\" tab");
		homePage.gotoPage(PageMenu.REGISTER, RegisterPage.class);
		
		System.out.println("Step 3: Enter valid email address and leave other fields empty");
		System.out.println("Step 4: Click on \"Register\" button");
		registerPage = registerPage.registerWithOnlyValidEmail(Constant.VALID_USERNAME);
		
		String actualMsgGeneralError = registerPage.getTextLblMsgGeneralError();
		String expectedMsgGeneralError = "There're errors in the form. Please correct the errors and try again.";
		System.out.println("VP: Message \"There're errors in the form. Please correct the errors and try again.\" appears above the form.");
		Assert.assertEquals(actualMsgGeneralError.replaceFirst("\\.$", ""), expectedMsgGeneralError.trim().replaceFirst("\\.$", ""), "Error message is not displayed as expected");
		
		String actualMsgPasswordError = registerPage.getTextLblMsgErrorPassword();
		String expectedMsgPasswordError = "Invalid password length.";
		System.out.println("VP: Next to password fields, error message \"Invalid password length.\" displays");
		Assert.assertEquals(actualMsgPasswordError.replaceFirst("\\.$", ""), expectedMsgPasswordError.trim().replaceFirst("\\.$", ""), "Error message is not displayed as expected");
		
		String actualMsgPipError = registerPage.getTextLblMsgErrorPip();
		String expectedMsgPipError = "Invalid ID length.";
		System.out.println("VP: Next to PID field, error message \"Invalid ID length.\" displays");
		Assert.assertEquals(actualMsgPipError.replaceFirst("\\.$", ""), expectedMsgPipError.trim().replaceFirst("\\.$", ""), "Error message is not displayed as expected");
	}
	
	@Test
	public void TC09() {
		System.out.println("TC09: Verify that user create and activate account");
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2:  Click on \"Create an account\"");
		System.out.println("Step 3:  Enter valid information into all fields");
		System.out.println("Step 4: Click on \"Register\" button");
		System.out.println("Step 5: Get email information (webmail address, mailbox and password) and navigate to that webmail ");
		System.out.println("Step 6: Login to the mailbox");
		System.out.println("Step 7: Open email with subject containing \"Please confirm your account\"  and the email of the new account at step 3");
		System.out.println("Step 8: Click on the activate link");
		RegisterAccount account = PreconditionHelper.createRandomAccount();
		String expectedMsgThankyou = "Thank you for registering your account";
		String expectedMsgConfirmed = "Registration Confirmed! You can now log in to the site.";
		System.out.println("VP: \"Thank you for registering your account\" is shown"
				+ "Redirect to Railways page and message \"Registration Confirmed! You can now log in to the site\" is shown");
		
		account = PreconditionHelper.createActivedAccount(account, true, expectedMsgThankyou, expectedMsgConfirmed);

	}
}
