package automation_exercise_tests.login;

import automation_exercise_pom.SecretsManager;
import automation_exercise_tests.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginLogoutTest extends BaseTest {

    @BeforeMethod
    public void preconditions(){
        mainPage
                .assertMainPageSuccessfullyLoaded()
                .openLoginPage()
                .loginUser(SecretsManager.get("email"), SecretsManager.get("password"));
    }

    @Test
    public void login_test(){
        mainPage
                .assertUserNameIsVisible("test");
    }

    @Test
    public void logout_test(){
        mainPage
                .clickLogoutPageButton()
                .assertLoginPageSuccessfullyLoaded();
    }
}
