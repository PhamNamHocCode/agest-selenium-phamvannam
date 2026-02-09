package Railway;

import Constant.Constant;
import Common.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class RegisterPage extends GeneralPage{
	// Locators
	private final By _txtEmail = By.xpath("//input[@id='email']");
	private final By _txtPassword = By.xpath("//input[@id='password']");
	private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By _txtPIPNumber = By.xpath("//input[@id='pid']");
	private final By _btnRegister = By.xpath("//input[@value='Register']");
	private final By _lblMsgGeneralError = By.xpath("//div[@id='content']/p[@class='message error']");
	private final By _lblMsgRegistrationConfirmed = By.xpath("//div[@id='content']//p");
	private final By _lblMsgThankyou = By.xpath("//div[@id='content']//h1");
	private final By _lblMsgErrorPassword = By.xpath("//form[@id='RegisterForm']//label[@class='validation-error' and @for='password']");
	private final By _lblMsgErrorPip = By.xpath("//form[@id='RegisterForm']//label[@class='validation-error' and @for='pid']");
	
	// Elements
	public WebElement getTxtEmail() {
		return Constant.WEBDRIVER.findElement(_txtEmail);
	}
	
	public WebElement getTxtPassword() {
		return Constant.WEBDRIVER.findElement(_txtPassword);
	}
	
	public WebElement getTxtConfirmPassword() {
		return Constant.WEBDRIVER.findElement(_txtConfirmPassword);
	}
	
	public WebElement getTxtPIPNumber() {
		return Constant.WEBDRIVER.findElement(_txtPIPNumber);
	}
	
	public WebElement getBtnRegister() {
		return Constant.WEBDRIVER.findElement(_btnRegister);
	}
	
	public WebElement getLblMsgGeneralError() {
		return Constant.WEBDRIVER.findElement(_lblMsgGeneralError);
	}
	public WebElement getLblMsgRegistrationConfirmed() {
		return Constant.WEBDRIVER.findElement(_lblMsgRegistrationConfirmed);
	}
	
	public WebElement getLblMsgErrorPassword() {
		return Constant.WEBDRIVER.findElement(_lblMsgErrorPassword);
	}
	 
	public WebElement getLblMsgErrorPip() {
		return Constant.WEBDRIVER.findElement(_lblMsgErrorPip);
	}
	
	public WebElement getLblMsgThankyou() {
		return Constant.WEBDRIVER.findElement(_lblMsgThankyou);
	}
	
	// Methods
	public String getTextLblMsgGeneralError() {
		return this.getLblMsgGeneralError().getText();
	}
	
	public String getTextLblMsgErrorPassword() {
		return this.getLblMsgErrorPassword().getText();
	}
	
	public String getTextLblMsgErrorPip() {
		return this.getLblMsgErrorPip().getText();
	}
	
	public String getTextLblMsgThankyou() {
	    return Constant.WEBDRIVER.findElement(Utilities
	            .waitForVisible(_lblMsgThankyou))
	            .getText();
	}
	
	public String getTextLblMsgRegistrationConfirmed() {
		return this.getLblMsgRegistrationConfirmed().getText();
	}
	
	public By getByLblMsgRegistrationConfirmed() {
		return _lblMsgRegistrationConfirmed;
	}
	
	
	public RegisterPage registerNewAccount(RegisterAccount account, Boolean isCheckLabel, String expectedMsgThankyou) {
		this.getTxtEmail().clear();
		this.getTxtEmail().sendKeys(account.getEmail());
		
		this.getTxtPassword().clear();
		this.getTxtPassword().sendKeys(account.getPassword());
		
		Utilities.scrollToElement(getTxtConfirmPassword());
		this.getTxtConfirmPassword().clear();
		this.getTxtConfirmPassword().sendKeys(account.getPassword());
		
		Utilities.scrollToElement(getTxtPIPNumber());
		this.getTxtPIPNumber().clear();
		this.getTxtPIPNumber().sendKeys(account.getPip());
		
		Utilities.scrollToElement(getBtnRegister());
		this.getBtnRegister().click();
		
		String actualMsg = new RegisterPage().getTextLblMsgThankyou();
		
		if (isCheckLabel) {
			Assert.assertEquals(actualMsg, expectedMsgThankyou, "Message is not displayed as expected");
		}
		
		return new RegisterPage();
	}
	
	public RegisterPage registerWithOnlyEmail(String registerEmail) {
		this.getTxtEmail().clear();
		this.getTxtEmail().sendKeys(registerEmail);
		
		Utilities.scrollToElement(getBtnRegister());
		this.getBtnRegister().click();
		
		return new RegisterPage();
	}
}
