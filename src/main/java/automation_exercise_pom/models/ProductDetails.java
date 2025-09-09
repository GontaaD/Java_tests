package automation_exercise_pom.models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class ProductDetails {
    private String name;
    private String category;
    private String price;
    private String availability;
    private String condition;
    private String brand;

    @Override
    public String toString() {
        return String.format(
                """
                        name        : [%-25s]
                        category    : [%-25s]
                        price       : [%-25s]
                        availability: [%-25s]
                        condition   : [%-25s]
                        brand       : [%-25s]
                        """,
                name, category, price, availability, condition, brand
        );
    }
}