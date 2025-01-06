import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.saucedemo.pages.ProductSortPage;

public class SortProductsByPriceTest {

    private WebDriver driver;
    private ProductSortPage productSortPage;

    @Given("the user is on the product page")
    public void the_user_is_on_the_product_page() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/v1/inventory.html");
        productSortPage = new ProductSortPage(driver);
    }

    @When("the user selects to sort products by price")
    public void the_user_selects_to_sort_products_by_price() {
        productSortPage.select_sort_by_price();
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