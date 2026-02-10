package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;
import Constant.PageMenu;

public class LogoutTest extends TestBase{
	
	@Test
	public void TC06() {
		HomePage homePage = new HomePage();
		LoginPage loginPage = new LoginPage();
		FAQPage faqPage = new FAQPage();
		
		System.out.println("TC06: Verify that user is redirected to Home page after logging out ");
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2: Login with valid Email and Password");
		loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		homePage = loginPage.login(Constant.VALID_USERNAME, Constant.VALID_PASSWORD);
		
		System.out.println("Step 3:  Click on 'FAQ' tab");
		faqPage = homePage.gotoPage(PageMenu.FAQ, FAQPage.class);
		
		System.out.println("Step 4: Click on 'Log out' tab");
		homePage = homePage.gotoPage(PageMenu.LOGOUT, HomePage.class);
		
		System.out.println("VP: Home page displays.");
		Assert.assertTrue(homePage.isHomePageDisplayed(),"Home page is not displays");
		
		System.out.println("VP: \"Log out\" tab is disappeared.");
		Assert.assertFalse(homePage.isMenuDisplayed(PageMenu.LOGOUT),"\"Log out\" tab is not disappeared");
	}
	
}
