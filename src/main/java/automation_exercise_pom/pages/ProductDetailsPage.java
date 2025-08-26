package automation_exercise_pom.pages;

import automation_exercise_pom.models.ProductDetails;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductDetailsPage extends BasePage{
    private final By productNameLocator = By.xpath("//div[@class='product-information']//h2");
    private final By productCategoryLocator = By.xpath("//div[@class='product-information']//p[1]");
    private final By productPriceLocator = By.xpath("//div[@class='product-information']//span//span");
    private final By productAvailabilityLocator = By.xpath("//div[@class='product-information']//p[2]");
    private final By productConditionLocator = By.xpath("//div[@class='product-information']//p[3]");
    private final By productBrandLocator = By.xpath("//div[@class='product-information']//p[4]");
    private final By productQuantityInputLocator = By.xpath("//input[@id='quantity']");
    private final By productAddToCartLocator = By.xpath("//button[contains(@class, 'cart')]");

    public String getTextNode(WebElement element) {
        return (String) ((JavascriptExecutor) BasePage.getDriver())
                .executeScript(
                        "return Array.from(arguments[0].childNodes)" +
                                ".filter(n => n.nodeType === Node.TEXT_NODE)" +
                                ".map(n => n.textContent.trim()).join('');",
                        element
                );
    }

    @Step("Get product details")
    public ProductDetails getProductDetails() {
        return ProductDetails.builder()
                .name(waiter.waitUntilVisibilityOfElementLocated(productNameLocator).getText().trim())
                .category(getTextNode(waiter.waitUntilVisibilityOfElementLocated(productCategoryLocator)).trim())
                .price(waiter.waitUntilVisibilityOfElementLocated(productPriceLocator).getText())
                .availability(waiter.waitUntilVisibilityOfElementLocated(productAvailabilityLocator).getText())
                .condition(waiter.waitUntilVisibilityOfElementLocated(productConditionLocator).getText())
                .brand(waiter.waitUntilVisibilityOfElementLocated(productBrandLocator).getText().replace(" ", ""))
                .build();
    }

    @Step("Set product quantity")
    public ProductDetailsPage setProductQuantity(int quantity) {
        logger.info("Set quantity to [{}]", quantity);
        WebElement input = waiter.waitUntilVisibilityOfElementLocated(productQuantityInputLocator);
        input.clear();
        input.sendKeys(String.valueOf(quantity));
        return this;
    }

    @Step("Click add to cart")
    public CartModal clickAddToCart() {
        logger.info("Click [add to cart] button");
        waiter.waitUntilElementClickable(productAddToCartLocator).click();
        return new CartModal();
    }

    @Step("Assert product details to be equals")
    public ProductDetailsPage assertProductDetailsToBeEquals(ProductDetails actual, ProductDetails expected) {
        logger.info(String.format("""
                        Assert: products to be equal
                         %-13s : actual: [%-25s] | expected: [%-25s]
                         %-13s : actual: [%-25s] | expected: [%-25s]
                         %-13s : actual: [%-25s] | expected: [%-25s]
                         %-13s : actual: [%-25s] | expected: [%-25s]
                         %-13s : actual: [%-25s] | expected: [%-25s]
                         %-13s : actual: [%-25s] | expected: [%-25s]
                        """,
                "Name", actual.getName(), expected.getName(),
                "Category", actual.getCategory(), expected.getCategory(),
                "Price", actual.getPrice(), expected.getPrice(),
                "Availability", actual.getAvailability(), expected.getAvailability(),
                "Condition", actual.getCondition(), expected.getCondition(),
                "Brand", actual.getBrand(), expected.getBrand()));
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
        logger.info("Assert result: [PASSED]");
        return this;
    }
}
