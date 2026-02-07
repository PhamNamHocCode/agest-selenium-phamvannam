package Railway;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;


import Common.Utilities;
import Constant.PageMenu;
import Constant.SeatType;
import Constant.StationCity;
import Constant.Constant;

public class BookTicket extends TestBase{

	@Test
	public void TC12() {
		System.out.println("TC12: Verify that user can book 1 ticket at a time");
		System.out.println("Pre-condition: an actived account is existing");
		HomePage homePage = new HomePage();
		homePage.open();
		
		String registerEmail = Utilities.generateRandomEmail();
		String registerPassword = Utilities.generateRandomPassword();
		String registerPip = Utilities.generateRandomPIP();
		RegisterAccount account = new RegisterAccount(registerEmail, registerPassword, registerPip);
		
		account = PreconditionHelper.createActivedAccount(account, false, null, null);
		
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2: Login with a valid account");
		homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		new LoginPage().login(Constant.VALID_USERNAME, Constant.VALID_PASSWORD);
		
		System.out.println("Step 3: Click on \"Book ticket\" tab"); 
		homePage.gotoPage(PageMenu.BOOK_TICKET, BookTicketPage.class);
		
		System.out.println("Step 4: Select the next 2 days from \"Depart date\""); 
		System.out.println("Step 5: Select Depart from \"Nha Trang\" and Arrive at \"Huế\""); 
		System.out.println("Step 6: Select \"Soft bed with air conditioner\" for \"Seat type\""); 
		System.out.println("Step 7: Select \"1\" for \"Ticket amount\""); 
		System.out.println("Step 8: Click on \"Book ticket\" button"); 
		BookTicketData bookTicketData = new BookTicketData();
		BookTicketPage bookTicketPage = new BookTicketPage();
		LocalDate targetDate =
		        LocalDate.parse(new Select(bookTicketPage.getSltDepartDate()).getOptions().get(0).getText(), Constant.DATE_FORMAT)
		                 .plusDays(2);
		bookTicketData.setDepartDate(targetDate);
		bookTicketData.setDepartFrom(StationCity.NHA_TRANG);
		bookTicketData.setArriveAt(StationCity.HUE);
		bookTicketData.setSeatType(SeatType.SOFT_BED_AC);
		bookTicketData.setTicketAmount(1);
		
		bookTicketPage.bookTicket(bookTicketData);
		bookTicketPage.checkTicket(bookTicketData);
		
	}
	
	@Test
	public void TC13() {
		System.out.println("TC13: Verify that user can book many tickets at a time");
		System.out.println("Pre-condition: an actived account is existing");
		HomePage homePage = new HomePage();
		homePage.open();
		
		String registerEmail = Utilities.generateRandomEmail();
		String registerPassword = Utilities.generateRandomPassword();
		String registerPip = Utilities.generateRandomPIP();
		RegisterAccount account = new RegisterAccount(registerEmail, registerPassword, registerPip);
		
		account = PreconditionHelper.createActivedAccount(account, false, null, null);
		
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2: Login with a valid account");
		homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		new LoginPage().login(Constant.VALID_USERNAME, Constant.VALID_PASSWORD);
		
		System.out.println("Step 3: Click on \"Book ticket\" tab"); 
		homePage.gotoPage(PageMenu.BOOK_TICKET, BookTicketPage.class);
		
		System.out.println("Step 4: Select the next 25 days from \"Depart date\"	"); 
		System.out.println("Step 5: Select \"Nha Trang\" for \"Depart from\" and \"Sài Gòn\" for \"Arrive at\""); 
		System.out.println("Step 6: Select \"Soft seat with air conditioner\" for \"Seat type\""); 
		System.out.println("Step 7: Select \"5\" for \"Ticket amount\""); 
		System.out.println("Step 8: Click on \"Book ticket\" button"); 
		BookTicketData bookTicketData = new BookTicketData();
		BookTicketPage bookTicketPage = new BookTicketPage();
		LocalDate targetDate =
		        LocalDate.parse(new Select(bookTicketPage.getSltDepartDate()).getOptions().get(0).getText(), Constant.DATE_FORMAT)
		                 .plusDays(25);
		bookTicketData.setDepartDate(targetDate);
		bookTicketData.setDepartFrom(StationCity.NHA_TRANG);
		bookTicketData.setArriveAt(StationCity.SAI_GON);
		bookTicketData.setSeatType(SeatType.SOFT_SEAT_AC);
		bookTicketData.setTicketAmount(5);
		
		bookTicketPage.bookTicket(bookTicketData);
		bookTicketPage.checkTicket(bookTicketData);
		
	}
}
