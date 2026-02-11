package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class TicketPricePage extends GeneralPage{
	//Locators
	private static final By _lblTblHeader = By.xpath("//table[@class='MyTable MedTable']//th[contains(text(), 'Ticket price from')]");
	
	//Dynamic locators
	private static final String _dymSeatType = "//div[@class='DivTable']//td[text()='%s']";
    private static final String _dymSeatPrice = "//div[@class='DivTable']//th[normalize-space()='Price (VND)']/following-sibling::td[%s]";
    
    //Elements
    protected static WebElement getLblTblHeader() {
		return Constant.WEBDRIVER.findElement(_lblTblHeader);
	}
    
    //Methods
    public String getTicketPriceTblHeader() {
    	return getLblTblHeader().getText();
    }
	
    public String getPriceOfSeatType (String seatType) {
        int index = getCellIndexOfSeatType(seatType);
        return Constant.WEBDRIVER.findElement(By.xpath(String.format(_dymSeatPrice, index))).getText();
    }
    
    public int getCellIndexOfSeatType(String seatType) {
        String xpathString = String.format(_dymSeatType, seatType);
        return Integer.parseInt(Constant.WEBDRIVER.findElement(By.xpath(xpathString)).getDomProperty("cellIndex"));
    }
}
