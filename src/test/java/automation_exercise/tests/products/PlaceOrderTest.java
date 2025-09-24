package automation_exercise.tests.products;

import automation_exercise.helpers.ExpectedProductBuilder;
import automation_exercise.models.CheckoutData;
import automation_exercise.models.Product;
import automation_exercise.models.ProductInCart;
import automation_exercise.models.UserRegistrationData;
import automation_exercise.pages.*;
import automation_exercise.base.BaseTest;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static automation_exercise.models.UserRegistrationData.getRandomEmail;
import static org.assertj.core.api.Assertions.assertThat;


public class PlaceOrderTest extends BaseTest {
    Faker faker = new Faker();
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
        UserRegistrationData user = new UserRegistrationData();

        mainMenu
                .clickLoginPageButton()
                .inputName(user.getUsername())
                .inputRegistrationEmail(getRandomEmail())
                .clickSignupButton();

        createAccountPage
                .userRegisterWithDetails(user)
                .clickContinueButton();

        assertThat(mainMenu.isUsernameVisible(user.getUsername()))
                .as("Username: " + user.getUsername() + " is visible")
                .isTrue();

        mainMenu
                .clickProductPageButton();

        List<Product> allProducts = productsPage
                .getAllProducts();

        Product product = allProducts.getFirst();

        productsPage
                .clickAddToCartButton(product)
                .clickViewCartButton()
                .clickCheckoutButton();

        CheckoutData actualCheckoutData = checkoutPage.getDeliveryAddress();
        CheckoutData expectedCheckoutData = checkoutPage.getExpectedCheckoutData(user);

        assertThat(checkoutPage.isDeliveryAddressToBeEqual(actualCheckoutData, expectedCheckoutData))
                .as("Checkout data are not equal")
                .isTrue();

        List<ProductInCart> allProductsInCart = checkoutPage
                .getAllProductsInCart();

        assertThat(allProductsInCart.size())
                .as("ERROR: The number of products in cart is not equal to expected")
                .isEqualTo(1);

        ProductInCart actualProductInCart = cartPage.filterProducts(allProductsInCart, "Blue Top");
        ProductInCart expectedProductInCart = ExpectedProductBuilder.getExpectedProduct("blueTop", 1);

        assertThat(cartPage.isProductsToBeEqual(actualProductInCart, expectedProductInCart))
                .as("ERROR: Products are not equal")
                .isTrue();

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
                .as("Error: Successfully order message is not visible")
                .isTrue();

        statusPage
                .clickContinueButton();

        mainMenu
                .clickDeleteAccountButton();

        assertThat(statusPage.isDeleteSuccessfullyMassageIsVisible())
                .as("ERROR: Delete successfully massage is not visible")
                .isTrue();
    }
}
