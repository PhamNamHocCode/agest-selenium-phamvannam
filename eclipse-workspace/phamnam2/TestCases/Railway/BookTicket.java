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
		LoginPage loginPage = new LoginPage();
		BookTicketPage bookTicketPage = new BookTicketPage();
		PreconditionHelper preconditionHelper = new PreconditionHelper();
		int ticketAmout = 1;
		
		System.out.println("TC12: Verify that user can book 1 ticket at a time");
		System.out.println("Pre-condition: an actived account is existing");
		homePage.open();
		
		RegisterAccount account = PreconditionHelper.createRandomAccount();
		account = PreconditionHelper.createAnAccount(account);
		PreconditionHelper.activeAccount(account);
		
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2: Login with a valid account");
		loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		homePage = loginPage.login(account.getEmail(), account.getPassword());
		
		System.out.println("Step 3: Click on \"Book ticket\" tab"); 
		bookTicketPage = homePage.gotoPage(PageMenu.BOOK_TICKET, BookTicketPage.class);
		LocalDate targetDate = bookTicketPage.getSelectedDepartDate(2, Constant.DATE_FORMAT);
		
		System.out.println("Step 4: Select the next 2 days from \"Depart date\""); 
		System.out.println("Step 5: Select Depart from \"Nha Trang\" and Arrive at \"Huế\""); 
		System.out.println("Step 6: Select \"Soft bed with air conditioner\" for \"Seat type\""); 
		System.out.println("Step 7: Select \"1\" for \"Ticket amount\""); 
		System.out.println("Step 8: Click on \"Book ticket\" button"); 
		BookTicketData bookTicketData = new BookTicketData(targetDate, StationCity.NHA_TRANG, StationCity.HUE, SeatType.SBC, ticketAmout);
		preconditionHelper.bookTicket(bookTicketData);
		
		System.out.println("VP: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		String expectedMsg = "Ticket booked successfully!";
		String message = "The message is not displayed as expected";
		String actualCenterMsg = bookTicketPage.getCenterMsg();
		Assert.assertEquals(actualCenterMsg, expectedMsg, message);
		
		String actualDepartStation = bookTicketPage.getTableCellValue(bookTicketData.getDepartFrom().getDisplayText(), TicketTableCol.DEPART_STATION);
		String actualArriveStation = bookTicketPage.getTableCellValue(bookTicketData.getArriveAt().getDisplayText(), TicketTableCol.ARRIVE_STATION);
		String actualSeatType = bookTicketPage.getTableCellValue(bookTicketData.getSeatType().getDisplayText(), TicketTableCol.SEAT_TYPE);
		String actualDepartDate = bookTicketPage.getTableCellValue(Utilities.formatDate(bookTicketData.getDepartDate()).toString(), TicketTableCol.DEPART_DATE);
		String actualAmount = bookTicketPage.getTableCellValue(String.valueOf(bookTicketData.getTicketAmount()), TicketTableCol.AMOUNT);
		
		Assert.assertEquals(actualDepartStation, bookTicketData.getDepartFrom().getDisplayText(), "Departure information is not displaying correctly");
		Assert.assertEquals(actualArriveStation, bookTicketData.getArriveAt().getDisplayText(), "Arrival station information is displayed incorrectly");
		Assert.assertEquals(actualSeatType, bookTicketData.getSeatType().getDisplayText(), "Seat type information is displayed incorrectly");
		Assert.assertEquals(actualDepartDate, Utilities.formatDate(bookTicketData.getDepartDate()).toString(), "Departure date information is displayed incorrectly");
		Assert.assertEquals(actualAmount, String.valueOf(bookTicketData.getTicketAmount()), "Ticket amount information is displayed incorrectly");
	}
	
	@Test
	public void TC13() {
		HomePage homePage = new HomePage();
		LoginPage loginPage = new LoginPage();
		BookTicketPage bookTicketPage = new BookTicketPage();
		PreconditionHelper preconditionHelper = new PreconditionHelper();
		int ticketAmout = 5;
		
		System.out.println("TC13: Verify that user can book many tickets at a time");
		System.out.println("Pre-condition: an actived account is existing");
		homePage.open();
		
		RegisterAccount account = PreconditionHelper.createRandomAccount();
		account = PreconditionHelper.createAnAccount(account);
		PreconditionHelper.activeAccount(account);
		
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2: Login with a valid account");
		loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		homePage = loginPage.login(account.getEmail(), account.getPassword());
		
		System.out.println("Step 3: Click on \"Book ticket\" tab"); 
		bookTicketPage = homePage.gotoPage(PageMenu.BOOK_TICKET, BookTicketPage.class);
		LocalDate targetDate = bookTicketPage.getSelectedDepartDate(25, Constant.DATE_FORMAT);

		
		System.out.println("Step 4: Select the next 25 days from \"Depart date\""); 
		System.out.println("Step 5: Select \"Nha Trang\" for \"Depart from\" and \"Sài Gòn\" for \"Arrive at\""); 
		System.out.println("Step 6: Select \"Soft seat with air conditioner\" for \"Seat type\""); 
		System.out.println("Step 7: Select \"5\" for \"Ticket amount\""); 
		System.out.println("Step 8: Click on \"Book ticket\" button"); 
		BookTicketData bookTicketData = new BookTicketData(targetDate, StationCity.NHA_TRANG, StationCity.SAI_GON, SeatType.SSC, ticketAmout);
		preconditionHelper.bookTicket(bookTicketData);
		
		System.out.println("VP: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		String expectedMsg = "Ticket booked successfully!";
		String message = "The message is not displayed as expected";
		String actualCenterMsg = bookTicketPage.getCenterMsg();
		Assert.assertEquals(actualCenterMsg, expectedMsg, message);
		
		String actualDepartStation = bookTicketPage.getTableCellValue(bookTicketData.getDepartFrom().getDisplayText(), TicketTableCol.DEPART_STATION);
		String actualArriveStation = bookTicketPage.getTableCellValue(bookTicketData.getArriveAt().getDisplayText(), TicketTableCol.ARRIVE_STATION);
		String actualSeatType = bookTicketPage.getTableCellValue(bookTicketData.getSeatType().getDisplayText(), TicketTableCol.SEAT_TYPE);
		String actualDepartDate = bookTicketPage.getTableCellValue(Utilities.formatDate(bookTicketData.getDepartDate()).toString(), TicketTableCol.DEPART_DATE);
		String actualAmount = bookTicketPage.getTableCellValue(String.valueOf(bookTicketData.getTicketAmount()), TicketTableCol.AMOUNT);
		
		Assert.assertEquals(actualDepartStation, bookTicketData.getDepartFrom().getDisplayText(), "Departure information is not displaying correctly");
		Assert.assertEquals(actualArriveStation, bookTicketData.getArriveAt().getDisplayText(), "Arrival station information is displayed incorrectly");
		Assert.assertEquals(actualSeatType, bookTicketData.getSeatType().getDisplayText(), "Seat type information is displayed incorrectly");
		Assert.assertEquals(actualDepartDate, Utilities.formatDate(bookTicketData.getDepartDate()).toString(), "Departure date information is displayed incorrectly");
		Assert.assertEquals(actualAmount, String.valueOf(bookTicketData.getTicketAmount()), "Ticket amount information is displayed incorrectly");
	}

	@Test
	public void TC14() {
		HomePage homePage = new HomePage();
		LoginPage loginPage = new LoginPage();
		TimetablePage timetablePage = new TimetablePage();
		TicketPricePage ticketPricePage = new TicketPricePage();
		StationCity departStation = StationCity.DA_NANG;
		StationCity arriveStation = StationCity.SAI_GON;
		Map<SeatType, String> prices = new EnumMap<>(SeatType.class);
		prices.put(SeatType.HS, "310000");
		prices.put(SeatType.SS, "335000");
		prices.put(SeatType.SSC, "360000");
		prices.put(SeatType.HB, "410000");
		prices.put(SeatType.SB, "460000");
		prices.put(SeatType.SBC, "510000");
		
		System.out.println("TC14: Verify that user can check price of ticket from Timetable");
		System.out.println("Pre-condition: an actived account is existing");
		homePage.open();
		
		RegisterAccount account = PreconditionHelper.createRandomAccount();
		account = PreconditionHelper.createAnAccount(account);
		PreconditionHelper.activeAccount(account);
		
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2: Login with a valid account");
		loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		homePage = loginPage.login(account.getEmail(), account.getPassword());
		
		System.out.println("Step 3: Click on \"Timetable\" tab");
		timetablePage = homePage.gotoPage(PageMenu.TIMETABLE, TimetablePage.class);
		
		System.out.println("Step 4: Click on \"check price\" link of the route from \"Đà Nẵng\" to \"Sài Gòn\""); 
		ticketPricePage = timetablePage.clickCheckPriceLink(departStation, arriveStation);
		
		System.out.println("VP: \"Ticket Price\" page is loaded.\r\n"
				+ "Ticket table shows \"Ticket price from Đà Nẵng to Sài Gòn\".\r\n"
				+ "Price for each seat displays correctly\r\n"
				+ "HS = 310000, SS = 335000, SSC = 360000, HB = 410000, SB = 460000, SBC = 510000");
		String expectedHeaderMsg = "Ticket price from Đà Nẵng to Sài Gòn";
		String actualHeaderMsg = ticketPricePage.getTicketPriceTblHeader();
		Assert.assertEquals(actualHeaderMsg, expectedHeaderMsg, "The ticket table is not display as expected");
		
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
		LoginPage loginPage = new LoginPage();
		TimetablePage timetablePage = new TimetablePage();
		PreconditionHelper preconditionHelper = new PreconditionHelper();
		LocalDate targetDate = LocalDate.now().plusDays(1);
		BookTicketPage bookTicketPage = new BookTicketPage();
		int ticketAmount = 5;
		
		System.out.println("TC15: Verify that user can book ticket from Timetable");
		System.out.println("Pre-condition: an actived account is existing");
		homePage.open();
		
		RegisterAccount account = PreconditionHelper.createRandomAccount();
		account = PreconditionHelper.createAnAccount(account);
		PreconditionHelper.activeAccount(account);
		
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2: Login with a valid account");
		loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		homePage = loginPage.login(account.getEmail(), account.getPassword());
		
		System.out.println("Step 3: Click on \"Timetable\" tab"); 
		timetablePage = homePage.gotoPage(PageMenu.TIMETABLE, TimetablePage.class);
		
		System.out.println("Step 4: Click on book ticket of route \"Quảng Ngãi\" to \"Huế\""); 
		StationCity departStation = StationCity.QUANG_NGAI;
		StationCity arriveStation = StationCity.HUE;
		timetablePage.bookTicketFromTimetable(departStation, arriveStation);
		
		System.out.println("VP: Book ticket form is shown with the corrected \"depart from\" and \"Arrive at\"");
		String msgDepart = "The depart selection is not displayed as expected";
		String msgArrive = "The arrive selection is not displayed as expected";
		String actualDepart = bookTicketPage.getSelectedDepartStation();
	    String actualArrie = bookTicketPage.getSelectedArriveStation();
	    Assert.assertEquals(actualDepart, departStation.getDisplayText(), msgDepart);
	    Assert.assertEquals(actualArrie, arriveStation.getDisplayText(), msgArrive);
		
		System.out.println("Step 5: Select Depart date = tomorrow");
		System.out.println("Step 6: Select Ticket amount = 5");
		System.out.println("Step 7: Click on \"Book ticket\" button");
		BookTicketData bookTicketData = new BookTicketData(targetDate, departStation, arriveStation, null, ticketAmount);
		preconditionHelper.bookTicket(bookTicketData);
		
		System.out.println("VP: Message \"Ticket booked successfully!\" displays. "
				+ "Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		String expectedMsg = "Ticket booked successfully!";
		String message = "The message is not displayed as expected";
		String actualCenterMsg = bookTicketPage.getCenterMsg();
		Assert.assertEquals(actualCenterMsg, expectedMsg, message);
		
		String actualDepartStation = bookTicketPage.getTableCellValue(bookTicketData.getDepartFrom().getDisplayText(), TicketTableCol.DEPART_STATION);
		String actualArriveStation = bookTicketPage.getTableCellValue(bookTicketData.getArriveAt().getDisplayText(), TicketTableCol.ARRIVE_STATION);
		String actualSeatType = bookTicketPage.getTableCellValue(bookTicketData.getSeatType().getDisplayText(), TicketTableCol.SEAT_TYPE);
		String actualDepartDate = bookTicketPage.getTableCellValue(Utilities.formatDate(bookTicketData.getDepartDate()).toString(), TicketTableCol.DEPART_DATE);
		String actualAmount = bookTicketPage.getTableCellValue(String.valueOf(bookTicketData.getTicketAmount()), TicketTableCol.AMOUNT);
		
		Assert.assertEquals(actualDepartStation, bookTicketData.getDepartFrom().getDisplayText(), "Departure information is not displaying correctly");
		Assert.assertEquals(actualArriveStation, bookTicketData.getArriveAt().getDisplayText(), "Arrival station information is displayed incorrectly");
		Assert.assertEquals(actualSeatType, bookTicketData.getSeatType().getDisplayText(), "Seat type information is displayed incorrectly");
		Assert.assertEquals(actualDepartDate, Utilities.formatDate(bookTicketData.getDepartDate()).toString(), "Departure date information is displayed incorrectly");
		Assert.assertEquals(actualAmount, String.valueOf(bookTicketData.getTicketAmount()), "Ticket amount information is displayed incorrectly");
	}
	
}
