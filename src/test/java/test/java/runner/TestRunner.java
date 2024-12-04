package test.java.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Path to your feature files
        glue = {"test.java.stepDefinitions"},               // Package containing step definitions
        plugin = {
                "pretty",                             // Prints the Gherkin steps in the console
                "html:target/cucumber-reports.html",  // Generates an HTML report
                "json:target/cucumber-reports.json",   // Generates a JSON report
              //  "cucumber.publish.quiet=true"

        },
        monochrome = true                           // Fails the test run if there are undefined or pending steps
		)
public class TestRunner {

}
