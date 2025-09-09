package automation_exercise.helpers;

import automation_exercise.models.UserRegistrationData;

import static automation_exercise.helpers.DataRandomizer.*;
import static automation_exercise.helpers.DataRandomizer.getRandomAddress1;
import static automation_exercise.helpers.DataRandomizer.getRandomAddress2;
import static automation_exercise.helpers.DataRandomizer.getRandomCity;
import static automation_exercise.helpers.DataRandomizer.getRandomCompany;
import static automation_exercise.helpers.DataRandomizer.getRandomCountry;
import static automation_exercise.helpers.DataRandomizer.getRandomLastName;
import static automation_exercise.helpers.DataRandomizer.getRandomMobile;
import static automation_exercise.helpers.DataRandomizer.getRandomState;
import static automation_exercise.helpers.DataRandomizer.getRandomZipCode;

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
