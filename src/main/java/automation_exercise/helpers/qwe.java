package automation_exercise.helpers;

import automation_exercise.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//public class qwe {
//    @Step("Get all products")
//    public List<Map<String, Object>> getAllProducts(){
//        List<Map<String, Object>> products = new ArrayList<>();
//        List<WebElement> productContainers = BasePage.getDriver().findElements(productContainerLocator);
//        for (WebElement container : productContainers){
//            Map<String, Object> productData = new HashMap<>();
//            productData.put("productImage", findOrNull(container, productImageLocator));
//            productData.put("productPrice", getTextOrNull(container, productPriceLocator));
//            productData.put("productName", getTextOrNull(container, productNameLocator));
//            productData.put("addToCartButton", findOrNull(container, addToCartButtonLocator));
//            productData.put("viewProductButton", findOrNull(container, viewProductButtonLocator));
//
//            products.add(productData);
//        }
//        return products;
//    }
//}
