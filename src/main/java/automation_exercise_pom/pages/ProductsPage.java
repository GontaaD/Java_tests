package automation_exercise_pom.pages;

import automation_exercise_pom.helpers.AdsHelper;
import automation_exercise_pom.models.Product;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsPage extends BasePage{
    AdsHelper adsHelper = new AdsHelper();
    private final By searchProductInputLocator = By.xpath("//input[@id='search_product']");
    private final By submitSearchButtonLocator = By.xpath("//button[@id='submit_search']");
    private final By productContainerLocator = By.xpath("//div[@class='product-image-wrapper']");
    private final By productImageLocator = By.xpath(".//img");
    private final By productPriceLocator = By.xpath(".//h2[1]");
    private final By productNameLocator = By.xpath(".//p[1]");
    private final By addToCartButtonLocator = By.xpath(".//a[contains(@class, 'add-to-cart')][1]");
    private final By viewProductButtonLocator = By.xpath("//a[contains(@href, '/product_details/')]");

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
        return  products;
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
        adsHelper.removeAds();
        return new ProductDetailsPage();
    }

    @Step("Click add to cart button")
    public CartModal clickAddToCartButton(Product product){
        logger.info("Click [add to cart] button");
        adsHelper.removeAds();
        product.getAddToCartButton().click();
        return new CartModal();
    }

    @Step("Is product details to be equal?")
    public boolean isProductDetailsToBeEqual(Product actualProduct, Product expectedProduct){
        logger.info(String.format(
                """
                        Is product details to be equal
                         %-12s : %-8s
                         %-12s : %-8s
                         %-12s : %-8s
                         %-12s : actual: [%-35s] | expected: [%-35s]
                         %-12s : actual: [%-35s] | expected: [%-35s]
                        """,
                "Image", actualProduct.getImage() == null ? "[Null]" : "[Not Null]",
                "Add to cart", actualProduct.getAddToCartButton() == null ? "[Null]" : "[Not Null]",
                "View product", actualProduct.getImage() == null ? "[Null]" : "[Not Null]",
                "Price", actualProduct.getPrice(), expectedProduct.getPrice(),
                "Name", actualProduct.getName(), expectedProduct.getName()
        ));
        try {
            assertThat(actualProduct.getImage()).isNotNull();
            assertThat(actualProduct.getAddToCartButton()).isNotNull();
            assertThat(actualProduct.getViewProductButton()).isNotNull();
            assertThat(actualProduct.getPrice()).isEqualTo(expectedProduct.getPrice());
            assertThat(actualProduct.getName()).isEqualTo(expectedProduct.getName());
            return true;
        }  catch (AssertionError e) {
            return false;
        }
    }
}
