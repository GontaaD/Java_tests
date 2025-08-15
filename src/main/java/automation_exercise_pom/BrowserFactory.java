package automation_exercise_pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserFactory {
    public WebDriver getWebdriverInstance(){
        return new ChromeDriver();
    }
}
