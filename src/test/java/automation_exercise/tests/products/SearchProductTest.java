package automation_exercise.tests.products;

import automation_exercise.pages.ProductsPage;
import automation_exercise.base.BaseTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchProductTest extends BaseTest {
    ProductsPage productsPage;
    String filter = "Printed";

    @Test
    public void searchProductsTest(){
        productsPage =  new ProductsPage();

        mainMenu
                .clickProductPageButton();

        productsPage
                .setSearchProduct(filter)
                .clickSubmitSearchButton();

        List<String> productNames = productsPage.getAllText(ProductsPage.ProductFields.NAME);

        assertThat(productNames)
                .as("The product does not match the filter")
                .allMatch(p -> p.contains(filter));
    }
}
