package Railway;

import Constant.Constant;
import Constant.SeatType;
import Constant.StationCity;

import java.time.LocalDate;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Common.Utilities;

public class BookTicketPage extends GeneralPage{
	//Locators
	private final By _sltDepartDate = By.xpath("//select[@name='Date']");
	private final By _sltDepratFrom = By.xpath("//select[@name='DepartStation']");
	private final By _sltArriveAt = By.xpath("//select[@name='ArriveStation']");
	private final By _sltSeatType = By.xpath("//select[@name='SeatType']");
	private final By _sltTicketAmount = By.xpath("//select[@name='TicketAmount']");
	private final By _btnBookTicket = By.xpath("//input[@value='Book ticket']");
	private static final String _tblCellXpath = "//tr[td[normalize-space()='%s']]/td[count(//table//th[normalize-space()='%s']/preceding-sibling::th) + 1]";

	// Elements
	public WebElement getSltDepartDate() {
		return Constant.WEBDRIVER.findElement(_sltDepartDate);
	}
	
	public WebElement getSltDepratFrom() {
		return Constant.WEBDRIVER.findElement(_sltDepratFrom);
	}
	
	public WebElement getSltArriveAt() {
		return Constant.WEBDRIVER.findElement(_sltArriveAt);
	}
	
	public WebElement getSltSeatType() {
		return Constant.WEBDRIVER.findElement(_sltSeatType);
	}
	
	public WebElement getSltTicketAmount() {
		return Constant.WEBDRIVER.findElement(_sltTicketAmount);
	}
	
	public WebElement getBtnBookTicket() {
		return Constant.WEBDRIVER.findElement(_btnBookTicket);
	}
	
	public String getTblCellXpath() {
		return _tblCellXpath;
	}
	
	// Methods
	public BookTicketPage bookTicket(BookTicketData data) {

        selectDepartDate(data.getDepartDate());
        selectDepartFrom(data.getDepartFrom());
        selectArriveAt(data.getArriveAt());
        selectSeatType(data.getSeatType());
        selectTicketAmount(data.getTicketAmount());
        
        Utilities.scrollToElement(this.getBtnBookTicket());
        clickBookTicket();

        return this;
    }
	
	public void checkTicket(BookTicketData data) {
		
		int row = 1;
		
		String actualDepartStation = getTableCellValue(row, "Depart Station");
		String actualArriveStation = getTableCellValue(row, "Arrive Station");
		String actualSeatType = getTableCellValue(row, "Seat Type");
		String actualDepartDate = getTableCellValue(row, "Depart Date");
		String actualAmount = getTableCellValue(row, "Amount");
		
		Assert.assertEquals(actualDepartStation, data.getDepartFrom().getDisplayText(), "Departure information is not displaying correctly");
		Assert.assertEquals(actualArriveStation, data.getArriveAt().getDisplayText(), "Arrival station information is displayed incorrectly");
		Assert.assertEquals(actualSeatType, data.getSeatType().getDisplayText(), "Seat type information is displayed incorrectly");
		Assert.assertEquals(actualDepartDate, data.getDepartDate().toString(), "Departure date information is displayed incorrectly");
		Assert.assertEquals(actualAmount, String.valueOf(data.getTicketAmount()), "Ticket amount information is displayed incorrectly");
	}

    //Low levels actions
    private void selectDepartDate(LocalDate date) {
        WebElement element =
                Constant.WEBDRIVER.findElement(_sltDepartDate);
        element.sendKeys(date.format(Constant.DATE_FORMAT));
    }

    private void selectDepartFrom(StationCity city) {
        selectByVisibleText(_sltDepratFrom, city.getDisplayText());
    }

    private void selectArriveAt(StationCity city) {
        selectByVisibleText(_sltArriveAt, city.getDisplayText());
    }

    private void selectSeatType(SeatType seatType) {
        selectByVisibleText(_sltSeatType, seatType.getDisplayText());
    }

    private void selectTicketAmount(int amount) {
        selectByVisibleText(_sltTicketAmount, String.valueOf(amount));
    }

    private void clickBookTicket() {
        Constant.WEBDRIVER.findElement(_btnBookTicket).click();
    }

    // Helper
    private void selectByVisibleText(By locator, String text) {
    	Utilities.waitForVisible(locator, Constant.WAIT_TIMEOUT);
    	
        Select select = new Select(
                Constant.WEBDRIVER.findElement(locator)
        );
        select.selectByVisibleText(text);
    }
    
    private String getTableCellValue(int rowValue, String colValue) {
        String xpath = String.format(_tblCellXpath,
                rowValue, colValue);
        return Constant.WEBDRIVER.findElement(By.xpath(xpath)).getText().trim();
    }
}
