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
	// Locators
	private final By _slbDepartDate = By.xpath("//select[@name='Date']");
	private final By _slbDepartFrom = By.xpath("//select[@name='DepartStation']");
	private final By _slbArriveAt = By.xpath("//select[@name='ArriveStation']");
	private final By _slbSeatType = By.xpath("//select[@name='SeatType']");
	private final By _slbTicketAmount = By.xpath("//select[@name='TicketAmount']");
	private final By _btnBookTicket = By.xpath("//input[@value='Book ticket']");
	private final By _lblCenterMsg = By.xpath("//div[@id='content']//h1");
	
	// Dynamic locators
	private final String _cellTblXpath = "//tr[td[normalize-space()='%s']]/td[count(//table//th[normalize-space()='%s']/preceding-sibling::th) + 1]";
	
	// Elements
	private WebElement getSlbDepartDate() {
		return Constant.WEBDRIVER.findElement(_slbDepartDate);
	}
	
	private WebElement getSlbDepartFrom() {
		return Constant.WEBDRIVER.findElement(_slbDepartFrom);
	}
	
	private WebElement getSlbArriveAt() {
		return Constant.WEBDRIVER.findElement(_slbArriveAt);
	}
	
	private WebElement getBtnBookTicket() {
		return Constant.WEBDRIVER.findElement(_btnBookTicket);
	}
	
	private WebElement getLblCenterMsg() {
		return Constant.WEBDRIVER.findElement(_lblCenterMsg);
	}
	
	// Methods
	public BookTicketPage bookTicket(BookTicketData data) {
		BookTicketPage bookTicketPage = new BookTicketPage();
		
		if (data.getDepartDate() != null) {
			if (!bookTicketPage.isDepartDateAvailable(Utilities.formatDate(data.getDepartDate()))) {
			    throw new IllegalStateException(
			        "Cannot select a date because select does not have a suitable date option: " + Utilities.formatDate(data.getDepartDate())
			    );
			}
			bookTicketPage.selectDepartDate(data.getDepartDate());
		}
		if (data.getDepartFrom() != null) {
			bookTicketPage.selectDepartFrom(data.getDepartFrom());
		}
		if (data.getArriveAt() != null) {
			bookTicketPage.waintUntilArriveStationRefreshed();
			bookTicketPage.selectArriveAt(data.getArriveAt());
		}
		if (data.getSeatType() != null) {
			bookTicketPage.selectSeatType(data.getSeatType());
		}
		bookTicketPage.selectTicketAmount(data.getTicketAmount());
        
        Utilities.scrollToElement(bookTicketPage.getBtnBookTicket());
        bookTicketPage.clickBookTicket();

        return bookTicketPage;
    }
	
	public boolean isDepartDateAvailable(String expectedDate) {
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
	
	public void selectDepartDate(LocalDate date) {
        WebElement element =
                Constant.WEBDRIVER.findElement(_slbDepartDate);
        element.sendKeys(Utilities.formatDate(date));
    }

    public void selectDepartFrom(StationCity city) {
        selectByVisibleText(_slbDepartFrom, city.getDisplayText());
    }

    public void selectArriveAt(StationCity city) {
        selectByVisibleText(_slbArriveAt, city.getDisplayText());
    }

    public void selectSeatType(SeatType seatType) {
        selectByVisibleText(_slbSeatType, seatType.getDisplayText());
    }

    public void selectTicketAmount(int amount) {
        selectByVisibleText(_slbTicketAmount, String.valueOf(amount));
    }

    public void clickBookTicket() {
        Constant.WEBDRIVER.findElement(_btnBookTicket).click();
    }

    public void selectByVisibleText(By locator, String text) {
    	
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
