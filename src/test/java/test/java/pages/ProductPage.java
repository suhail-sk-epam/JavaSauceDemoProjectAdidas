package test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductPage {

    WebDriver driver;

    // Locators
    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");
    private By productImages = By.cssSelector(".inventory_item_img img");

    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // Navigate to product page
    public void navigateToProductPage() {
        driver.get("https://www.saucedemo.com/v1/inventory.html");
    }

    // Get product names
    public List<WebElement> getProductNames() {
        return driver.findElements(productNames);
    }

    // Get product prices
    public List<WebElement> getProductPrices() {
        return driver.findElements(productPrices);
    }

    // Get product images
    public List<WebElement> getProductImages() {
        return driver.findElements(productImages);
    }

    // Scroll through the product list
    public void scrollThroughProductList() {
        WebElement lastElement = driver.findElements(productNames).get(driver.findElements(productNames).size() - 1);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView(true);", lastElement);
    }
}
