package automation_exercise_tests.tests.login;

import automation_exercise_pom.helpers.UserFactory;
import automation_exercise_pom.models.UserRegistrationData;
import automation_exercise_pom.pages.CreateAccountPage;
import automation_exercise_pom.pages.StatusPage;
import automation_exercise_tests.base.BaseTest;
import org.testng.annotations.Test;

import static automation_exercise_pom.helpers.DataRandomizer.getRandomEmail;
import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationTest extends BaseTest {
    StatusPage statusPage;
    CreateAccountPage createAccountPage;

    @Test
    public void registration_test() {
        createAccountPage = new CreateAccountPage();
        statusPage = new StatusPage();

        UserRegistrationData user = UserFactory.getUserForRegistration();

        mainMenu
                .clickLoginPageButton()
                .inputName(UserFactory.userName)
                .inputRegistrationEmail(getRandomEmail())
                .clickSignupButton();

        createAccountPage
                .userRegisterWithDetails(user);

        assertThat(statusPage.isSuccessfullyCreatedAccount())
                .as("ERROR: Failed create account")
                .isTrue();

        statusPage
                .clickContinueButton();

        assertThat(mainMenu.isUsernameVisible(UserFactory.userName))
                .as("ERROR: Username: " + UserFactory.userName + " is not visible")
                .isTrue();

        mainMenu
                .clickDeleteAccountButton();

        assertThat(statusPage.isDeleteSuccessfullyMassageIsVisible())
                .as("ERROR: Delete successfully massage is not visible")
                .isTrue();
    }
}
