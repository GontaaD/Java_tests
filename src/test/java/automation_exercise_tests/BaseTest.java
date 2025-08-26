package automation_exercise_tests;

import automation_exercise_pom.BrowserFactory;
import automation_exercise_pom.helpers.CreateUserAccount;
import automation_exercise_pom.helpers.Waiter;
import automation_exercise_pom.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected DeletedAccountPage deletedAccountPage;
    protected CreatedAccountPage createdAccountPage;
    protected CreateAccountPage createAccountPage;
    protected ProductsPage productsPage;
    protected ProductDetailsPage productDetailsPage;
    protected CartPage cartPage;
    protected CheckoutPage checkoutPage;
    protected CreateUserAccount createUserAccount;
    protected CartModal cartModal;


    @BeforeMethod(alwaysRun = true)
    public synchronized void startBrowser(){
        BrowserFactory browserFactory = new BrowserFactory();
        WebDriver driver = browserFactory.getWebdriverInstance();
        BasePage.setDriver(driver);

        Waiter waiter = new Waiter(BasePage.getDriver());
        BasePage.getDriver().get("https://www.automationexercise.com/");
        waiter.waitUntilUrlToBe("https://www.automationexercise.com/");

        mainPage = new MainPage();
        loginPage = new LoginPage();
        deletedAccountPage = new DeletedAccountPage();
        createdAccountPage = new CreatedAccountPage();
        createAccountPage = new CreateAccountPage();
        productsPage = new ProductsPage();
        productDetailsPage = new ProductDetailsPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
        createUserAccount = new CreateUserAccount();
        cartModal =  new CartModal();

    }

    @AfterMethod(alwaysRun = true)
    public void quit(){
        if(BasePage.getDriverThreadLocal() != null) {
            BasePage.getDriver().quit();
        }
    }
}
