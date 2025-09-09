package automation_exercise.tests.cart;

import automation_exercise.helpers.ExpectedProductBuilder;
import automation_exercise.models.Product;
import automation_exercise.models.ProductInCart;
import automation_exercise.pages.CartPage;
import automation_exercise.pages.ProductsPage;
import automation_exercise.base.BaseTest;
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

        mainMenu
                .clickProductPageButton();

        List<Product> allProducts = productsPage
                .getAllProducts();

        Product firstProduct = allProducts.get(0);

        productsPage
                .clickAddToCartButton(firstProduct)
                .clickCloseModalButton();

        Product secondProduct = allProducts.get(1);

        productsPage
                .clickAddToCartButton(secondProduct)
                .clickViewCartButton();

        assertThat(cartPage.getProductCountInCart())
                .as("ERROR: Products count: " + cartPage.getProductCountInCart() + " is not as 2")
                .isEqualTo(2);

        List<ProductInCart> allProductsInCart = cartPage
                .getAllProductsInCart();

        ProductInCart expectedFirstProductInCart = ExpectedProductBuilder.getExpectedProduct("blueTop", 1);
        ProductInCart actualFirstProductInCart = cartPage.filterProducts(allProductsInCart, "Blue Top");

        assertThat(cartPage.isProductsToBeEqual(actualFirstProductInCart, expectedFirstProductInCart))
                .as("ERROR: Products are not equal")
                .isTrue();

        ProductInCart expectedSecondProductInCart = ExpectedProductBuilder.getExpectedProduct("menTshirt", 1);
        ProductInCart actualSecondProductInCart = cartPage.filterProducts(allProductsInCart, "Men Tshirt");

        assertThat(cartPage.isProductsToBeEqual(actualSecondProductInCart, expectedSecondProductInCart))
                .as("ERROR: Products are not equal")
                .isTrue();
    }
}
