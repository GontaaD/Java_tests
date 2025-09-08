package automation_exercise_pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

public class PaymentPage extends BasePage {
    private final By cardNameInputLocator = By.xpath("//input[@data-qa='name-on-card']");
    private final By cardNumberInputLocator = By.xpath("//input[@data-qa='card-number']");
    private final By cardCVCInputLocator = By.xpath("//input[@data-qa='cvc']");
    private final By cardExpirationMonthInputLocator = By.xpath("//input[@data-qa='expiry-month']");
    private final By cardExpirationYearInputLocator = By.xpath("//input[@data-qa='expiry-year']");
    private final By submitOrderButtonLocator = By.xpath("//button[@id='submit']");
    private final By orderPlacedMessageLocator = By.xpath("//b[text()='Order Placed!']");

    @Step("Input card name")
    public PaymentPage inputCardName(String name) {
        logger.info("Input card name: [{}]", name);
        waitUntilVisibilityOfElementLocated(cardNameInputLocator).sendKeys(name);
        return this;
    }

    @Step("Input card number")
    public PaymentPage inputCardNumber(String number) {
        logger.info("Input card number: [{}]", number);
        waitUntilVisibilityOfElementLocated(cardNumberInputLocator).sendKeys(number);
        return this;
    }

    @Step("Input card cvc")
    public PaymentPage inputCardCVC(String cvc) {
        logger.info("Input card cvc: [{}]", cvc);
        waitUntilVisibilityOfElementLocated(cardCVCInputLocator).sendKeys(cvc);
        return this;
    }

    @Step("Input card expiration month")
    public PaymentPage inputCardExpirationMonth(String month) {
        logger.info("Input card expiry month: [{}]", month);
        waitUntilVisibilityOfElementLocated(cardExpirationMonthInputLocator).sendKeys(month);
        return this;
    }

    @Step("Input card expiration year")
    public PaymentPage inputCardExpirationYear(String year) {
        logger.info("Input card expiry year: [{}]", year);
        waitUntilVisibilityOfElementLocated(cardExpirationYearInputLocator).sendKeys(year);
        return this;
    }

    @Step("Click submit order button")
    public PaymentPage clickSubmitOrderButton() {
        logger.info("Clicking [submit order] button");
        waitUntilElementClickable(submitOrderButtonLocator).click();
        return this;
    }

    @Step("Is successfully order message is visible?")
    public boolean isSuccessfullyOrderMessageIsVisible() {
        logger.info("Is successfully order message is visible?");
        return waitUntilVisibilityOfElementLocated(orderPlacedMessageLocator, Duration.ofSeconds(5));
    }
}
