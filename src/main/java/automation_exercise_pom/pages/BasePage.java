package automation_exercise_pom.pages;

import automation_exercise_pom.helpers.Waiter;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public abstract class BasePage {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected final Waiter waiter = new Waiter(getDriver());
    protected final By logoLocator = By.xpath("//img[contains(@src, 'logo.png')]");
    protected final By loginButtonLocator = By.xpath("//a[@href='/login']");
    protected final By logoutButtonLocator = By.xpath("//a[@href='/logout']");
    protected final By loginedUserNameLocator = By.xpath("//b");
    private final By deleteAccountButtonLocator = By.xpath("//a[@href='/delete_account']");
    private final By productPageButtonLocator = By.xpath("//a[@href='/products']");
    private final By googleAdsLocator = By.xpath("//ins[@data-anchor-status='displayed' and @data-adsbygoogle-status='done']");

    @Getter
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }
    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    @Step("Open login page")
    public LoginPage openLoginPage() {
        return this
                .clickLoginPageButton()
                .assertLoginPageSuccessfullyLoaded();
    }

    public void removeAds() {
        try {
            new WebDriverWait(getDriver(), Duration.ofMillis(500))
                    .until(visibilityOfElementLocated(googleAdsLocator));

            ((JavascriptExecutor) getDriver()).executeScript(
                    "document.querySelector('ins.adsbygoogle[data-anchor-status=\"displayed\"]').remove();"
            );
        } catch (TimeoutException _) {
        }
    }

    @Step("Click delete account button")
    public DeletedAccountPage clickDeleteAccountButton(){
        logger.info("Clicking [delete account] button");
        waiter.waitUntilElementClickable(deleteAccountButtonLocator).click();
        removeAds();
        return new DeletedAccountPage();
    }

    @Step("Click login page button")
    public LoginPage clickLoginPageButton() {
        logger.info("Click [login page] button");
        waiter.waitUntilElementClickable(loginButtonLocator).click();
        removeAds();
        return new LoginPage();
    }

    @Step("Click logout page button")
    public LoginPage clickLogoutPageButton() {
        logger.info("Click [logout] button");
        waiter.waitUntilElementClickable(logoutButtonLocator).click();
        removeAds();
        return new LoginPage();
    }

    @Step("Open products page")
    public ProductsPage openProductsPage() {
        return this
                .clickProductPageButton()
                .assertProductsPageSuccessfullyLoaded();
    }

    @Step("Click product page button")
    public ProductsPage clickProductPageButton() {
        logger.info( "Click [product page] button");
        waiter.waitUntilElementClickable(productPageButtonLocator).click();
        removeAds();
        return new ProductsPage();
    }

    @Step("Assert user name is visible")
    public MainPage assertUserNameIsVisible(String text){
        waiter.waitUntilTextToBeInElement(loginedUserNameLocator, text);
        logger.info("Assert: user name: [{}] is visible [PASSED]",  text);
        return new MainPage();
    }
}
