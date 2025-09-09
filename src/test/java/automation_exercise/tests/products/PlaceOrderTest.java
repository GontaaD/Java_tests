package automation_exercise.tests.products;

import automation_exercise.helpers.ExpectedProductBuilder;
import automation_exercise.helpers.UserFactory;
import automation_exercise.models.CheckoutData;
import automation_exercise.models.Product;
import automation_exercise.models.ProductInCart;
import automation_exercise.models.UserRegistrationData;
import automation_exercise.pages.*;
import automation_exercise.base.BaseTest;
import org.testng.annotations.Test;

import java.util.List;

import static automation_exercise.helpers.DataRandomizer.*;
import static org.assertj.core.api.Assertions.assertThat;


public class PlaceOrderTest extends BaseTest {
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

        UserRegistrationData user = UserFactory.getUserForRegistration();

        mainMenu
                .clickLoginPageButton()
                .inputName(UserFactory.userName)
                .inputRegistrationEmail(getRandomEmail())
                .clickSignupButton();

        createAccountPage
                .userRegisterWithDetails(user)
                .clickContinueButton();

        assertThat(mainMenu.isUsernameVisible(UserFactory.userName))
                .as("Username: " + UserFactory.userName + " is visible")
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
                .inputCardName(getRandomCardName())
                .inputCardNumber(getRandomCardNumber())
                .inputCardCVC(getRandomCVC())
                .inputCardExpirationMonth(getRandomExpirationMonth())
                .inputCardExpirationYear(getRandomExpirationYear())
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
