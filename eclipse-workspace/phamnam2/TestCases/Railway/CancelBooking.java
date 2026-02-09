package Railway;

import java.time.LocalDate;

import org.testng.annotations.Test;

import Common.Utilities;
import Constant.PageMenu;
import Constant.SeatType;
import Constant.StationCity;

public class CancelBooking extends TestBase{
	
	@Test
	public void TC16() {
		System.out.println("TC16: Verify that user can cancel a ticket");
		System.out.println("Pre-condition: an actived account is existing");
		HomePage homePage = new HomePage();
		homePage.open();
		
		RegisterAccount account = PreconditionHelper.createRandomAccount();
		account = PreconditionHelper.createActivedAccount(account, false, null, null);
		
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2: Login with a valid account");
		loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		homePage = loginPage.login(account.getEmail(), account.getPassword());
		
		System.out.println("Step 3: Book a ticket"); 
		BookTicketPage bookTicketPage = new BookTicketPage();
		bookTicketPage = homePage.gotoPage(PageMenu.BOOK_TICKET, BookTicketPage.class);
		LocalDate targetDate = LocalDate.now();
		int ticketAmount = 1;
		BookTicketData bookTicketData = new BookTicketData(targetDate, StationCity.NHA_TRANG, StationCity.HUE, SeatType.SBC, ticketAmount);
		Boolean isEditDepartFrom = true;
		
		bookTicketPage.bookTicket(bookTicketData, isEditDepartFrom);
		System.out.println("Step 4:  Click on \"My ticket\" tab"); 
		MyTicketPage myTicketPage = new MyTicketPage();
		myTicketPage = bookTicketPage.gotoPage(PageMenu.MY_TICKET, MyTicketPage.class);
		
		System.out.println("Step 5: Click on \"Cancel\" button of ticket which user want to cancel");
		System.out.println("Step 6: Click on \"OK\" button on Confirmation message \"Are you sure?\"");
		myTicketPage = myTicketPage.cancleBooking(bookTicketData.getDepartFrom(), bookTicketData.getArriveAt());
		String message = "The canceled ticket is not disappeared";
		System.out.println("VP: The canceled ticket is disappeared.");
		myTicketPage.verifyCancleBooking(bookTicketData.getDepartFrom(), bookTicketData.getArriveAt(), message);
		
	}
}
