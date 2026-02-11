package Guerrilla;

import Railway.LoginPage;

import Constant.Constant;
import Common.Utilities;

import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GuerrillaHomePage {
	// Locators
	private final By _editNameBtn = By.xpath("//span[@class='editable button' and @id='inbox-id']");
	private final By _txtName = By.xpath("//span[@id='inbox-id']/input");
	private final String _emailLetter = "//tbody[@id='email_list']//span[contains(text(),'%s')]";
	private final By _linkConfirmAccount = By.xpath("//a[contains(text(), 'saferailway')]");
	private final By _checkboxScramble = By.xpath("//input[@id='use-alias']");
	private final By _fullEmailAddress = By.xpath("//span[@id='email-widget']");

	// Elements
	protected WebElement getEditNameBtn() {
		return Constant.WEBDRIVER.findElement(_editNameBtn);
	}

	protected WebElement getTxtName() {
		return Constant.WEBDRIVER.findElement(_txtName);
	}

	protected WebElement getLinkConfirmAccount() {
		return Constant.WEBDRIVER.findElement(_linkConfirmAccount);
	}

	protected WebElement getCheckboxScramble() {
		return Constant.WEBDRIVER.findElement(_checkboxScramble);
	}

	protected WebElement getFullEmailAddress() {
		return Constant.WEBDRIVER.findElement(_fullEmailAddress);
	}

	protected By getByEmailLetter(String letterKeyword) {
		switch (letterKeyword) {
			case "Registration":
				return By.xpath(String.format(_emailLetter, "confirmation code"));

			case "ForgotPassword":
				return By.xpath(String.format(_emailLetter, "password reset"));

			default:
				throw new IllegalArgumentException("Unsupported email type");
		}
	}

	protected WebElement getEmailLetterElement(String letterKeyword) {
		return Constant.WEBDRIVER.findElement(getByEmailLetter(letterKeyword));
	}

	// Methods
	/**
	 * Creates a new temporary email address on Guerrilla Mail
	 * @param emailName Desired username for the email
	 * @return Full email address (username@guerrillamail.com)
	 */
	public String createNewEmail(String emailName) {
		Utilities.waitForVisible(_editNameBtn, Constant.WAIT_TIMEOUT).click();
		this.getTxtName().clear();
		this.getTxtName().sendKeys(emailName, Keys.ENTER);

		if (this.getCheckboxScramble().isEnabled()
				&& this.getCheckboxScramble().isSelected()) {
			this.getCheckboxScramble().click();
		}

		return this.getFullEmailAddress().getText().trim();
	}

	public void confirmRegistrationEmail(String emailName) {
		this.getEditNameBtn().click();

		this.getTxtName().clear();
		this.getTxtName().sendKeys(emailName, Keys.ENTER);

		Utilities.waitForVisibleWithRefresh(getByEmailLetter("Registration"), Constant.WAIT_TIMEOUT);
		getEmailLetterElement("Registration").click();

		Utilities.waitForClickable(_linkConfirmAccount).click();

		for (String handle : Constant.WEBDRIVER.getWindowHandles()) {
			Constant.WEBDRIVER.switchTo().window(handle);
		}
	}

	public LoginPage confirmForgotPasswordEmail(String emailName) {
		this.getEditNameBtn().click();

		this.getTxtName().clear();
		this.getTxtName().sendKeys(emailName, Keys.ENTER);

		Utilities.waitForVisibleWithRefresh(getByEmailLetter("ForgotPassword")).click();

		Utilities.waitForClickable(_linkConfirmAccount, Constant.WAIT_TIMEOUT).click();

		for (String handle : Constant.WEBDRIVER.getWindowHandles()) {
			Constant.WEBDRIVER.switchTo().window(handle);
		}
		return new LoginPage();
	}

	public GuerrillaHomePage open() {
		Constant.WEBDRIVER.navigate().to(Constant.GUERRILLA_URL);
		return this;
	}
}
