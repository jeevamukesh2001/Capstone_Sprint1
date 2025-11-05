package pages;

import java.time.Duration;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.CommonMethods;

public class Loginpage extends CommonMethods {
    protected FluentWait<WebDriver> wait;
    Logger log = Logger.getLogger(Loginpage.class);
    
    @FindBy(xpath = "//div[@data-test-id='main-nav-bar']//button[@data-uds-child=\"popover-trigger\"]")
    private WebElement profile;
    
    @FindBy(xpath = "//div[@data-uds-child=\"popover-body\"]")
    private WebElement box1;

    @FindBy(xpath = "//button[@data-test-id=\"login-button\"]")
    private WebElement loginbutton;
    
    @FindBy(xpath = "//div[@data-uds-child=\"popover-body\"]")
    private WebElement box2;
    
    @FindBy(xpath = "//button[@data-test-id=\"otp-login-with-email-password\"]")
    private WebElement emailpass;
    
    @FindBy(xpath = "//div[@data-uds-child=\"popover-body\"]")
    private WebElement box3;
    
    @FindBy(xpath = "//input[@id='email']")
    private WebElement email;
    
    @FindBy(xpath = "//input[@id='password']")
    private WebElement pass;
    
    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement loginapp;
    
    @FindBy(xpath = "//p[contains(text(),\"Enter your email and password to log in\")]")
    private WebElement form;
    
    @FindBy(xpath = "//p[@data-test-id=\"login-form-error-content\"]")
    private WebElement error;
   
    public Loginpage(WebDriver dr) {
        this.dr = dr;
        this.wait = new FluentWait<>(dr)
                .withTimeout(Duration.ofSeconds(30))       // Total wait time
                .pollingEvery(Duration.ofMillis(100))      // Polling interval
                .ignoring(NoSuchElementException.class);

        PageFactory.initElements(dr, this);
        PropertyConfigurator.configure("src/test/resources/log4j.properties");
    }
    
    public void profile() throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(profile));
    	profile.click();
    	Thread.sleep(5000);
    }
    
    public void loginclick() throws InterruptedException {
    	wait.until(ExpectedConditions.visibilityOf(box1));
    	Actions act=new Actions(dr);
    	act.moveToElement(box1).perform();
    	JavascriptExecutor js = (JavascriptExecutor) dr;
    	js.executeScript("arguments[0].scrollIntoView(true)", loginbutton);
        wait.until(ExpectedConditions.elementToBeClickable(loginbutton));
        loginbutton.click();
    }
    
    public void credentialslink() throws InterruptedException {
    	Actions act=new Actions(dr);
    	act.moveToElement(box2).perform();
    	Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(emailpass));
        emailpass.click();
    }
    
    public void credentialsdata(String emailid, String password) throws InterruptedException {
    	Actions act=new Actions(dr);
    	act.moveToElement(box3).perform();
    	Thread.sleep(2000);
    	email.sendKeys(emailid);
    	pass.sendKeys(password);
    }
    
    public void submitlogin() {
    	JavascriptExecutor js = (JavascriptExecutor) dr;
    	js.executeScript("arguments[0].scrollIntoView(true)", loginapp);
        wait.until(ExpectedConditions.elementToBeClickable(loginapp));
        loginapp.click();
    }

	public String getErrorMessage() {
		JavascriptExecutor js = (JavascriptExecutor) dr;
    	js.executeScript("arguments[0].scrollIntoView(true)", form);
		return error.getText();
	}

}