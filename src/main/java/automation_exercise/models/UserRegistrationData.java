package automation_exercise.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRegistrationData {
    private String title;
    private String password;
    private String day;
    private String month;
    private String year;
    private String firstName;
    private String lastName;
    private String company;
    private String address1;
    private String address2;
    private String country;
    private String state;
    private String city;
    private String zipcode;
    private String mobile;

}
