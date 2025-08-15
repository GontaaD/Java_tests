package automation_exercise_pom.helpers;

import automation_exercise_pom.models.ProductInCart;

import java.util.HashMap;
import java.util.Map;

public class ExpectedProductBuilder {
    private static final Map<String, ProductInCart> expectedProducts = new HashMap<>();

    static {
        expectedProducts.put("blueTop", ProductInCart.builder()
                .name("Blue Top")
                .price("Rs. 500")
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







//    public String totalPriceCalculator(String expectedPrice, String  expectedQuantity){
//        String formatPrice = expectedPrice.replace("Rs.", "").trim();
//        Integer intPrice = Integer.parseInt(formatPrice);
//        Integer intQuantity = Integer.parseInt(expectedQuantity);
//        int intTotalPrice = intPrice * intQuantity;
//        String stringTotalPrice = Integer.toString(intTotalPrice);
//        return "Rs. " + stringTotalPrice;
//    }
}
