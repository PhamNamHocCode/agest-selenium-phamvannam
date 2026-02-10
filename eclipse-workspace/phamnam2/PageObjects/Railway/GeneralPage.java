package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;
import Constant.PageMenu;

public class GeneralPage {
	
	// Locators
	private final By lblWelcomeMessage = By.xpath("//div[@id='banner']//strong");
	
	//Elements
	protected WebElement getLblWelcomeMessage() {
		return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
	}
	
	protected String getWelcomeMessage() {
		return this.getLblWelcomeMessage().getText();
	}
	
	//Methods
	public <T> T gotoPage(PageMenu menu, Class<T> pageClass) {
		Constant.WEBDRIVER.findElement(menu.getLocator()).click();
		try {
			return pageClass.getDeclaredConstructor().newInstance();
		}
		catch(Exception e) {
			throw new RuntimeException("Cannot navigate to page: " + pageClass.getSimpleName(), e);
		}
	}
	
	public boolean isMenuDisplayed(PageMenu menu) {
	    return !Constant.WEBDRIVER.findElements(menu.getLocator()).isEmpty();
	}
	
}
