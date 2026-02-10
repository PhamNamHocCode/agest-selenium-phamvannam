package Railway;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;

import Common.Utilities;
import Constant.Constant;
import Constant.StationCity;
import Constant.TicketTableCol;

public class MyTicketPage extends GeneralPage{
	//Locators
	private static final String _btnCancle = "//input";
	
	//Elements
	protected String getBtnCancle() {
		return _btnCancle;
	}
	
	//Methods
	public MyTicketPage cancleBooking(StationCity departStation, StationCity arriveStation) {
		int departCol = TimetablePage.getColIndexByHeader(TicketTableCol.DEPART_STATION);
		int arriveCol = TimetablePage.getColIndexByHeader(TicketTableCol.ARRIVE_STATION);
		By linkCheckPrice = By.xpath(TableHelper.getRowBy2Cols(departCol, departStation.getDisplayText(), arriveCol, arriveStation.getDisplayText()) + getBtnCancle());
		
		Utilities.clickByJs(Constant.WEBDRIVER.findElement(linkCheckPrice));
		
		Alert alert = Constant.WEBDRIVER.switchTo().alert();
		alert.accept();
		
		return new MyTicketPage();
	}
	
	public boolean isTicketCancled(int departCol, int arriveCol, BookTicketData bookTicketData) {
		By bookedTicket = By.xpath(TableHelper.getRowBy2Cols(departCol, bookTicketData.getDepartFrom().getDisplayText(), arriveCol, bookTicketData.getArriveAt().getDisplayText()));
		
		Boolean actualResult = Constant.WEBDRIVER.findElements(bookedTicket).size() > 0;
		
		return actualResult;
	}
}
