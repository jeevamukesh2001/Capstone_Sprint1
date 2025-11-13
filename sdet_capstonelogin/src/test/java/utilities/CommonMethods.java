package utilities;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods {
    protected static WebDriver dr;
    protected static WebDriverWait wait;
    static configreader config = new configreader();
    public void launchBrowser(String browser) {
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
            System.setProperty("webdriver.edge.driver", "src\\test\\resources\\edgedriver_v141.exe");
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-single-click-autofill"); 
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-features=AutofillProfileUpdate,AutofillAddressSavePrompt");
            options.addArguments("--disable-single-click-autofill");
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("autofill.profile_enabled", false);
            prefs.put("autofill.enabled", false);
            options.setExperimentalOption("prefs", prefs);
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
    
    public static void explicitwait(WebDriver dr, WebElement wb, int sec, String test) {
    	WebDriverWait wait = new WebDriverWait(dr,Duration.ofSeconds(sec));
    	switch(test) {
    	case "visibility" :
    		wait.until(ExpectedConditions.visibilityOf(wb));
    		break;
    	case "clickable" :
    		wait.until(ExpectedConditions.elementToBeClickable(wb));
    		break;
    	}
    }
    
    public static void fluentwait(WebDriver dr, WebElement wb, int sec, String test) {
    	FluentWait<WebDriver> wait = new FluentWait<>(dr)
        .withTimeout(Duration.ofSeconds(sec))
        .pollingEvery(Duration.ofMillis(10000))
        .ignoring(NoSuchElementException.class);
    	switch(test) {
    	case "visibility" :
    		wait.until(ExpectedConditions.visibilityOf(wb));
    		break;
    	case "clickable" :
    		wait.until(ExpectedConditions.elementToBeClickable(wb));
    		break;
    	}
    }
    
    public void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) dr;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

}