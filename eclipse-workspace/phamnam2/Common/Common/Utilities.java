package Common;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
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
		return "user" + System.currentTimeMillis();
	}
	
	public static String generateRandomPassword() {
		return "password" + System.currentTimeMillis();
	}
	
	public static String generateRandomPIP() {
		StringBuilder result = new StringBuilder();

	    result.append((int) (Math.random() * 9) + 1);

	    for (int i = 1; i < 9; i++) {
	        result.append((int) (Math.random() * 10));
	    }
	    return result.toString();
    }
	
	/* WAIT */
//	public static By waitForClickable(By locator) {
//		return waitForClickable(locator, Constant.WAIT_TIMEOUT);
//	}
//	
//	public static By waitForClickable(By locator, int timeout) {
//		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//		return locator;
//	}
	public static By waitForVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return locator;
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
	
}
