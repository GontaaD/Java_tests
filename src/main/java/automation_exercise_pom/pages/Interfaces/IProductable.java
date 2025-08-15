package automation_exercise_pom.pages.Interfaces;

import automation_exercise_pom.helpers.Waiter;
import automation_exercise_pom.models.Product;
import automation_exercise_pom.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public interface IProductable {

    default Waiter waiter(){
        return new Waiter(BasePage.getDriver());
    }
    By productContainerLocator = By.xpath("//div[@class='product-image-wrapper']");
    By productImageLocator = By.xpath(".//img");
    By productPriceLocator = By.xpath(".//h2[1]");
    By productNameLocator = By.xpath(".//p[1]");
    By addToCartButtonLocator = By.xpath(".//a[contains(@class, 'add-to-cart')][1]");
    By viewProductButtonLocator = By.xpath("//a[contains(@href, '/product_details/')]");

    default WebElement findOrNull(WebElement container, By locator) {
        List<WebElement> elements = container.findElements(locator);
        return elements.isEmpty() ? null : elements.getFirst();
    }

    default String getTextOrNull(WebElement container, By locator) {
        List<WebElement> elements = container.findElements(locator);
        return elements.isEmpty() ? null : elements.getFirst().getText();
    }

    default List<Product> getAllProducts(){
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

    default IProductable assertProductsCountToBe(int count){
        waiter().waitUntilCountElementsToBe(productContainerLocator, count);
        return this;
    }

    default IProductable assertProductDetailsToBeEqual(Product actualProduct, Product expectedProduct){
        assertNotNull(actualProduct.getImage());
        assertEquals(actualProduct.getPrice(), expectedProduct.getPrice());
        assertEquals(actualProduct.getName(), expectedProduct.getName());
        assertNotNull(actualProduct.getAddToCartButton());
        assertNotNull(actualProduct.getViewProductButton());
        return this;
    }

}
