package automation_exercise.helpers;

import automation_exercise.models.ProductInCart;
import io.qameta.allure.Step;

import java.util.HashMap;
import java.util.Map;

public class ExpectedProductBuilder {
    private static final Map<String, ProductInCart> expectedProducts = new HashMap<>();

    static {
        expectedProducts.put("blueTop", ProductInCart.builder()
                .name("Blue Top")
                .price("Rs. 501")
                .quantity("1")
                .totalPrice("Rs. 500")
                .build());

        expectedProducts.put("menTshirt", ProductInCart.builder()
                .name("Men Tshirt")
                .price("Rs. 400")
                .quantity("1")
                .totalPrice("Rs. 400")
                .build());
    }

    @Step("Get expected product")
    public static ProductInCart getExpectedProduct(String key, int quantity) {
        ProductInCart base =  expectedProducts.get(key);
        if (base == null) {
            throw new IllegalArgumentException("No expected product for key: " + key);
        }
        int price = Integer.parseInt(base.getPrice().replace("Rs. ", ""));
        int totalPrice = price * quantity;

        return ProductInCart.builder()
                .name(base.getName())
                .price(base.getPrice())
                .quantity(String.valueOf(quantity))
                .totalPrice("Rs. " + totalPrice)
                .build();
    }
}
