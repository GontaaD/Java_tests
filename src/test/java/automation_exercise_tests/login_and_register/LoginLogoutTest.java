package automation_exercise_tests.login_and_register;

import automation_exercise_pom.SecretsManager;
import automation_exercise_tests.BaseTest;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginLogoutTest extends BaseTest {

    @BeforeMethod
    @Step("Preconditions")
    public void preconditions(){
        mainPage
                .assertMainPageSuccessfullyLoaded()
                .openLoginPage()
                .loginUser(SecretsManager.get("email"), SecretsManager.get("password"));
    }

    @Test
    @Step("Login test start")
    public void loginTest(){
        mainPage
                .assertUserNameIsVisible("test");
    }

    @Test
    @Step("Logout test start")
    public void logoutTest(){
        mainPage
                .clickLogoutPageButton()
                .assertLoginPageSuccessfullyLoaded();
    }
}
