package Railway;

import Constant.Constant;
import Common.Utilities;
import Guerrilla.GuerrillaHomePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class RegisterPage extends GeneralPage{
	// Locators
	private final By _txtEmail = By.xpath("//input[@id='email']");
	private final By _txtPassword = By.xpath("//input[@id='password']");
	private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By _txtPIPNumber = By.xpath("//input[@id='pid']");
	private final By _btnRegister = By.xpath("//input[@value='Register']");
	
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
	
	
	// Methods
	
	public RegisterPage registerNewAccount(String registerEmail, String registerPassword, String registerPIP) {
		this.getTxtEmail().clear();
		this.getTxtEmail().sendKeys(registerEmail);
		
		this.getTxtPassword().clear();
		this.getTxtPassword().sendKeys(registerPassword);
		
		this.getTxtConfirmPassword().clear();
		this.getTxtConfirmPassword().sendKeys(registerPassword);
		
		this.getTxtPIPNumber().clear();
		this.getTxtPIPNumber().sendKeys(registerPIP);
		
//		Utilities.scrollToElement(getBtnRegister());
		this.getBtnRegister().click();
		
		return new RegisterPage();
	}
	
}
