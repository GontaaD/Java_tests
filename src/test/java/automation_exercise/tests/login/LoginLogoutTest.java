package automation_exercise.tests.login;

import automation_exercise.utils.SecretsManager;

import automation_exercise.pages.LoginPage;
import automation_exercise.base.BaseTest;
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
        assertThat(mainMenu.isUsernameVisible("test"))
                .as("ERROR: Username: test, is not visible")
                .isTrue();
    }

    @Test
    public void logoutTest(){
        loginPage = new LoginPage();

        mainMenu
                .clickLogoutButton();

        assertThat(loginPage.isLoginPageSuccessfullyLoaded())
                .as("ERROR: Login page is displayed")
                .isTrue();
    }
}
