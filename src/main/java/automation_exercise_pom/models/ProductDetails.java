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




}
