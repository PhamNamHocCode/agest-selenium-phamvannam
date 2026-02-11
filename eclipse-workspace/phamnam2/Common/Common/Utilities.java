package Common;

import java.time.Duration;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;

public class Utilities {
	/* Scroll */
	public static void scrollToElement(WebElement element) {
		((JavascriptExecutor) Constant.WEBDRIVER)
        .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
	}
	
	public static void scrollToElement(By locator) {
	    WebElement element = Constant.WEBDRIVER.findElement(locator);

	    ((JavascriptExecutor) Constant.WEBDRIVER)
	        .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
	}
	
	/*Click*/
	public static void clickByJs(WebElement element) {
	    try {
	        element.click();
	    } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
	        System.out.println("Normal click failed, using JS click: " + e.getMessage());
	        ((JavascriptExecutor) Constant.WEBDRIVER)
	            .executeScript("arguments[0].click();", element);
	    }
	}
	
	/*Data generator*/
	public static String generateRandomEmail(int limit) {
		return "user" + getRandomString(limit);
	}
	
	public static String generateRandomPassword(int limit) {
		return "password" + getRandomString(limit);
	}
	
	public static String generateRandomPIP() {
		StringBuilder result = new StringBuilder();

	    result.append((int) (Math.random() * 9) + 1);

	    for (int i = 1; i < 9; i++) {
	        result.append((int) (Math.random() * 10));
	    }
	    return result.toString();
    }
	/**
	 * Generates a random alphanumeric string of specified length
	 * Uses base-36 encoding (0-9, a-z) for compact representation
	 * 
	 * Strategy:
	 * 1. Combine time stamp + random number in base-36 for uniqueness
	 * 2. If result is longer than needed, take last N characters
	 * 3. If result is shorter, pad with random lower case letters
	 * 
	 * @param limit Desired length of the string
	 * @return Random alphanumeric string of exactly 'limit' length
	 */
	private static String getRandomString(int limit) {
	    String timestamp = Long.toString(System.currentTimeMillis(), 36);
	    String randomPart = Integer.toString(ThreadLocalRandom.current().nextInt(1000000), 36);
	    String combined = (timestamp + randomPart).toLowerCase();
	    
	    if (combined.length() >= limit) {
	        return combined.substring(combined.length() - limit);
	    } else {
	        return String.format("%" + limit + "s", combined)
	                     .replace(' ', (char)('a' + ThreadLocalRandom.current().nextInt(26)));
	    }
	}

	/* Wait */
	public static WebElement  waitForClickable(By locator) {
		return waitForClickable(locator, Constant.WAIT_TIMEOUT);
	}
	
	public static WebElement  waitForClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public static WebElement  waitForVisible(By locator) {
		return waitForVisible(locator, Constant.WAIT_TIMEOUT);
	}
	
	public static WebElement  waitForVisible(By locator, int timeout) {
	    WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public static WebElement  waitForVisibleWithRefresh(By locator) {
        return waitForVisibleWithRefresh(locator, Constant.WAIT_TIMEOUT);
	}
	
	/**
	 * Waits for element to be visible. If timeout occurs, refreshes page and retries.
	 * 
	 * Use case: When elements are loaded dynamically or may need page refresh to appear
	 * (e.g., email list that needs refresh to show new email)
	 * 
	 * @param locator Element locator
	 * @param timeout Maximum wait time in seconds for each attempt
	 * @return WebElement when visible
	 * @throws TimeoutException if element not visible after refresh and second wait
	 */
	public static WebElement  waitForVisibleWithRefresh(By locator, int timeout) {
	    WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
	    
	    try {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    } catch (TimeoutException e) {
	        Constant.WEBDRIVER.navigate().refresh();

	        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    }
	}
	
	/**
	 * Waits for element to become stale (removed/replaced in DOM)
	 * Useful when waiting for page elements to refresh (e.g., drop down options reload)
	 * 
	 * Note: Silently catches exceptions if element doesn't become stale within timeout.
	 * This is intentional to avoid breaking flow when element state is uncertain.
	 * 
	 * @param element WebElement to wait for staleness
	 */
	public static void waitUntilStale(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.WAIT_TIMEOUT));
			wait.until(ExpectedConditions.stalenessOf(element));
		} catch (Exception e) {

		}
	}
	
	/* Switch */
	
	/**
	 * Switches to browser window/tab whose URL contains the specified text
	 * Useful when dealing with multiple windows (e.g., email verification flow)
	 * 
	 * Example: switchToPageByUrlContains("safe railway") will switch to Railway site
	 * 
	 * @param urlFragment Text that the target window's URL should contain
	 * @throws RuntimeException if no window with matching URL is found
	 */
	public static void switchToPageByUrlContains(String urlFragment) {
        for (String window : Constant.WEBDRIVER.getWindowHandles()) {
        	Constant.WEBDRIVER.switchTo().window(window);

            String currentUrl = Constant.WEBDRIVER.getCurrentUrl();
            if (currentUrl != null && currentUrl.contains(urlFragment)) {
                return;
            }
        }

        throw new RuntimeException(
            "Cannot switch to window with URL contains: " + urlFragment
        );
    }
	
	
	/* Open Tab */
	public static void openUrlInNewTab(String url) {
		Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
		Constant.WEBDRIVER.get(url);
	}
	
	/* Quit Browser */
	
	/**
	 * Closes current browser window/tab and switches to another if available
	 * Does NOT quit entire browser - only closes current window
	 * 
	 * Behavior:
	 * - If driver is null: do nothing
	 * - If only 1 window: do nothing (keeps window open)
	 * - If multiple windows: close current and switch to first remaining window
	 */
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
	
	/* Element State */
	public static boolean isElementHasValue(By locator) {
	    try {
	        WebElement element = waitForVisible(locator);
	        String value = element.getDomProperty("value");
	        return value != null && !value.trim().isEmpty();
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	/*Format */
	public static String formatDate(LocalDate date) {
		return date.format(Constant.DATE_FORMAT);
	}
	
}
