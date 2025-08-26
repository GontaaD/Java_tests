package automation_exercise_tests.products;

import automation_exercise_pom.helpers.ExpectedProductBuilder;
import automation_exercise_pom.helpers.UserFactory;
import automation_exercise_pom.models.CheckoutData;
import automation_exercise_pom.models.Product;
import automation_exercise_pom.models.ProductInCart;
import automation_exercise_pom.models.UserRegistrationData;
import automation_exercise_tests.BaseTest;
import org.testng.annotations.Test;

import java.util.List;

public class PlaceOrderTest extends BaseTest {

    @Test
    public void placeOrderTest(){
        UserRegistrationData user = UserFactory.getUserForRegistration();

        mainPage
                .assertMainPageSuccessfullyLoaded()
                .openLoginPage()
                .signUpUser(UserFactory.userName);

        createUserAccount
                .userRegisterWithDetails(user)
                .clickContinueButton()
                .assertUserNameIsVisible(UserFactory.userName)
                .openProductsPage();

        List<Product> allProducts = productsPage
                .getAllProducts();

        allProducts
                .getFirst()
                .clickAddToCartButton()
                .clickViewCartButton()
                .clickCheckoutButton();

        CheckoutData actualCheckoutData = checkoutPage.getDeliveryAddress();
        CheckoutData expectedCheckoutData = checkoutPage.getExpectedCheckoutData(user);

        checkoutPage.assertDeliveryAddressToBeEqual(actualCheckoutData, expectedCheckoutData);

        List<ProductInCart> allProductsInCart = checkoutPage
                .assertProductInCartToBe(1)
                .getAllProductsInCart();

        ProductInCart actualProductInCart = cartPage.filterProducts(allProductsInCart, "Blue Top");
        ProductInCart expectedProductInCart = ExpectedProductBuilder.getExpectedProduct("blueTop", 1);

        cartPage.assertProductsToBeEqual(actualProductInCart, expectedProductInCart);

        checkoutPage
                .clickPaymentButton()
                .assertPaymentPageLoaded()
                .inputCardData()
                .clickSubmitOrderButton()
                .assertSuccessfullyOrderMessageIsVisible()
                .clickContinueButton();

        deletedAccountPage
                .deleteAccount();
    }
}
