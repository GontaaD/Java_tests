package automation_exercise.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverFactory {
    public static WebDriver createDriver() throws Exception {
        String seleniumUrl = System.getenv("SELENIUM_URL");

        if (seleniumUrl != null && !seleniumUrl.isEmpty()) {
            // RemoteWebDriver для Docker
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");

            return new RemoteWebDriver(new URL(seleniumUrl), options);
        } else {
            // Локальний запуск Chrome
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");

            return new ChromeDriver(options);
        }
    }
}
