package automation_exercise_tests.cart;

import automation_exercise_pom.models.Product;
import automation_exercise_pom.pages.CartPage;
import automation_exercise_pom.pages.ProductsPage;
import automation_exercise_tests.BaseTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteProductFromCartTest extends BaseTest {
    ProductsPage productsPage;
    CartPage cartPage;

    @Test
    public void deleteProductFromCartTest() {
        productsPage =  new ProductsPage();
        cartPage =  new CartPage();

        mainMenu
                .clickProductPageButton();

        List<Product> allProducts = productsPage
                .getAllProducts();

        Product product = allProducts.getFirst();

        productsPage
                .clickAddToCartButton(product)
                .clickViewCartButton();

        cartPage
                .clickDeleteFromCartButton();

        assertThat(cartPage.isCartIsEmpty())
                .as("ERROR: Cart should be empty, but it is not")
                .isTrue();
    }
}