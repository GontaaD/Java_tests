package automation_exercise_pom.pages;

import automation_exercise_pom.models.ProductInCart;
import automation_exercise_pom.pages.interfaces.IProductInCart;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

public class CartPage extends BasePage implements IProductInCart {
    private final By productInCartContainerLocator = By.xpath("//tr[contains(@id, 'product-')]");
    private final By productDeleteFromCartButtonLocator = By.xpath("//a[@class='cart_quantity_delete']");
    private final By cartIsEmptyMessageLocator = By.xpath("//b[text()='Cart is empty!']");
    private final By proceedToCheckoutButton = By.xpath("//a[contains(@class, 'check_out')]");

    @Step("Click delete from cart button")
    public CartPage clickDeleteFromCartButton(){
        logger.info("Click [delete product from cart] button");
        waiter.waitUntilVisibilityOfElementLocated(productDeleteFromCartButtonLocator).click();
        return this;
    }

    @Step("Click checkout button")
    public CheckoutPage clickCheckoutButton(){
        logger.info("Click [checkout] button");
        waiter.waitUntilElementClickable(proceedToCheckoutButton).click();
        return  new CheckoutPage();
    }

    @Step("Assert cart is empty")
    public CartPage assertCartIsEmpty(){
        waiter.waitUntilVisibilityOfElementLocated(cartIsEmptyMessageLocator, Duration.ofSeconds(2));
        logger.info("Assert: cart is empty [PASSED]");
        return this;
    }

    @Step("Assert product in cart to be")
    public CartPage assertProductInCartToBe(int count){
        new WebDriverWait(getDriver(), Duration.ofSeconds(2))
                .until(d -> !d.findElements(productInCartContainerLocator).isEmpty());
        List<WebElement> elements = getDriver().findElements(productInCartContainerLocator);
        assertEquals(elements.size(), count, "Number of products in cart does not match expected");
        logger.info("Assert: product in cart to be: {} [PASSED]", count);
        return this;
    }

    @Step("Assert products to be equal")
    public CartPage assertProductsToBeEqual(ProductInCart actual, ProductInCart expected){
        logger.info(String.format("""
                        Assert: products to be equal
                         %-8s : actual: [%-10s] | expected: [%-10s]
                         %-8s : actual: [%-10s] | expected: [%-10s]
                         %-8s : actual: [%-10s] | expected: [%-10s]
                         %-8s : actual: [%-10s] | expected: [%-10s]
                        """,
                "Name", actual.getName(), expected.getName(),
                "Price", actual.getPrice(), expected.getPrice(),
                "Quantity", actual.getQuantity(), expected.getQuantity(),
                "Total", actual.getTotalPrice(), expected.getTotalPrice()));
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
        logger.info("Assert result: [PASSED]");
        return this;
    }
}
