package click.akshaygupta;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigateToTab {

    private static final String APP_NAV_SELECTOR = "one-appnav";
    private static final String APP_NAV_BAR_SELECTOR = "one-app-nav-bar";
    private static final String NAV_BAR_ITEM_SELECTOR = "one-app-nav-bar-item-root";
    private static final String GET_SHADOW_ROOT_JS_CODE = "return arguments[0].shadowRoot";

    public static void clickTab(WebDriver driver, WebDriverWait wait, String tabTitle, JavascriptExecutor js) {
        WebElement appNavShadowHost = driver.findElement(By.cssSelector(APP_NAV_SELECTOR));
        SearchContext appNavShadowRoot = getShadowRoot(js, appNavShadowHost);

        WebElement appNavBarShadowHost = appNavShadowRoot.findElement(By.cssSelector(APP_NAV_BAR_SELECTOR));
        SearchContext appNavbarShadowRoot = getShadowRoot(js, appNavBarShadowHost);

        List<WebElement> navBarItems = appNavbarShadowRoot.findElements(By.cssSelector(NAV_BAR_ITEM_SELECTOR));

        for (WebElement navBarItem : navBarItems) {
            SearchContext navBarItemShadowRoot = getShadowRoot(js, navBarItem);
            List<WebElement> links = navBarItemShadowRoot.findElements(By.cssSelector("a"));

            for (WebElement link : links) {
                if (link.getDomAttribute("title").contains(tabTitle)) {
                    js.executeScript("arguments[0].click()", link);
                    wait.until(ExpectedConditions.titleContains(tabTitle));
                    System.out.println("Navigated to " + tabTitle + " Page");
                    break;
                }
            }
        }
    }

    public static SearchContext getShadowRoot(JavascriptExecutor js, WebElement element) {
        return (SearchContext) js.executeScript(GET_SHADOW_ROOT_JS_CODE, element);
    }
}
