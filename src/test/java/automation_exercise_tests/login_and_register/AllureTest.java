package automation_exercise_tests.login_and_register;

import io.qameta.allure.Step;
import org.testng.annotations.Test;

public class AllureTest{
    @Test
    @Step("Simple step")
    public void simpleTest() {
        System.out.println("Step executed");
    }

}
