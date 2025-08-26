package automation_exercise_pom.pages.interfaces;

import automation_exercise_pom.models.ProductInCart;
import automation_exercise_pom.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public interface IProductInCart {
    By productInCartContainerLocator = By.xpath("//tr[contains(@id, 'product-')]");
    By productInCartNameLocator = By.xpath(".//a[contains(@href, '/product_details/')]");
    By productInCartPriceLocator = By.xpath(".//td[@class='cart_price']//p");
    By productInCartQuantityLocator = By.xpath(".//td[@class='cart_quantity']//button");
    By productInCartTotalPriceLocator = By.xpath(".//p[@class='cart_total_price']");

    default String getTextOrNull(WebElement container, By locator) {
        List<WebElement> elements = container.findElements(locator);
        return elements.isEmpty() ? null : elements.getFirst().getText();
    }

    @Step("Get all products in cart")
    default List<ProductInCart> getAllProductsInCart(){
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

    @Step("Filter products")
    default ProductInCart filterProducts(List<ProductInCart> allProductInCart, String expectedName){
        return allProductInCart.stream().filter(p->p.getName().equals(expectedName)).findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found: " + expectedName));
    }


}
