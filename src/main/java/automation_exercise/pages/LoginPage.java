package automation_exercise.pages;

import automation_exercise.components.MainMenu;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static automation_exercise.utils.AdsRemove.removeAds;

public class LoginPage extends BasePage {
    private final By loginTitleLocator = By.xpath("//h2[text()='New User Signup!']");
    private final By nameInputLocator = By.xpath("//input[@data-qa='signup-name']");
    private final By emailRegistrationInputLocator = By.xpath("//input[@data-qa='signup-email']");
    private final By emailLoginInputLocator = By.xpath("//input[@data-qa='login-email']");
    private final By passwordLoginInputLocator = By.xpath("//input[@data-qa='login-password']");
    private final By signupButtonLocator = By.xpath("//button[@data-qa='signup-button']");
    private final By loginButtonLocator = By.xpath("//button[@data-qa='login-button']");

    @Step("Input name")
    public LoginPage inputName(String name){
        logger.info("Input name [{}] for signup: ", name);
        waitUntilVisibilityOfElementLocated(nameInputLocator).sendKeys(name);
        return this;
    }

    @Step("Input registration email")
    public LoginPage inputRegistrationEmail(String email) {
        logger.info("Input email [{}] for signup: ", email);
        waitUntilVisibilityOfElementLocated(emailRegistrationInputLocator).sendKeys(email);
        return this;
    }

    @Step("Click signup button")
    public CreateAccountPage clickSignupButton(){
        logger.info("Click [signup button] button");
        waitUntilVisibilityOfElementLocated(signupButtonLocator).click();
        removeAds();
        return new CreateAccountPage();
    }

    @Step("Input login email")
    public LoginPage inputLoginEmail(String email) {
        logger.info("Set login email: [{}]", email);
        waitUntilVisibilityOfElementLocated(emailLoginInputLocator).sendKeys(email);
        return this;
    }

    @Step("Input login password")
    public LoginPage inputLoginPassword(String password) {
        logger.info("Set login password");
        waitUntilVisibilityOfElementLocated(passwordLoginInputLocator).sendKeys(password);
        return this;
    }

    @Step("Click login button")
    public MainMenu clickLoginButton(){
        logger.info("Click [login] button");
        waitUntilElementClickable(loginButtonLocator).click();
        removeAds();
        return new MainMenu();
    }

    @Step("Is login page successfully loaded?")
    public boolean isLoginPageSuccessfullyLoaded(){
        logger.info("Is login page successfully loaded?");
        return waitUntilVisibilityOfElementLocated(loginTitleLocator, Duration.ofSeconds(1));
    }

}
