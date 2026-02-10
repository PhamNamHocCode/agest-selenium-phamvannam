package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class HomePage extends GeneralPage{
	// Locators
	private final By _lblWelcomeMsg = By.xpath("//h1[contains(text(),'Welcome')]");
	private final By _linkCreateAccount = By.xpath("//div[@id='content']//a[contains(@href, 'Register')]");

	// Elements
	protected By getLblWelcomeMsg() {
		return _lblWelcomeMsg;
	}
	
	// Methods
	public HomePage open() {
		Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
		return this;
	}
	public WebElement getLinkCreateAccount() {
		return Constant.WEBDRIVER.findElement(_linkCreateAccount);
	}
	public boolean isHomePageDisplayed() {
		return !Constant.WEBDRIVER
				.findElements(_lblWelcomeMsg)
				.isEmpty();
	}
}
