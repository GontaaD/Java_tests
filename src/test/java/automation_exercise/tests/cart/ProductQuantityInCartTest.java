package automation_exercise.tests.cart;

import automation_exercise.pages.CartPage;
import automation_exercise.pages.ProductDetailsPage;
import automation_exercise.pages.ProductsPage;
import automation_exercise.pages.ProductsPage.ProductFields;
import automation_exercise.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductQuantityInCartTest extends BaseTest {
    ProductsPage productsPage;
    ProductDetailsPage productDetailsPage;
    CartPage cartPage;

    @Test
    public void productQuantityInCurtTest() {
        productsPage =  new ProductsPage();
        productDetailsPage =  new ProductDetailsPage();
        cartPage =  new CartPage();

        String name = "Blue Top";
        String quantity = "4";

        mainMenu
                .clickProductPageButton();

        List<WebElement> allProducts = productsPage.getAllElementsFromProducts(ProductFields.VIEW_BUTTON);

        productsPage
                .clickViewProductButton(allProducts.getFirst());

        productDetailsPage
                .setProductQuantity(quantity)
                .clickAddToCartButton()
                .clickViewCartButton();

        List<String> productName = cartPage.getAllTextFromCart(CartPage.CartFields.NAME);
        List<String> productQuantity = cartPage.getAllTextFromCart(CartPage.CartFields.QUANTITY);

        assertThat(productName.getFirst())
                .as("Incorrect product name")
                .isEqualTo(name);

        assertThat(productQuantity.getFirst())
                .as("Incorrect product quantity")
                .isEqualTo(quantity);
    }
}
