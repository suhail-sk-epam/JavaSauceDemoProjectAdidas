package test.java.stepDefinitions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import test.java.util.ExtentReportManager;

public class Hooks {
    private static WebDriver driver;
    private ExtentTest test;

    @Before
    public void setUp(Scenario scenario) {
        System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Use getInstance() instead of getReporter()
        test = ExtentReportManager.getInstance().createTest(scenario.getName());
        test.log(Status.INFO, "Browser launched for scenario: " + scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed Screenshot");
            test.log(Status.FAIL, "Scenario failed: " + scenario.getName());
            test.fail("Screenshot attached").addScreenCaptureFromBase64String(
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64));
        } else {
            test.log(Status.PASS, "Scenario passed: " + scenario.getName());
        }
        driver.quit();
        ExtentReportManager.flushReport();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
