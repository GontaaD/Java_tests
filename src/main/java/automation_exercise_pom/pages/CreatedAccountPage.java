package automation_exercise_pom.pages;

import org.openqa.selenium.By;

public class CreatedAccountPage extends BasePage {
    private final By createdAccountSuccessfullyMassage = By.xpath("//b[text()='Account Created!']");
    private final By continueButton = By.xpath("//a[@data-qa='continue-button']");

    public CreatedAccountPage assertSuccessfullyCreatedAccount() {
        waiter.waitUntilVisibilityOfElementLocated(createdAccountSuccessfullyMassage);
        return this;
    }

    public MainPage clickContinueButton() {
        waiter.waitUntilElementClickable(continueButton).click();
        removeAds();
        return new MainPage();
    }
}
