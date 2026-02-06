package Railway;

import Constant.Constant;
import Constant.SeatType;
import Constant.StationCity;

import java.time.LocalDate;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Common.Utilities;

public class BookTicketPage extends GeneralPage{
	private final By _sltDepartDate = By.xpath("//select[@name='Date']");
	private final By _sltDepratFrom = By.xpath("//select[@name='DepartStation']");
	private final By _sltArriveAt = By.xpath("//select[@name='ArriveStation']");
	private final By _sltSeatType = By.xpath("//select[@name='SeatType']");
	private final By _sltTicketAmount = By.xpath("//select[@name='TicketAmount']");
	private final By _btnBookTicket = By.xpath("//input[@value='Book ticket']");
	private final By _tblBookTicket = By.xpath("//div[@id='content']//table");

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
	
	public WebElement getTblBookTicket() {
		return Constant.WEBDRIVER.findElement(_tblBookTicket);
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
	
//	public boolean checkTicket(BookTicketData data) {
//		
//	}

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
        Select select = new Select(
                Constant.WEBDRIVER.findElement(locator)
        );
        select.selectByVisibleText(text);
    }
}
