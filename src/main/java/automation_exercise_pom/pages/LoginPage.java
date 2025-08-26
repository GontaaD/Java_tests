package automation_exercise_pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static automation_exercise_pom.helpers.DataRandomizer.*;

public class LoginPage extends BasePage {
    String randomEmail = getRandomEmail();
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
        waiter.waitUntilVisibilityOfElementLocated(nameInputLocator).sendKeys(name);
        return this;
    }

    @Step("Input registration email")
    public LoginPage inputRegistrationEmail(String email) {
        logger.info("Input email [{}] for signup: ", email);
        waiter.waitUntilVisibilityOfElementLocated(emailRegistrationInputLocator).sendKeys(email);
        return this;
    }

    @Step("Click signup button")
    public CreateAccountPage clickSignupButton(){
        logger.info("Click [signup button] button");
        waiter.waitUntilVisibilityOfElementLocated(signupButtonLocator).click();
        removeAds();
        return new CreateAccountPage();
    }

    @Step("Sign up user")
    public CreateAccountPage signUpUser(String userName) {
        return this
                .inputName(userName)
                .inputRegistrationEmail(randomEmail)
                .clickSignupButton()
                .assertCreateAccountPageSuccessfullyLoaded();
    }

    @Step("Input login email")
    public LoginPage inputLoginEmail(String email) {
        logger.info("Set login email: [{}]", email);
        waiter.waitUntilVisibilityOfElementLocated(emailLoginInputLocator).sendKeys(email);
        return this;
    }

    @Step("Input login password")
    public LoginPage inputLoginPassword(String password) {
        logger.info("Set login password");
        waiter.waitUntilVisibilityOfElementLocated(passwordLoginInputLocator).sendKeys(password);
        return this;
    }

    @Step("Click login button")
    public MainPage clickLoginButton(){
        logger.info("Click [login] button");
        waiter.waitUntilElementClickable(loginButtonLocator).click();
        removeAds();
        return new MainPage();
    }

    @Step("Login user")
    public MainPage loginUser(String email, String password) {
        return this
                .inputLoginEmail(email)
                .inputLoginPassword(password)
                .clickLoginButton();
    }

    @Step("Assert login page successfully loaded")
    public LoginPage assertLoginPageSuccessfullyLoaded(){
        waiter.waitUntilVisibilityOfElementLocated(loginTitleLocator);
        logger.info("Assert: login page successfully loaded [PASSED]");
        return this;
    }

}
