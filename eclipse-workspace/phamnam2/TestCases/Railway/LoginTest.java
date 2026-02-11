package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;
import Constant.PageMenu;

public class LoginTest extends TestBase {
	
	@Test
	public void TC01() {
		HomePage homePage = new HomePage();
		LoginPage loginPage = new LoginPage();
		
		System.out.println("TC01: Verify that user can log into Railway with valid username and password");
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2: Click on 'Login' tab");
		loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);

		System.out.println("Step 3: Enter valid Email and Password");
		System.out.println("Step 4: Click on 'Login' button");
		System.out.println("VP: User is logged into Railway. Welcome user message is displayed.");
		String actualMsg = loginPage.login(Constant.VALID_USERNAME, Constant.VALID_PASSWORD).getWelcomeMsg();
		String expectedMsg = "Welcome " + Constant.VALID_USERNAME;
		Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
	}
	
	@Test
	public void TC02() {
		HomePage homePage = new HomePage();
		LoginPage loginPage = new LoginPage();
		System.out.println("TC02: Verify that user cannot login with blank 'Username' textbox");
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2: Click on 'Login' tab");
		loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		
		System.out.println("Step 3: User doesn't type any words into 'Username' textbox but enter valid information into 'Password' textbox ");
		System.out.println("Step 4: Click on 'Login' button");
		loginPage = loginPage.login(null, Constant.VALID_PASSWORD);
		
		System.out.println("VP: User can't login and message \"There was a problem with your login and/or errors exist in your form. \" appears.");
		String actualMsg = loginPage.getLoginErrorMsg();
		String expectedMsg = "There was a problem with your login and/or errors exist in your form. ";
		Assert.assertEquals(actualMsg, expectedMsg.trim(), "Error message is not displayed as expected");
	}
	
	@Test
	public void TC03() {
		HomePage homePage = new HomePage();
		LoginPage loginPage = new LoginPage();
		System.out.println("TC03: Verify that user cannot log into Railway with invalid password ");
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2: Click on 'Login' tab");
		loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		
		System.out.println("Step 3: Enter valid Email and invalid Password");
		System.out.println("Step 4: Click on 'Login' button");
		loginPage = loginPage.login(null, Constant.VALID_PASSWORD);
		
		System.out.println("VP: Error message \"There was a problem with your login and/or errors exist in your form.\" is displayed");
		String actualMsg = loginPage.getLoginErrorMsg();
		String expectedMsg = "There was a problem with your login and/or errors exist in your form.";
		Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
	}
	
	@Test
	public void TC04() {
		HomePage homePage = new HomePage();
		LoginPage loginPage = new LoginPage();
		System.out.println("TC04: Verify that system shows message when user enters wrong password many times");
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2: Click on 'Login' tab");
		loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		
		System.out.println("Step 3: Enter valid information into 'Username' textbox except 'Password' textbox.");
		System.out.println("Step 4: Click on 'Login' button");
		System.out.println("Step 5: Repeat step 3 and 4 three more times");
		loginPage = loginPage.login(Constant.VALID_USERNAME, null);
		
		System.out.println("VP: \"Invalid username or password. Please try again\" is shown");
		String actualMsg = loginPage.getLoginErrorMsg();
		String expectedMsg = "Invalid username or password. Please try again";
		Assert.assertEquals(actualMsg, expectedMsg.trim(), "Error message is not displayed as expected");
		
		for (int i = 2; i <= 4; i++) {
			loginPage = loginPage.login(Constant.VALID_USERNAME, null);
			actualMsg = loginPage.getLoginErrorMsg();
		}
		System.out.println("VP: User can't login and message \"You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.\" appears.");
		expectedMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
		
		Assert.assertEquals(actualMsg, expectedMsg.trim(), "Error message is not displayed as expected");
	}
	
	@Test
	public void TC05() {
		HomePage homePage = new HomePage();
		LoginPage loginPage = new LoginPage();
		RegisterPage registerPage = new RegisterPage();
		
		System.out.println("TC05: Verify that user can't login with an account hasn't been activated");
		System.out.println("Pre-condition: a not-active account is existing");
		homePage.open();
		RegisterAccount account = PreconditionHelper.generateRandomRegisterAccount();
		account = PreconditionHelper.createUnactiveAccout(account);
		registerPage = registerPage.registerNewAccount(account);

		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2: Click on 'Login' tab");
		loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		
		System.out.println("Step 3: Enter username and password of account hasn't been activated.");
		System.out.println("Step 4: Click on 'Login' button");
		loginPage = loginPage.login(account.getEmail(), account.getPassword());
		
		System.out.println("VP: User can't login and message \"Invalid username or password. Please try again.\" appears.");
		String actualMsg = loginPage.getLoginErrorMsg();
		String expectedMsg = "Invalid username or password. Please try again.";
		Assert.assertEquals(actualMsg, expectedMsg.trim(), "Error message is not displayed as expected");
	}
	
}
