package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class ProductListPage {
    private WebDriver driver;

    public ProductListPage(WebDriver driver) {
        this.driver = driver;
    }

    private By productListContainer = By.id("product-list");
    private By productItems = By.className("product-item");
    private By sortingDropdown = By.id("sort-options");

    public List<Product> getProductDetails() {
        List<Product> details = new ArrayList<>();
        List<WebElement> items = driver.findElements(productItems);
        for (WebElement item : items) {
            String image = item.findElement(By.tagName("img")).getAttribute("src");
            String name = item.findElement(By.className("product-name")).getText();
            String description = item.findElement(By.className("product-description")).getText();
            String price = item.findElement(By.className("product-price")).getText();
            details.add(new Product(name, description, price, image));
        }
        return details;
    }

    public void addProductToCart(int productIndex) {
        List<WebElement> items = driver.findElements(productItems);
        WebElement addToCartButton = items.get(productIndex).findElement(By.className("add-to-cart"));
        addToCartButton.click();
    }

    public static class Product {
        public String name;
        public String description;
        public String price;
        public String image;

        public Product(String name, String description, String price, String image) {
            this.name = name;
            this.description = description;
            this.price = price;
            this.image = image;
        }
    }
}
