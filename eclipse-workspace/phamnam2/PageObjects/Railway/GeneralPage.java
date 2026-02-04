package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class GeneralPage {
	
	// Locators
	private final By tabLogin = By.xpath("//div[@id='menu']//a[contains(@href, 'Login')]");
	private final By tabLogout = By.xpath("//div[@id='menu']//a[contains(@href, 'Logout')]");
	private final By lblWelcomeMessage = By.xpath("//div[@id='banner']//strong");
	private final By tabFAQ = By.xpath("//div[@id='menu']//a[contains(@href, 'FAQ')]");

	//Elements
	protected WebElement getTabLogin() {
		return Constant.WEBDRIVER.findElement(tabLogin);
	}

	protected WebElement getTabLogout() {
		return Constant.WEBDRIVER.findElement(tabLogout);
	}
	
	protected WebElement getLblWelcomeMessage() {
		return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
	}
	
	protected WebElement getTabFAQ () {
		return Constant.WEBDRIVER.findElement(tabFAQ);
	}

	//Methods
	public String getWelcomeMessage() {
		return this.getLblWelcomeMessage().getText();
	}
	
	public LoginPage gotoLoginPage() {
		this.getTabLogin().click();
		return new LoginPage();
	}
	
	public FAQPage gotoFAQPage() {
		this.getTabFAQ().click();
		
		return new FAQPage(); 
	}
}
