package automation_exercise.utils;

import automation_exercise.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class AdsRemove extends BasePage {
    private static final By googleAdsLocator = By.xpath("//ins[@data-anchor-status='displayed' and @data-adsbygoogle-status='done']");

    public static void removeAds() {
        try {
            new WebDriverWait(getDriver(), Duration.ofMillis(1500))
                    .until(visibilityOfElementLocated(googleAdsLocator));

            ((JavascriptExecutor) getDriver()).executeScript(
                    "document.querySelector('ins.adsbygoogle[data-anchor-status=\"displayed\"]')?.remove();"
            );
        } catch (TimeoutException | JavascriptException ignored) {
        }
    }
}
