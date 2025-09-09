package automation_exercise_pom.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class BasePage {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

//    private final WebDriver driver;
    private final Duration defaultWaitTimeout = Duration.ofSeconds(5);

//    public BasePage(WebDriver driver) {
//        this.driver = driver;
//        wait = new WebDriverWait(driver, defaultWaitTimeout);
//    }
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    public WebElement waitUntilVisibilityOfElementLocated(By locator) {
        try {
            return new WebDriverWait(getDriver(), defaultWaitTimeout)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            String message = "Waiting for element located [" + locator + "] timed out";
            logger.error(message, e);
            throw new TimeoutException(message, e);
        }
    }

    public WebElement waitUntilElementClickable(By locator) {
        try{
            return new WebDriverWait(getDriver(), defaultWaitTimeout)
                    .until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            String message = "Waiting for element clickable [" + locator + "] timed out";
            logger.error(message, e);
            throw new TimeoutException(message, e);
        }
    }

    public boolean waitUntilVisibilityOfElementLocated(By locator, Duration duration) {
        try {
            new WebDriverWait(getDriver(), duration)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean waitUntilTextToBeInElement(By locator,  String text) {
        try {
            new WebDriverWait(getDriver(), defaultWaitTimeout)
                    .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
            return true;
        }  catch (TimeoutException e) {
            return false;
        }
    }
}
