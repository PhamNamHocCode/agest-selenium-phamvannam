package Common;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;

public class Utilities {
	
	public static void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	/*Create Account*/
	public static String generateRandomEmail() {
		return "user" + getRandomString(5);
	}
	
	public static String generateRandomPassword() {
		return "password" + getRandomString(5);
	}
	
	public static String generateRandomPIP() {
		StringBuilder result = new StringBuilder();

	    result.append((int) (Math.random() * 9) + 1);

	    for (int i = 1; i < 9; i++) {
	        result.append((int) (Math.random() * 10));
	    }
	    return result.toString();
    }
	
	private static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = ThreadLocalRandom.current().nextInt(Constant.ALPHA_NUMERIC.length());
            sb.append(Constant.ALPHA_NUMERIC.charAt(index));
        }
        return sb.toString();
    }
	
	/* WAIT */
	public static By waitForClickable(By locator) {
		return waitForClickable(locator, Constant.WAIT_TIMEOUT);
	}
	
	public static By waitForClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return locator;
	}
	
	public static void waitForElementExist(By locator) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.WAIT_TIMEOUT));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public static WebElement waitForVisible(By locator, int timeout) {
	    WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	/* Switch to */
	public static void switchToPageByUrlContains(String locator) {

        for (String window : Constant.WEBDRIVER.getWindowHandles()) {
        	Constant.WEBDRIVER.switchTo().window(window);

            String currentUrl = Constant.WEBDRIVER.getCurrentUrl();
            if (currentUrl != null && currentUrl.contains(locator)) {
                return;
            }
        }

        throw new RuntimeException(
            "Cannot switch to window with URL contains: " + locator
        );
    }
	
	
	/* Open Tab */
	public static void openUrlInNewTab(String url) {
		Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
		Constant.WEBDRIVER.get(url);
	}
	
	/* Quit Browser */
	public static void closeBrowser() {
		if (Constant.WEBDRIVER == null) return;

	    if (Constant.WEBDRIVER.getWindowHandles().size() > 1) {
	        Constant.WEBDRIVER.close();

	        for (String handle : Constant.WEBDRIVER.getWindowHandles()) {
	            Constant.WEBDRIVER.switchTo().window(handle);
	            break;
	        }
	    }
    }
	
	/* Close tab */
//	pubclic static void closeTab() {
//		
//	}
	
}
