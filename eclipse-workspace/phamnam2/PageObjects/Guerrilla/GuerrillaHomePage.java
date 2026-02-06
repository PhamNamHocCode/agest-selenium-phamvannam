package Guerrilla;

import Railway.RegisterPage;

import Constant.Constant;
import Constant.PageMenu;
import Common.Utilities;

import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


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
	public String createNewEmail(String emailName) {
		Utilities.waitForVisible(_editNameBtn, Constant.WAIT_TIMEOUT);
		this.getEditNameBtn().click();
		this.getTxtName().clear();
		this.getTxtName().sendKeys(emailName, Keys.ENTER);
		
		if (this.getCheckboxScramble().isEnabled()
		        && this.getCheckboxScramble().isSelected()) {
			this.getCheckboxScramble().click();
		}
		
		return this.getFullEmailAddress().getText().trim();
	}
	
	public RegisterPage confirmNewEmail(String emailName, Boolean isCheckLabel, String expectedMsgConfirmed) {
		this.getEditNameBtn().click();
		
		this.getTxtName().clear();
		this.getTxtName().sendKeys(emailName, Keys.ENTER);
		
		Utilities.waitForVisible(_emailLetter, Constant.WAIT_TIMEOUT);
		this.getEmailLetter().click();

		Utilities.waitForClickable(_linkConfirmAccount, Constant.WAIT_TIMEOUT);
		this.getLinkConfirmAccout().click();

		for (String handle : Constant.WEBDRIVER.getWindowHandles()) {
			Constant.WEBDRIVER.switchTo().window(handle);
		}
		RegisterPage registerPage = new RegisterPage();
		Utilities.waitForVisible(registerPage.getByLblMsgRegistrationConfirmed(),Constant.WAIT_TIMEOUT);
		
		if (isCheckLabel) {
			Assert.assertEquals(registerPage.getTextLblMsgRegistrationConfirmed(), expectedMsgConfirmed, "Message is not displayed as expected");
		}
		return registerPage;
	}
	
	public GuerrillaHomePage open() {
		Constant.WEBDRIVER.navigate().to(Constant.GUERRILLA_URL);
		return this;
	}
}
