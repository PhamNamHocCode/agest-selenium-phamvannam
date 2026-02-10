package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import Constant.Constant;
import Guerrilla.GuerrillaHomePage;

public class TestBase {
//	GuerrillaHomePage guerrillaHomePage = new GuerrillaHomePage();
//	HomePage homePage = new HomePage();
//	RegisterPage registerPage = new RegisterPage();
//	LoginPage loginPage = new LoginPage();
//	BookTicketPage bookTicketPage = new BookTicketPage();
//	TimetablePage timetablePage = new TimetablePage();
//	FAQPage faqPage = new FAQPage();
//	

//	AssertionHelper assertionHelper  = new AssertionHelper();
//	TicketPricePage ticketPricePage = new TicketPricePage();
//	PreconditionHelper preconditionHelper = new PreconditionHelper();
	
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
