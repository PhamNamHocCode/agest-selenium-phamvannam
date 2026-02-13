package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.PageMenu;
import Guerrilla.GuerrillaHomePage;
import Constant.Constant;

public class CreateAccount extends TestBase{
	
	@Test
	public void TC07() {
		HomePage homePage = new HomePage();
		RegisterPage registerPage = new RegisterPage();
		RegisterAccount account = PreconditionHelper.generateRandomRegisterAccount();
		String expectedMsg = "This email address is already in use.";

		System.out.println("TC07: User can't create account with an already in-use email");
		System.out.println("Pre-condition: an actived account is existing");
		account = PreconditionHelper.createAnActiveAccount(account);
		
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage = homePage.open();
		
		System.out.println("Step 2:  Click on \"Register\" tab");
		registerPage  = homePage.gotoPage(PageMenu.REGISTER, RegisterPage.class);
		
		System.out.println("Step 3: Enter information of the created account in Pre-condition");
		System.out.println("Step 4: Click on \"Register\" button");
		registerPage = registerPage.registerNewAccount(account);
		
		System.out.println("VP: Error message \"This email address is already in use.\" displays above the form.");
		String actualMsg = registerPage.geMsgGeneralError();
		Assert.assertEquals(actualMsg, expectedMsg.trim(), "Error message is not displayed as expected");
	}
	
	@Test
	public void TC08() {
		HomePage homePage = new HomePage();
		RegisterPage registerPage = new RegisterPage();
		String expectedMsgGeneralError = "There're errors in the form. Please correct the errors and try again.";
		String expectedMsgPasswordError = "Invalid password length.";
		String expectedMsgPipError = "Invalid ID length.";
		String message = "Error message is not displayed as expected";
		RegisterAccount account = new RegisterAccount(Constant.VALID_USERNAME, null);
				
		System.out.println("TC08: Verify that user can't create account while password and PID fields are empty");
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage = homePage.open();
		
		System.out.println("Step 2:  Click on \"Register\" tab");
		registerPage = homePage.gotoPage(PageMenu.REGISTER, RegisterPage.class);
		
		System.out.println("Step 3: Enter valid email address and leave other fields empty");
		System.out.println("Step 4: Click on \"Register\" button");
		registerPage = registerPage.registerNewAccount(account);
		
		System.out.println("VP: Message \"There're errors in the form. Please correct the errors and try again.\" appears above the form.");
		String actualMsgGeneralError = registerPage.geMsgGeneralError();
		Assert.assertEquals(actualMsgGeneralError.replaceFirst("\\.$", ""), expectedMsgGeneralError.trim().replaceFirst("\\.$", ""), message);
		
		System.out.println("VP: Next to password fields, error message \"Invalid password length.\" displays");
		String actualMsgPasswordError = registerPage.getMsgErrorPassword();
		Assert.assertEquals(actualMsgPasswordError.replaceFirst("\\.$", ""), expectedMsgPasswordError.trim().replaceFirst("\\.$", ""), message);
		
		System.out.println("VP: Next to PID field, error message \"Invalid ID length.\" displays");
		String actualMsgPipError = registerPage.getMsgErrorPip();
		Assert.assertEquals(actualMsgPipError.replaceFirst("\\.$", ""), expectedMsgPipError.trim().replaceFirst("\\.$", ""), message);
	}
	
	@Test
	public void TC09() {
		HomePage homePage = new HomePage();
		RegisterPage registerPage = new RegisterPage();
		GuerrillaHomePage guerrillaHomePage = new GuerrillaHomePage();
		RegisterAccount account = PreconditionHelper.generateRandomRegisterAccount();
		String expectedMsgThankyou = "Thank you for registering your account";
		String expectedMsgConfirmed = "Registration Confirmed! You can now log in to the site.";

		System.out.println("TC09: Verify that user create and activate account");
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage = homePage.open();
		
		System.out.println("VP: Home page is shown with guide containing href \"create an account\" to \"Register\" page");
		Assert.assertTrue(
		        homePage.isCreateAccountLinkDisplayed(),
		        "The create account link is not displayed as expected"
		);		
		
		System.out.println("Step 2:  Click on \"Create an account\"");
		registerPage = homePage.clickCreateAccountLink();
		
		System.out.println("Step 3:  Enter valid information into all fields");
		System.out.println("Step 4: Click on \"Register\" button");
		System.out.println("Step 5: Get email information (webmail address, mailbox and password) and navigate to that webmail ");
		System.out.println("Step 6: Login to the mailbox");
		System.out.println("Step 7: Open email with subject containing \"Please confirm your account\"  and the email of the new account at step 3");
		System.out.println("Step 8: Click on the activate link");
		account = PreconditionHelper.createAnEmail(account);
		registerPage = registerPage.registerNewAccount(account);
		
		System.out.println("VP: \"Thank you for registering your account\" is shown");
		String actualMsgThankyou = registerPage.getMsgThankyou();
		Assert.assertEquals(actualMsgThankyou, expectedMsgThankyou, "Message is not displayed as expected");

		guerrillaHomePage.open();
		guerrillaHomePage.confirmRegistrationEmail(account.getEmail());
		
		System.out.println("VP: Redirect to Railways page and message \"Registration Confirmed! You can now log in to the site\" is shown");
		String actualMsgConfirmed = registerPage.getMsgRegistrationConfirmed();
		Assert.assertEquals(actualMsgConfirmed, expectedMsgConfirmed, "Message is not displayed as expected");
	}
}
