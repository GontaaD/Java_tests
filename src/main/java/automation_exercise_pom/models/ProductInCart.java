package automation_exercise_pom.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Builder
@AllArgsConstructor
@Getter
public class ProductInCart {
    private String name;
    private String price;
    private String quantity;
    private String totalPrice;
}
