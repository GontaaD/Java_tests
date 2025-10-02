package automation_exercise.components;

import automation_exercise.pages.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static automation_exercise.utils.AdsRemove.removeAds;

public class MainMenu extends BasePage {
    private final By loginButtonLocator = By.xpath("//a[@href='/login']");
    private final By logoutButtonLocator = By.xpath("//a[@href='/logout']");
    private final By loginedUsernameLocator = By.xpath("//b");
    private final By deleteAccountButtonLocator = By.xpath("//a[@href='/delete_account']");
    private final By productPageButtonLocator = By.xpath("//a[@href='/products']");

    @Step("Click delete account button")
    public StatusPage clickDeleteAccountButton(){
        logger.info("Clicking [delete account] button");
        waitUntilElementClickable(deleteAccountButtonLocator).click();
        removeAds();
        return new StatusPage();
    }

    @Step("Click login page button")
    public LoginPage clickLoginPageButton() {
        logger.info("Click [login page] button");
        waitUntilElementClickable(loginButtonLocator).click();
        removeAds();
        return new LoginPage();
    }

    @Step("Click logout button")
    public LoginPage clickLogoutButton() {
        logger.info("Click [logout] button");
        waitUntilElementClickable(logoutButtonLocator).click();
        removeAds();
        return new LoginPage();
    }

    @Step("Click product page button")
    public ProductsPage clickProductPageButton() {
        logger.info( "Click [product page] button");
        waitUntilElementClickable(productPageButtonLocator).click();
        removeAds();
        return new ProductsPage();
    }

    @Step("Is username visible")
    public boolean isUsernameVisible(String text){
        logger.info("Is username: [" + text + "] visible?");
        return waitUntilTextToBeInElement(loginedUsernameLocator, text);
    }
}

