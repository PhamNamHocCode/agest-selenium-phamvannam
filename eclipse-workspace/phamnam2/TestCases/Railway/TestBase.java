package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Constant.Constant;

public class TestBase {
	HomePage homePage = new HomePage();
	BookTicketPage bookTicketPage = new BookTicketPage();
	TimetablePage timetablePage = new TimetablePage();
	FAQPage faqPage = new FAQPage();
	RegisterPage registerPage = new RegisterPage();
	LoginPage loginPage = new LoginPage();
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Pre-condition");
		
		Constant.WEBDRIVER = new ChromeDriver();
		Constant.WEBDRIVER.manage().window().maximize();
		
	}
	
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("Post-condition");
		
		Constant.WEBDRIVER.quit();
	}
}
