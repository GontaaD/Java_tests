package automation_exercise_tests;

import automation_exercise_pom.helpers.MainMenu;
import automation_exercise_pom.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.time.Duration;

@Listeners({
        automation_exercise_tests.TestListener.class
})
public abstract class BaseTest {
    protected MainMenu mainMenu;
    protected Logger logger;

    @BeforeMethod(alwaysRun = true)
    public synchronized void startBrowser(){
        WebDriver driver = new ChromeDriver();
        BasePage.setDriver(driver);

        BasePage.getDriver().get("https://www.automationexercise.com/");
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlToBe("https://www.automationexercise.com/"));

        mainMenu = new MainMenu();
        logger = LoggerFactory.getLogger(this.getClass());
    }

    @AfterMethod(alwaysRun = true)
    public void quit(){
//        if(BasePage.getDriverThreadLocal() != null) {
            BasePage.getDriver().quit();
    }
}
