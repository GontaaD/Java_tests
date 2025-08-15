package automation_exercise_pom.pages;

import automation_exercise_pom.models.ProductInCart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartPage extends BasePage {
    private final By productInCartContainerLocator = By.xpath("//tr[contains(@id, 'product-')]");
    private final By productInCartNameLocator = By.xpath(".//a[contains(@href, '/product_details/')]");
    private final By productInCartPriceLocator = By.xpath(".//td[@class='cart_price']//p");
    private final By productInCartQuantityLocator = By.xpath(".//td[@class='cart_quantity']//button");
    private final By productInCartTotalPriceLocator = By.xpath(".//p[@class='cart_total_price']");
    private final By productDeleteFromCartButtonLocator = By.xpath("//a[@class='cart_quantity_delete']");
    private final By cartIsEmptyMessageLocator = By.xpath("//b[text()='Cart is empty!']");

    public String getTextOrNull(WebElement container, By locator) {
        List<WebElement> elements = container.findElements(locator);
        return elements.isEmpty() ? null : elements.getFirst().getText();
    }

    public List<ProductInCart> getAllProductsInCart(){
        List<ProductInCart> productInCartList = new ArrayList<>();
        List<WebElement> productInCartContainers = BasePage.getDriver().findElements(productInCartContainerLocator);
        for (WebElement productInCartContainer : productInCartContainers) {
            String productInCartName = getTextOrNull(productInCartContainer, productInCartNameLocator);
            String productInCartPrice = getTextOrNull(productInCartContainer, productInCartPriceLocator);
            String productInCartQuantity = getTextOrNull(productInCartContainer, productInCartQuantityLocator);
            String productInCartTotalPrice = getTextOrNull(productInCartContainer, productInCartTotalPriceLocator);

            ProductInCart productInCart = ProductInCart.builder()
                    .name(productInCartName)
                    .price(productInCartPrice)
                    .quantity(productInCartQuantity)
                    .totalPrice(productInCartTotalPrice)
                    .build();

            productInCartList.add(productInCart);
        }
        return productInCartList;
    }

    public ProductInCart filterProducts(List<ProductInCart> allProductInCart, String expectedName){
        return allProductInCart.stream().filter(p->p.getName().equals(expectedName)).findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found: " + expectedName));
    }

    public CartPage clickDeleteFromCartButton(){
        waiter.waitUntilVisibilityOfElementLocated(productDeleteFromCartButtonLocator).click();
        return this;
    }

    public CartPage assertEmptyCart(){
        waiter.waitUntilVisibilityOfElementLocated(cartIsEmptyMessageLocator, Duration.ofSeconds(2));
        return this;
    }

    public CartPage assertProductInCartToBe(int count){
        new WebDriverWait(getDriver(), Duration.ofSeconds(2))
                .until(d -> !d.findElements(productInCartContainerLocator).isEmpty());
        List<WebElement> elements = getDriver().findElements(productInCartContainerLocator);
        assertEquals(elements.size(), count, "Number of products in cart does not match expected");
        return this;
    }

    public CartPage assertProductInCartToBeEqual(ProductInCart actualProduct, ProductInCart expectedProduct){
        assertEquals(actualProduct.getName(), expectedProduct.getName());
        System.out.println("actual name: [" + actualProduct.getName() + "] " + "expected name: [" + expectedProduct.getName() + "]");
        assertEquals(actualProduct.getPrice(), expectedProduct.getPrice());
        System.out.println("actual price: [" + actualProduct.getPrice() + "] " + "expected price: [" + expectedProduct.getPrice() + "]");
        assertEquals(actualProduct.getQuantity(), expectedProduct.getQuantity());
        System.out.println("actual quantity: [" + actualProduct.getQuantity() + "] " + "expected quantity: [" + expectedProduct.getQuantity() + "]");
        assertEquals(actualProduct.getTotalPrice(), expectedProduct.getTotalPrice());
        System.out.println("actual total price: [" + actualProduct.getTotalPrice() + "] " + "expected total price: [" + expectedProduct.getTotalPrice() + "]");
        return this;
    }
}
