package automation_exercise_pom.models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class CheckoutData {
    private String name;
    private String company;
    private String address1;
    private String address2;
    private String city;
    private String country;
    private String phone;

}
