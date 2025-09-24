package automation_exercise.tests.login;

import automation_exercise.models.UserRegistrationData;
import automation_exercise.pages.CreateAccountPage;
import automation_exercise.pages.StatusPage;
import automation_exercise.base.BaseTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationTest extends BaseTest {
    StatusPage statusPage;
    CreateAccountPage createAccountPage;

    @Test
    public void registration_test() {
        createAccountPage = new CreateAccountPage();
        statusPage = new StatusPage();

        UserRegistrationData user = new UserRegistrationData();

        mainMenu
                .clickLoginPageButton()
                .inputName(user.getUsername())
                .inputRegistrationEmail(user.getEmail())
                .clickSignupButton();

        createAccountPage
                .userRegisterWithDetails(user);

        assertThat(statusPage.isSuccessfullyCreatedAccount())
                .as("ERROR: Failed create account")
                .isTrue();

        statusPage
                .clickContinueButton();

        assertThat(mainMenu.isUsernameVisible(user.getUsername()))
                .as("ERROR: Username: " + user.getUsername() + " is not visible")
                .isTrue();

        mainMenu
                .clickDeleteAccountButton();

        assertThat(statusPage.isDeleteSuccessfullyMassageIsVisible())
                .as("ERROR: Delete successfully massage is not visible")
                .isTrue();
    }
}
