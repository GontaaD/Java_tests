package automation_exercise_pom.pages;

import io.qameta.allure.Step;

public class MainPage extends BasePage {

    @Step("Assert main page successfully loaded")
    public MainPage assertMainPageSuccessfullyLoaded() {
        waiter.waitUntilVisibilityOfElementLocated(logoLocator);
        logger.info("Assert: main page successfully loaded: [PASSED]");
        return this;
    }
}
