package automation_exercise.models;

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

    @Override
    public String toString() {
        return String.format(
                """
                        image            : [%-8s]
                        addToCartButton  : [%-8s]
                        viewProductButton: [%-8s]
                        price            : [%-35s]
                        name             : [%-35s]
                        """,
                getImage() == null ? "Null" : "Not Null",
                getAddToCartButton() == null ? "Null" : "Not Null",
                getViewProductButton() == null ? "Null" : "Not Null",
                price,
                name
        );
    }
}
