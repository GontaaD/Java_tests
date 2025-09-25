package automation_exercise.interfaces;

import automation_exercise.models.ProductInCart;
import automation_exercise.pages.ProductsPage;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static automation_exercise.pages.BasePage.getDriver;

public interface IProductInCart {
    By productInCartContainerLocator = By.xpath("//tr[contains(@id, 'product-')]");
    By productInCartNameLocator = By.xpath(".//a[contains(@href, '/product_details/')]");
    By productInCartPriceLocator = By.xpath(".//td[@class='cart_price']//p");
    By productInCartQuantityLocator = By.xpath(".//td[@class='cart_quantity']//button");
    By productInCartTotalPriceLocator = By.xpath(".//p[@class='cart_total_price']");

    @Getter
    @AllArgsConstructor
    enum CartFields implements LocatorProvider {
        NAME(By.xpath("//tr[contains(@id, 'product-')]//a[contains(@href, '/product_details/')]")),
        PRICE(By.xpath("//tr[contains(@id, 'product-')]//td[@class='cart_price']//p")),
        QUANTITY(By.xpath("//tr[contains(@id, 'product-')]//td[@class='cart_quantity']//button")),
        TOTAL_PRICE(By.xpath("//tr[contains(@id, 'product-')]//p[@class='cart_total_price']"));

        private final By locator;
    }

    default List<String> getAllText(CartFields field) {
        return getDriver().findElements(field.getLocator()).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    default String getTextOrNull(WebElement container, By locator) {
        List<WebElement> elements = container.findElements(locator);
        return elements.isEmpty() ? null : elements.getFirst().getText();
    }

    default String getTotalPrice(String price,  String  quantity) {
        int intPrice = Integer.parseInt(price.replace("Rs. ", ""));
        int intQuantity = Integer.parseInt(quantity.trim());
        int totalPrice  = intPrice * intQuantity;
        return "Rs. " + totalPrice;
    }

    default List<String> getTotalPrices(List<String> prices,  List<String> quantitys) {
        if (quantitys.size() != prices.size()) {
            throw new IllegalArgumentException("Lists must have the same size");
        }

        List<String> totalPrices = new ArrayList<>();
        for (int i = 0; i < prices.size(); i++) {
            int price;
            int quantity;

            try {
                price = Integer.parseInt(prices.get(i).replace("Rs. ", ""));
            } catch (NumberFormatException e) {
                price = 0;
            }

            try {
                quantity = Integer.parseInt(quantitys.get(i).trim());
            } catch (NumberFormatException e) {
                quantity = 0;
            }

            totalPrices.add("Rs. " + price * quantity);
        }
        return totalPrices;
    }

    @Step("Get all products in cart")
    default List<ProductInCart> getAllProductsInCart(){
        List<ProductInCart> productInCartList = new ArrayList<>();
        List<WebElement> productInCartContainers = getDriver().findElements(productInCartContainerLocator);
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