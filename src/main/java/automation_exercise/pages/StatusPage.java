package automation_exercise.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;
import static automation_exercise.utils.AdsHelper.removeAds;

public class StatusPage extends BasePage{
    private final By createdAccountSuccessfullyMassage = By.xpath("//b[text()='Account Created!']");
    private final By continueButton = By.xpath("//a[@data-qa='continue-button']");
    private final By deleteSuccessfullyMassage = By.xpath("//b[text()='Account Deleted!']");

    @Step("Click continue button")
    public void clickContinueButton() {
        logger.info("Click [continue] button");
        waitUntilElementClickable(continueButton).click();
        removeAds();
    }

    @Step("Is successfully created account?")
    public boolean isSuccessfullyCreatedAccount() {
        logger.info("Is successfully created account?");
        return waitUntilVisibilityOfElementLocated(createdAccountSuccessfullyMassage, Duration.ofSeconds(1));
    }

    @Step("Is delete successfully massage is visible?")
    public boolean isDeleteSuccessfullyMassageIsVisible(){
        logger.info("Is delete successfully massage is visible?");
        return waitUntilVisibilityOfElementLocated(deleteSuccessfullyMassage, Duration.ofSeconds(1));
    }
}
