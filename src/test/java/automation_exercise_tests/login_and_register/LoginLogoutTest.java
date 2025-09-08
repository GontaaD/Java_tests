package automation_exercise_tests.login_and_register;

import automation_exercise_pom.SecretsManager;

import automation_exercise_pom.pages.LoginPage;
import automation_exercise_tests.BaseTest;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginLogoutTest extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod
    @Step("Preconditions")
    public void preconditions(){
        mainMenu
                .clickLoginPageButton()
                .inputLoginEmail(SecretsManager.get("email"))
                .inputLoginPassword(SecretsManager.get("password"))
                .clickLoginButton();
    }

    @Test
    public void loginTest(){
        assertThat(mainMenu.isUserNameIsVisible("test"))
                .as("ERROR login: Username: test, is not visible")
                .isTrue();
    }

    @Test
    public void logoutTest(){
        loginPage = new LoginPage();

        mainMenu
                .clickLogoutPageButton();

        assertThat(loginPage.isLoginPageSuccessfullyLoaded())
                .as("ERROR logout: Logout page is displayed")
                .isTrue();
    }
}
