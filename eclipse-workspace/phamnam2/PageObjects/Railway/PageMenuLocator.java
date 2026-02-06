package Railway;

import java.util.EnumMap;

import org.openqa.selenium.By;

import Constant.PageMenu;

public class PageMenuLocator {
	//Locators
	private static final EnumMap<PageMenu, By> LOCATORS = 
			new EnumMap<>(PageMenu.class);
	
	static {
		LOCATORS.put(PageMenu.LOGIN, 
				By.xpath("//div[@id='menu']//a[contains(@href,'Login')]"));
		
		LOCATORS.put(PageMenu.LOGOUT, 
				By.xpath("//div[@id='menu']//a[contains(@href,'Logout')]"));
		
		LOCATORS.put(PageMenu.FAQ, 
				By.xpath("//div[@id='menu']//a[contains(@href,'FAQ')]"));
		
		LOCATORS.put(PageMenu.REGISTER, 
				By.xpath("//div[@id='menu']//a[contains(@href,'Register')]"));
		
		LOCATORS.put(PageMenu.BOOK_TICKET, 
				By.xpath("//div[@id='menu']//a[contains(@href,'BookTicketPage')]"));
	}
	
	// Elements
	public static By get(PageMenu menu) {
		return LOCATORS.get(menu);
	}
}
