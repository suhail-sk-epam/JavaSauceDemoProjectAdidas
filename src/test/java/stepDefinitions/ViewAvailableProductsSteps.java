package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ProductListPage;

import java.util.List;

public class ViewAvailableProductsSteps {
    private WebDriver driver;
    private ProductListPage productListPage;

    @Given("the user is on the product page")
    public void the_user_is_on_the_product_page() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/v1");
        productListPage = new ProductListPage(driver);
    }

    @When("the user scrolls through the product list")
    public void the_user_scrolls_through_the_product_list() {
        // Assuming scrolling is handled by WebDriver automatically when interacting with elements
    }

    @Then("the user is presented with a list of products including images, names, descriptions, prices, and 'Add to Cart' buttons")
    public void the_user_is_presented_with_a_list_of_products_including_images_names_descriptions_prices_and_add_to_cart_buttons() {
        List<ProductListPage.Product> products = productListPage.getProductDetails();
        for (ProductListPage.Product product : products) {
            assert product.image != null;
            assert product.name != null;
            assert product.description != null;
            assert product.price != null;
        }
    }

    @Then("the products should be sorted according to the default sorting option \(e.g., price low to high)")
    public void the_products_should_be_sorted_according_to_the_default_sorting_option_e_g_price_low_to_high() {
        // Implement sorting validation based on the default sorting option
    }

    @Then("the products should be displayed with names, prices, and images")
    public void the_products_should_be_displayed_with_names_prices_and_images() {
        List<ProductListPage.Product> products = productListPage.getProductDetails();
        for (ProductListPage.Product product : products) {
            assert product.image != null;
            assert product.name != null;
            assert product.price != null;
        }
    }
}
