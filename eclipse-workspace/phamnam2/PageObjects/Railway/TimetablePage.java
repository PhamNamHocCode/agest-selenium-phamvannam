package Railway;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import Constant.StationCity;
import Constant.TicketTableCol;

public class TimetablePage extends GeneralPage{
	//Locators
	private final static By _tblHeader = By.xpath("//table[contains(@class,'MyTable')]//th");
	private static final String _linkInTbl = "//a[normalize-space()='%s']";
	
	//Elements
	protected WebElement getTblHeader() {
		return Constant.WEBDRIVER.findElement(_tblHeader);
	}
	
	protected static By getByTblHeader() {
		return _tblHeader;
	}
	
	//Methods
	public static String getLinkInTbl() {
		return _linkInTbl;
	}
	
	public static int getColIndexByHeader(TicketTableCol ticketTableCol) {
		List<WebElement> headers = Constant.WEBDRIVER.findElements(getByTblHeader());
		
		for (int i = 0; i < headers.size(); i++) {
	        if (headers.get(i).getText().trim().equalsIgnoreCase(ticketTableCol.getDisplayName())) {
	            return i + 1;
	        }
	    }
		throw new RuntimeException("Header in table not found: " + ticketTableCol.getDisplayName());
	}
	
	public BookTicketPage bookTicketFromTimetable(StationCity departStation, StationCity arriveStation) {
		int departCol = getColIndexByHeader(TicketTableCol.DEPART_STATION);
		int arriveCol = getColIndexByHeader(TicketTableCol.ARRIVE_STATION);
		
		By linkCheckPrice = By.xpath(TableHelper.getRowBy2Cols(departCol, departStation.getDisplayText(), arriveCol, arriveStation.getDisplayText()) + String.format(getLinkInTbl(), "book ticket"));
		
		Utilities.scrollToElement(linkCheckPrice);
		Utilities.clickByJs(Constant.WEBDRIVER.findElement(linkCheckPrice));
		
		return new BookTicketPage();
	}
	
	public TicketPricePage clickCheckPriceLink(StationCity departStation, StationCity arriveStation) {
		int departCol = TimetablePage.getColIndexByHeader(TicketTableCol.DEPART_STATION);
		int arriveCol = TimetablePage.getColIndexByHeader(TicketTableCol.ARRIVE_STATION);
		By linkCheckPrice = By.xpath(TableHelper.getRowBy2Cols(departCol, departStation.getDisplayText(), arriveCol, arriveStation.getDisplayText()) + String.format(TimetablePage.getLinkInTbl(), "check price"));
		
		Utilities.clickByJs(Constant.WEBDRIVER.findElement(linkCheckPrice));
		
		return new TicketPricePage();
	}
}
