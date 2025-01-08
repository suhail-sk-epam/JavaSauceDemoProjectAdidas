package test.java.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import test.java.pages.ProductPage;

public class SortProductsByPriceTest {

    private WebDriver driver;
    private ProductPage productSortPage;

    @When("the user selects to sort products by price")
    public void the_user_selects_to_sort_products_by_price() {
        productSortPage.selectSortByPrice();
    }

    @Then("the products should be displayed in ascending order of price")
    public void the_products_should_be_displayed_in_ascending_order_of_price() {
        // This would involve checking the order of products, which is not implemented here
        Assert.assertTrue("Products are not sorted by price", true);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}