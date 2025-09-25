package automation_exercise.tests.products;

import automation_exercise.models.UserRegistrationData;
import automation_exercise.pages.*;
import automation_exercise.pages.ProductsPage.ProductFields;
import automation_exercise.pages.CheckoutPage.CheckoutFields;
import automation_exercise.base.BaseTest;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static automation_exercise.models.UserRegistrationData.getRandomEmail;
import static org.assertj.core.api.Assertions.assertThat;


public class PlaceOrderTest extends BaseTest {
    Faker faker;
    CreateAccountPage createAccountPage;
    ProductsPage productsPage;
    CheckoutPage checkoutPage;
    CartPage cartPage;
    PaymentPage paymentPage;
    StatusPage statusPage;

    @Test
    public void placeOrderTest(){
        createAccountPage = new CreateAccountPage();
        productsPage =  new ProductsPage();
        checkoutPage =  new CheckoutPage();
        cartPage =  new CartPage();
        paymentPage =  new PaymentPage();
        statusPage =  new StatusPage();
        faker = new Faker();

        UserRegistrationData user = new UserRegistrationData();
        String name = "Blue Top";
        String price = "Rs. 500";
        String quantity = "1";
        String totalPrice = "Rs. 500";

        mainMenu
                .clickLoginPageButton()
                .inputName(user.getUsername())
                .inputRegistrationEmail(getRandomEmail())
                .clickSignupButton();

        createAccountPage
                .inputUserDataForRegistration(user)
                .clickContinueButton();

        assertThat(mainMenu.isUsernameVisible(user.getUsername()))
                .as("Username: " + user.getUsername() + " is visible")
                .isTrue();

        mainMenu
                .clickProductPageButton();

        List<WebElement> allProducts = productsPage
                .getAllElementsFromProducts(ProductFields.ADD_BUTTON);

        productsPage
                .clickAddToCartButton(allProducts.getFirst())
                .clickViewCartButton()
                .clickOpenCheckoutButton();

        assertThat(checkoutPage.getTextFromCheckout(CheckoutFields.NAME))
                .as("Name is incorrect")
                .isEqualTo(user.getTitle() + ". " + user.getUsername());

        assertThat(checkoutPage.getTextFromCheckout(CheckoutFields.COMPANY))
                .as("Company is incorrect")
                .isEqualTo(user.getCompany());

        assertThat(checkoutPage.getTextFromCheckout(CheckoutFields.ADDRESS1))
                .as("Address1 is incorrect")
                .isEqualTo(user.getAddress1());

        assertThat(checkoutPage.getTextFromCheckout(CheckoutFields.ADDRESS2))
                .as("Address2 is incorrect")
                .isEqualTo(user.getAddress2());

        assertThat(checkoutPage.getTextFromCheckout(CheckoutFields.CITY))
                .as("City is incorrect")
                .isEqualTo(user.getCity() + " " + user.getState() + " " + user.getZipcode());

        assertThat(checkoutPage.getTextFromCheckout(CheckoutFields.COUNTRY))
                .as("Country is incorrect")
                .isEqualTo(user.getCountry());

        List<String> productName = checkoutPage.getAllTextFromCart(CheckoutPage.CartFields.NAME);
        List<String> productPrice = checkoutPage.getAllTextFromCart(CheckoutPage.CartFields.PRICE);
        List<String> productQuantity = checkoutPage.getAllTextFromCart(CheckoutPage.CartFields.QUANTITY);
        List<String> productTotalPrice = checkoutPage.getAllTextFromCart(CheckoutPage.CartFields.TOTAL_PRICE);

        assertThat(productName.size())
                .as("The number of products in cart is not equal to expected")
                .isEqualTo(1);

        assertThat(productName.getFirst())
                .as("Name is incorrect")
                .isEqualTo(name);

        assertThat(productPrice.getFirst())
                .as("Price is incorrect")
                .isEqualTo(price);

        assertThat(productQuantity.getFirst())
                .as("Quantity is incorrect")
                .isEqualTo(quantity);

        assertThat(productTotalPrice.getFirst())
                .as("Total price is incorrect")
                .isEqualTo(totalPrice);

        checkoutPage
                .clickPaymentButton()
                .inputCardName(faker.finance().creditCard())
                .inputCardNumber(faker.business().creditCardNumber())
                .inputCardCVC(String.valueOf(ThreadLocalRandom.current().nextInt(100, 1000)))
                .inputCardExpirationMonth(String.format("%02d", ThreadLocalRandom.current().nextInt(1, 13)))
                .inputCardExpirationYear(String.valueOf(ThreadLocalRandom.current()
                        .nextInt(java.time.Year.now().getValue() + 1, java.time.Year.now().getValue() + 6)))
                .clickSubmitOrderButton();

        assertThat(paymentPage.isSuccessfullyOrderMessageIsVisible())
                .as("Successfully order message is not visible")
                .isTrue();

        statusPage
                .clickContinueButton();

        mainMenu
                .clickDeleteAccountButton();

        assertThat(statusPage.isDeleteSuccessfullyMassageIsVisible())
                .as("Delete successfully massage is not visible")
                .isTrue();
    }
}
