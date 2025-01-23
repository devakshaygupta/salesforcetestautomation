package click.akshaygupta;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateOpportunity {
    private static final String HOME_URL_FRAGMENT = "/lightning/page/home";
    private static final String NEW_OPPORTUNITY_URL = "lightning/action/quick/Global.Enterprise_New_Opportunity?objectApiName=Opportunity&context=RECORD_DETAIL&recordId=";

    public static void main(String[] args) {
        ChromeOptions chromeOptions = WebDriverInitializer.getDefaultChromeOptions();
        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);

        WebDriver driver = new ChromeDriver(chromeOptions);

        try {
            // Wait for the username field to be visible and enter the username
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // Use JavaScript to access the shadow DOM and click the Accounts tab
            JavascriptExecutor js = (JavascriptExecutor) driver;

            String env = System.getProperty("env");

            ConfigLoader configLoader = new ConfigLoader(env);

            String targetRecordId = configLoader.getProperty("recordId");
            String targetURL = configLoader.getProperty("url");

            // Login into Salesforce org with username and password
            LoginPage.login(driver, wait, configLoader);

            // Wait for the page to load and then interact with the shadow DOM element
            wait.until(ExpectedConditions.urlContains(HOME_URL_FRAGMENT));

            // Navigate to Accounts page
            NavigateToTab.clickTab(driver, wait, "Account", js);

            // Navigate to Test Account record
            driver.get(targetURL.concat(targetRecordId));

            // Open new opportunity window
            driver.get(targetURL.concat(NEW_OPPORTUNITY_URL).concat(targetRecordId));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close();
            System.out.println("Browser closed");
        }
    }
}
