package automation_exercise.pages;

import automation_exercise.components.CartModal;
import automation_exercise.interfaces.LocatorProvider;
import automation_exercise.models.Product;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static automation_exercise.utils.AdsHelper.removeAds;

public class ProductsPage extends BasePage{
    private final By searchProductInputLocator = By.xpath("//input[@id='search_product']");
    private final By submitSearchButtonLocator = By.xpath("//button[@id='submit_search']");
    private final By productContainerLocator = By.xpath("//div[@class='product-image-wrapper']");
    private final By productImageLocator = By.xpath(".//img");
    private final By productPriceLocator = By.xpath(".//h2[1]");
    private final By productNameLocator = By.xpath(".//p[1]");
    private final By addToCartButtonLocator = By.xpath(".//a[contains(@class, 'add-to-cart')][1]");
    private final By viewProductButtonLocator = By.xpath("//a[contains(@href, '/product_details/')]");

    @Getter
    @AllArgsConstructor
    public enum ProductFields implements LocatorProvider {
        IMAGE(By.xpath(".//img")),
        PRICE(By.xpath(".//h2[1]")),
        NAME(By.xpath(".//p[1]")),
        ADD_BUTTON(By.xpath(".//a[contains(@class, 'add-to-cart')][1]")),
        VIEW_BUTTON(By.xpath("//a[contains(@href, '/product_details/')]"));

        private final By locator;
    }

    public List<WebElement> getAllElements(ProductFields field) {
        return getDriver().findElements(field.getLocator());
    }

    public WebElement findOrNull(WebElement container, By locator) {
        List<WebElement> elements = container.findElements(locator);
        return elements.isEmpty() ? null : elements.getFirst();
    }

    public String getTextOrNull(WebElement container, By locator) {
        List<WebElement> elements = container.findElements(locator);
        return elements.isEmpty() ? null : getTextNode(elements.getFirst());
    }

    public String getTextNode(WebElement element) {
        return (String) ((JavascriptExecutor) BasePage.getDriver())
                .executeScript(
                        "return Array.from(arguments[0].childNodes)" +
                                ".filter(n => n.nodeType === Node.TEXT_NODE)" +
                                ".map(n => n.textContent.trim()).join('');",
                        element
                );
    }

    @Step("Get all products")
    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();
        List<WebElement> productContainers = BasePage.getDriver().findElements(productContainerLocator);
        for (WebElement container : productContainers){
            WebElement productImage = findOrNull(container, productImageLocator);
            String productPrice = getTextOrNull(container, productPriceLocator);
            String productName = getTextOrNull(container, productNameLocator);
            WebElement addToCartButton = findOrNull(container, addToCartButtonLocator);
            WebElement viewProductButton = findOrNull(container, viewProductButtonLocator);

            Product product = Product.builder()
                    .image(productImage)
                    .price(productPrice)
                    .name(productName)
                    .addToCartButton(addToCartButton)
                    .viewProductButton(viewProductButton)
                    .build();

            products.add(product);
        }
        return products;
    }

    @Step("Set search product")
    public ProductsPage setSearchProduct(String text){
        logger.info("Set search product name: [{}]", text);
        waitUntilVisibilityOfElementLocated(searchProductInputLocator).sendKeys(text);
        return this;
    }

    @Step("Click submit search button")
    public ProductsPage clickSubmitSearchButton(){
        logger.info("Click [submit search] button");
        waitUntilElementClickable(submitSearchButtonLocator).click();
        return this;
    }

    @Step("Click view product button")
    public ProductDetailsPage clickViewProductButton(Product product){
        logger.info("Click [view product] button");
        product.getViewProductButton().click();
        removeAds();
        return new ProductDetailsPage();
    }

    @Step("Click add to cart button")
    public CartModal clickAddToCartButton(Product product){
        logger.info("Click [add to cart] button");
        removeAds();
        product.getAddToCartButton().click();
        return new CartModal();
    }

    @Step("Is product details to be equal?")
    public boolean isProductDetailsToBeEqual(Product actual, Product expected){
        Allure.addAttachment("Actual list", actual.toString());
        Allure.addAttachment("Expected list", expected.toString());
        logger.info(String.format(
                """
                        Is product details to be equal
                         %-12s : %-8s
                         %-12s : %-8s
                         %-12s : %-8s
                         %-12s : actual: [%-35s] | expected: [%-35s]
                         %-12s : actual: [%-35s] | expected: [%-35s]
                        """,
                "Image", actual.getImage() == null ? "[Null]" : "[Not Null]",
                "Add to cart", actual.getAddToCartButton() == null ? "[Null]" : "[Not Null]",
                "View product", actual.getImage() == null ? "[Null]" : "[Not Null]",
                "Price", actual.getPrice(), expected.getPrice(),
                "Name", actual.getName(), expected.getName()
        ));
        try {
            assertThat(actual.getImage()).isNotNull();
            assertThat(actual.getAddToCartButton()).isNotNull();
            assertThat(actual.getViewProductButton()).isNotNull();
            assertThat(actual.getPrice()).isEqualTo(expected.getPrice());
            assertThat(actual.getName()).isEqualTo(expected.getName());
            return true;
        }  catch (AssertionError e) {
            return false;
        }
    }
}
