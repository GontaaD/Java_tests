package automation_exercise.pages;

import automation_exercise.components.CartModal;
import automation_exercise.interfaces.LocatorProvider;
import automation_exercise.utils.ListUtil;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static automation_exercise.utils.AdsHelper.removeAds;

public class ProductsPage extends BasePage {
    private final By searchProductInputLocator = By.xpath("//input[@id='search_product']");
    private final By submitSearchButtonLocator = By.xpath("//button[@id='submit_search']");

    @Getter
    @AllArgsConstructor
    public enum ProductFields implements LocatorProvider {
        IMAGE(By.xpath("//div[@class='product-image-wrapper']//img")),
        NAME(By.xpath("//div[@class='product-image-wrapper']//div[@class='productinfo text-center']//p")),
        ADD_BUTTON(By.xpath("//div[@class='product-image-wrapper']//a[contains(@class, 'add-to-cart')][1]")),
        VIEW_BUTTON(By.xpath("//div[@class='product-image-wrapper']//a[contains(@href, '/product_details/')]"));

        private final By locator;
    }

    public List<WebElement> getAllElementsFromProducts(ProductFields field) {
        return getDriver().findElements(field.getLocator()).stream()
                .filter(e -> e.isDisplayed() && e.isEnabled())
                .collect(Collectors.toList());
    }

    public List<String> getAllTextFromProducts(ProductFields field) {
        return getDriver().findElements(field.getLocator()).stream()
                .map(ListUtil::getTextNode)
                .collect(Collectors.toList());
    }

    @Step("Set search product")
    public ProductsPage setSearchProduct(String text){
        logger.info("Set search product name: [{}]", text);
        waitUntilVisibilityOfElementLocated(searchProductInputLocator).sendKeys(text);
        return this;
    }

    @Step("Click submit search button")
    public ProductsPage clickSubmitSearchButton(){
        logger.info("Click [submit search] button");
        waitUntilElementClickable(submitSearchButtonLocator).click();
        return this;
    }

    @Step("Click view product button")
    public ProductDetailsPage clickViewProductButton(WebElement product){
        logger.info("Click [view product] button");
        product.click();
        removeAds();
        return new ProductDetailsPage();
    }

    @Step("Click add to cart button")
    public CartModal clickAddToCartButton(WebElement product){
        logger.info("Click [add to cart] button");
        removeAds();
        product.click();
        return new CartModal();
    }
}
