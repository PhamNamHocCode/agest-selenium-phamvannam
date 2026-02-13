package Railway;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.PageMenu;
import Constant.SeatType;
import Constant.StationCity;
import Constant.TicketTableCol;
import Constant.Constant;

public class BookTicket extends TestBase{

	@Test
	public void TC12() {
		HomePage homePage = new HomePage();
		int ticketAmout = 1;
		RegisterAccount account = PreconditionHelper.generateRandomRegisterAccount();
		String expectedMsg = "Ticket booked successfully!";
		
		System.out.println("TC12: Verify that user can book 1 ticket at a time");
		System.out.println("Pre-condition: an actived account is existing");
		account = PreconditionHelper.createAnActiveAccount(account);
		
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage = homePage.open();
		
		System.out.println("Step 2: Login with a valid account");
		LoginPage loginPage  = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		homePage = loginPage.login(account);
		
		System.out.println("Step 3: Click on \"Book ticket\" tab"); 
		BookTicketPage bookTicketPage = homePage.gotoPage(PageMenu.BOOK_TICKET, BookTicketPage.class);
		LocalDate targetDate = bookTicketPage.getSelectedDepartDate(2, Constant.DATE_FORMAT);
		
		System.out.println("Step 4: Select the next 2 days from \"Depart date\""); 
		System.out.println("Step 5: Select Depart from \"Nha Trang\" and Arrive at \"Huế\""); 
		System.out.println("Step 6: Select \"Soft bed with air conditioner\" for \"Seat type\""); 
		System.out.println("Step 7: Select \"1\" for \"Ticket amount\""); 
		System.out.println("Step 8: Click on \"Book ticket\" button"); 
		BookTicketData bookTicketData = new BookTicketData(targetDate, StationCity.NHA_TRANG, StationCity.HUE, SeatType.SOFT_BED_WITH_AIR_CONDITIONER, ticketAmout);
		bookTicketPage = bookTicketPage.bookTicket(bookTicketData);
		
		System.out.println("VP: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		String actualCenterMsg = bookTicketPage.getCenterMsg();
		Assert.assertEquals(actualCenterMsg, expectedMsg, "The message is not displayed as expected");
		
		String actualDepartStation = bookTicketPage.getTableCellValue(bookTicketData.getDepartFrom().getDisplayText(), TicketTableCol.DEPART_STATION);
		Assert.assertEquals(actualDepartStation, bookTicketData.getDepartFrom().getDisplayText(), "Departure information is not displaying correctly");

		String actualArriveStation = bookTicketPage.getTableCellValue(bookTicketData.getArriveAt().getDisplayText(), TicketTableCol.ARRIVE_STATION);
		Assert.assertEquals(actualArriveStation, bookTicketData.getArriveAt().getDisplayText(), "Arrival station information is displayed incorrectly");

		String actualSeatType = bookTicketPage.getTableCellValue(bookTicketData.getSeatType().getDisplayText(), TicketTableCol.SEAT_TYPE);
		Assert.assertEquals(actualSeatType, bookTicketData.getSeatType().getDisplayText(), "Seat type information is displayed incorrectly");

		String actualDepartDate = bookTicketPage.getTableCellValue(Utilities.formatDate(bookTicketData.getDepartDate()).toString(), TicketTableCol.DEPART_DATE);
		Assert.assertEquals(actualDepartDate, Utilities.formatDate(bookTicketData.getDepartDate()).toString(), "Departure date information is displayed incorrectly");

		String actualAmount = bookTicketPage.getTableCellValue(String.valueOf(bookTicketData.getTicketAmount()), TicketTableCol.AMOUNT);
		Assert.assertEquals(actualAmount, String.valueOf(bookTicketData.getTicketAmount()), "Ticket amount information is displayed incorrectly");
	}
	
	@Test
	public void TC13() {
		HomePage homePage = new HomePage();
		int ticketAmout = 5;
		RegisterAccount account = PreconditionHelper.generateRandomRegisterAccount();
		String expectedMsg = "Ticket booked successfully!";
		
		System.out.println("TC13: Verify that user can book many tickets at a time");
		System.out.println("Pre-condition: an actived account is existing");
		account = PreconditionHelper.createAnActiveAccount(account);
		
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage = homePage.open();
		
		System.out.println("Step 2: Login with a valid account");
		LoginPage loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		homePage = loginPage.login(account);
		
		System.out.println("Step 3: Click on \"Book ticket\" tab"); 
		BookTicketPage bookTicketPage = homePage.gotoPage(PageMenu.BOOK_TICKET, BookTicketPage.class);
		
		System.out.println("Step 4: Select the next 25 days from \"Depart date\""); 
		System.out.println("Step 5: Select \"Nha Trang\" for \"Depart from\" and \"Sài Gòn\" for \"Arrive at\""); 
		System.out.println("Step 6: Select \"Soft seat with air conditioner\" for \"Seat type\""); 
		System.out.println("Step 7: Select \"5\" for \"Ticket amount\""); 
		System.out.println("Step 8: Click on \"Book ticket\" button"); 
		LocalDate targetDate = bookTicketPage.getSelectedDepartDate(25, Constant.DATE_FORMAT);
		BookTicketData bookTicketData = new BookTicketData(targetDate, StationCity.NHA_TRANG, StationCity.SAI_GON, SeatType.SOFT_SEAT_WITH_AIR_CONDITIONER, ticketAmout);
		bookTicketPage = bookTicketPage.bookTicket(bookTicketData);
		
		System.out.println("VP: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		String actualCenterMsg = bookTicketPage.getCenterMsg();
		Assert.assertEquals(actualCenterMsg, expectedMsg, "The message is not displayed as expected");
		
		String actualDepartStation = bookTicketPage.getTableCellValue(bookTicketData.getDepartFrom().getDisplayText(), TicketTableCol.DEPART_STATION);
		Assert.assertEquals(actualDepartStation, bookTicketData.getDepartFrom().getDisplayText(), "Departure information is not displaying correctly");

		String actualArriveStation = bookTicketPage.getTableCellValue(bookTicketData.getArriveAt().getDisplayText(), TicketTableCol.ARRIVE_STATION);
		Assert.assertEquals(actualArriveStation, bookTicketData.getArriveAt().getDisplayText(), "Arrival station information is displayed incorrectly");

		String actualSeatType = bookTicketPage.getTableCellValue(bookTicketData.getSeatType().getDisplayText(), TicketTableCol.SEAT_TYPE);
		Assert.assertEquals(actualSeatType, bookTicketData.getSeatType().getDisplayText(), "Seat type information is displayed incorrectly");

		String actualDepartDate = bookTicketPage.getTableCellValue(Utilities.formatDate(bookTicketData.getDepartDate()).toString(), TicketTableCol.DEPART_DATE);
		Assert.assertEquals(actualDepartDate, Utilities.formatDate(bookTicketData.getDepartDate()).toString(), "Departure date information is displayed incorrectly");

		String actualAmount = bookTicketPage.getTableCellValue(String.valueOf(bookTicketData.getTicketAmount()), TicketTableCol.AMOUNT);
		Assert.assertEquals(actualAmount, String.valueOf(bookTicketData.getTicketAmount()), "Ticket amount information is displayed incorrectly");
	}

	@Test
	public void TC14() {
		HomePage homePage = new HomePage();
		StationCity departStation = StationCity.DA_NANG;
		StationCity arriveStation = StationCity.SAI_GON;
		Map<SeatType, String> prices = new EnumMap<>(SeatType.class);
		prices.put(SeatType.HARD_SEAT, "310000");
		prices.put(SeatType.SOFT_SEAT, "335000");
		prices.put(SeatType.SOFT_SEAT_WITH_AIR_CONDITIONER, "360000");
		prices.put(SeatType.HARD_BED, "410000");
		prices.put(SeatType.SOFT_BED, "460000");
		prices.put(SeatType.SOFT_BED_WITH_AIR_CONDITIONER, "510000");
		RegisterAccount account = PreconditionHelper.generateRandomRegisterAccount();
		String expectedHeaderMsg = "Ticket price from Đà Nẵng to Sài Gòn";

		System.out.println("TC14: Verify that user can check price of ticket from Timetable");
		System.out.println("Pre-condition: an actived account is existing");
		account = PreconditionHelper.createAnActiveAccount(account);
		
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage = homePage.open();
		
		System.out.println("Step 2: Login with a valid account");
		LoginPage loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		homePage = loginPage.login(account);
		
		System.out.println("Step 3: Click on \"Timetable\" tab");
		TimetablePage timetablePage = homePage.gotoPage(PageMenu.TIMETABLE, TimetablePage.class);
		
		System.out.println("Step 4: Click on \"check price\" link of the route from \"Đà Nẵng\" to \"Sài Gòn\""); 
		TicketPricePage ticketPricePage = timetablePage.clickCheckPriceLink(departStation, arriveStation);
		
		System.out.println("VP: \"Ticket Price\" page is loaded.\r\n"
				+ "Ticket table shows \"Ticket price from Đà Nẵng to Sài Gòn\".\r\n"
				+ "Price for each seat displays correctly\r\n"
				+ "HS = 310000, SS = 335000, SSC = 360000, HB = 410000, SB = 460000, SBC = 510000");
		Assert.assertEquals(ticketPricePage.getTicketPriceTblHeader(), expectedHeaderMsg, "The ticket table is not display as expected");
		
		for(Map.Entry<SeatType, String> entry: prices.entrySet()) {
			String actualPrice = ticketPricePage.getPriceOfSeatType(entry.getKey().name());
			Assert.assertEquals(actualPrice, 
					entry.getValue(),
					"The " + entry.getKey() + " price is not display as expected");
		}
	}
	
	@Test
	public void TC15() {
		HomePage homePage = new HomePage();
		LocalDate targetDate = LocalDate.now().plusDays(1);
		String expectedMsg = "Ticket booked successfully!";
		int ticketAmount = 5;
		StationCity departStation = StationCity.QUANG_NGAI;
		StationCity arriveStation = StationCity.HUE;
		BookTicketData bookTicketData = new BookTicketData(targetDate, departStation, arriveStation, null, ticketAmount);
		RegisterAccount account = PreconditionHelper.generateRandomRegisterAccount();
		
		System.out.println("TC15: Verify that user can book ticket from Timetable");
		System.out.println("Pre-condition: an actived account is existing");
		account = PreconditionHelper.createAnActiveAccount(account);
		
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2: Login with a valid account");
		LoginPage loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		homePage = loginPage.login(account);
		
		System.out.println("Step 3: Click on \"Timetable\" tab"); 
		TimetablePage timetablePage = homePage.gotoPage(PageMenu.TIMETABLE, TimetablePage.class);
		
		System.out.println("Step 4: Click on book ticket of route \"Quảng Ngãi\" to \"Huế\""); 
		BookTicketPage bookTicketPage = timetablePage.bookTicketFromTimetable(departStation, arriveStation);
		
		System.out.println("VP: Book ticket form is shown with the corrected \"depart from\" and \"Arrive at\"");
	    Assert.assertEquals(bookTicketPage.getSelectedDepartStation(), departStation.getDisplayText(), "The depart selection is not displayed as expected");
	    Assert.assertEquals(bookTicketPage.getSelectedArriveStation(), arriveStation.getDisplayText(), "The arrive selection is not displayed as expected");
		
		System.out.println("Step 5: Select Depart date = tomorrow");
		System.out.println("Step 6: Select Ticket amount = 5");
		System.out.println("Step 7: Click on \"Book ticket\" button");
		bookTicketPage = bookTicketPage.bookTicket(bookTicketData);
		
		System.out.println("VP: Message \"Ticket booked successfully!\" displays. "
				+ "Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		String actualCenterMsg = bookTicketPage.getCenterMsg();
		Assert.assertEquals(actualCenterMsg, expectedMsg, "The message is not displayed as expected");
		
		String actualDepartStation = bookTicketPage.getTableCellValue(bookTicketData.getDepartFrom().getDisplayText(), TicketTableCol.DEPART_STATION);
		Assert.assertEquals(actualDepartStation, bookTicketData.getDepartFrom().getDisplayText(), "Departure information is not displaying correctly");

		String actualArriveStation = bookTicketPage.getTableCellValue(bookTicketData.getArriveAt().getDisplayText(), TicketTableCol.ARRIVE_STATION);
		Assert.assertEquals(actualArriveStation, bookTicketData.getArriveAt().getDisplayText(), "Arrival station information is displayed incorrectly");

		String actualSeatType = bookTicketPage.getTableCellValue(bookTicketData.getSeatType().getDisplayText(), TicketTableCol.SEAT_TYPE);
		Assert.assertEquals(actualSeatType, bookTicketData.getSeatType().getDisplayText(), "Seat type information is displayed incorrectly");

		String actualDepartDate = bookTicketPage.getTableCellValue(Utilities.formatDate(bookTicketData.getDepartDate()).toString(), TicketTableCol.DEPART_DATE);
		Assert.assertEquals(actualDepartDate, Utilities.formatDate(bookTicketData.getDepartDate()).toString(), "Departure date information is displayed incorrectly");

		String actualAmount = bookTicketPage.getTableCellValue(String.valueOf(bookTicketData.getTicketAmount()), TicketTableCol.AMOUNT);
		Assert.assertEquals(actualAmount, String.valueOf(bookTicketData.getTicketAmount()), "Ticket amount information is displayed incorrectly");
	}
	
}
