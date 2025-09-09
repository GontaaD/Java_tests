package automation_exercise.models;

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

    @Override
    public String toString() {
        return String.format(
                """
                        name      : [%-10s]
                        price     : [%-10s]
                        quantity  : [%-10s]
                        totalPrice: [%-10s]
                        """,
                name, price, quantity, totalPrice
        );
    }
}
