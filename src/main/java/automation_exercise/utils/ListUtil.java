package automation_exercise.utils;

import automation_exercise.interfaces.LocatorProvider;
import automation_exercise.pages.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ListUtil extends BasePage {

    public static String getTextNode(WebElement element) {
        return (String) ((JavascriptExecutor) getDriver())
                .executeScript(
                        "return Array.from(arguments[0].childNodes)" +
                                ".filter(n => n.nodeType === Node.TEXT_NODE || n.nodeType === Node.ELEMENT_NODE)" +
                                ".map(n => n.nodeType === Node.TEXT_NODE ? n.textContent.trim() : n.textContent.trim())" +
                                ".join('');",
                        element
                );
    }
}
