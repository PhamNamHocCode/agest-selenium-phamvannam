package Railway;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.PageMenu;
import Constant.SeatType;
import Constant.StationCity;
import Constant.TicketTableCol;

public class CancelBooking extends TestBase{
	
	@Test
	public void TC16() {
		HomePage homePage = new HomePage();
		PreconditionHelper preconditionHelper = new PreconditionHelper();
		int ticketAmount = 1;
		LocalDate targetDate = LocalDate.now();
		BookTicketData bookTicketData = new BookTicketData(targetDate, StationCity.NHA_TRANG, StationCity.HUE, SeatType.SBC, ticketAmount);
		RegisterAccount account = PreconditionHelper.generateRandomRegisterAccount();

		System.out.println("TC16: Verify that user can cancel a ticket");
		System.out.println("Pre-condition: an actived account is existing");
		homePage = homePage.open();
		
		account = PreconditionHelper.createAnAccount(account);
		RegisterPage registerPage = homePage.gotoPage(PageMenu.REGISTER, RegisterPage.class);
		PreconditionHelper.activateAccount(account);
		
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage = homePage.open();
		
		System.out.println("Step 2: Login with a valid account");
		LoginPage loginPage = homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		homePage = loginPage.login(account);
		
		System.out.println("Step 3: Book a ticket"); 
		BookTicketPage bookTicketPage = homePage.gotoPage(PageMenu.BOOK_TICKET, BookTicketPage.class);
		
		bookTicketPage = preconditionHelper.bookTicket(bookTicketData);
		
		System.out.println("Step 4:  Click on \"My ticket\" tab"); 
		MyTicketPage myTicketPage = bookTicketPage.gotoPage(PageMenu.MY_TICKET, MyTicketPage.class);
		
		System.out.println("Step 5: Click on \"Cancel\" button of ticket which user want to cancel");
		System.out.println("Step 6: Click on \"OK\" button on Confirmation message \"Are you sure?\"");
		myTicketPage = myTicketPage.cancelBooking(bookTicketData.getDepartFrom(), bookTicketData.getArriveAt());
		
		System.out.println("VP: The canceled ticket is disappeared.");
		int departCol = TimetablePage.getColIndexByHeader(TicketTableCol.DEPART_STATION);
		int arriveCol = TimetablePage.getColIndexByHeader(TicketTableCol.ARRIVE_STATION);
		Boolean actualResult = myTicketPage.isTicketCanceled(departCol, arriveCol, bookTicketData);
		
		Assert.assertFalse(actualResult, "The canceled ticket is not disappeared");

	}
}
