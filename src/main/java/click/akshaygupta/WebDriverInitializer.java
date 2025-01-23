package click.akshaygupta;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverInitializer {
    public static ChromeOptions getDefaultChromeOptions() {
        // Create a map to store preferences
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2); // 2 means block

        // Create ChromeOptions and set the preferences
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--start-maximized");

        return options;
    }
}
