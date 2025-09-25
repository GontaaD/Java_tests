package automation_exercise.pages;

import automation_exercise.interfaces.LocatorProvider;
import automation_exercise.models.CheckoutData;
import automation_exercise.models.UserRegistrationData;
import automation_exercise.interfaces.IProductInCart;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static automation_exercise.utils.AdsHelper.removeAds;

public class CheckoutPage extends BasePage implements IProductInCart {
    private final By deliveryAddressContainerLocator = By.xpath("//ul[@id='address_delivery']");
    private final By deliveryNameLocator = By.xpath(".//li[contains(@class, 'address_firstname')]");
    private final By deliveryCompanyLocator = By.xpath(".//li[contains(@class, 'address_address1')][1]");
    private final By deliveryAddress1Locator = By.xpath(".//li[contains(@class, 'address_address1')][2]");
    private final By deliveryAddress2Locator = By.xpath(".//li[contains(@class, 'address_address1')][3]");
    private final By deliveryCityLocator = By.xpath(".//li[contains(@class, 'address_city')]");
    private final By deliveryCountryLocator = By.xpath(".//li[@class='address_country_name']");
    private final By deliveryPhoneLocator = By.xpath(".//li[@class='address_phone']");
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

    public String getText(CheckoutFields field) {
        WebElement element = getDriver().findElement(field.getLocator());
        return element.getText();
    }

    @Step("Get delivery address")
    public CheckoutData getDeliveryAddress() {
        WebElement container = getDriver().findElement(deliveryAddressContainerLocator);

        return CheckoutData.builder()
                .name(getTextOrNull(container, deliveryNameLocator))
                .company(getTextOrNull(container, deliveryCompanyLocator))
                .address1(getTextOrNull(container, deliveryAddress1Locator))
                .address2(getTextOrNull(container, deliveryAddress2Locator))
                .city(getTextOrNull(container, deliveryCityLocator))
                .country(getTextOrNull(container, deliveryCountryLocator))
                .phone(getTextOrNull(container, deliveryPhoneLocator))
                .build();
    }

    @Step("Get expected checkout data")
    public CheckoutData getExpectedCheckoutData(UserRegistrationData user){
        return CheckoutData.builder()
                .name(user.getTitle() + ". " + user.getFirstName() + " " + user.getLastName())
                .company(user.getCompany())
                .address1(user.getAddress1())
                .address2(user.getAddress2())
                .city(user.getCity() + " " + user.getState() + " " + user.getZipcode())
                .country(user.getCountry())
                .build();
    }

    @Step("Click payment button")
    public PaymentPage clickPaymentButton(){
        removeAds();
        logger.info("Clicking [payment] button");
        waitUntilVisibilityOfElementLocated(paymentButtonLocator).click();
        return new PaymentPage();
    }

    @Step("Is delivery address to be equal")
    public boolean isDeliveryAddressToBeEqual(CheckoutData actual, CheckoutData expected){
        Allure.addAttachment("Actual list", actual.toString());
        Allure.addAttachment("Expected list", expected.toString());
        logger.info(String.format("""
                        Is delivery address to be equal
                         %-8s : actual: [%-50s] | expected: [%-50s]
                         %-8s : actual: [%-50s] | expected: [%-50s]
                         %-8s : actual: [%-50s] | expected: [%-50s]
                         %-8s : actual: [%-50s] | expected: [%-50s]
                         %-8s : actual: [%-50s] | expected: [%-50s]
                         %-8s : actual: [%-50s] | expected: [%-50s]
                         %-8s : actual: [%-50s] | expected: [%-50s]
                        """,
                "Name", actual.getName(), expected.getName(),
                "Company", actual.getCompany(), expected.getCompany(),
                "Address1", actual.getAddress1(), expected.getAddress1(),
                "Address2", actual.getAddress2(), expected.getAddress2(),
                "City", actual.getCity(), expected.getCity(),
                "Country", actual.getCountry(), expected.getCountry(),
                "Phone", actual.getPhone(), expected.getPhone()
        ));
        try {
            assertThat(actual)
                    .usingRecursiveComparison()
                    .isEqualTo(expected);
            return true;
        }  catch (AssertionError e) {
            return  false;
        }
    }
}