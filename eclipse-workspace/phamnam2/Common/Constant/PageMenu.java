package Constant;

import org.openqa.selenium.By;

public enum PageMenu {
	
	// Locators
	LOGIN(By.xpath("//div[@id='menu']//a[contains(@href,'Login')]")),
    LOGOUT(By.xpath("//div[@id='menu']//a[contains(@href,'Logout')]")),
    FAQ(By.xpath("//div[@id='menu']//a[contains(@href,'FAQ')]")),
	REGISTER(By.xpath("//div[@id='menu']//a[contains(@href,'Register')]"));
	
	public final By locator;
	
	PageMenu(By locator){
		this.locator = locator;
	}
}
