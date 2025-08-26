package automation_exercise_tests.cart;

import automation_exercise_pom.models.Product;
import automation_exercise_tests.BaseTest;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

import java.util.List;

public class DeleteProductFromCartTest extends BaseTest {

    @Test
    @Step("Delete product from cart test start")
    public void deleteProductFromCartTest(){
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
                .assertCartIsEmpty();
    }
}
