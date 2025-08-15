package automation_exercise_tests.products;

import automation_exercise_pom.models.Product;
import automation_exercise_tests.BaseTest;
import org.testng.annotations.Test;

import java.util.List;

public class DeleteProductFromCartTest extends BaseTest {

    @Test
    public void deleteProductFromCart(){
        mainPage
                .assertMainPageSuccessfullyLoaded()
                .openProductsPage();

        List<Product> allProducts = productsPage
                .getAllProducts();

        allProducts
                .getFirst()
                .clickAddToCartButton()
                .clickViewCartButton();

        cartPage
                .clickDeleteFromCartButton()
                .assertEmptyCart();
    }
}
