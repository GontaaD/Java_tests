package automation_exercise.tests.cart;

import automation_exercise.helpers.ExpectedProductBuilder;
import automation_exercise.models.Product;
import automation_exercise.models.ProductInCart;
import automation_exercise.pages.CartPage;
import automation_exercise.pages.ProductDetailsPage;
import automation_exercise.pages.ProductsPage;
import automation_exercise.base.BaseTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductQuantityInCurtTest extends BaseTest {
    ProductsPage productsPage;
    ProductDetailsPage productDetailsPage;
    CartPage cartPage;

    @Test
    public void productQuantityInCurtTest() {
        productsPage =  new ProductsPage();
        productDetailsPage =  new ProductDetailsPage();
        cartPage =  new CartPage();

        mainMenu
                .clickProductPageButton();

        List<Product> allProducts = productsPage
                .getAllProducts();

        Product product = allProducts.getFirst();

        productsPage
                .clickViewProductButton(product);

        ProductInCart expectedProductInCart = ExpectedProductBuilder
                .getExpectedProduct("blueTop", 4);

        productDetailsPage
                .setProductQuantity(4)
                .clickAddToCart()
                .clickViewCartButton();

        assertThat(cartPage.getProductCountInCart())
                .as("ERROR: Products count is not as expected")
                .isEqualTo(1);

        List<ProductInCart> allProductsInCart = cartPage
                .getAllProductsInCart();

        ProductInCart actualProductInCart = allProductsInCart.getFirst();

        assertThat(cartPage.isProductsToBeEqual(actualProductInCart, expectedProductInCart))
                .as("ERROR: products are not equal")
                .isTrue();
    }
}
