package test.java.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import test.java.pages.ProductPage;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ProductPageSteps {

    WebDriver driver;
    ProductPage productPage;

    @Given("the user is on the product page")
    public void the_user_is_on_the_product_page() {
        //WebDriverManager.chromedriver().setup();
    	System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to product page
        productPage = new ProductPage();
        productPage.navigateToProductPage();
    }

    @When("the user scrolls through the product list")
    public void the_user_scrolls_through_the_product_list() {
        productPage.scrollThroughProductList();
        System.out.println("@@@@Entered into PDF block-11");

        try {
            System.out.println("@@@@Entered into PDF block-22");
            Thread.sleep(3000);
            productPage.triggerPrintDialog();
            productPage.confirmPrint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("the products should be displayed with names, prices, and images")
    public void the_products_should_be_displayed_with_names_prices_and_images() {
        // Verify product names
        List<WebElement> names = productPage.getProductNames();
        assertTrue("Product names are not displayed!", names.size() > 0);

        // Verify product prices
        List<WebElement> prices = productPage.getProductPrices();
        assertTrue("Product prices are not displayed!", prices.size() > 0);

        // Verify product images
        List<WebElement> images = productPage.getProductImages();
        assertTrue("Product images are not displayed!", images.size() > 0);

        /*finally {
            driver.quit();
        }*/

        // Close the browser
        //driver.quit();
    }
}
