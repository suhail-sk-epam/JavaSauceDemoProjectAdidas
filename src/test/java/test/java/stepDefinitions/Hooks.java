package test.java.stepDefinitions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {
    private WebDriver driver;

    @Before
    public void setUp(Scenario scenario) {
        System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        scenario.log("Browser initialized and maximized.");
        System.out.println("Browser initialized and maximized for Scenario: " + scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (driver != null) {
            driver.quit();
            scenario.log("Browser closed successfully.");
            System.out.println("Browser closed successfully for Scenario: " + scenario.getName());
        }
    }


    public WebDriver getDriver() {
        return driver;
    }
}