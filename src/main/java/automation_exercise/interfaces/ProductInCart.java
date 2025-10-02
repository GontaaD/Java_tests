package automation_exercise.interfaces;

import automation_exercise.utils.TextNodeGetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static automation_exercise.pages.BasePage.getDriver;

public interface ProductInCart {

    @Getter
    @AllArgsConstructor
    enum CartFields implements LocatorProvider {
        NAME(By.xpath("//tr[contains(@id, 'product-')]//a[contains(@href, '/product_details/')]")),
        PRICE(By.xpath("//tr[contains(@id, 'product-')]//td[@class='cart_price']//p")),
        QUANTITY(By.xpath("//tr[contains(@id, 'product-')]//td[@class='cart_quantity']//button")),
        TOTAL_PRICE(By.xpath("//tr[contains(@id, 'product-')]//p[@class='cart_total_price']"));

        private final By locator;
    }

    default List<String> getAllTextFromCart(CartFields field) {
        return getDriver().findElements(field.getLocator()).stream()
                .map(TextNodeGetter::getTextNode)
                .collect(Collectors.toList());
    }

    default List<String> getProductTotalPrices(List<String> prices, List<String> quantitys) {
        if (quantitys.size() != prices.size()) {
            throw new IllegalArgumentException("Lists must have the same size");
        }

        List<String> totalPrices = new ArrayList<>();
        for (int i = 0; i < prices.size(); i++) {
            int price;
            int quantity;

            try {
                price = Integer.parseInt(prices.get(i).replace("Rs. ", ""));
            } catch (NumberFormatException e) {
                price = 0;
            }

            try {
                quantity = Integer.parseInt(quantitys.get(i).trim());
            } catch (NumberFormatException e) {
                quantity = 0;
            }

            totalPrices.add("Rs. " + price * quantity);
        }
        return totalPrices;
    }
}