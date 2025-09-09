package automation_exercise_pom.pages;

import automation_exercise_pom.models.ProductInCart;
import automation_exercise_pom.interfaces.IProductInCart;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBeMoreThan;

public class CartPage extends BasePage implements IProductInCart {
    private final By productInCartContainerLocator = By.xpath("//tr[contains(@id, 'product-')]");
    private final By productDeleteFromCartButtonLocator = By.xpath("//a[@class='cart_quantity_delete']");
    private final By cartIsEmptyMessageLocator = By.xpath("//b[text()='Cart is empty!']");
    private final By proceedToCheckoutButton = By.xpath("//a[contains(@class, 'check_out')]");

    @Step("Click delete from cart button")
    public CartPage clickDeleteFromCartButton(){
        logger.info("Click [delete product from cart] button");
        waitUntilVisibilityOfElementLocated(productDeleteFromCartButtonLocator).click();
        return this;
    }

    @Step("Click checkout button")
    public CheckoutPage clickCheckoutButton(){
        logger.info("Click [checkout] button");
        waitUntilElementClickable(proceedToCheckoutButton).click();
        return new CheckoutPage();
    }

    @Step("Is cart is empty?")
    public boolean isCartIsEmpty(){
        logger.info("Is cart is empty?");
        return waitUntilVisibilityOfElementLocated(cartIsEmptyMessageLocator, Duration.ofSeconds(2));
    }

    @Step("Get products count in cart")
    public int getProductCountInCart() {
        List<WebElement> elements = new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                .until(numberOfElementsToBeMoreThan(productInCartContainerLocator, -1));
        logger.info("Get products count in cart. Count: " + elements.size());
        return elements.size();
    }

    @Step("Is products to be equal?")
    public boolean isProductsToBeEqual(ProductInCart actual, ProductInCart expected){
        Allure.addAttachment("Actual list", actual.toString());
        Allure.addAttachment("Expected list", expected.toString());
        logger.info(String.format("""
                        Is products to be equal
                         %-8s : actual: [%-10s] | expected: [%-10s]
                         %-8s : actual: [%-10s] | expected: [%-10s]
                         %-8s : actual: [%-10s] | expected: [%-10s]
                         %-8s : actual: [%-10s] | expected: [%-10s]
                        """,
                "Name", actual.getName(), expected.getName(),
                "Price", actual.getPrice(), expected.getPrice(),
                "Quantity", actual.getQuantity(), expected.getQuantity(),
                "Total", actual.getTotalPrice(), expected.getTotalPrice()));
        try {
            assertThat(actual)
                    .usingRecursiveComparison()
                    .isEqualTo(expected);
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }
}
