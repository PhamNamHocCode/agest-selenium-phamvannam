package Railway;

import Constant.Constant;
import Constant.PageMenu;
import Constant.FieldsLogin;
import Common.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends GeneralPage{
	//Locators
	private final static By _txtUsername = By.xpath("//input[@id='username']");
	private final static By _txtPassword= By.xpath("//input[@id='password']");
	private final By _btnLogin = By.xpath("//input[@value='login']");
	private final static By _lblLoginErrorMsg = By.xpath("//div[@id='content']//p[contains(@class, 'message error LoginForm')]");
	private final static By _linkForgotPassword = By.xpath("//div[@id='content']//a[contains(@href,'ForgotPassword')]");
	private final By _txtEmailForgotPassword = By.xpath("//input[@id='email']");
	private final By _btnSendInstructions = By.xpath("//div[@id='content']//input[@value='Send Instructions']");
	private final By _txtNewPassword = By.xpath("//input[@id='newPassword']");
	private final By _txtConfirmNewPassword = By.xpath("//input[@id='confirmPassword']");
	private final By _btnResetPassword = By.xpath("//input[@value='Reset Password']");
	private final static By _txtResetPasswordToken = By.xpath("//input[@id='resetToken']");
	private final static By _lblForgotPasswordGeneralMsg = By.xpath("//div[@id='content']//p[contains(@class,'message')]");
	private final static By _lblForgotPasswordConfirmPasswordMsg = By.xpath("//label[@for='confirmPassword' and @class='validation-error']");
	
	// Elements
	protected WebElement getTxtUsername() {
		return Constant.WEBDRIVER.findElement(_txtUsername);
	}
	
	protected WebElement getTxtPassword() {
		return Constant.WEBDRIVER.findElement(_txtPassword);
	}

	protected WebElement getBtnLogin() {
		return Constant.WEBDRIVER.findElement(_btnLogin);
	}
	
	protected WebElement getLblLoginErrorMsg() {
		return Constant.WEBDRIVER.findElement(_lblLoginErrorMsg);
	}
	
	protected WebElement getLinkForgotPassword() {
		return Constant.WEBDRIVER.findElement(_linkForgotPassword);
	}
	
	protected WebElement getTxtEmailForgotPassword() {
		return Constant.WEBDRIVER.findElement(_txtEmailForgotPassword);
	}
	
	protected WebElement getTxtNewPassword() {
		return Constant.WEBDRIVER.findElement(_txtNewPassword);
	}
	
	protected WebElement getTxtConfirmNewPassword() {
		return Constant.WEBDRIVER.findElement(_txtConfirmNewPassword);
	}
	
	protected WebElement getBtnResetPassword() {
		return Constant.WEBDRIVER.findElement(_btnResetPassword);
	}
	
	protected WebElement getTxtResetPasswordToken() {
		return Constant.WEBDRIVER.findElement(_txtResetPasswordToken);
	}
	
	protected WebElement getLblForgotPasswordGeneralMsg() {
		Utilities.waitForVisible(_lblForgotPasswordGeneralMsg);
		return Constant.WEBDRIVER.findElement(_lblForgotPasswordGeneralMsg);
	}
	
	protected WebElement getLblForgotPasswordConfirmPasswordMsg() {
		return Constant.WEBDRIVER.findElement(_lblForgotPasswordConfirmPasswordMsg);
	}
	
	protected static By getLocator(FieldsLogin element) {
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
        
        case RESET_PASSWORD_TOKEN:
        	return _txtResetPasswordToken;
        	
        default:
            throw new IllegalArgumentException("Unsupported element: " + element);
		}
	}
	
	
	// Methods
	public boolean isLoggedIn () {
		try {
			return isMenuDisplayed(PageMenu.LOGOUT);
		} catch (Exception e) {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T extends GeneralPage> T login(String username, String password) {
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
		
		if (!this.isLoggedIn()) {
			return (T) this;
		}
		return (T) new HomePage();
	}

	public String getLoginErrorMsg() {
		return this.getLblLoginErrorMsg().getText();
	}
	
	public String getForgotPasswordGeneralMsg() {
		return getLblForgotPasswordGeneralMsg().getText();
	}
	
	public String getForgotConfirmPasswordMsg() {
		return getLblForgotPasswordConfirmPasswordMsg().getText();
	}
	
	public boolean isResetPasswordTokenDisplayed() {
		return Utilities.isElementHasValue(getLocator(FieldsLogin.RESET_PASSWORD_TOKEN));
	}
	
	public LoginPage forgotPassword(RegisterAccount account) {
		LoginPage loginPage = new LoginPage();
		loginPage.getLinkForgotPassword().click();
		return sendInstructions(account.getEmail());
	}
	
	public LoginPage enterResetPassword(RegisterAccount account, String confirmPassword) {
		this.getTxtNewPassword().clear();
		this.getTxtNewPassword().sendKeys(account.getEmail());
		
		this.getTxtConfirmNewPassword().clear();
		this.getTxtConfirmNewPassword().sendKeys(confirmPassword);
		
		this.getBtnResetPassword().click();
		
		return new LoginPage();
	}
	
	public LoginPage sendInstructions(String email) {
		Constant.WEBDRIVER.findElement(_txtEmailForgotPassword).clear();
		Constant.WEBDRIVER.findElement(_txtEmailForgotPassword).sendKeys(email);
		
		Constant.WEBDRIVER.findElement(_btnSendInstructions).click();
		
		return new LoginPage();
	}
	
}
