package test.java.stepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.AfterStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.java.pages.ProductPage;
import test.java.util.ExtentReportManager;
import org.openqa.selenium.WebElement;
import test.java.util.PDFContent;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ProductPageSteps {

    private WebDriver driver;
    private ProductPage productPage;
    private Scenario scenario;
    private ExtentTest test;
    private List<String> capturedProductNames;
    private List<String> capturedProductPrices;
    private static final Logger logger = LoggerFactory.getLogger(ProductPageSteps.class);

    public ProductPageSteps(Hooks hooks) {
        this.driver = hooks.getDriver();
    }

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
        test = ExtentReportManager.getInstance().createTest(scenario.getName());
    }

    @Given("the user is on the product page")
    public void the_user_is_on_the_product_page() {
        try {
            productPage = new ProductPage(driver);
            productPage.navigateToProductPage();
            capturedProductNames = productPage.getProductNamesText();
            capturedProductPrices = productPage.getProductPricesText();
            test.pass("User successfully navigated to the product page.");
        } catch (Exception e) {
            test.fail("Failed to navigate to the product page: " + e.getMessage());
            throw e;
        }
    }

    @When("the user scrolls through the product list")
    public void the_user_scrolls_through_the_product_list() {
        try {
            productPage.scrollThroughProductList();
            test.pass("User scrolled through the product list.");
        } catch (Exception e) {
            test.fail("Error scrolling through the product list: " + e.getMessage());
            throw e;
        }
    }

    @When("a PDF has been generated from the Products page")
    public void a_pdf_has_been_generated_from_the_products_page() throws Exception {
        try {
            productPage.bringWindowToFront();
            Thread.sleep(3000);  // Consider using a more robust wait mechanism
            productPage.triggerPrintDialog();
            productPage.confirmPrint();
            Thread.sleep(3000);  // Consider using a more robust wait mechanism

            logger.info("Triggered and confirmed print dialog for PDF generation.");
            scenario.log("PDF has been generated from the products page.");
        } catch (Exception e) {
            logger.error("Error generating PDF from the products page: ", e);
            scenario.log("Failed to generate PDF from the products page.");
            throw e;
        }
    }

    @Then("the products should be displayed with names, prices, and images")
    public void the_products_should_be_displayed_with_names_prices_and_images() {
        // Verify product names
        List<WebElement> names = productPage.getProductNames();
        assertTrue("Product names are not displayed!", names.size() > 0);
        scenario.log("Verified that product names are displayed.");

        // Verify product prices
        List<WebElement> prices = productPage.getProductPrices();
        assertTrue("Product prices are not displayed!", prices.size() > 0);
        scenario.log("Verified that product prices are displayed.");

        // Verify product images
        List<WebElement> images = productPage.getProductImages();
        assertTrue("Product images are not displayed!", images.size() > 0);
        scenario.log("Verified that product images are displayed.");
    }

    @Then("the PDF should contain the expected product names and prices")
    public void the_pdf_should_contain_the_expected_product_names_and_prices() {
        String filePath = "C:\\Users\\Suhail_Shehzad\\Downloads\\testPDF.pdf"; // Path to the PDF file
        if (!capturedProductNames.isEmpty() && !capturedProductPrices.isEmpty()) {
            String firstProductName = capturedProductNames.get(0);
            String firstProductPrice = capturedProductPrices.get(0);
            String dollarPartOfPrice = firstProductPrice.split("\\.")[0];

            boolean nameValid = PDFContent.validatePDFContent(filePath, firstProductName);
            boolean priceValid = PDFContent.validatePDFContent(filePath, dollarPartOfPrice);

            // Detailed logging for each comparison
            logger.info("Checking PDF for product name: '{}'. Validation result: {}", firstProductName, nameValid ? "Passed" : "Failed");
            logger.info("Checking PDF for product price: '{}'. Validation result: {}", dollarPartOfPrice, priceValid ? "Passed" : "Failed");

            scenario.log("PDF validation for product name '" + firstProductName + "': " + (nameValid ? "Passed" : "Failed"));
            scenario.log("PDF validation for product price '" + dollarPartOfPrice + "': " + (priceValid ? "Passed" : "Failed"));

            assertTrue("The first product name or price in the PDF does not meet the expectations.", nameValid && priceValid);
        } else {
            logger.error("No products captured from the webpage.");
            scenario.log("Failed to capture any products from the webpage.");
            assertTrue("No products were captured from the webpage.", false);
        }
    }

    @When("the user clicks on the Add to Cart button for a product")
    public void theUserClicksOnTheAddToCartButtonForAProduct() {
        try {
            productPage.clickAddToCart();
            logger.info("Clicked on the Add to Cart button for a product.");
        } catch (Exception e) {
            logger.error("Failed to click on the Add to Cart button.", e);
            throw e;
        }
    }

    @Then("the product should be added to the shopping cart")
    public void theProductShouldBeAddedToTheShoppingCart() {
        try {
            String cartCount = productPage.getCartCount();
            assert cartCount.equals("1");
            logger.info("Verified the product was added to the shopping cart. Cart count: {}", cartCount);
        } catch (Exception e) {
            logger.error("Product was not added to the shopping cart.", e);
            throw e;
        }
    }

    @And("the cart icon should reflect the updated cart count")
    public void theCartIconShouldReflectTheUpdatedCartCount() {
        try {
            String cartCount = productPage.getCartCount();
            assert cartCount.equals("1");
            logger.info("Cart icon reflects the updated cart count: {}", cartCount);
        } catch (Exception e) {
            logger.error("Cart icon did not reflect the updated cart count.", e);
            throw e;
        }
    }

    @When("the user clicks on the cart icon")
    public void theUserClicksOnTheCartIcon() {
        try {
            productPage.clickAddToCart();
            productPage.navigateToCart();
            logger.info("User clicked on the cart icon and navigated to the cart page.");
        } catch (Exception e) {
            logger.error("Failed to navigate to the cart page after clicking the cart icon.", e);
            throw e;
        }
    }

    @Then("the user should be navigated to the cart page")
    public void theUserShouldBeNavigatedToTheCartPage() {
        try {
            productPage.viewCart();
            logger.info("User successfully navigated to the cart page.");
        } catch (Exception e) {
            logger.error("Failed to navigate to the cart page.", e);
            throw e;
        }
    }

    @And("each product should display its name, quantity, and price")
    public void eachProductShouldDisplayItsNameQuantityAndPrice() {
        try {
            List<String> productDetails = productPage.getProductDetails();
            logger.info("Product details displayed: {}", productDetails);
        } catch (Exception e) {
            logger.error("Failed to display product details.", e);
            throw e;
        }
    }

    @Given("the user is on the cart page")
    public void theUserIsOnTheCartPage() {
        try {
            productPage = new ProductPage(driver);
            productPage.navigateToProductPage();
            logger.info("Navigated to the product page.");
            productPage.clickAddToCart();
            productPage.navigateToCart();
            productPage.viewCart();
            logger.info("User is on the cart page.");
        } catch (Exception e) {
            logger.error("Failed to navigate to the cart page.", e);
            throw e;
        }
    }

    @When("the user clicks on the Checkout button")
    public void theUserClicksOnTheCheckoutButton() throws InterruptedException {
        try {
            productPage.clickCheckoutButton();
            logger.info("User clicked on the Checkout button.");
        } catch (Exception e) {
            logger.error("Failed to click on the Checkout button.", e);
            throw e;
        }
    }

    @Then("the user should be navigated to the checkout page")
    public void theUserShouldBeNavigatedToTheCheckoutPage() {
        try {
            productPage.viewCheckout();
            logger.info("User navigated to the checkout page.");
        } catch (Exception e) {
            logger.error("Failed to navigate to the checkout page.", e);
            throw e;
        }
    }

    @And("user asked to enters their first name, last name, and zip code")
    public void userAskedToEntersTheirFirstNameLastNameAndZipPostalCode() {
        try {
            productPage.enterUserDetails("John", "Doe", "12345");
            logger.info("User entered first name, last name, and zip code.");
        } catch (Exception e) {
            logger.error("Failed to enter user details.", e);
            throw e;
        }
    }

    @Then("a successful order submission should show a confirmation page")
    public void aSuccessfulOrderSubmissionShouldShowAConfirmationPage() throws InterruptedException {
        try {
            Thread.sleep(2000);
            productPage.clickCheckoutButton();
            Thread.sleep(2000);
            productPage.viewOrderConfirmation();
            logger.info("Order submission successful. Confirmation page displayed.");
        } catch (Exception e) {
            logger.error("Failed to display order confirmation page.", e);
            throw e;
        }
    }

    @When("the user selects to sort products by price")
    public void the_user_selects_to_sort_products_by_price() {
        try {
            productPage.selectSortByPrice();
            logger.info("User selected to sort products by price.");
        } catch (Exception e) {
            logger.error("Failed to sort products by price.", e);
            throw e;
        }
    }

    @Then("the products should be displayed in ascending order of price")
    public void the_products_should_be_displayed_in_ascending_order_of_price() {
        try {
            Assert.assertTrue("Products are not sorted in ascending order of price.", productPage.isProductsSortedByPrice());
            logger.info("Verified that products are sorted in ascending order of price.");
        } catch (Exception e) {
            logger.error("Failed to verify sorting of products by price.", e);
            throw e;
        }
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "screenshot");
            }
        } catch (Exception e) {
            logger.error("Error capturing screenshot.", e);
        }
    }

    @After
    public void after(Scenario scenario) {
        if (test != null) {
            ExtentReportManager.getInstance().flush();
        }
    }
}
