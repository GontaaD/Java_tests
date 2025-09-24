package automation_exercise.utils;

import automation_exercise.pages.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ListUtil {
    public static List<String> toTextList(List<WebElement> elements) {
        return elements.stream()
                .map(e -> e == null ? null : getTextNode(e))
                .collect(Collectors.toList());
    }

    public static String getTextNode(WebElement element) {
        return (String) ((JavascriptExecutor) BasePage.getDriver())
                .executeScript(
                        "return Array.from(arguments[0].childNodes)" +
                                ".filter(n => n.nodeType === Node.TEXT_NODE)" +
                                ".map(n => n.textContent.trim()).join('');",
                        element
                );
    }
}
