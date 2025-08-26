package automation_exercise_pom.pages;

import automation_exercise_pom.helpers.DataRandomizer;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

public class PaymentPage extends BasePage {
    private final By paymentPageTitleLocator = By.xpath("//h2[text()='Payment']");
    private final By cardNameInputLocator = By.xpath("//input[@data-qa='name-on-card']");
    private final By cardNumberInputLocator = By.xpath("//input[@data-qa='card-number']");
    private final By cardCVCInputLocator = By.xpath("//input[@data-qa='cvc']");
    private final By cardExpirationMonthInputLocator = By.xpath("//input[@data-qa='expiry-month']");
    private final By cardExpirationYearInputLocator = By.xpath("//input[@data-qa='expiry-year']");
    private final By submitOrderButtonLocator = By.xpath("//button[@id='submit']");
    private final By orderPlacedMessageLocator = By.xpath("//b[text()='Order Placed!']");
    private final By continueButtonLocator = By.xpath("//a[@data-qa='continue-button']");

    @Step("Input card name")
    public PaymentPage inputCardName(String name) {
        logger.info("Input card name: [{}]", name);
        waiter.waitUntilVisibilityOfElementLocated(cardNameInputLocator).sendKeys(name);
        return this;
    }

    @Step("Input card number")
    public PaymentPage inputCardNumber(String number) {
        logger.info("Input card number: [{}]", number);
        waiter.waitUntilVisibilityOfElementLocated(cardNumberInputLocator).sendKeys(number);
        return this;
    }

    @Step("Input card cvc")
    public PaymentPage inputCardCVC(String cvc) {
        logger.info("Input card cvc: [{}]", cvc);
        waiter.waitUntilVisibilityOfElementLocated(cardCVCInputLocator).sendKeys(cvc);
        return this;
    }

    @Step("Input card expiration month")
    public PaymentPage inputCardExpirationMonth(String month) {
        logger.info("Input card expiry month: [{}]", month);
        waiter.waitUntilVisibilityOfElementLocated(cardExpirationMonthInputLocator).sendKeys(month);
        return this;
    }

    @Step("Input card expiration year")
    public PaymentPage inputCardExpirationYear(String year) {
        logger.info("Input card expiry year: [{}]", year);
        waiter.waitUntilVisibilityOfElementLocated(cardExpirationYearInputLocator).sendKeys(year);
        return this;
    }

    @Step("Click submit order button")
    public PaymentPage clickSubmitOrderButton() {
        logger.info("Clicking [submit order] button");
        waiter.waitUntilElementClickable(submitOrderButtonLocator).click();
        return this;
    }

    @Step("Click continue button")
    public MainPage clickContinueButton() {
        logger.info("Clicking [continue] button");
        waiter.waitUntilElementClickable(continueButtonLocator).click();
        return new MainPage();
    }

    @Step("Input card data")
    public PaymentPage inputCardData() {
        return this
                .inputCardName(DataRandomizer.getRandomCardName())
                .inputCardNumber(DataRandomizer.getRandomCardNumber())
                .inputCardCVC(DataRandomizer.getRandomCVC())
                .inputCardExpirationMonth(DataRandomizer.getRandomExpirationMonth())
                .inputCardExpirationYear(DataRandomizer.getRandomExpirationYear());
    }

    @Step("Assert payment page loaded")
    public PaymentPage assertPaymentPageLoaded() {
        waiter.waitUntilVisibilityOfElementLocated(paymentPageTitleLocator, Duration.ofSeconds(5));
        removeAds();
        logger.info("Assert: payment page loaded [PASSED]");
        return this;
    }

    @Step("Assert successfully order message is visible")
    public PaymentPage assertSuccessfullyOrderMessageIsVisible() {
        waiter.waitUntilVisibilityOfElementLocated(orderPlacedMessageLocator, Duration.ofSeconds(5));
        logger.info("Assert: successfully order message is visible [PASSED]");
        return this;
    }
}
