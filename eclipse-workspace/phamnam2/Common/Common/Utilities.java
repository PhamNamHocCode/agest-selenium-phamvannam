package Common;

import java.time.Duration;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
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
		return "user" + getRandomString(5);
	}
	
	public static String generateRandomPassword(int limit) {
		return "password" + getRandomString(5);
	}
	
	public static String generateRandomPIP(int limit) {
		StringBuilder result = new StringBuilder();

	    result.append((int) (Math.random() * 9) + 1);

	    for (int i = 1; i < 9; i++) {
	        result.append((int) (Math.random() * 10));
	    }
	    return result.toString();
    }
	
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
	
	public static WebElement  waitForVisibleWithRefresh(By locator, int timeout) {
	    WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
	    
	    try {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    } catch (TimeoutException e) {
	        Constant.WEBDRIVER.navigate().refresh();

	        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    }
	}
	
	public static void waitUntilStale(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.WAIT_TIMEOUT));
			wait.until(ExpectedConditions.stalenessOf(element));
		} catch (Exception e) {

		}
	}
	
	/* Switch */
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
	
	public static String getElementTextStatus(By locator) {
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
	
	/*Format */
	public static String formatDate(LocalDate date) {
		return date.format(Constant.DATE_FORMAT);
	}
	
}
