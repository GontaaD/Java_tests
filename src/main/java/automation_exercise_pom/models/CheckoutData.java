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

    @Override
    public String toString() {
        return String.format(
                """
                        name    : [%-50s]
                        company : [%-50s]
                        address1: [%-50s]
                        address2: [%-50s]
                        city    : [%-50s]
                        country : [%-50s]
                        phone   : [%-50s]
                        """,
                name, company, address1, address2, city, country, phone
        );
    }
}
