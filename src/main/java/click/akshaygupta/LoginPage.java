package click.akshaygupta;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private static final String USERNAME_SELECTOR = "username";
    private static final String PASSWORD_SELECTOR = "password";
    private static final String LOGIN_BUTTON_SELECTOR = "Login";

    public static void login(WebDriver driver, WebDriverWait wait, ConfigLoader configLoader) {
        // Extract environment variables
        String usernameEnv = configLoader.getProperty("username");
        String passwordEnv = configLoader.getProperty("password");
        String targetURL = configLoader.getProperty("url");

        // Navigate to the Salesforce login page
        driver.get(targetURL);
        System.out.println("Navigated to Salesforce login page");

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(USERNAME_SELECTOR)));
        username.sendKeys(usernameEnv);
        System.out.println("Entered username");

        // Wait for the password field to be visible and enter the password
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PASSWORD_SELECTOR)));
        password.sendKeys(passwordEnv);
        System.out.println("Entered password");

        // Wait for the login button to be visible and click it
        WebElement loginButton = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(LOGIN_BUTTON_SELECTOR)));
        loginButton.click();
        System.out.println("Clicked login button");
    }
}
