package automation_exercise_pom.pages;

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

    public LoginPage inputName(String name){
        waiter.waitUntilVisibilityOfElementLocated(nameInputLocator).sendKeys(name);
        return this;
    }

    public LoginPage inputRegistrationEmail(String email) {
        waiter.waitUntilVisibilityOfElementLocated(emailRegistrationInputLocator).sendKeys(email);
        return this;
    }

    public CreateAccountPage clickSignupButton(){
        waiter.waitUntilVisibilityOfElementLocated(signupButtonLocator).click();
        removeAds();
        return new CreateAccountPage();
    }

    public CreateAccountPage signUpUser(String userName) {
        return this
                .inputName(userName)
                .inputRegistrationEmail(randomEmail)
                .clickSignupButton()
                .assertCreateAccountPageSuccessfullyLoaded();
    }

    public LoginPage inputLoginEmail(String email) {
        waiter.waitUntilVisibilityOfElementLocated(emailLoginInputLocator).sendKeys(email);
        return this;
    }

    public LoginPage inputLoginPassword(String password) {
        waiter.waitUntilVisibilityOfElementLocated(passwordLoginInputLocator).sendKeys(password);
        return this;
    }

    public MainPage clickLoginButton(){
        waiter.waitUntilElementClickable(loginButtonLocator).click();
        removeAds();
        return new MainPage();
    }

    public MainPage loginUser(String email, String password) {
        return this
                .inputLoginEmail(email)
                .inputLoginPassword(password)
                .clickLoginButton();
    }

    public LoginPage assertLoginPageSuccessfullyLoaded(){
        waiter.waitUntilVisibilityOfElementLocated(loginTitleLocator);
        return this;
    }

}
