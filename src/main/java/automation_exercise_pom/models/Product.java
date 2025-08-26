package automation_exercise_pom.models;

import automation_exercise_pom.pages.CartModal;
import automation_exercise_pom.pages.ProductDetailsPage;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private final Logger logger = LoggerFactory.getLogger(Product.class);

    @Step("Click view product button")
    public ProductDetailsPage clickViewProductButton(){
        logger.info("Click [view product] button");
        this.viewProductButton.click();
        return new ProductDetailsPage();
    }

    @Step("Click add to cart button")
    public CartModal clickAddToCartButton(){
        logger.info("Click [add to cart] button");
        this.addToCartButton.click();
        return new CartModal();
    }
}
