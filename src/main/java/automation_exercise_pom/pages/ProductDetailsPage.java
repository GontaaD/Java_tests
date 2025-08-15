package automation_exercise_pom.pages;

import automation_exercise_pom.models.ProductDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductDetailsPage extends BasePage{
    private final By productNameLocator = By.xpath("//div[@class='product-information']//h2");
    private final By productCategoryLocator = By.xpath("//div[@class='product-information']//p[1]");
    private final By productPriceLocator = By.xpath("//div[@class='product-information']//span//span");
    private final By productAvailabilityLocator = By.xpath("//div[@class='product-information']//p[2]");
    private final By productConditionLocator = By.xpath("//div[@class='product-information']//p[3]");
    private final By productBrandLocator = By.xpath("//div[@class='product-information']//p[4]");
    private final By productQuantityInputLocator = By.xpath("//input[@id='quantity']");
    private final By productAddToCartLocator = By.xpath("//button[contains(@class, 'cart')]");

    public ProductDetails getProductDetails() {
        return ProductDetails.builder()
                .name(waiter.waitUntilVisibilityOfElementLocated(productNameLocator).getText())
                .category(waiter.waitUntilVisibilityOfElementLocated(productCategoryLocator).getText())
                .price(waiter.waitUntilVisibilityOfElementLocated(productPriceLocator).getText())
                .availability(waiter.waitUntilVisibilityOfElementLocated(productAvailabilityLocator).getText())
                .condition(waiter.waitUntilVisibilityOfElementLocated(productConditionLocator).getText())
                .brand(waiter.waitUntilVisibilityOfElementLocated(productBrandLocator).getText().replace(" ", ""))
                .build();
    }

    public ProductDetailsPage setProductQuantity(int quantity) {
        WebElement input = waiter.waitUntilVisibilityOfElementLocated(productQuantityInputLocator);
        input.clear();
        input.sendKeys(String.valueOf(quantity));
        return this;
    }

    public CartModal clickAddToCart() {
        waiter.waitUntilElementClickable(productAddToCartLocator).click();
        return new CartModal();
    }
}
