package Railway;

import Constant.Constant;
import Guerrilla.GuerrillaHomePage;
import Common.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage extends GeneralPage{
	
	//Locators
	private final By _txtUsername = By.xpath("//input[@id='username']");
	private final By _txtPassword= By.xpath("//input[@id='password']");
	private final By _btnLogin = By.xpath("//input[@value='login']");
	private final By _lblLoginErrorMsg = By.xpath("//div[@id='content']//p[contains(@class, 'message error LoginForm')]");
	private final By _linkForgotPassword = By.xpath("//div[@id='content']//a[contains(@href,'ForgotPassword')]");
	private final By _txtEmailForgotPassword = By.xpath("//input[@id='email']");
	private final By _btnSendInstructions = By.xpath("//div[@id='content']//input[@value='Send Instructions']");
	private final By _txtNewPassword = By.xpath("//input[@id='newPassword']");
	private final By _txtConfirmNewPassword = By.xpath("//input[@id='confirmPassword']");
	private final By _btnResetPassword = By.xpath("//input[@value='Reset Password']");
	private final By _txtResetPasswordToken = By.xpath("//input[@id='resetToken']");
	private final By _lblMessage = By.xpath("//div[@id='content']//p[contains(@class,'message')]");
	
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
	
	public WebElement getTxtNewPassword() {
		return Constant.WEBDRIVER.findElement(_txtNewPassword);
	}
	
	public WebElement getTxtConfirmNewPassword() {
		return Constant.WEBDRIVER.findElement(_txtConfirmNewPassword);
	}
	
	public WebElement getBtnResetPassword() {
		return Constant.WEBDRIVER.findElement(_btnResetPassword);
	}
	
	public WebElement getTxtResetPasswordToken() {
		return Constant.WEBDRIVER.findElement(_txtResetPasswordToken);
	}
	
	public WebElement getLblMessage() {
		return Constant.WEBDRIVER.findElement(_lblMessage);
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
	
	public LoginPage forgotPassword(String email, String newPassword, Boolean isCheck, String expectedMsg) {
		LoginPage loginPage = new LoginPage();
		GuerrillaHomePage guerrillaHomePage = new GuerrillaHomePage();
		loginPage.getLinkForgotPassword().click();
		loginPage = sendInstructions(email);
		
		guerrillaHomePage.open();
		loginPage = guerrillaHomePage.confirmForgotPasswordEmail(email);
		
		if (isCheck) {
			Assert.assertTrue(Utilities.isTextboxNotEmpty(_txtResetPasswordToken), "\"Password Change Form\" is not shown with the reset password token");
		}
		
		this.getTxtNewPassword().clear();
		this.getTxtNewPassword().sendKeys(newPassword);
		
		this.getTxtConfirmNewPassword().clear();
		this.getTxtConfirmNewPassword().sendKeys(newPassword);
		
		this.getBtnResetPassword().click();
		
		if (isCheck) {
			String actualMsg = getLblMessage().getText();
			Assert.assertEquals(actualMsg, expectedMsg, "The message is not displayed as expected");
		}
		
		return loginPage;
	}
	
	public LoginPage sendInstructions(String email) {
		Constant.WEBDRIVER.findElement(_txtEmailForgotPassword).clear();
		Constant.WEBDRIVER.findElement(_txtEmailForgotPassword).sendKeys(email);
		
		Constant.WEBDRIVER.findElement(_btnSendInstructions).click();
		
		return new LoginPage();
	}
	
}
