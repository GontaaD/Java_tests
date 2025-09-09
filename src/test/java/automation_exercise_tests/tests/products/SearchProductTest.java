package automation_exercise_tests.tests.products;

import automation_exercise_pom.models.Product;
import automation_exercise_pom.pages.ProductsPage;
import automation_exercise_tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchProductTest extends BaseTest {
    ProductsPage productsPage;

    @Test
    public void searchProductsTest(){
        productsPage =  new ProductsPage();

        mainMenu
                .clickProductPageButton();

        List<Product> allProducts = productsPage
                .setSearchProduct("printed")
                .clickSubmitSearchButton()
                .getAllProducts();

        Product actualFirstProduct = allProducts.getFirst();
        Product firstExpectedProduct = Product.builder()
                .name("Sleeves Printed Top - White")
                .price("Rs. 499")
                .build();

        assertThat(productsPage.isProductDetailsToBeEqual(actualFirstProduct, firstExpectedProduct))
                .as("ERROR: Product details are not equals")
                .isTrue();

        Product actualSecondProduct = allProducts.get(1);
        Product secondExpectedProduct = Product.builder()
                .name("Printed Off Shoulder Top - White")
                .price("Rs. 315")
                .build();

        assertThat(productsPage.isProductDetailsToBeEqual(actualSecondProduct, secondExpectedProduct))
                .as("ERROR: Product details are not equals")
                .isTrue();
    }
}
