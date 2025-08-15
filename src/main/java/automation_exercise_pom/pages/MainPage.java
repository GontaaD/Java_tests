package automation_exercise_pom.pages;

public class MainPage extends BasePage {

    public MainPage assertMainPageSuccessfullyLoaded() {
        waiter.waitUntilVisibilityOfElementLocated(logoLocator);
        return this;
    }
}
