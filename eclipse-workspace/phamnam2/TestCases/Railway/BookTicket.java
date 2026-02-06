package Railway;

import java.time.LocalDate;

import org.testng.annotations.Test;

import Common.Utilities;
import Constant.PageMenu;
import Constant.SeatType;
import Constant.StationCity;
import Constant.Constant;

public class BookTicket extends TestBase{

	@Test
	public void TC12() {
		System.out.println("Pre-condition: an actived account is existing");
		HomePage homePage = new HomePage();
		homePage.open();
		
		String registerEmail = Utilities.generateRandomEmail();
		String registerPassword = Utilities.generateRandomPassword();
		String registerPip = Utilities.generateRandomPIP();
		RegisterAccount account = new RegisterAccount(registerEmail, registerPassword, registerPip);
		
		account = PreconditionHelper.createActivedAccount(account, false, "", "");
		
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
		bookTicketData.setDepartDate(LocalDate.now().plusDays(2));
		bookTicketData.setDepartFrom(StationCity.NHA_TRANG);
		bookTicketData.setArriveAt(StationCity.HUE);
		bookTicketData.setSeatType(SeatType.SOFT_BED_AC);
		bookTicketData.setTicketAmount(1);
		
		BookTicketPage bookTicketPage = new BookTicketPage();
		bookTicketPage.bookTicket(bookTicketData);
		
	}
}
