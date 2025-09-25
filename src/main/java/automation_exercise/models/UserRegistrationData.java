package automation_exercise.models;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Getter
@Setter
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
    private String email;
    private String username;

    private static final Faker faker = new Faker();
    private static final String[] randomBirthday = getRandomBirthday();
    private static final List<String> titles = List.of("Mr", "Mrs");
    private static final List<String> countrys = List.of("India", "United States", "Canada", "Australia", "Israel", "New Zealand", "Singapore");

    public UserRegistrationData() {
        this.title = titles.get(new Random().nextInt(titles.size()));
        this.password = faker.internet().password(true);
        this.day = randomBirthday[0];
        this.month = randomBirthday[1];
        this.year = randomBirthday[2];
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.company = faker.company().name();
        this.address1 = faker.address().streetAddress();
        this.address2 = faker.address().secondaryAddress();
        this.country = countrys.get(new Random().nextInt(countrys.size()));
        this.state = faker.address().state();
        this.city = faker.address().city();
        this.zipcode = faker.address().zipCode();
        this.mobile = faker.phoneNumber().phoneNumber();
        this.username = this.firstName + " " + this.lastName;
        this.email = getRandomEmail();
    }

    private static String[] getRandomBirthday() {
        Date birthday = faker.date().birthday(18, 70);
        LocalDate localDate = birthday.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
        String formatLocalDate = localDate.format(formatter);
        return formatLocalDate.split(" ");
    }

    public static String getRandomEmail() {
        UUID uuid = UUID.randomUUID();
        String emailPart = uuid.toString().substring(0, 4);
        return emailPart + "_" + faker.internet().emailAddress();
    }
}
