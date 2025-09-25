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
import static org.assertj.core.api.InstanceOfAssertFactories.FILE;

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

        List<WebElement> allProducts = productsPage.getAllElementsFromProducts(ProductFields.VIEW_BUTTON);

        productsPage
                .clickViewProductButton(allProducts.getFirst());

        String productName = productDetailsPage.getTextFromProductDetails(ProductDetailsFields.NAME);
        String productCategory = productDetailsPage.getTextFromProductDetails(ProductDetailsFields.CATEGORY);
        String productPrice = productDetailsPage.getTextFromProductDetails(ProductDetailsFields.PRICE);
        String productAvailability = productDetailsPage.getTextFromProductDetails(ProductDetailsFields.AVAILABILITY);
        String productCondition = productDetailsPage.getTextFromProductDetails(ProductDetailsFields.CONDITION);
        String productBrand = productDetailsPage.getTextFromProductDetails(ProductDetailsFields.BRAND);

        assertThat(productName)
                .as("Incorrect product name")
                .isEqualTo(name);

        assertThat(productCategory)
                .as("Incorrect product category")
                .isEqualTo(category);

        assertThat(productPrice)
                .as("Incorrect product price")
                .isEqualTo(price);

        assertThat(productAvailability)
                .as("Incorrect product availability")
                .contains(availability);

        assertThat(productCondition)
                .as("Incorrect product condition")
                .contains(condition);

        assertThat(productBrand)
                .as("Incorrect product brand")
                .contains(brand);
    }
}
