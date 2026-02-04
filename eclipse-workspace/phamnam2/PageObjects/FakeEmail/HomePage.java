package FakeEmail;

import Railway.RegisterPage;
import Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class HomePage {
	
	// Locators
	private final By _editNameBtn = By.xpath("//span[@class='editable button' and @id='inbox-id']");
	private final By _txtName = By.xpath("//span[@class='editable button edit-in-progress' and @id='inbox-id']");
	private final By _emailLetter = By.xpath("//tbody[@id='email_list']//td[contains(text(),'thanhletraining')]");
	private final By _linkConfirmAccount = By.xpath("//a[contains(text(), 'saferailway')]");
	
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
	
	// Methods
	public RegisterPage confirmAccount(String emailName) {
		this.getEditNameBtn().click();
		this.getTxtName().sendKeys(emailName);
		
		this.getEmailLetter().click();
		
		this.getLinkConfirmAccout().click();
		
		return new RegisterPage();
	}
	
	public HomePage open() {
		Constant.WEBDRIVER.navigate().to(Constant.FAKEMAIL_URL);
		return this;
	}
}
