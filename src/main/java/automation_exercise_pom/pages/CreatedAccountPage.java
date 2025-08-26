package automation_exercise_pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CreatedAccountPage extends BasePage {
    private final By createdAccountSuccessfullyMassage = By.xpath("//b[text()='Account Created!']");
    private final By continueButton = By.xpath("//a[@data-qa='continue-button']");

    @Step("Click continue button")
    public MainPage clickContinueButton() {
        logger.info("Click [continue] button");
        waiter.waitUntilElementClickable(continueButton).click();
        removeAds();
        return new MainPage();
    }

    @Step("Assert successfully created account")
    public CreatedAccountPage assertSuccessfullyCreatedAccount() {
        waiter.waitUntilVisibilityOfElementLocated(createdAccountSuccessfullyMassage);
        logger.info("Assert: successfully created account [PASSED]");
        return this;
    }
}
