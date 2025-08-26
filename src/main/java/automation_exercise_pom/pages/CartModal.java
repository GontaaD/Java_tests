package automation_exercise_pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CartModal extends BasePage{
    private final By closeModalButtonLocator = By.xpath("//button[contains(@class, 'close-modal')]");
    private final By viewCartButtonLocator = By.xpath("//div[@id='cartModal']//a[@href='/view_cart']");

    @Step("Click close modal button")
    public void clickCloseModalButton(){
        logger.info("Click [close modal] button");
        waiter.waitUntilElementClickable(closeModalButtonLocator).click();
    }

    @Step("Click view cart button")
    public CartPage clickViewCartButton(){
        logger.info("Click [view cart] button");
        waiter.waitUntilElementClickable(viewCartButtonLocator).click();
        return new CartPage();
    }
}
