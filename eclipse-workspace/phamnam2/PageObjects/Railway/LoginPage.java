package Railway;

import Constant.Constant;
import Common.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends GeneralPage{
	
	//Locators
	private final By _txtUsername = By.xpath("//input[@id='username']");
	private final By _txtPassword= By.xpath("//input[@id='password']");
	private final By _btnLogin = By.xpath("//input[@value='login']");
	private final By _lblLoginErrorMsg = By.xpath("//div[@id='content']//p[contains(@class, 'message error LoginForm')]");
	private final By _linkForgotPassword = By.xpath("//div[@id='content']//a[contains(@href,'ForgotPassword')]");
	private final By _txtEmailForgotPassword = By.xpath("//input[@id='email']");
	private final By _btnSendInstructions = By.xpath("//div[@id='content']//input[@value='Send Instructions']");
	
	// Elements
	public WebElement getTxtUsername() {
		return Constant.WEBDRIVER.findElement(_txtUsername);
	}
	
	public WebElement getTxtPassword() {
		return Constant.WEBDRIVER.findElement(_txtPassword);
	}

	public WebElement getBtnLogin() {
		return Constant.WEBDRIVER.findElement(_btnLogin);
	}
	
	public WebElement getLblLoginErrorMsg() {
		return Constant.WEBDRIVER.findElement(_lblLoginErrorMsg);
	}
	
	public WebElement getLinkForgotPassword() {
		return Constant.WEBDRIVER.findElement(_linkForgotPassword);
	}
	
	public WebElement getTxtEmailForgotPassword() {
		return Constant.WEBDRIVER.findElement(_txtEmailForgotPassword);
	}
	
	public WebElement getBtnSendInstructions() {
		return Constant.WEBDRIVER.findElement(_btnSendInstructions);
	}
	
	// Methods
	public LoginPage login(String username, String password) {
	    if (username != null) {
	        this.getTxtUsername().clear();
	        this.getTxtUsername().sendKeys(username);
	    }

	    if (password != null) {
	        this.getTxtPassword().clear();
	        this.getTxtPassword().sendKeys(password);
	    }

	    Utilities.scrollToElement(this.getBtnLogin());
	    this.getBtnLogin().click();

	    return this;
	}
	
	public String getLoginErrorMsg() {
		return this.getLblLoginErrorMsg().getText();
	}
	
}
