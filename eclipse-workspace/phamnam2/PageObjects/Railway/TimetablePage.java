package Railway;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import Constant.StationCity;

public class TimetablePage extends GeneralPage{
	//Locators
	private final By _tblHeader = By.xpath("//table[contains(@class,'MyTable')]//th");
	private static final String _linkInTbl = "//a[normalize-space()='%s']";
	
	//Elements
	public String getLinkInTbl() {
		return _linkInTbl;
	}
	
	public WebElement getTblHeader() {
		return Constant.WEBDRIVER.findElement(_tblHeader);
	}
	
	public By getByTblHeader() {
		return _tblHeader;
	}
	
	//Methods
	public int getColIndexByHeader(String header) {
		List<WebElement> headers = Constant.WEBDRIVER.findElements(getByTblHeader());
		
		for (int i = 0; i < headers.size(); i++) {
	        if (headers.get(i).getText().trim().equalsIgnoreCase(header)) {
	            return i + 1;
	        }
	    }
		throw new RuntimeException("Header in table not found: " + header);
	}
	
	public TicketPricePage checkPrice(StationCity departStation, StationCity arriveStation) {
		int departCol = getColIndexByHeader("Depart Station");
		int arriveCol = getColIndexByHeader("Arrive Station");
		By linkCheckPrice = By.xpath(TableHelper.getRowBy2Cols(departCol, departStation.getDisplayText(), arriveCol, arriveStation.getDisplayText()) + String.format(getLinkInTbl(), "check price"));
		
		Utilities.safeClick(Constant.WEBDRIVER.findElement(linkCheckPrice));
		
		return new TicketPricePage();
	}
	
	public BookTicketPage bookTicket(StationCity departStation, StationCity arriveStation) {
		int departCol = getColIndexByHeader("Depart Station");
		int arriveCol = getColIndexByHeader("Arrive Station");
		
		By linkCheckPrice = By.xpath(TableHelper.getRowBy2Cols(departCol, departStation.getDisplayText(), arriveCol, arriveStation.getDisplayText()) + String.format(getLinkInTbl(), "book ticket"));
		
		Utilities.scrollToElement(linkCheckPrice);
		Utilities.safeClick(Constant.WEBDRIVER.findElement(linkCheckPrice));
		
		return new BookTicketPage();
	}
}
