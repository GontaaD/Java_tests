package automation_exercise_pom.models;

import automation_exercise_pom.pages.CartModal;
import automation_exercise_pom.pages.ProductDetailsPage;
import automation_exercise_pom.pages.ProductsPage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    private WebElement image;
    private String price;
    private String name;
    private WebElement addToCartButton;
    private WebElement viewProductButton;

    public ProductDetailsPage clickViewProductButton(){
        this.viewProductButton.click();
        return new ProductDetailsPage();
    }

    public CartModal clickAddToCartButton(){
        this.addToCartButton.click();
        return new CartModal();
    }
}
