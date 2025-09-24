package automation_exercise.helpers;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

//public class DataRandomizer {
//    public static final Faker faker = new Faker();

//    public static String getRandomEmail() {
//        UUID uuid = UUID.randomUUID();
//        String emailPart = uuid.toString().substring(0, 4);
//        return emailPart + "_" + faker.internet().emailAddress();
//    }

//    public static String getRandomTitle() {
//        List <String> titles = List.of("Mr", "Mrs");
//        return titles.get(new Random().nextInt(titles.size()));
//    }

//    public static String getRandomPassword() {
//        return faker.internet().password(true);
//    }

//    public static String[] getRandomBirthday() {
//        Date birthday = faker.date().birthday(18, 70);
//        LocalDate localDate = birthday.toInstant()
//                .atZone(ZoneId.systemDefault())
//                .toLocalDate();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
//        String formatLocalDate = localDate.format(formatter);
//        return formatLocalDate.split(" ");
//    }

//    public static String getRandomFirstName() {
//        return faker.name().firstName();
//    }

//    public static String getRandomLastName() {
//        return faker.name().lastName();
//    }
//
//    public static String getRandomCompany() {
//        return faker.company().name();
//    }
//
//    public static String getRandomAddress1() {
//        return faker.address().fullAddress();
//    }
//
//    public static String getRandomAddress2() {
//        return faker.address().secondaryAddress();
//    }
//
//    public static String getRandomCountry() {
//        List <String> country = List.of("India", "United States", "Canada", "Australia", "Israel", "New Zealand", "Singapore");
//        return country.get(new Random().nextInt(country.size()));
//    }
//
//    public static String getRandomState() {
//        return faker.address().state();
//    }
//
//    public static String getRandomCity() {
//        return faker.address().city();
//    }
//
//    public static String getRandomZipCode() {
//        return faker.address().zipCode();
//    }
//
//    public static String getRandomMobile() {
//        return faker.phoneNumber().phoneNumber();
//    }

//    public static String getRandomCardName() {
//        return faker.finance().creditCard();
//    }
//
//    public static String getRandomCardNumber() {
//        return faker.finance().creditCard();
//    }
//
//    public static String getRandomCVC() {
//        int cvc = ThreadLocalRandom.current().nextInt(100, 1000);
//        return String.valueOf(cvc);
//    }
//
//    public static String getRandomExpirationMonth() {
//        int monthInt = ThreadLocalRandom.current().nextInt(1, 13);
//        return String.format("%02d", monthInt);
//    }
//
//    public static String getRandomExpirationYear() {
//        int currentYear = java.time.Year.now().getValue();
//        int yearInt = ThreadLocalRandom.current().nextInt(currentYear + 1, currentYear + 6);
//        return String.valueOf(yearInt);
//    }
//}