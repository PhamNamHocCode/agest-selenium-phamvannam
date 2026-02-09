package Constant;

import org.openqa.selenium.By;

public enum PageMenu {
	
	// Menu items
	LOGIN("Login"),
    LOGOUT("Logout"),
    FAQ("FAQ"),
    REGISTER("Register"),
    BOOK_TICKET("BookTicketPage"),
    TIMETABLE("TrainTimeListPage"),
    MY_TICKET("ManageTicket");
	
	//Locators
	private final By locator;
	
	//Build locator for each menu item
	PageMenu(String href){
		this.locator = By.xpath("//div[@id='menu']//a[contains(@href,'" + href + "')]");
	}
	
	//Accessory
	public By getLocator() {
		return locator;
	}
}
