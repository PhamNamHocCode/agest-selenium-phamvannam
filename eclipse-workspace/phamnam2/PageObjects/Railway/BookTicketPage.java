package Railway;

import Constant.Constant;
import Constant.SeatType;
import Constant.StationCity;
import Constant.TicketTableCol;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Common.Utilities;

public class BookTicketPage extends GeneralPage{
	//Locators
	private static final By _slbDepartDate = By.xpath("//select[@name='Date']");
	private static final By _slbDepartFrom = By.xpath("//select[@name='DepartStation']");
	private static final By _slbArriveAt = By.xpath("//select[@name='ArriveStation']");
	private static final By _slbSeatType = By.xpath("//select[@name='SeatType']");
	private static final By _slbTicketAmount = By.xpath("//select[@name='TicketAmount']");
	private static final By _btnBookTicket = By.xpath("//input[@value='Book ticket']");
	private static final By _lblCenterMsg = By.xpath("//div[@id='content']//h1");
	private static final String _cellTblXpath = "//tr[td[normalize-space()='%s']]/td[count(//table//th[normalize-space()='%s']/preceding-sibling::th) + 1]";
	
	// Elements
	protected static WebElement getSlbDepartDate() {
		return Constant.WEBDRIVER.findElement(_slbDepartDate);
	}
	
	protected static WebElement getSlbDepartFrom() {
		return Constant.WEBDRIVER.findElement(_slbDepartFrom);
	}
	
	protected static WebElement getSlbArriveAt() {
		return Constant.WEBDRIVER.findElement(_slbArriveAt);
	}
	
	protected static WebElement getSlbSeatType() {
		return Constant.WEBDRIVER.findElement(_slbSeatType);
	}
	
	protected static WebElement getSlbTicketAmount() {
		return Constant.WEBDRIVER.findElement(_slbTicketAmount);
	}
	
	protected static WebElement getBtnBookTicket() {
		return Constant.WEBDRIVER.findElement(_btnBookTicket);
	}
	
	protected static WebElement getLblCenterMsg() {
		return Constant.WEBDRIVER.findElement(_lblCenterMsg);
	}
	
	protected static String getTblCellXpath() {
		return _cellTblXpath;
	}
	
	// Methods
	public static boolean isDepartDateAvailable(String expectedDate) {
		Select select = new Select(getSlbDepartDate());
		
		return select.getOptions().stream().anyMatch(
				option -> option.getText().equals(expectedDate));
	}
	
	public String getCenterMsg() {
		return getLblCenterMsg().getText();
	}
	
	public LocalDate getSelectedDepartDate(int index, DateTimeFormatter dateFormat) {
		Select select = new Select(getSlbDepartDate());
		String departDate = select.getOptions().get(index).getText();
		return LocalDate.parse(departDate, dateFormat);
	}
	
	public String getSelectedDepartStation() {
	    Select departSelect = new Select(getSlbDepartFrom());
	    return departSelect.getFirstSelectedOption().getText();
	}

	public String getSelectedArriveStation() {
	    Select arriveSelect = new Select(getSlbArriveAt());
	    return arriveSelect.getFirstSelectedOption().getText();
	}
	
	public void waintUntilArriveStationRefreshed() {
		WebElement element = getSlbArriveAt();
		Utilities.waitUntilStale(element);
	}
	
    //Low levels actions
	public static void selectDepartDate(LocalDate date) {
        WebElement element =
                Constant.WEBDRIVER.findElement(_slbDepartDate);
        element.sendKeys(Utilities.formatDate(date));
    }

    public static void selectDepartFrom(StationCity city) {
        selectByVisibleText(_slbDepartFrom, city.getDisplayText());
    }

    public static void selectArriveAt(StationCity city) {
        selectByVisibleText(_slbArriveAt, city.getDisplayText());
    }

    public static void selectSeatType(SeatType seatType) {
        selectByVisibleText(_slbSeatType, seatType.getDisplayText());
    }

    public static void selectTicketAmount(int amount) {
        selectByVisibleText(_slbTicketAmount, String.valueOf(amount));
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
    
    public  String getTableCellValue(String rowValue, TicketTableCol ticketTableCol) {
        String xpath = String.format(_cellTblXpath,
                rowValue, ticketTableCol.getDisplayName());
        return Constant.WEBDRIVER.findElement(By.xpath(xpath)).getText().trim();
    }
}
