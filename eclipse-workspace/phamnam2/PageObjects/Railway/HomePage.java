package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class HomePage extends GeneralPage{
	// Locators
	private final By _lblWelcomeMsg = By.xpath("//h1[contains(text(),'Welcome')]");
	private final By _linkCreateAccount = By.xpath("//div[@id='content']//a[contains(@href, 'Register')]");

	// Elements
	protected WebElement getLinkCreateAccount() {
		return Constant.WEBDRIVER.findElement(_linkCreateAccount);
	}
	
	protected WebElement getLblWelcomeMsg() {
		return Constant.WEBDRIVER.findElement(_lblWelcomeMsg);
	}
	
	// Methods
	public HomePage open() {
		Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
		return this;
	}
	
	public boolean isHomePageDisplayed() {
		return !Constant.WEBDRIVER
				.findElements(_lblWelcomeMsg)
				.isEmpty();
	}
	
	public boolean isCreateAccountLinkDisplayed() {
		return !Constant.WEBDRIVER
				.findElements(_linkCreateAccount)
				.isEmpty();
	}
	
	public RegisterPage clickCreateAccountLink() {
	    getLinkCreateAccount().click();
	    return new RegisterPage();
	}
}
