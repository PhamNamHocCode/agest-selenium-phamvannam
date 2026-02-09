package Common;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;

public class Utilities {
	
	/* Scroll */
	public static void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	/*Click*/
	public static void safeClick(WebElement element) {
	    try {
	        element.click();
	    } catch (Exception e) {
	        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].click();", element);
	    }
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
	
	private static String getRandomString(int limit) {
	    String base36 = Long.toString(System.currentTimeMillis(), 36);
	    return base36.substring(base36.length() - limit).toUpperCase();
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
	    
	    try {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    } catch (TimeoutException e) {
	        Constant.WEBDRIVER.navigate().refresh();

	        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    }
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
	
	/* Check */
	public static boolean isTextboxNotEmpty(By locator) {
	    try {
	        WebElement element = waitForVisible(locator, Constant.WAIT_TIMEOUT);
	        String value = element.getDomProperty("value");
	        return value != null && !value.trim().isEmpty();
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	public static String getElementTextVisible(By locator) {
		try {
			WebElement element = Constant.WEBDRIVER.findElement(locator);
			if (element.isDisplayed()) {
				return element.getText();
			}
			return "HIDDEN";
		}
		catch (NoSuchElementException e) {
			return "NOT_FOUND";
		}
	}
	
}
