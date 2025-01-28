package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;
import pages.LoginPage;

import static org.junit.Assert.*;

public class UserAuthenticationSteps {
    WebDriver driver;
    LoginPage loginPage;

    @Given("a registered user with valid credentials")
    public void a_registered_user_with_valid_credentials() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/v1");
        loginPage = new LoginPage(driver);
    }

    @When("the user enters their username and password")
    public void the_user_enters_their_username_and_password() {
        loginPage.enterUsername("valid_username");
        loginPage.enterPassword("valid_password");
    }

    @When("the user clicks the {string} button")
    public void the_user_clicks_the_button(String button) {
        if (button.equals("Login")) {
            loginPage.clickLoginButton();
        }
    }

    @Then("the user should be logged in and redirected to their dashboard")
    public void the_user_should_be_logged_in_and_redirected_to_their_dashboard() {
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("inventory.html"));
        driver.quit();
    }

    @Given("a registered user with invalid credentials")
    public void a_registered_user_with_invalid_credentials() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/v1");
        loginPage = new LoginPage(driver);
    }

    @When("the user attempts to log in")
    public void the_user_attempts_to_log_in() {
        loginPage.enterUsername("invalid_username");
        loginPage.enterPassword("invalid_password");
        loginPage.clickLoginButton();
    }

    @Then("the system should display an error message and remain on the login page")
    public void the_system_should_display_an_error_message_and_remain_on_the_login_page() {
        String errorMessage = loginPage.getErrorMessage();
        assertTrue(errorMessage.contains("Username and password do not match"));
        driver.quit();
    }
}
