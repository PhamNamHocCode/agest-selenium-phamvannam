package Railway;

import Constant.Constant;
import Constant.LoginElement;
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
	private final By _lblForgotPasswordGeneralMsg = By.xpath("//div[@id='content']//p[contains(@class,'message')]");
	private final By _lblForgotPasswordConfirmPasswordMsg = By.xpath("//label[@for='confirmPassword' and @class='validation-error']");
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
	
	public WebElement getLblForgotPasswordGeneralMsg() {
		return Constant.WEBDRIVER.findElement(_lblForgotPasswordGeneralMsg);
	}
	
	public WebElement getLblForgotPasswordConfirmPasswordMsg() {
		return Constant.WEBDRIVER.findElement(_lblForgotPasswordConfirmPasswordMsg);
	}
	
	public By getLocator(LoginElement element) {
		switch (element) {
        case USERNAME:
            return _txtUsername;

        case PASSWORD:
            return _txtPassword;

        case LOGIN_ERROR_MSG:
            return _lblLoginErrorMsg;

        case FORGOT_PASSWROD_GENERAL_MSG:
            return _lblForgotPasswordGeneralMsg;

        case FORGOT_PASSWORD_LINK:
            return _linkForgotPassword;
         
        case FORGOT_PASSWORD_CONFIRM_PASSWORD_MSG:
        	return _lblForgotPasswordConfirmPasswordMsg;
        	
        default:
            throw new IllegalArgumentException("Unsupported element: " + element);
		}
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
	
	public LoginPage forgotPassword(String email, String newPassword, String confirmPassword, Boolean isCheck, String expectedGenralMsg) {
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
		this.getTxtConfirmNewPassword().sendKeys(confirmPassword);
		
		this.getBtnResetPassword().click();
		
		if (isCheck) {
			String actualMsg = getLblForgotPasswordGeneralMsg().getText();
			Assert.assertEquals(actualMsg, expectedGenralMsg, "The message is not displayed as expected");
		}
		
		return loginPage;
	}
	
	public void checkLblExists(LoginElement element, String expectedMsg) {
		By locator = getLocator(element);
		String actualMsg = Utilities.getElementTextVisible(locator);
		
		if ("NOT_FOUND".equals(actualMsg)) {
	        Assert.fail("The error messege NOT FOUND: " + element);
	    }

	    if ("HIDDEN".equals(actualMsg)) {
	        Assert.fail("The error messege is HIDDEN: " + element);
	    }

	    Assert.assertEquals(
	        actualMsg,
	        expectedMsg,
	        "The error messege is not displayed as expected: " + element
	    );	
	}
	
	public LoginPage sendInstructions(String email) {
		Constant.WEBDRIVER.findElement(_txtEmailForgotPassword).clear();
		Constant.WEBDRIVER.findElement(_txtEmailForgotPassword).sendKeys(email);
		
		Constant.WEBDRIVER.findElement(_btnSendInstructions).click();
		
		return new LoginPage();
	}
	
}
