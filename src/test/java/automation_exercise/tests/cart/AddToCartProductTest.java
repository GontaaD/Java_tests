package automation_exercise.tests.cart;

import automation_exercise.pages.CartPage;
import automation_exercise.pages.ProductsPage;
import automation_exercise.pages.ProductsPage.ProductFields;
import automation_exercise.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AddToCartProductTest extends BaseTest {
    ProductsPage productsPage;
    CartPage cartPage;

    @Test
    public void addToCartProductTest(){
        productsPage =  new ProductsPage();
        cartPage =  new CartPage();

        List<String> names = List.of("Blue Top", "Men Tshirt");
        List<String> prices = List.of("Rs. 500", "Rs. 400");
        List<String> quantity = List.of("1", "1");
        List<String> totalPrices = cartPage.getTotalPrices(prices, quantity);


        mainMenu
                .clickProductPageButton();

        List<WebElement> allProducts = productsPage.getAllElements(ProductFields.ADD_BUTTON);

        productsPage
                .clickAddToCartButton(allProducts.getFirst())
                .clickCloseModalButton();

        productsPage
                .clickAddToCartButton(allProducts.get(1))
                .clickViewCartButton();

        assertThat(cartPage.getProductCountInCart())
                .as("Products count: " + cartPage.getProductCountInCart() + " is not as 2")
                .isEqualTo(2);

        List<String> productNames = cartPage.getAllText(CartPage.CartFields.NAME);
        List<String> productPrices = cartPage.getAllText(CartPage.CartFields.PRICE);
        List<String> productQuantity = cartPage.getAllText(CartPage.CartFields.QUANTITY);
        List<String> productTotalPrice = cartPage.getAllText(CartPage.CartFields.TOTAL_PRICE);

        assertThat(productNames)
                .as("Incorrect product names")
                .isEqualTo(names);

        assertThat(productPrices)
                .as("Incorrect product prices")
                .isEqualTo(prices);

        assertThat(productQuantity)
                .as("Incorrect product quantity")
                .isEqualTo(quantity);

        assertThat(productTotalPrice)
                .as("Incorrect product total price")
                .isEqualTo(totalPrices);
    }
}
