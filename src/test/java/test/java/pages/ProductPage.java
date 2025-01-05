package test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class ProductPage {

    WebDriver driver;
    Robot robot;

    {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    // Locators
    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");
    private By productImages = By.cssSelector(".inventory_item_img img");

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

    public void triggerPrintDialog() throws Exception {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_P);
        robot.keyRelease(KeyEvent.VK_P);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(2000);  // Wait for the print dialog to open
    }

    public void confirmPrint() throws Exception {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}
