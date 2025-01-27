package test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;
    private WebElement usernameInput;
    private WebElement passwordInput;
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.usernameInput = driver.findElement(By.id("user-name"));
        this.passwordInput = driver.findElement(By.id("password"));
        this.loginButton = driver.findElement(By.id("login-button"));
    }

    public void login(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }
}
