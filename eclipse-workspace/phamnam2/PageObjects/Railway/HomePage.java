package Railway;

import org.openqa.selenium.By;

import Constant.Constant;

public class HomePage extends GeneralPage{
	// Locators
	private final By _lblWelcomeMsg = By.xpath("//h1[contains(text(),'Welcome')]");
	
	// Elements
	
	
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
}
