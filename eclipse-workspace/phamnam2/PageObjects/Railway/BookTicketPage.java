package Railway;

import Constant.Constant;
import Constant.SeatType;
import Constant.StationCity;

import java.time.LocalDate;

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
	private final By _lblCenterMsg = By.xpath("//div[@id='content']//h1");
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
	
	public WebElement getLblCenterMsg() {
		return Constant.WEBDRIVER.findElement(_lblCenterMsg);
	}
	
	public String getTblCellXpath() {
		return _tblCellXpath;
	}
	
	
	// Methods
	public BookTicketPage bookTicket(BookTicketData data, Boolean isEditDepartFrom) {
		if (data.getDepartDate() != null) {
			if (!isDepartDateAvailable(Utilities.formatDate(data.getDepartDate()))) {
			    throw new IllegalStateException(
			        "Cannot select a date because select does not have a suitable date option: " + Utilities.formatDate(data.getDepartDate())
			    );
			}
			selectDepartDate(data.getDepartDate());
		}
		if (data.getDepartFrom() != null) {
			selectDepartFrom(data.getDepartFrom());
		}
		if (data.getArriveAt() != null && isEditDepartFrom) {
			Utilities.waitUntilStale(getSltArriveAt());
			selectArriveAt(data.getArriveAt());
		}
		if (data.getSeatType() != null) {
			selectSeatType(data.getSeatType());
		}
		selectTicketAmount(data.getTicketAmount());
        
        Utilities.scrollToElement(this.getBtnBookTicket());
        clickBookTicket();

        return this;
    }
	public void verifyBookTicketForm(String expectedDepart, String expectedArrive, String msgDepart, String msgArrive) {
		Select departSelect = new Select(getSltDepratFrom());
	    Select arriveSelect = new Select(getSltArriveAt());
	    
	    String actualDepart = departSelect.getFirstSelectedOption().getText();
	    String actualArrie = arriveSelect.getFirstSelectedOption().getText();
	    
		Assert.assertEquals(actualDepart, expectedDepart, msgDepart);
		Assert.assertEquals(actualArrie, expectedArrive, msgArrive);
	}
	public void verifyCenterMsg(String expectedMsg, String message) {
		String actualMsg = getLblCenterMsg().getText();
		
		Assert.assertEquals(actualMsg, expectedMsg, message);
	}
	
	public void verifyTicket(BookTicketData data) {
		
		String actualDepartStation = getTableCellValue(data.getDepartFrom().getDisplayText(), "Depart Station");
		String actualArriveStation = getTableCellValue(data.getArriveAt().getDisplayText(), "Arrive Station");
		String actualSeatType = getTableCellValue(data.getSeatType().getDisplayText(), "Seat Type");
		String actualDepartDate = getTableCellValue(Utilities.formatDate(data.getDepartDate()).toString(), "Depart Date");
		String actualAmount = getTableCellValue(String.valueOf(data.getTicketAmount()), "Amount");
		
		Assert.assertEquals(actualDepartStation, data.getDepartFrom().getDisplayText(), "Departure information is not displaying correctly");
		Assert.assertEquals(actualArriveStation, data.getArriveAt().getDisplayText(), "Arrival station information is displayed incorrectly");
		Assert.assertEquals(actualSeatType, data.getSeatType().getDisplayText(), "Seat type information is displayed incorrectly");
		Assert.assertEquals(actualDepartDate, Utilities.formatDate(data.getDepartDate()).toString(), "Departure date information is displayed incorrectly");
		Assert.assertEquals(actualAmount, String.valueOf(data.getTicketAmount()), "Ticket amount information is displayed incorrectly");
	}
	
	public boolean isDepartDateAvailable(String expectedDate) {
		Select select = new Select(getSltDepartDate());
		
		return select.getOptions().stream().anyMatch(
				option -> option.getText().equals(expectedDate));
	}
	
    //Low levels actions
    private void selectDepartDate(LocalDate date) {
        WebElement element =
                Constant.WEBDRIVER.findElement(_sltDepartDate);
        element.sendKeys(Utilities.formatDate(date));
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
    	
        Select select = new Select(
        		Utilities.waitForVisible(locator, Constant.WAIT_TIMEOUT)
        );
        select.selectByVisibleText(text);
    }

    
    private String getTableCellValue(String rowValue, String colValue) {
        String xpath = String.format(_tblCellXpath,
                rowValue, colValue);
        return Constant.WEBDRIVER.findElement(By.xpath(xpath)).getText().trim();
    }
}
