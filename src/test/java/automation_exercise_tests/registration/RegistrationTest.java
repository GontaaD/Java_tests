package automation_exercise_tests.registration;

import automation_exercise_pom.helpers.CreateUserAccount;
import automation_exercise_pom.helpers.UserFactory;
import automation_exercise_pom.models.UserRegistrationData;
import automation_exercise_pom.pages.*;
import automation_exercise_tests.BaseTest;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {

    @Test
    public void registration_test() {
        UserRegistrationData user = UserFactory.getUserForRegistration();

        mainPage
                .assertMainPageSuccessfullyLoaded()
                .openLoginPage()
                .signUpUser(UserFactory.userName);

        CreateUserAccount
                .userRegisterWithDetails(user)
                .assertSuccessfullyCreatedAccount()
                .clickContinueButton()
                .assertUserNameIsVisible(UserFactory.userName);

        deletedAccountPage
                .deleteAccount();
    }
}
