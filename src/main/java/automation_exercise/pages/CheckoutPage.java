package automation_exercise.pages;

import automation_exercise.interfaces.LocatorProvider;
import automation_exercise.interfaces.ProductInCart;
import automation_exercise.utils.ListUtil;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static automation_exercise.utils.AdsHelper.removeAds;

public class CheckoutPage extends BasePage implements ProductInCart {
    private final By paymentButtonLocator = By.xpath("//a[@href='/payment']");

    @Getter
    @AllArgsConstructor
    public enum CheckoutFields implements LocatorProvider {
        NAME(By.xpath("//ul[@id='address_delivery']//li[contains(@class, 'address_firstname')]")),
        COMPANY(By.xpath("//ul[@id='address_delivery']//li[contains(@class, 'address_address1')][1]")),
        ADDRESS1(By.xpath("//ul[@id='address_delivery']//li[contains(@class, 'address_address1')][2]")),
        ADDRESS2(By.xpath("//ul[@id='address_delivery']//li[contains(@class, 'address_address1')][3]")),
        CITY(By.xpath("//ul[@id='address_delivery']//li[contains(@class, 'address_city')]")),
        COUNTRY(By.xpath("//ul[@id='address_delivery']//li[@class='address_country_name']"));

        private final By locator;
    }

    public String getTextFromCheckout(CheckoutFields field) {
        try {
            WebElement element = getDriver().findElement(field.getLocator());
            return ListUtil.getTextNode(element).replaceAll("\\s+", " ").trim();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Step("Click payment button")
    public PaymentPage clickPaymentButton(){
        removeAds();
        logger.info("Clicking [payment] button");
        waitUntilVisibilityOfElementLocated(paymentButtonLocator).click();
        return new PaymentPage();
    }
}