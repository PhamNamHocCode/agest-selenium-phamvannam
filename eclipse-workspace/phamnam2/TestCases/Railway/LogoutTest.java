package Railway;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;

public class LogoutTest {
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Pre-condition");
		
		io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
		
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("Post-condition");
		
		Constant.WEBDRIVER.quit();
		
	}
	
	@Test
	public void TC06() {
		System.out.println("TC06: Verify that user is redirected to Home page after logging out ");
		HomePage homePage = new HomePage();
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		
	}
	
}
