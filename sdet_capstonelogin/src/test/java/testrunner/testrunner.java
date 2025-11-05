package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features="src//test//resources//features",
glue="stepdefinitions",
plugin={"pretty","html:target/cucumber-reports/report.html"}
)


public class testrunner extends AbstractTestNGCucumberTests {

}
