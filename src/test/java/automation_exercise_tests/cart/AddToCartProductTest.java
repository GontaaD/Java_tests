package automation_exercise_tests.cart;

import automation_exercise_pom.helpers.ExpectedProductBuilder;
import automation_exercise_pom.models.Product;
import automation_exercise_pom.models.ProductInCart;
import automation_exercise_tests.BaseTest;
import org.testng.annotations.Test;

import java.util.List;

public class AddToCartProductTest extends BaseTest {

    @Test
    public void addToCartProductTest(){
        mainPage
                .assertMainPageSuccessfullyLoaded()
                .openProductsPage();

        List<Product> allProducts = productsPage
                .getAllProducts();

        allProducts
                .get(0)
                .clickAddToCartButton()
                .clickCloseModalButton();

        allProducts
                .get(1)
                .clickAddToCartButton()
                .clickViewCartButton();

        List<ProductInCart> allProductsInCart = cartPage
                .assertProductInCartToBe(2)
                .getAllProductsInCart();

        ProductInCart expectedFirstProductInCart = ExpectedProductBuilder.getExpectedProduct("blueTop", 1);
        ProductInCart actualFirstProductInCart = cartPage.filterProducts(allProductsInCart, "Blue Top");

        cartPage.assertProductsToBeEqual(actualFirstProductInCart, expectedFirstProductInCart);

        ProductInCart expectedSecondProductInCart = ExpectedProductBuilder.getExpectedProduct("menTshirt", 1);
        ProductInCart actualSecondProductInCart = cartPage.filterProducts(allProductsInCart, "Men Tshirt");

        cartPage.assertProductsToBeEqual(actualSecondProductInCart, expectedSecondProductInCart);
    }
}
