package Railway;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;


import Constant.PageMenu;
import Constant.SeatType;
import Constant.StationCity;
import Constant.Constant;

public class BookTicket extends TestBase{

	@Test
	public void TC12() {
		System.out.println("TC12: Verify that user can book 1 ticket at a time");
		System.out.println("Pre-condition: an actived account is existing");
		homePage.open();
		
		RegisterAccount account = PreconditionHelper.createRandomAccount();
		account = PreconditionHelper.createActivedAccount(account, false, null, null);
		
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2: Login with a valid account");
		homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		new LoginPage().login(account.getEmail(), account.getPassword());
		
		System.out.println("Step 3: Click on \"Book ticket\" tab"); 
		bookTicketPage = homePage.gotoPage(PageMenu.BOOK_TICKET, BookTicketPage.class);
		
		System.out.println("Step 4: Select the next 2 days from \"Depart date\""); 
		System.out.println("Step 5: Select Depart from \"Nha Trang\" and Arrive at \"Huế\""); 
		System.out.println("Step 6: Select \"Soft bed with air conditioner\" for \"Seat type\""); 
		System.out.println("Step 7: Select \"1\" for \"Ticket amount\""); 
		System.out.println("Step 8: Click on \"Book ticket\" button"); 
		LocalDate targetDate =
		        LocalDate.parse(new Select(bookTicketPage.getSltDepartDate()).getOptions().get(0).getText(), Constant.DATE_FORMAT)
		                 .plusDays(2);
		int ticketAmout = 1;
		BookTicketData bookTicketData = new BookTicketData(targetDate, StationCity.NHA_TRANG, StationCity.HUE, SeatType.SBC, ticketAmout);
		Boolean isEditDepartFrom = true;
		
		bookTicketPage.bookTicket(bookTicketData, isEditDepartFrom);
		System.out.println("VP: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		bookTicketPage.verifyTicket(bookTicketData);
	}
	
	@Test
	public void TC13() {
		System.out.println("TC13: Verify that user can book many tickets at a time");
		System.out.println("Pre-condition: an actived account is existing");
		homePage.open();
		
		RegisterAccount account = PreconditionHelper.createRandomAccount();
		account = PreconditionHelper.createActivedAccount(account, false, null, null);
		
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2: Login with a valid account");
		homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		new LoginPage().login(account.getEmail(), account.getPassword());
		
		System.out.println("Step 3: Click on \"Book ticket\" tab"); 
		bookTicketPage = homePage.gotoPage(PageMenu.BOOK_TICKET, BookTicketPage.class);
		
		System.out.println("Step 4: Select the next 25 days from \"Depart date\""); 
		System.out.println("Step 5: Select \"Nha Trang\" for \"Depart from\" and \"Sài Gòn\" for \"Arrive at\""); 
		System.out.println("Step 6: Select \"Soft seat with air conditioner\" for \"Seat type\""); 
		System.out.println("Step 7: Select \"5\" for \"Ticket amount\""); 
		System.out.println("Step 8: Click on \"Book ticket\" button"); 
		LocalDate targetDate =
		        LocalDate.parse(new Select(bookTicketPage.getSltDepartDate()).getOptions().get(0).getText(), Constant.DATE_FORMAT)
		                 .plusDays(25);
		int ticketAmout = 5;
		BookTicketData bookTicketData = new BookTicketData(targetDate, StationCity.NHA_TRANG, StationCity.SAI_GON, SeatType.SSC, ticketAmout);
		Boolean isEditDepartFrom = true;
		
		bookTicketPage.bookTicket(bookTicketData, isEditDepartFrom);
		System.out.println("VP: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		bookTicketPage.verifyTicket(bookTicketData);
	}
	
	@Test
	public void TC14() {
		System.out.println("TC14: Verify that user can check price of ticket from Timetable");
		System.out.println("Pre-condition: an actived account is existing");
		homePage.open();
		
		RegisterAccount account = PreconditionHelper.createRandomAccount();
		account = PreconditionHelper.createActivedAccount(account, false, null, null);
		
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2: Login with a valid account");
		homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		new LoginPage().login(account.getEmail(), account.getPassword());
		
		System.out.println("Step 3: Click on \"Timetable\" tab");
		timetablePage = homePage.gotoPage(PageMenu.TIMETABLE, TimetablePage.class);
		
		System.out.println("Step 4: Click on \"check price\" link of the route from \"Đà Nẵng\" to \"Sài Gòn\""); 
		StationCity departStation = StationCity.DA_NANG;
		StationCity arriveStation = StationCity.SAI_GON;
		TicketPricePage ticketPricePage = new TicketPricePage();
		ticketPricePage = timetablePage.checkPrice(departStation, arriveStation);
		
		String expectedHeaderMsg = "Ticket price from Đà Nẵng to Sài Gòn";
		Map<SeatType, String> prices = new EnumMap<>(SeatType.class);
		prices.put(SeatType.HS, "310000");
		prices.put(SeatType.SS, "335000");
		prices.put(SeatType.SSC, "360000");
		prices.put(SeatType.HB, "410000");
		prices.put(SeatType.SB, "460000");
		prices.put(SeatType.SBC, "510000");
		
		System.out.println("VP: \"Ticket Price\" page is loaded.\r\n"
				+ "Ticket table shows \"Ticket price from Đà Nẵng to Sài Gòn\".\r\n"
				+ "Price for each seat displays correctly\r\n"
				+ "HS = 310000, SS = 335000, SSC = 360000, HB = 410000, SB = 460000, SBC = 510000");
		ticketPricePage.checkTableData(expectedHeaderMsg, prices);
	}
	
	@Test
	public void TC15() {
		System.out.println("TC15: Verify that user can book ticket from Timetable");
		System.out.println("Pre-condition: an actived account is existing");
		homePage.open();
		
		RegisterAccount account = PreconditionHelper.createRandomAccount();
		account = PreconditionHelper.createActivedAccount(account, false, null, null);
		
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2: Login with a valid account");
		homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		new LoginPage().login(account.getEmail(), account.getPassword());
		
		System.out.println("Step 3: Click on \"Timetable\" tab"); 
		TimetablePage timetablePage = new TimetablePage();
		timetablePage = homePage.gotoPage(PageMenu.TIMETABLE, TimetablePage.class);
		
		System.out.println("Step 4: Click on book ticket of route \"Quảng Ngãi\" to \"Huế\""); 
		StationCity departStation = StationCity.QUANG_NGAI;
		StationCity arriveStation = StationCity.HUE;
		timetablePage.bookTicket(departStation, arriveStation);
		String msgDepart = "The depart selection is not displayed as expected";
		String msgArrive = "The arrive selection is not displayed as expected";
		System.out.println("VP: Book ticket form is shown with the corrected \"depart from\" and \"Arrive at\"");
		bookTicketPage.verifyBookTicketForm(departStation.getDisplayText(), arriveStation.getDisplayText(), msgDepart, msgArrive);
		
		System.out.println("Step 5: Select Depart date = tomorrow");
		System.out.println("Step 6: Select Ticket amount = 5");
		System.out.println("Step 7: Click on \"Book ticket\" button");
		LocalDate targetDate = LocalDate.now().plusDays(1);
		int ticketAmount = 5;
		BookTicketData bookTicketData = new BookTicketData(targetDate, departStation, arriveStation, null, ticketAmount);
		Boolean isEditDepartFrom = false;
		bookTicketPage.bookTicket(bookTicketData, isEditDepartFrom);
		
		String expectedMsg = "Ticket booked successfully!";
		String message = "The message is not displayed as expected";
		bookTicketPage.verifyCenterMsg(expectedMsg, message);
		System.out.println("VP: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		bookTicketPage.verifyTicket(bookTicketData);
	}
	
}
