package test.java.util;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Screenshot {

    public String takeScreenshot(String stepName, WebDriver driver) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path screenshotPath = Paths.get(System.getProperty("user.dir"), "target", "screenshots", stepName + ".png");
        try {
            FileUtils.copyFile(scrFile, screenshotPath.toFile());
            return screenshotPath.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
