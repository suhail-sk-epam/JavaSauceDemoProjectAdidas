package test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class ProductPage {

    WebDriver driver;
    Robot robot;

    public ProductPage(WebDriver driver) {  // Accept WebDriver in the constructor
        this.driver = driver;
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
    private By sortByDropdownLocator = By.className("product_sort_container");

    public void selectSortByPrice() {
        Select dropdown = new Select(driver.findElement(sortByDropdownLocator));
        dropdown.selectByVisibleText("Price (low to high)");  // Assumes text, needs actual value
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

        Thread.sleep(2000);

        // Type the path and filename
        String filePath = "testPDF"; // Specify the path and filename
        for (char c : filePath.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (Character.isUpperCase(c)) {
                robot.keyPress(KeyEvent.VK_SHIFT);
            }
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
            if (Character.isUpperCase(c)) {
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
            Thread.sleep(1000); // add delay to ensure each keystroke is registered
        }

        // Press Enter to go to Save dialog
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);

        // Press Enter to save the file
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
    public void bringWindowToFront() {
        try {
            // The title of the browser window you want to activate
            String browserWindowTitle = "Swag Labs"; // Change this to the actual title, or part of it
            Runtime.getRuntime().exec("powershell.exe (Get-Process | Where-Object { $_.MainWindowTitle -match '" + browserWindowTitle + "' }).SetForegroundWindow()");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
