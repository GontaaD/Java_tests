package automation_exercise_tests.cart;

import automation_exercise_pom.helpers.ExpectedProductBuilder;
import automation_exercise_pom.models.Product;
import automation_exercise_pom.models.ProductInCart;
import automation_exercise_tests.BaseTest;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

import java.util.List;

public class ProductQuantityInCurtTest extends BaseTest {

    @Test
    @Step("Product quantity in curt test start")
    public void productQuantityInCurtTest() {
        mainPage
                .assertMainPageSuccessfullyLoaded()
                .openProductsPage();

        List<Product> allProducts = productsPage
                .assertProductsCountToBe(34)
                .getAllProducts();

        allProducts
                .getFirst()
                .clickViewProductButton();

        ProductInCart expectedProductInCart = ExpectedProductBuilder
                .getExpectedProduct("blueTop", 4);

        productDetailsPage
                .setProductQuantity(4)
                .clickAddToCart()
                .clickViewCartButton();

        List<ProductInCart> allProductsInCart = cartPage
                .assertProductInCartToBe(1)
                .getAllProductsInCart();

        ProductInCart actualProductInCart = allProductsInCart.getFirst();

        cartPage.assertProductsToBeEqual(actualProductInCart, expectedProductInCart);
    }
}
