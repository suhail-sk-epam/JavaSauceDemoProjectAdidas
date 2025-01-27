package test.java.stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
test.java.pages.LoginPage;
test.java.pages.ProductPage;
import static org.junit.Assert.assertTrue;

public class Steps {
    WebDriver driver;
    LoginPage loginPage;
    ProductPage productPage;

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/v1");
        loginPage = new LoginPage(driver);
    }

    @When("the user logs in with valid credentials")
    public void the_user_logs_in_with_valid_credentials() {
        loginPage.login("standard_user", "secret_sauce");
    }

    @Then("the user should see the list of available products")
    public void the_user_should_see_the_list_of_available_products() {
        productPage = new ProductPage(driver);
        String products = productPage.getProductList();
        assertTrue("Product list is not displayed.", products.contains("Sauce Labs"));
        driver.quit();
    }
}
