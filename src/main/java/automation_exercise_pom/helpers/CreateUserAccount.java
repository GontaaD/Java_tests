package automation_exercise_pom.helpers;

import automation_exercise_pom.models.UserRegistrationData;
import automation_exercise_pom.pages.CreateAccountPage;
import automation_exercise_pom.pages.CreatedAccountPage;
import io.qameta.allure.Step;

public class CreateUserAccount {
    private final CreateAccountPage createAccountPage =  new CreateAccountPage();

    @Step("userRegisterWithDetails")
    public CreatedAccountPage userRegisterWithDetails(UserRegistrationData user){
       return createAccountPage
                .clickGenderRadioButton(user.getTitle())
                .passwordInput(user.getPassword())
                .selectDays(user.getDay())
                .selectMonths(user.getMonth())
                .selectYears(user.getYear())
                .clickNewsletter()
                .clickOptin()
                .firstNameInput(user.getFirstName())
                .lastNameInput(user.getLastName())
                .companyInput(user.getCompany())
                .address1Input(user.getAddress1())
                .address2Input(user.getAddress2())
                .selectCountry(user.getCountry())
                .stateInput(user.getState())
                .cityInput(user.getCity())
                .zipcodeInput(user.getZipcode())
                .mobileNumberInput(user.getMobile())
                .clickCreateAccountButton();
    }
}
