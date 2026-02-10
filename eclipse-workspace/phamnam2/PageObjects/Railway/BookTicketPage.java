package Railway;

import Constant.Constant;
import Constant.SeatType;
import Constant.StationCity;
import Constant.TicketTableCol;

import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Common.Utilities;

public class BookTicketPage extends GeneralPage{
	//Locators
	private static final By _sltDepartDate = By.xpath("//select[@name='Date']");
	private static final By _sltDepratFrom = By.xpath("//select[@name='DepartStation']");
	private static final By _sltArriveAt = By.xpath("//select[@name='ArriveStation']");
	private static final By _sltSeatType = By.xpath("//select[@name='SeatType']");
	private static final By _sltTicketAmount = By.xpath("//select[@name='TicketAmount']");
	private static final By _btnBookTicket = By.xpath("//input[@value='Book ticket']");
	private static final By _lblCenterMsg = By.xpath("//div[@id='content']//h1");
	private static final String _tblCellXpath = "//tr[td[normalize-space()='%s']]/td[count(//table//th[normalize-space()='%s']/preceding-sibling::th) + 1]";
	
	// Elements
	protected static WebElement getSltDepartDate() {
		return Constant.WEBDRIVER.findElement(_sltDepartDate);
	}
	
	protected static WebElement getSltDepratFrom() {
		return Constant.WEBDRIVER.findElement(_sltDepratFrom);
	}
	
	protected static WebElement getSltArriveAt() {
		return Constant.WEBDRIVER.findElement(_sltArriveAt);
	}
	
	protected static WebElement getSltSeatType() {
		return Constant.WEBDRIVER.findElement(_sltSeatType);
	}
	
	protected static WebElement getSltTicketAmount() {
		return Constant.WEBDRIVER.findElement(_sltTicketAmount);
	}
	
	protected static WebElement getBtnBookTicket() {
		return Constant.WEBDRIVER.findElement(_btnBookTicket);
	}
	
	protected static WebElement getLblCenterMsg() {
		return Constant.WEBDRIVER.findElement(_lblCenterMsg);
	}
	
	protected static String getTblCellXpath() {
		return _tblCellXpath;
	}
	
	
	// Methods
	
	public static boolean isDepartDateAvailable(String expectedDate) {
		Select select = new Select(getSltDepartDate());
		
		return select.getOptions().stream().anyMatch(
				option -> option.getText().equals(expectedDate));
	}
	
    //Low levels actions
	public static void selectDepartDate(LocalDate date) {
        WebElement element =
                Constant.WEBDRIVER.findElement(_sltDepartDate);
        element.sendKeys(Utilities.formatDate(date));
    }

    public static void selectDepartFrom(StationCity city) {
        selectByVisibleText(_sltDepratFrom, city.getDisplayText());
    }

    public static void selectArriveAt(StationCity city) {
        selectByVisibleText(_sltArriveAt, city.getDisplayText());
    }

    public static void selectSeatType(SeatType seatType) {
        selectByVisibleText(_sltSeatType, seatType.getDisplayText());
    }

    public static void selectTicketAmount(int amount) {
        selectByVisibleText(_sltTicketAmount, String.valueOf(amount));
    }

    public static void clickBookTicket() {
        Constant.WEBDRIVER.findElement(_btnBookTicket).click();
    }

    // Helper
    public static void selectByVisibleText(By locator, String text) {
    	
        Select select = new Select(
        	Utilities.waitForVisible(locator)
        );
        select.selectByVisibleText(text);
    }
    
    public static String getTableCellValue(String rowValue, TicketTableCol ticketTableCol) {
        String xpath = String.format(_tblCellXpath,
                rowValue, ticketTableCol.getDisplayName());
        return Constant.WEBDRIVER.findElement(By.xpath(xpath)).getText().trim();
    }
}
