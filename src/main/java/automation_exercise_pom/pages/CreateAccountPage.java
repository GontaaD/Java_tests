package automation_exercise_pom.pages;

import automation_exercise_pom.models.Title;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class CreateAccountPage extends BasePage {
    private final By createAccountInfoTitleLocator = By.xpath("//b[text()='Enter Account Information']");
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

    public CreateAccountPage assertCreateAccountPageSuccessfullyLoaded(){
        waiter.waitUntilVisibilityOfElementLocated(createAccountInfoTitleLocator);
        return this;
    }

    public CreateAccountPage clickGenderRadioButton(Title title){
        if(title.equals(Title.Mr)){
            waiter.waitUntilElementClickable(mrRadioButtonLocator).click();
        }else{
            waiter.waitUntilVisibilityOfElementLocated(mrsRadioButtonLocator).click();
        }
        return this;
    }

    public CreateAccountPage passwordInput(String value){
        waiter.waitUntilVisibilityOfElementLocated(passwordInputLocator).sendKeys(value);
        return this;
    }

    public CreateAccountPage selectDays(String value){
        new Select(waiter.waitUntilVisibilityOfElementLocated(daysSelectLocator)).selectByVisibleText(value);
        return this;
    }

    public CreateAccountPage selectMonths(String value){
        new Select(waiter.waitUntilVisibilityOfElementLocated(monthsSelectLocator)).selectByVisibleText(value);
        return this;
    }

    public CreateAccountPage selectYears(String value){
        new Select(waiter.waitUntilVisibilityOfElementLocated(yearsSelectLocator)).selectByVisibleText(value);
        return this;
    }

    public CreateAccountPage clickNewsletter(){
        waiter.waitUntilElementClickable(newsletterButtonLocator).click();
        return this;
    }

    public CreateAccountPage clickOptin(){
        waiter.waitUntilElementClickable(optinButtonLocator).click();
        return this;
    }

    public CreateAccountPage firstNameInput(String value){
        waiter.waitUntilVisibilityOfElementLocated(firstNameInputLocator).sendKeys(value);
        return this;
    }

    public CreateAccountPage lastNameInput(String value){
        waiter.waitUntilVisibilityOfElementLocated(lastNameInputLocator).sendKeys(value);
        return this;
    }

    public CreateAccountPage companyInput(String value){
        waiter.waitUntilVisibilityOfElementLocated(companyInputLocator).sendKeys(value);
        return this;
    }

    public CreateAccountPage address1Input(String value){
        waiter.waitUntilVisibilityOfElementLocated(address1InputLocator).sendKeys(value);
        return this;
    }

    public CreateAccountPage address2Input(String value){
        waiter.waitUntilVisibilityOfElementLocated(address2InputLocator).sendKeys(value);
        return this;
    }

    public CreateAccountPage selectCountry(String value){
        new Select(waiter.waitUntilVisibilityOfElementLocated(countrySelectLocator)).selectByVisibleText(value);
        return this;
    }

    public CreateAccountPage stateInput(String value){
        waiter.waitUntilVisibilityOfElementLocated(stateInputLocator).sendKeys(value);
        return this;
    }

    public CreateAccountPage cityInput(String value){
        waiter.waitUntilVisibilityOfElementLocated(cityInputLocator).sendKeys(value);
        return this;
    }

    public CreateAccountPage zipcodeInput(String value){
        waiter.waitUntilVisibilityOfElementLocated(zipcodeInputLocator).sendKeys(value);
        return this;
    }

    public CreateAccountPage mobileNumberInput(String value){
        waiter.waitUntilVisibilityOfElementLocated(mobileNumberInputLocator).sendKeys(value);
        return this;
    }

    public CreatedAccountPage clickCreateAccountButton(){
        waiter.waitUntilElementClickable(createAccountButtonLocator).click();
        return new CreatedAccountPage();
    }

}
