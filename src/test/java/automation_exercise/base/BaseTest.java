package automation_exercise.base;

import automation_exercise.components.MainMenu;
import automation_exercise.driver.DriverFactory;
import automation_exercise.pages.*;
import automation_exercise.listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.time.Duration;

@Listeners({
        TestListener.class
})
public abstract class BaseTest {
    protected MainMenu mainMenu;

    @BeforeMethod(alwaysRun = true)
    public synchronized void startBrowser() throws Exception {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--start-maximized");
//
//        WebDriver driver = new ChromeDriver(options);
        WebDriver driver = DriverFactory.createDriver();
        BasePage.setDriver(driver);

        BasePage.getDriver().get("https://www.automationexercise.com/");
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlToBe("https://www.automationexercise.com/"));

        mainMenu = new MainMenu();
    }

    @AfterMethod(alwaysRun = true)
    public void quit(){
//        if(BasePage.getDriverThreadLocal() != null) {
//            BasePage.getDriver().quit();
        if (BasePage.getDriver() != null) {
            BasePage.getDriver().quit();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
