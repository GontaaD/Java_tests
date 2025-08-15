package automation_exercise_pom.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Waiter {
    private final WebDriver driver;
    private final Duration defaultWaitTimeout = Duration.ofSeconds(5);


    public Waiter(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilUrlToBe(String expectedUrl) {
        new WebDriverWait(driver, defaultWaitTimeout)
                .until(ExpectedConditions.urlToBe(expectedUrl));
    }

    public WebElement waitUntilVisibilityOfElementLocated(By locator) {
        return new WebDriverWait(driver, defaultWaitTimeout)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitUntilVisibilityOfElementLocated(By locator, Duration duration) {
        return new WebDriverWait(driver, duration)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitUntilElementClickable(By locator) {
        return new WebDriverWait(driver, defaultWaitTimeout)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public Boolean waitUntilTextToBeInElement(By locator,  String text) {
        return new WebDriverWait(driver, defaultWaitTimeout)
                .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public List<WebElement> waitUntilCountElementsToBe(By locator, int count) {
        return new WebDriverWait(driver, defaultWaitTimeout)
                .until(ExpectedConditions.numberOfElementsToBe(locator, count));
    }
}
