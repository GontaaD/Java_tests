package automation_exercise_tests.login_and_register;

import automation_exercise_pom.helpers.CreateUserAccount;
import automation_exercise_pom.helpers.UserFactory;
import automation_exercise_pom.models.UserRegistrationData;
import automation_exercise_pom.pages.StatusPage;
import automation_exercise_tests.BaseTest;
import org.testng.annotations.Test;

import static automation_exercise_pom.helpers.DataRandomizer.getRandomEmail;
import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationTest extends BaseTest {
    CreateUserAccount createUserAccount;
    StatusPage statusPage;

    @Test
    public void registration_test() {
        createUserAccount =  new CreateUserAccount();
        statusPage = new StatusPage();

        UserRegistrationData user = UserFactory.getUserForRegistration();

        mainMenu
                .clickLoginPageButton()
                .inputName(UserFactory.userName)
                .inputRegistrationEmail(getRandomEmail())
                .clickSignupButton();

        createUserAccount
                .userRegisterWithDetails(user);

        assertThat(statusPage.isSuccessfullyCreatedAccount())
                .as("ERROR: Failed create account")
                .isTrue();

        statusPage
                .clickContinueButton();

        assertThat(mainMenu.isUserNameIsVisible(UserFactory.userName))
                .as("ERROR: Username: " + UserFactory.userName + " is not visible")
                .isTrue();

        mainMenu
                .clickDeleteAccountButton();

        assertThat(statusPage.isDeleteSuccessfullyMassageIsVisible())
                .as("ERROR: Delete successfully massage is not visible")
                .isTrue();
    }
}
