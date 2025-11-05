package stepdefinitions;

import io.cucumber.java.en.*;
import org.apache.log4j.Logger;import org.testng.Assert;
import pages.Homepage;
import pages.Loginpage;
import utilities.CommonMethods;
import utilities.configreader;

public class Feature extends CommonMethods {

    configreader config = new configreader();
    Logger log = Logger.getLogger(Feature.class);
    Homepage ho;
    Loginpage login;

    @Given("the user launches the browser")
    public void launch_browser() {
        launchBrowser("Chrome");
        ho = new Homepage(dr);
        login = new Loginpage(dr);
        log.info("Browser launched");
    }

    @And("the user navigates to the homepage")
    public void navigate_homepage() {
        ho.cookies();
        log.info("Homepage loaded");
    }
    
    @And("the user is on the login page")
    public void user_on_login_page() throws InterruptedException {
        login.profile();
        login.loginclick();
        login.credentialslink();
        log.info("Login page opened");
    }

    @When("the user enters {string} and {string}")
    public void enter_credentials(String username, String password) throws InterruptedException {
        login.credentialsdata(username, password);
        log.info("Entered credentials: " + username);
    }

    @Then("the login result should be {string}")
    public void verify_login_result(String expectedResult) {
        if (expectedResult.equalsIgnoreCase("success")) {
            Assert.assertEquals(dr.getTitle(), "PUMA.COM | Forever Faster");
            log.info("Login successful");
        } else {
            String errorMsg = login.getErrorMessage();
            Assert.assertEquals(errorMsg, "Expected error message not displayed!");
            log.info("Error message displayed: " + errorMsg);
        }
    }

    @And("clicks the login button")
    public void click_login_button() {
        login.submitlogin();
        log.info("Clicked login button");
    }
}