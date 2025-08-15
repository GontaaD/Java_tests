package automation_exercise_pom.pages;

import org.openqa.selenium.By;

public class DeletedAccountPage extends BasePage {
    private final By deleteSuccessfullyMassage = By.xpath("//b[text()='Account Deleted!']");

    public DeletedAccountPage asserDeleteSuccessfullyMassage(){
        waiter.waitUntilVisibilityOfElementLocated(deleteSuccessfullyMassage);
        return this;
    }

    public DeletedAccountPage deleteAccount(){
        return this
                .clickDeleteAccountButton()
                .asserDeleteSuccessfullyMassage();
    }
}
