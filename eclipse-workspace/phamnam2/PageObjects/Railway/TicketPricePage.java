package Railway;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Constant.Constant;
import Constant.SeatType;

public class TicketPricePage extends GeneralPage{
	//Locators
	private final By _lblTblHeader = By.xpath("//table[@class='MyTable MedTable']//th[contains(text(), 'Ticket price from')]");
	private final String _dymSeatType = "//div[@class='DivTable']//td[text()='%s']";
    private final String _dymSeatPrice = "//div[@class='DivTable']//th[normalize-space()='Price (VND)']/following-sibling::td[%s]";

    public int getCellIndexOfSeatType(String seatType) {
        String xpathString = String.format(_dymSeatType, seatType);
        return Integer.parseInt(Constant.WEBDRIVER.findElement(By.xpath(xpathString)).getDomProperty("cellIndex"));
    }

    public String getPriceOfSeatType (String seatType) {
        int index = getCellIndexOfSeatType(seatType);
        return Constant.WEBDRIVER.findElement(By.xpath(String.format(_dymSeatPrice, index))).getText();
    }
	
	//Elements
	public WebElement getLblTblHeader() {
		return Constant.WEBDRIVER.findElement(_lblTblHeader);
	}
	public By getByLblTblHeader() {
		return _lblTblHeader;
	}
	
	//Methods
	public void checkTableData(String expectedHeaderMsg, Map<SeatType, String> expectedPrices) {
		String actualHeaderMsg = Constant.WEBDRIVER.findElement(getByLblTblHeader()).getText();
		if (expectedHeaderMsg != null) {
			Assert.assertEquals(actualHeaderMsg, expectedHeaderMsg, "The ticket table is not display as expected");
		}
		for(Map.Entry<SeatType, String> entry: expectedPrices.entrySet()) {
			String actualPrice = getPriceOfSeatType(entry.getKey().name());
			Assert.assertEquals(actualPrice, 
					entry.getValue(),
					"The " + entry.getKey() + " price is not display as expected");
		}
	}
}
