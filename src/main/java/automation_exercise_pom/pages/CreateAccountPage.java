package automation_exercise_pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class CreateAccountPage extends BasePage {
    private final By mrRadioButtonLocator = By.xpath("//div[@id='uniform-id_gender1']");
    private final By mrsRadioButtonLocator = By.xpath("//div[@id='uniform-id_gender2']");
    private final By passwordInputLocator = By.xpath("//input[@id='password']");
    private final By daysSelectLocator = By.xpath("//select[@id='days']");
    private final By monthsSelectLocator = By.xpath("//select[@id='months']");
    private final By yearsSelectLocator = By.xpath("//select[@id='years']");
    private final By newsletterButtonLocator = By.xpath("//input[@id='newsletter']");
    private final By optinButtonLocator = By.xpath("//input[@id='optin']");
    private final By firstNameInputLocator = By.xpath("//input[@id='first_name']");
    private final By lastNameInputLocator = By.xpath("//input[@id='last_name']");
    private final By companyInputLocator = By.xpath("//input[@id='company']");
    private final By address1InputLocator = By.xpath("//input[@id='address1']");
    private final By address2InputLocator = By.xpath("//input[@id='address2']");
    private final By countrySelectLocator = By.xpath("//select[@id='country']");
    private final By stateInputLocator = By.xpath("//input[@id='state']");
    private final By cityInputLocator = By.xpath("//input[@id='city']");
    private final By zipcodeInputLocator = By.xpath("//input[@id='zipcode']");
    private final By mobileNumberInputLocator = By.xpath("//input[@id='mobile_number']");
    private final By createAccountButtonLocator = By.xpath("//button[@data-qa='create-account']");

    @Step("Click gender radio button")
    public CreateAccountPage clickGenderRadioButton(String title){
        logger.info("Click [gender: {}] for registration",  title);
        if(title.equals("Mr")){
            waitUntilElementClickable(mrRadioButtonLocator).click();
        }else{
            waitUntilVisibilityOfElementLocated(mrsRadioButtonLocator).click();
        }
        return this;
    }

    @Step("Password input")
    public CreateAccountPage passwordInput(String value){
        logger.info("Input [password] for registration");
        waitUntilVisibilityOfElementLocated(passwordInputLocator).sendKeys(value);
        return this;
    }

    @Step("Select days")
    public CreateAccountPage selectDays(String value){
        logger.info("Select [day of birth: {}] for registration",  value);
        new Select(waitUntilVisibilityOfElementLocated(daysSelectLocator)).selectByVisibleText(value);
        return this;
    }

    @Step("Select months")
    public CreateAccountPage selectMonths(String value){
        logger.info("Select [months of birth: {}] for registration",  value);
        new Select(waitUntilVisibilityOfElementLocated(monthsSelectLocator)).selectByVisibleText(value);
        return this;
    }

    @Step("Select years")
    public CreateAccountPage selectYears(String value){
        logger.info("Select [years of birth: {}] for registration",  value);
        new Select(waitUntilVisibilityOfElementLocated(yearsSelectLocator)).selectByVisibleText(value);
        return this;
    }

    @Step("Click newsletter")
    public CreateAccountPage clickNewsletter(){
        logger.info("Click [News letter] checkbox for registration");
        waitUntilElementClickable(newsletterButtonLocator).click();
        return this;
    }

    @Step("Click optin")
    public CreateAccountPage clickOptin(){
        logger.info("Click [Optin] checkbox for registration");
        waitUntilElementClickable(optinButtonLocator).click();
        return this;
    }

    @Step("First name input")
    public CreateAccountPage firstNameInput(String value){
        logger.info("Input [first name: {}] for registration",  value);
        waitUntilVisibilityOfElementLocated(firstNameInputLocator).sendKeys(value);
        return this;
    }

    @Step("Last name input")
    public CreateAccountPage lastNameInput(String value){
        logger.info("Input [last name: {}] for registration",  value);
        waitUntilVisibilityOfElementLocated(lastNameInputLocator).sendKeys(value);
        return this;
    }

    @Step("Company input")
    public CreateAccountPage companyInput(String value){
        logger.info("Input [company: {}] for registration",  value);
        waitUntilVisibilityOfElementLocated(companyInputLocator).sendKeys(value);
        return this;
    }

    @Step("Address1 input")
    public CreateAccountPage address1Input(String value){
        logger.info("Input [address 1: {}] for registration",  value);
        waitUntilVisibilityOfElementLocated(address1InputLocator).sendKeys(value);
        return this;
    }

    @Step("Address2 input")
    public CreateAccountPage address2Input(String value){
        logger.info("Input [address 2: {}] for registration",  value);
        waitUntilVisibilityOfElementLocated(address2InputLocator).sendKeys(value);
        return this;
    }

    @Step("Select country")
    public CreateAccountPage selectCountry(String value){
        logger.info("Select [country: {}] for registration",  value);
        new Select(waitUntilVisibilityOfElementLocated(countrySelectLocator)).selectByVisibleText(value);
        return this;
    }

    @Step("State input")
    public CreateAccountPage stateInput(String value){
        logger.info("Input [state: {}] for registration",  value);
        waitUntilVisibilityOfElementLocated(stateInputLocator).sendKeys(value);
        return this;
    }

    @Step("City input")
    public CreateAccountPage cityInput(String value){
        logger.info("Input [city: {}] for registration",  value);
        waitUntilVisibilityOfElementLocated(cityInputLocator).sendKeys(value);
        return this;
    }

    @Step("Zipcode input")
    public CreateAccountPage zipcodeInput(String value){
        logger.info("Input [zipcode: {}] for registration",  value);
        waitUntilVisibilityOfElementLocated(zipcodeInputLocator).sendKeys(value);
        return this;
    }

    @Step("Mobile number input")
    public CreateAccountPage mobileNumberInput(String value){
        logger.info("Input [mobile number: {}] for registration",  value);
        waitUntilVisibilityOfElementLocated(mobileNumberInputLocator).sendKeys(value);
        return this;
    }

    @Step("Click create account button")
    public StatusPage clickCreateAccountButton(){
        logger.info("Click [Create Account] button");
        waitUntilElementClickable(createAccountButtonLocator).click();
        return new StatusPage();
    }
}
