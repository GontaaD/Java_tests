package automation_exercise.pages;

import automation_exercise.interfaces.ProductInCart;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBeMoreThan;

public class CartPage extends BasePage implements ProductInCart {
    private final By productInCartContainerLocator = By.xpath("//tr[contains(@id, 'product-')]");
    private final By productDeleteFromCartButtonLocator = By.xpath("//a[@class='cart_quantity_delete']");
    private final By cartIsEmptyMessageLocator = By.xpath("//b[text()='Cart is empty!']");
    private final By proceedToCheckoutButton = By.xpath("//a[contains(@class, 'check_out')]");

    @Step("Click delete from cart button")
    public CartPage clickDeleteFromCartButton() {
        logger.info("Click [delete product from cart] button");
        waitUntilVisibilityOfElementLocated(productDeleteFromCartButtonLocator).click();
        return this;
    }

    @Step("Click open checkout button")
    public CheckoutPage clickOpenCheckoutButton(){
        logger.info("Click [open checkout] button");
        waitUntilElementClickable(proceedToCheckoutButton).click();
        return new CheckoutPage();
    }

    @Step("Is cart empty?")
    public boolean isCartEmpty(){
        logger.info("Is cart empty?");
        return waitUntilVisibilityOfElementLocated(cartIsEmptyMessageLocator, Duration.ofSeconds(2));
    }

    @Step("Get products number in cart")
    public int getProductNumberInCart() {
        List<WebElement> elements = new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                .until(numberOfElementsToBeMoreThan(productInCartContainerLocator, -1));
        logger.info("Get products number in cart. Number: " + elements.size());
        return elements.size();
    }
}
