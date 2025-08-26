package automation_exercise_tests.products;

import automation_exercise_pom.models.Product;
import automation_exercise_tests.BaseTest;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

import java.util.List;

public class SearchProductTest extends BaseTest {

    @Test
    @Step("Search products test start")
    public void searchProductsTest(){
        mainPage
                .assertMainPageSuccessfullyLoaded()
                .openProductsPage();

        List<Product> allProducts = productsPage
                .searchProduct("printed")
                .assertProductsCountToBe(2)
                .getAllProducts();

        Product actualfirstProduct = allProducts.getFirst();
        Product firstExpectedProduct = Product.builder()
                .name("Sleeves Printed Top - White")
                .price("Rs. 499")
                .build();

        productsPage.assertProductDetailsToBeEqual(actualfirstProduct, firstExpectedProduct);

        Product actualsecondProduct = allProducts.get(1);
        Product secondExpectedProduct = Product.builder()
                .name("Printed Off Shoulder Top - White")
                .price("Rs. 315")
                .build();

        productsPage.assertProductDetailsToBeEqual(actualsecondProduct, secondExpectedProduct);
    }
}
