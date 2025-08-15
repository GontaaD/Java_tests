package automation_exercise_pom.helpers;

import automation_exercise_pom.models.UserRegistrationData;

import static automation_exercise_pom.helpers.DataRandomizer.*;
import static automation_exercise_pom.helpers.DataRandomizer.getRandomAddress1;
import static automation_exercise_pom.helpers.DataRandomizer.getRandomAddress2;
import static automation_exercise_pom.helpers.DataRandomizer.getRandomCity;
import static automation_exercise_pom.helpers.DataRandomizer.getRandomCompany;
import static automation_exercise_pom.helpers.DataRandomizer.getRandomCountry;
import static automation_exercise_pom.helpers.DataRandomizer.getRandomLastName;
import static automation_exercise_pom.helpers.DataRandomizer.getRandomMobile;
import static automation_exercise_pom.helpers.DataRandomizer.getRandomState;
import static automation_exercise_pom.helpers.DataRandomizer.getRandomZipCode;

public class UserFactory {
    public static String userName = getRandomFirstName() + " " + getRandomLastName();
    public static UserRegistrationData getUserForRegistration(){
        String[] randomBirthday = getRandomBirthday();
        return UserRegistrationData.builder()
                .title(getRandomTitle())
                .password(getRandomPassword())
                .day(randomBirthday[0])
                .month(randomBirthday[1])
                .year(randomBirthday[2])
                .firstName(getRandomFirstName())
                .lastName(getRandomLastName())
                .company(getRandomCompany())
                .address1(getRandomAddress1())
                .address2(getRandomAddress2())
                .country(getRandomCountry())
                .state(getRandomState())
                .city(getRandomCity())
                .zipcode(getRandomZipCode())
                .mobile(getRandomMobile()).build();
    }
}
