package Railway;

import Common.Utilities;
import Constant.Constant;
import Constant.PageMenu;
import Guerrilla.GuerrillaHomePage;

public class PreconditionHelper {
	
	//Account
	public static RegisterAccount createAnAccount(RegisterAccount account) {
		HomePage homePage = new HomePage();
		GuerrillaHomePage guerrillaHomePage = new GuerrillaHomePage();
		String railwayHanlde = Constant.WEBDRIVER.getWindowHandle();
		
		Utilities.openUrlInNewTab(Constant.GUERRILLA_URL);
		account.setEmail(guerrillaHomePage.createNewEmail(account.getEmail()));
		
		Constant.WEBDRIVER.switchTo().window(railwayHanlde);
		homePage.getLinkCreateAccount().click();
		
		return account;
	}
	
	public static void activeAccount(RegisterAccount account) {
		RegisterPage registerPage = new RegisterPage();
		GuerrillaHomePage guerrillaHomePage = new GuerrillaHomePage();
		
		registerPage = registerPage.registerNewAccount(account);
		guerrillaHomePage.open();
		guerrillaHomePage.confirmRegistrationEmail(account.getEmail());
	}
	
	public static RegisterAccount createUnactiveAccout(RegisterAccount account) {
		RegisterPage registerPage = new RegisterPage();
		String railwayHanlde = Constant.WEBDRIVER.getWindowHandle();
		
		Utilities.openUrlInNewTab(Constant.GUERRILLA_URL);
		GuerrillaHomePage guerrillaHomePage = new GuerrillaHomePage();
		account.setEmail(guerrillaHomePage.createNewEmail(account.getEmail()));

		Constant.WEBDRIVER.switchTo().window(railwayHanlde);
		registerPage.gotoPage(PageMenu.REGISTER, RegisterPage.class);
		
		return account;
	}
	
	public static RegisterAccount createRandomAccount() {
		return new RegisterAccount(
			Utilities.generateRandomEmail(5),
			Utilities.generateRandomPassword(5),
			Utilities.generateRandomPIP(5)
		);
	}
	
	//Book ticket
	public BookTicketPage bookTicket(BookTicketData data) {
		BookTicketPage bookTicketPage = new BookTicketPage();
		
		if (data.getDepartDate() != null) {
			if (!BookTicketPage.isDepartDateAvailable(Utilities.formatDate(data.getDepartDate()))) {
			    throw new IllegalStateException(
			        "Cannot select a date because select does not have a suitable date option: " + Utilities.formatDate(data.getDepartDate())
			    );
			}
			BookTicketPage.selectDepartDate(data.getDepartDate());
		}
		if (data.getDepartFrom() != null) {
			BookTicketPage.selectDepartFrom(data.getDepartFrom());
		}
		if (data.getArriveAt() != null) {
			bookTicketPage.waintUntilArriveStationRefreshed();
			BookTicketPage.selectArriveAt(data.getArriveAt());
		}
		if (data.getSeatType() != null) {
			BookTicketPage.selectSeatType(data.getSeatType());
		}
		BookTicketPage.selectTicketAmount(data.getTicketAmount());
        
        Utilities.scrollToElement(BookTicketPage.getBtnBookTicket());
        BookTicketPage.clickBookTicket();

        return new BookTicketPage();
    }
	
	
}
