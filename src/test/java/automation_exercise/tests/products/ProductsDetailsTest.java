package automation_exercise.tests.products;

import automation_exercise.pages.ProductDetailsPage.ProductDetailsFields;
import automation_exercise.pages.ProductsPage.ProductFields;
import automation_exercise.pages.ProductDetailsPage;
import automation_exercise.pages.ProductsPage;
import automation_exercise.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsDetailsTest extends BaseTest {
    ProductsPage productsPage;
    ProductDetailsPage productDetailsPage;

    String name = "Blue Top";
    String category = "Category: Women > Tops";
    String price = "Rs. 500";
    String availability = "In Stock";
    String condition = "New";
    String brand = "Polo";

    @Test
    public void checkProductDetailsTest(){
        productsPage =  new ProductsPage();
        productDetailsPage =  new ProductDetailsPage();

        mainMenu
                .clickProductPageButton();

        List<WebElement> allProducts = productsPage.getAllElements(ProductFields.VIEW_BUTTON);

        productsPage
                .clickViewProductButton(allProducts.getFirst());

        List<String> productName = productDetailsPage.getAllText(ProductDetailsFields.NAME);
        List<String> productCategory = productDetailsPage.getAllText(ProductDetailsFields.CATEGORY);
        List<String> productPrice = productDetailsPage.getAllText(ProductDetailsFields.PRICE);
        List<String> productAvailability = productDetailsPage.getAllText(ProductDetailsFields.AVAILABILITY);
        List<String> productCondition = productDetailsPage.getAllText(ProductDetailsFields.CONDITION);
        List<String> productBrand = productDetailsPage.getAllText(ProductDetailsFields.BRAND);

        assertThat(productName.getFirst())
                .as("Incorrect product name")
                .isEqualTo(name);

        assertThat(productCategory.getFirst())
                .as("Incorrect product category")
                .isEqualTo(category);

        assertThat(productPrice.getFirst())
                .as("Incorrect product price")
                .isEqualTo(price);

        assertThat(productAvailability.getFirst())
                .as("Incorrect product availability")
                .contains(availability);

        assertThat(productCondition.getFirst())
                .as("Incorrect product condition")
                .contains(condition);

        assertThat(productBrand.getFirst())
                .as("Incorrect product brand")
                .contains(brand);
    }
}
