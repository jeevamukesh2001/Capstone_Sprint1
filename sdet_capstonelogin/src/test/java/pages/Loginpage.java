package pages;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonMethods;

public class Loginpage extends CommonMethods {
    Logger log = Logger.getLogger(Loginpage.class);

    @FindBy(xpath = "//div[@data-test-id='main-nav-bar']//button[@data-uds-child='popover-trigger']")
    private WebElement profile;

    @FindBy(xpath = "//div[@data-uds-child='popover-body']")
    private WebElement box1;

    @FindBy(xpath = "//button[@data-test-id='login-button']")
    private WebElement loginbutton;

    @FindBy(xpath = "//button[@data-test-id='otp-login-with-email-password']")
    private WebElement emailpass;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement email;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement pass;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginapp;

    @FindBy(xpath = "//p[contains(text(),'Enter your email and password to log in')]")
    private WebElement form;

    @FindBy(xpath = "//p[@data-test-id=\"login-form-error-content\"]")
    private WebElement error;
    
    @FindBy(id="sf-connect2-companion")
    WebElement iframe;

    protected WebDriver dr;

    public Loginpage(WebDriver dr) {
        this.dr = dr;
        PageFactory.initElements(dr, this);
        PropertyConfigurator.configure("src\\test\\resources\\log4j.properties");
    }

    private void safeClick(WebElement element) {
        try {
            fluentwait(dr, element, 10, "clickable");
            element.click();
        } catch (ElementClickInterceptedException e) {
            log.warn("Click intercepted, using JavaScript click.");
            ((JavascriptExecutor) dr).executeScript("arguments[0].click();", element);
        }
    }

    private void removeOverlayIfPresent() {
        try {
            ((JavascriptExecutor) dr).executeScript("arguments[0].style.display='none';", iframe);
            log.info("Overlay iframe removed.");
        } catch (NoSuchElementException e) {
            log.info("No overlay iframe found.");
        }
    }

    public void clickProfile() {
        fluentwait(dr, profile, 10, "clickable");
        safeClick(profile);
        log.info("Profile button clicked.");
    }

    public void loginclick() {
        Actions act = new Actions(dr);
        act.moveToElement(box1).perform();
        scrollIntoView(loginbutton);
        safeClick(loginbutton);
        log.info("Login button clicked.");
    }

    public void credentialslink() {
        removeOverlayIfPresent();
        Actions act = new Actions(dr);
        act.moveToElement(emailpass).perform();
        safeClick(emailpass);
        log.info("Email/Password option clicked.");
    }

    public void credentialsdata(String emailId, String password) {
        fluentwait(dr, email, 10, "visibility");
        email.sendKeys(emailId);
        fluentwait(dr, pass, 10, "visibility");
        pass.sendKeys(password);
        log.info("Credentials entered.");
    }

    public void submitlogin() {
        scrollIntoView(loginapp);
        safeClick(loginapp);
        log.info("Login submitted.");
    }

    public String getErrorMessage() {
        scrollIntoView(form);
        fluentwait(dr, error, 5, "visibility");
        String errorMsg = error.getText().trim();
        log.info("Error message: " + errorMsg);
        return errorMsg;
    }
}