import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class ValidatePDFContent {
    public static void main(String[] args) {
        try (PDDocument document = PDDocument.load(new File("path/to/downloaded.pdf"))) {
            if (!document.isEncrypted()) {
                PDFTextStripper stripper = new PDFTextStripper();
                String text = stripper.getText(document);
                System.out.println("Text in the PDF: " + text);

                // Perform validation
                if (text.contains("Expected Product Name") && text.contains("Expected Price")) {
                    System.out.println("Validation Passed");
                } else {
                    System.out.println("Validation Failed");
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}