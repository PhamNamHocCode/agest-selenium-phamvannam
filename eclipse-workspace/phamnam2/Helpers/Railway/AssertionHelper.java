package Railway;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import Common.Utilities;
import Constant.Constant;
import Constant.FieldsLogin;
import Constant.StationCity;
import Constant.TicketTableCol;

public class AssertionHelper {
	SoftAssert softAssert = new SoftAssert();
	
	//Book ticket
	public void verifyCenterMsg(String expectedMsg, String message) {
		String actualMsg = BookTicketPage.getLblCenterMsg().getText();
		
		softAssert.assertEquals(actualMsg, expectedMsg, message);
	}
	
	public void verifyBookTicketForm(String expectedDepart, String expectedArrive, String msgDepart, String msgArrive) {
		Select departSelect = new Select(BookTicketPage.getSltDepratFrom());
	    Select arriveSelect = new Select(BookTicketPage.getSltArriveAt());
	    
	    String actualDepart = departSelect.getFirstSelectedOption().getText();
	    String actualArrie = arriveSelect.getFirstSelectedOption().getText();
	    
		softAssert.assertEquals(actualDepart, expectedDepart, msgDepart);
		softAssert.assertEquals(actualArrie, expectedArrive, msgArrive);
	}
	
	// Timetable
	public TicketPricePage verifyTicketPrice(StationCity departStation, StationCity arriveStation) {
		int departCol = TimetablePage.getColIndexByHeader(TicketTableCol.DEPART_STATION);
		int arriveCol = TimetablePage.getColIndexByHeader(TicketTableCol.ARRIVE_STATION);
		By linkCheckPrice = By.xpath(TableHelper.getRowBy2Cols(departCol, departStation.getDisplayText(), arriveCol, arriveStation.getDisplayText()) + String.format(TimetablePage.getLinkInTbl(), "check price"));
		
		Utilities.clickByJs(Constant.WEBDRIVER.findElement(linkCheckPrice));
		
		return new TicketPricePage();
	}
	
	//Login
	public void verifyLblExists(FieldsLogin element, String expectedMsg) {
		By locator = LoginPage.getLocator(element);
		String actualMsg = Utilities.getElementTextStatus(locator);
		
		if ("NOT_FOUND".equals(actualMsg)) {
	        softAssert.fail("The error messege NOT FOUND: " + element);
	    }

	    if ("HIDDEN".equals(actualMsg)) {
	    	softAssert.fail("The error messege is HIDDEN: " + element);
	    }

	    softAssert.assertEquals(
	        actualMsg,
	        expectedMsg,
	        "The error messege is not displayed as expected: " + element
	    );	
	}
	
	//My ticket
	public void verifyCancleBooking(StationCity departStation, StationCity arriveStation, String message) {
		int departCol = TimetablePage.getColIndexByHeader(TicketTableCol.DEPART_STATION);
		int arriveCol = TimetablePage.getColIndexByHeader(TicketTableCol.ARRIVE_STATION);
		By bookedTicket = By.xpath(TableHelper.getRowBy2Cols(departCol, departStation.getDisplayText(), arriveCol, arriveStation.getDisplayText()));
		
		Boolean actualResult = Constant.WEBDRIVER.findElements(bookedTicket).size() > 0;
		
		softAssert.assertFalse(actualResult, message);
	}
}
