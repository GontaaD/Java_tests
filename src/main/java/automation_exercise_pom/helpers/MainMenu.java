package automation_exercise_pom.helpers;

import automation_exercise_pom.pages.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class MainMenu extends BasePage {
    AdsHelper adsHelper = new AdsHelper();
    private final By loginButtonLocator = By.xpath("//a[@href='/login']");
    private final By logoutButtonLocator = By.xpath("//a[@href='/logout']");
    private final By loginedUserNameLocator = By.xpath("//b");
    private final By deleteAccountButtonLocator = By.xpath("//a[@href='/delete_account']");
    private final By productPageButtonLocator = By.xpath("//a[@href='/products']");

    @Step("Click delete account button")
    public StatusPage clickDeleteAccountButton(){
        logger.info("Clicking [delete account] button");
        waitUntilElementClickable(deleteAccountButtonLocator).click();
        adsHelper.removeAds();
        return new StatusPage();
    }

    @Step("Click login page button")
    public LoginPage clickLoginPageButton() {
        logger.info("Click [login page] button");
        waitUntilElementClickable(loginButtonLocator).click();
        adsHelper.removeAds();
        return new LoginPage();
    }

    @Step("Click logout page button")
    public LoginPage clickLogoutPageButton() {
        logger.info("Click [logout] button");
        waitUntilElementClickable(logoutButtonLocator).click();
        adsHelper.removeAds();
        return new LoginPage();
    }

    @Step("Click product page button")
    public ProductsPage clickProductPageButton() {
        logger.info( "Click [product page] button");
        waitUntilElementClickable(productPageButtonLocator).click();
        adsHelper.removeAds();
        return new ProductsPage();
    }

    @Step("Is user name is visible")
    public boolean isUserNameIsVisible(String text){
        logger.info("Is user name: [" + text + "] visible?");
        return waitUntilTextToBeInElement(loginedUserNameLocator, text);
    }
}

