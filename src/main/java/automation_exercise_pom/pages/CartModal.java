package automation_exercise_pom.pages;

import org.openqa.selenium.By;

public class CartModal extends BasePage{
    private final By closeModalButtonLocator = By.xpath("//button[contains(@class, 'close-modal')]");
    private final By viewCartButtonLocator = By.xpath("//div[@id='cartModal']//a[@href='/view_cart']");

    public void clickCloseModalButton(){
        waiter.waitUntilElementClickable(closeModalButtonLocator).click();
    }

    public CartPage clickViewCartButton(){
        waiter.waitUntilElementClickable(viewCartButtonLocator).click();
        return new CartPage();
    }
}
