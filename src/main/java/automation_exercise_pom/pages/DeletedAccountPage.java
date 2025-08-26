package automation_exercise_pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class DeletedAccountPage extends BasePage {
    private final By deleteSuccessfullyMassage = By.xpath("//b[text()='Account Deleted!']");

    @Step("Delete account")
    public DeletedAccountPage deleteAccount(){
        return this
                .clickDeleteAccountButton()
                .assertDeleteSuccessfullyMassageIsVisible();
    }

    @Step("Assert delete successfully massage is visible")
    public DeletedAccountPage assertDeleteSuccessfullyMassageIsVisible(){
        waiter.waitUntilVisibilityOfElementLocated(deleteSuccessfullyMassage);
        logger.info("Assert: delete successfully massage is visible [PASSED]");
        return this;
    }
}
