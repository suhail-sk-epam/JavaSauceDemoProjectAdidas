package test.java.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import test.java.util.ExtentReportManager;

@RunWith(Cucumber.class)
@CucumberOptions(
        publish = false, // Set to true if you want to enable publishing
        //quiet = true,     // Set to true to disable the publishing prompt
        features = "src/test/resources/features", // Path to your feature files
        glue = {"test.java.stepDefinitions"},               // Package containing step definitions
        plugin = {
                "pretty",                             // Prints the Gherkin steps in the console
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
                //  "cucumber.publish.quiet=true"

        },
        monochrome = true                           // Fails the test run if there are undefined or pending steps
		)
public class TestRunner {
    @AfterClass
    public static void tearDown() {
        ExtentReportManager.flushReport(); // Flush the report after all tests
    }
}
