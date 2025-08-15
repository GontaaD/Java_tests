package automation_exercise_tests.products;

import automation_exercise_pom.models.Product;
import automation_exercise_pom.models.ProductDetails;
import automation_exercise_tests.BaseTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsDetailsTest extends BaseTest {

    @Test
    public void checkProductDetailsTest(){

        mainPage
                .assertMainPageSuccessfullyLoaded()
                .openProductsPage();

        List<Product> allProducts = productsPage
                .assertProductsCountToBe(34)
                .getAllProducts();

        allProducts
                .getFirst()
                .clickViewProductButton();

        ProductDetails actualProductDetails = productDetailsPage
                .getProductDetails();

        ProductDetails expectedProductDetails = ProductDetails.builder()
                .name("Blue Top")
                .category("Category: Women > Tops")
                .price("Rs. 500")
                .availability("Availability: In Stock")
                .condition("Condition: New")
                .brand("Brand:Polo")
                .build();

        assertThat(actualProductDetails)
                .usingRecursiveComparison()
                .isEqualTo(expectedProductDetails);
    }
}
