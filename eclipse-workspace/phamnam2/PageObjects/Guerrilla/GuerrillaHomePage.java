package Guerrilla;

import Railway.HomePage;
import Railway.RegisterPage;
import Constant.Constant;
import Common.Utilities;

import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class GuerrillaHomePage {
	
	// Locators
	private final By _editNameBtn = By.xpath("//span[@class='editable button' and @id='inbox-id']");
	private final By _txtName = By.xpath("//span[@id='inbox-id']/input");
	private final By _emailLetter = By.xpath("//tbody[@id='email_list']//td[contains(text(),'thanhletraining')]");
	private final By _linkConfirmAccount = By.xpath("//a[contains(text(), 'saferailway')]");
	private final By _checkboxScramble = By.xpath("//input[@id='use-alias']");
	private final By _fullEmailAddress = By.xpath("//span[@id='email-widget']");
	
	// Elements
	public WebElement getEditNameBtn() {
		return Constant.WEBDRIVER.findElement(_editNameBtn);
	}
	
	public WebElement getTxtName() {
		return Constant.WEBDRIVER.findElement(_txtName);
	}
	
	public WebElement getEmailLetter() {
		return Constant.WEBDRIVER.findElement(_emailLetter);
	}
	
	public WebElement getLinkConfirmAccout() {
		return Constant.WEBDRIVER.findElement(_linkConfirmAccount);
	}
	
	public WebElement getCheckboxScramble() {
		return Constant.WEBDRIVER.findElement(_checkboxScramble);
	}
	
	public WebElement getFullEmailAddress() {
		return Constant.WEBDRIVER.findElement(_fullEmailAddress);
	}
	
	// Methods
	public String createNewEmail() {
		String emailName = Utilities.generateRandomEmail();

		this.getEditNameBtn().click();
		this.getTxtName().clear();
		this.getTxtName().sendKeys(emailName, Keys.ENTER);
		
		if (this.getCheckboxScramble().isEnabled()
		        && this.getCheckboxScramble().isSelected()) {
			this.getCheckboxScramble().click();
		}
		
		return this.getFullEmailAddress().getText().trim();
	}
	
	public RegisterPage confirmNewEmail(String emailName) {
		this.getEditNameBtn().click();
		
		this.getTxtName().clear();
		this.getTxtName().sendKeys(emailName, Keys.ENTER);
		
		Utilities.waitForVisible(_emailLetter, Constant.WAIT_TIMEOUT);
		
		this.getEmailLetter().click();
		
		this.getLinkConfirmAccout().click();
		
		return new RegisterPage();
	}
	
	public GuerrillaHomePage open() {
		Constant.WEBDRIVER.navigate().to(Constant.GUERRILLA_URL);
		return this;
	}
}
