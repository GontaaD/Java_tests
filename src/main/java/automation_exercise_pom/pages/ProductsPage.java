package automation_exercise_pom.pages;

import automation_exercise_pom.pages.Interfaces.IProductable;
import org.openqa.selenium.By;

public class ProductsPage extends BasePage implements IProductable {
    private final By allProductsTitleLocator = By.xpath("//h2[@class='title text-center']");
    private final By searchProductInputLocator = By.xpath("//input[@id='search_product']");
    private final By submitSearchButtonLocator = By.xpath("//button[@id='submit_search']");

    public ProductsPage setSearchProduct(String text){
        waiter.waitUntilVisibilityOfElementLocated(searchProductInputLocator).sendKeys(text);
        return this;
    }

    public ProductsPage clickSubmitSearchButton(){
        waiter.waitUntilElementClickable(submitSearchButtonLocator).click();
        return this;
    }

    public ProductsPage searchProduct(String text){
        return this
                .setSearchProduct(text)
                .clickSubmitSearchButton();
    }

//    public ProductsPage clickCloseModalButton(){
//        waiter.waitUntilElementClickable(closeModalButtonLocator).click();
//        return this;
//    }

//    public CartPage clickViewCartButton(){
//        waiter.waitUntilElementClickable(viewCartButtonLocator).click();
//        removeAds();
//        return new CartPage();
//    }

    public ProductsPage assertProductsPageSuccessfullyLoaded(){
        waiter.waitUntilVisibilityOfElementLocated(allProductsTitleLocator);
        return this;
    }



}
