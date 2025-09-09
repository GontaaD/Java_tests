package automation_exercise.tests.products;

import automation_exercise.models.Product;
import automation_exercise.models.ProductDetails;
import automation_exercise.pages.ProductDetailsPage;
import automation_exercise.pages.ProductsPage;
import automation_exercise.base.BaseTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsDetailsTest extends BaseTest {
    ProductsPage productsPage;
    ProductDetailsPage productDetailsPage;

    @Test
    public void checkProductDetailsTest(){
        productsPage =  new ProductsPage();
        productDetailsPage =  new ProductDetailsPage();

        mainMenu
                .clickProductPageButton();

        List<Product> allProducts = productsPage
                .getAllProducts();

        Product product = allProducts.getFirst();

        productsPage
                .clickViewProductButton(product);

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

        assertThat(productDetailsPage.isProductDetailsToBeEquals(actualProductDetails,  expectedProductDetails))
                .as("ERROR: Product details are not equals")
                .isTrue();
    }
}
