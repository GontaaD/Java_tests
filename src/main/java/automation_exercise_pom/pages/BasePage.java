package automation_exercise_pom.pages;

import automation_exercise_pom.helpers.Waiter;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public abstract class BasePage {
    protected final Waiter waiter = new Waiter(getDriver());
    protected final By logoLocator = By.xpath("//img[contains(@src, 'logo.png')]");
    protected final By loginButtonLocator = By.xpath("//a[@href='/login']");
    protected final By logoutButtonLocator = By.xpath("//a[@href='/logout']");
    protected final By loginedUserNameLocator = By.xpath("//b");
    private final By deleteAccountButtonLocator = By.xpath("//a[@href='/delete_account']");
    private final By productPageButtonLocator = By.xpath("//a[@href='/products']");
    private final By googleAdsLocator = By.xpath("//ins[@data-anchor-status='displayed' and @data-adsbygoogle-status='done']");

    @Getter
    @Setter
    private static WebDriver driver;

    public LoginPage openLoginPage() {
        return this
                .clickLoginPageButton()
                .assertLoginPageSuccessfullyLoaded();
    }

    public void removeAds() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(1))
                    .until(visibilityOfElementLocated(googleAdsLocator));

            ((JavascriptExecutor) driver).executeScript(
                    "document.querySelector('ins.adsbygoogle[data-anchor-status=\"displayed\"]').remove();"
            );
        } catch (TimeoutException _) {
        }
    }

    public DeletedAccountPage clickDeleteAccountButton(){
        waiter.waitUntilElementClickable(deleteAccountButtonLocator).click();
        removeAds();
        return new DeletedAccountPage();
    }

    public LoginPage clickLoginPageButton() {
        waiter.waitUntilElementClickable(loginButtonLocator).click();
        removeAds();
        return new LoginPage();
    }

    public LoginPage clickLogoutPageButton() {
        waiter.waitUntilElementClickable(logoutButtonLocator).click();
        removeAds();
        return new LoginPage();
    }

    public ProductsPage openProductsPage() {
        return this
                .clickProductPageButton()
                .assertProductsPageSuccessfullyLoaded();
    }

    public ProductsPage clickProductPageButton() {
        waiter.waitUntilElementClickable(productPageButtonLocator).click();
        removeAds();
        return new ProductsPage();
    }

    public MainPage assertUserNameIsVisible(String text){
        waiter.waitUntilTextToBeInElement(loginedUserNameLocator, text);
        return new MainPage();
    }
}
