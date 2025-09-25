package automation_exercise.pages;

import automation_exercise.components.CartModal;
import automation_exercise.interfaces.LocatorProvider;
import automation_exercise.utils.ListUtil;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;


public class ProductDetailsPage extends BasePage {
    private final By productQuantityInputLocator = By.xpath("//input[@id='quantity']");
    private final By productAddToCartLocator = By.xpath("//button[contains(@class, 'cart')]");

    @Getter
    @AllArgsConstructor
    public enum ProductDetailsFields implements LocatorProvider {
        NAME(By.xpath("//div[@class='product-information']//h2")),
        CATEGORY(By.xpath("//div[@class='product-information']//p[1]")),
        PRICE(By.xpath("//div[@class='product-information']//span//span")),
        AVAILABILITY(By.xpath("//div[@class='product-information']//p[2]")),
        CONDITION(By.xpath("//div[@class='product-information']//p[3]")),
        BRAND(By.xpath("//div[@class='product-information']//p[4]"));

        private final By locator;
    }

    public String getTextFromProductDetails(ProductDetailsFields field) {
        try {
            WebElement element = getDriver().findElement(field.getLocator());
            return ListUtil.getTextNode(element);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Step("Set product quantity")
    public ProductDetailsPage setProductQuantity(String quantity) {
        logger.info("Set quantity to [{}]", quantity);
        WebElement input = waitUntilVisibilityOfElementLocated(productQuantityInputLocator);
        input.clear();
        input.sendKeys(String.valueOf(quantity));
        return this;
    }

    @Step("Click add to cart")
    public CartModal clickAddToCartButton() {
        logger.info("Click [add to cart] button");
        waitUntilElementClickable(productAddToCartLocator).click();
        return new CartModal();
    }
}
