package automation_exercise.helpers;

import automation_exercise.models.UserRegistrationData;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

//import static automation_exercise.helpers.DataRandomizer.*;

//public class UserFactory {
//    private static final Faker faker = new Faker();
//    public static String userName = faker.name().firstName() + " " + faker.name().lastName();
//    private static final List<String> titles = List.of("Mr", "Mrs");
//    private static final List<String> country = List.of("India", "United States", "Canada", "Australia", "Israel", "New Zealand", "Singapore");

//    public static UserRegistrationData getUserForRegistration(){
//        String[] randomBirthday = getRandomBirthday();
//        return UserRegistrationData.builder()
//                .title(titles.get(new Random().nextInt(titles.size())))
//                .password(faker.internet().password(true))
//                .day(randomBirthday[0])
//                .month(randomBirthday[1])
//                .year(randomBirthday[2])
//                .firstName(faker.name().firstName())
//                .lastName(faker.name().lastName())
//                .company(faker.company().name())
//                .address1(faker.address().fullAddress())
//                .address2(faker.address().secondaryAddress())
//                .country(country.get(new Random().nextInt(country.size())))
//                .state(faker.address().state())
//                .city(faker.address().city())
//                .zipcode(faker.address().zipCode())
//                .mobile(faker.phoneNumber().phoneNumber()).build();
//    }

//    private static String[] getRandomBirthday() {
//        Date birthday = faker.date().birthday(18, 70);
//        LocalDate localDate = birthday.toInstant()
//                .atZone(ZoneId.systemDefault())
//                .toLocalDate();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
//        String formatLocalDate = localDate.format(formatter);
//        return formatLocalDate.split(" ");
//    }

//    public static String getRandomEmail() {
//        UUID uuid = UUID.randomUUID();
//        String emailPart = uuid.toString().substring(0, 4);
//        return emailPart + "_" + faker.internet().emailAddress();
//    }
//}
