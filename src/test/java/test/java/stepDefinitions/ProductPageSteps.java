package test.java.stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.AfterStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.java.pages.ProductPage;
import test.java.util.PDFContent;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.java.util.Screenshot;

import static org.junit.Assert.assertTrue;

public class ProductPageSteps {

    WebDriver driver;
    ProductPage productPage;
    Screenshot screenshot = new Screenshot();
    private static final Logger logger = LoggerFactory.getLogger(ProductPageSteps.class);
    private List<String> capturedProductNames;
    private List<String> capturedProductPrices;

    public ProductPageSteps(Hooks hooks) {
        this.driver = hooks.getDriver();
    }
    private Scenario scenario;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }
    @Given("the user is on the product page")
    public void the_user_is_on_the_product_page() {
        try {
            // Navigate to product page
            productPage = new ProductPage(driver);
            productPage.navigateToProductPage();
            logger.info("Navigated to the product page.");

            capturedProductNames = productPage.getProductNames().stream().map(WebElement::getText).collect(Collectors.toList());
            capturedProductPrices = productPage.getProductPrices().stream().map(WebElement::getText).collect(Collectors.toList());
            logger.info("Captured product names and prices from the page.");

            scenario.log("User is on the product page with products listed.");
        } catch (Exception e) {
            logger.error("Error navigating to the product page: ", e);
            scenario.log("Failed to navigate to the product page.");
            throw e;
        }
    }

    @When("the user scrolls through the product list")
    public void the_user_scrolls_through_the_product_list() {
        try {
            productPage.scrollThroughProductList();
            logger.info("Scrolled through the product list.");
            scenario.log("User scrolled through the product list.");
        } catch (Exception e) {
            logger.error("Error scrolling through the product list: ", e);
            scenario.log("Failed to scroll through the product list.");
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

    @AfterStep
    public void afterEachStep() {
        if (driver != null) {
            String screenshotPath = screenshot.takeScreenshot(scenario.getName() + "-" + System.currentTimeMillis(), driver);
            if (screenshotPath != null) {
                scenario.attach(screenshotPath, "image/png", "Screenshot");
            }
        } else {
            logger.error("Driver is null, cannot take screenshot");
        }
    }
}

