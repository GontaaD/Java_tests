package automation_exercise.tests.cart;

import automation_exercise.pages.CartPage;
import automation_exercise.pages.ProductsPage;
import automation_exercise.pages.ProductsPage.ProductFields;
import automation_exercise.base.BaseTest;
import org.openqa.selenium.WebElement;
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

        List<WebElement> allProducts = productsPage.getAllElements(ProductFields.ADD_BUTTON);

        productsPage
                .clickAddToCartButton(allProducts.getFirst())
                .clickViewCartButton();

        cartPage
                .clickDeleteFromCartButton();

        assertThat(cartPage.isCartIsEmpty())
                .as("Cart should be empty, but it is not")
                .isTrue();
    }
}