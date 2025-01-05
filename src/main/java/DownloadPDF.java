import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class DownloadPDF {
    public static void downloadFile(String fileURL, String saveDir) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(new URL(fileURL).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(saveDir)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            // Handle exception
        }
    }

    public static void main(String[] args) throws IOException {
        WebDriver driver = new ChromeDriver();
        driver.get("URL_OF_PDF");
        downloadFile(driver.getCurrentUrl(), "path/to/downloaded.pdf");
        driver.quit();
    }
}