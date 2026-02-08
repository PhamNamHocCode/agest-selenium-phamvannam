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
		
		String registerEmail = Utilities.generateRandomEmail();
		String registerPassword = Utilities.generateRandomPassword();
		String registerPip = Utilities.generateRandomPIP();
		RegisterAccount account = new RegisterAccount(registerEmail, registerPassword, registerPip);
		
		account = PreconditionHelper.createActivedAccount(account, false, null, null);
		
		System.out.println("Step 1: Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2: Login with a valid account");
		homePage.gotoPage(PageMenu.LOGIN, LoginPage.class);
		new LoginPage().login(account.getEmail(), account.getPassword());
		
		System.out.println("Step 3: Book a ticket"); 
		BookTicketPage bookTicketPage = new BookTicketPage();
		BookTicketData bookTicketData = new BookTicketData();
		bookTicketPage = homePage.gotoPage(PageMenu.BOOK_TICKET, BookTicketPage.class);

		bookTicketData.setDepartDate(LocalDate.now());
		bookTicketData.setDepartFrom(StationCity.NHA_TRANG);
		bookTicketData.setArriveAt(StationCity.HUE);
		bookTicketData.setSeatType(SeatType.SBC);
		bookTicketData.setTicketAmount(1);
		Boolean isEditDepartFrom = true;
		
		bookTicketPage.bookTicket(bookTicketData, isEditDepartFrom);
		System.out.println("Step 4:  Click on \"My ticket\" tab"); 
		MyTicketPage myTicketPage = new MyTicketPage();
		myTicketPage = bookTicketPage.gotoPage(PageMenu.MY_TICKET, MyTicketPage.class);
		
		System.out.println("Step 5: Click on \"Cancel\" button of ticket which user want to cancel");
		System.out.println("Step 6: Click on \"OK\" button on Confirmation message \"Are you sure?\"");
		myTicketPage = myTicketPage.cancleBooking(bookTicketData.getDepartFrom(), bookTicketData.getArriveAt());
		String message = "The canceled ticket is not disappeared";
		myTicketPage.verifyCancleBooking(bookTicketData.getDepartFrom(), bookTicketData.getArriveAt(), message);
		
	}
}
