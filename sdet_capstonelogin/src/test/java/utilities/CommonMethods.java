package utilities;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods {
    protected static WebDriver dr;
    protected static WebDriverWait wait;
    static configreader config = new configreader();
    public static void launchBrowser(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_v141.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-single-click-autofill"); 
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-features=AutofillProfileUpdate,AutofillAddressSavePrompt");
            options.addArguments("--disable-single-click-autofill");
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("autofill.profile_enabled", false);
            prefs.put("autofill.enabled", false);
            options.setExperimentalOption("prefs", prefs);
            dr = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "edgedriver_v141.exe");
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("user-data-dir=C:\\CustomChromeProfile"); // Path to your profile
            options.addArguments("--profile-directory=Default");
            dr = new EdgeDriver(options);
        }
        dr.get(config.getProperty("url"));
        dr.manage().window().maximize();
        wait = new WebDriverWait(dr, Duration.ofSeconds(10));
    }

    public static void closeBrowser() {
        if (dr != null) {
            dr.quit();
        }
    }
}