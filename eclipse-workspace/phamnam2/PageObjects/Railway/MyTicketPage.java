package Railway;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;

import Common.Utilities;
import Constant.Constant;
import Constant.StationCity;

public class MyTicketPage extends GeneralPage{
	//Locators
	private static final String _btnCancle = "//input";
	
	//Elements
	public String getBtnCancle() {
		return _btnCancle;
	}
	
	
	//Methods
	public void verifyCancleBooking(StationCity departStation, StationCity arriveStation, String message) {
		TimetablePage timetablePage = new TimetablePage();
		int departCol = timetablePage.getColIndexByHeader("Depart Station");
		int arriveCol = timetablePage.getColIndexByHeader("Arrive Station");
		By bookedTicket = By.xpath(TableHelper.getRowBy2Cols(departCol, departStation.getDisplayText(), arriveCol, arriveStation.getDisplayText()));
		
		Boolean actualResult = Constant.WEBDRIVER.findElements(bookedTicket).size() > 0;
		
		Assert.assertFalse(actualResult, message);
	}
	
	public MyTicketPage cancleBooking(StationCity departStation, StationCity arriveStation) {
		TimetablePage timetablePage = new TimetablePage();
		int departCol = timetablePage.getColIndexByHeader("Depart Station");
		int arriveCol = timetablePage.getColIndexByHeader("Arrive Station");
		By linkCheckPrice = By.xpath(TableHelper.getRowBy2Cols(departCol, departStation.getDisplayText(), arriveCol, arriveStation.getDisplayText()) + getBtnCancle());
		
		Utilities.safeClick(Constant.WEBDRIVER.findElement(linkCheckPrice));
		
		Alert alert = Constant.WEBDRIVER.switchTo().alert();
		alert.accept();
		
		return new MyTicketPage();
	}
}
