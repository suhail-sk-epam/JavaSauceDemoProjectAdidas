package util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;


public class PDFContent {

   /*
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

    public static boolean waitForFile(String filePath, int timeoutInSeconds) {
        File file = new File(filePath);
        int timeElapsed = 0;
        while (timeElapsed <= timeoutInSeconds) {
            if (file.exists() && file.length() > 0) {
                try (PDDocument document = PDDocument.load(file)) {
                    return true; // File is ready and can be opened
                } catch (IOException e) {
                    System.out.println("Waiting for file to be ready: " + e.getMessage());
                }
            }
            try {
                Thread.sleep(1000);
                timeElapsed += 1;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return false;
    }
    public static boolean validatePDFContent(String filePath, String expectedText1) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File does not exist: " + filePath);
            return false;
        }
        if (file.length() == 0) {
            System.out.println("File is empty: " + filePath);
            return false;
        }

        try (PDDocument document = PDDocument.load(file)) {
            if (document.isEncrypted()) {
                System.out.println("PDF is encrypted, unable to extract text.");
                return false;
            }
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            System.out.println("Text in the PDF: '" + text + "'");

            if (text.contains(expectedText1)) {
                System.out.println("Validation Passed for text: " + expectedText1);
                return true;
            } else {
                System.out.println("Validation Failed for text: " + expectedText1);
                return false;
            }
        } catch (IOException e) {
            System.err.println("Error reading or parsing the PDF: " + e.getMessage());
            return false;
        }
    }

    public static String extractTextFromPDF(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            System.out.println("File does not exist or is empty.");
            return "";
        }

        try (PDDocument document = PDDocument.load(file)) {
            if (document.isEncrypted()) {
                System.out.println("PDF is encrypted.");
                return "";
            }
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        } catch (IOException e) {
            System.err.println("Error reading PDF file: " + e.getMessage());
            return "";
        }
    }
*/
    public static String extractTextFromPDFWithOCR(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            System.out.println("File does not exist or is empty.");
            return "";
        }

        try (PDDocument document = PDDocument.load(file)) {
            PDFRenderer renderer = new PDFRenderer(document);
            StringBuilder out = new StringBuilder();
            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath("C:\\Users\\Suhail_Shehzad\\Desktop\\FrequentlyUsed\\TCC\\Baxter\\project\\tessdata");  // Update this path
            tesseract.setLanguage("eng");

            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                BufferedImage image = renderer.renderImageWithDPI(page, 300);
                try {
                    String result = tesseract.doOCR(image);
                    out.append(result);
                } catch (TesseractException e) {
                    System.err.println("Error during OCR: " + e.getMessage());
                }
            }
            return out.toString();
        } catch (IOException e) {
            System.err.println("Error reading PDF file: " + e.getMessage());
            return "";
        }
    }


    public static boolean validatePDFContent(String filePath, String expectedText) {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            System.out.println("File does not exist or is empty.");
            return false;
        }

        try (PDDocument document = PDDocument.load(file)) {
            if (document.isEncrypted()) {
                System.out.println("PDF is encrypted, unable to extract text.");
                return false;
            }
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            System.out.println("Text in the PDF: '" + text + "'");

            if (text.contains(expectedText)) {
                System.out.println("Validation Passed for text: " + expectedText);
                return true;
            } else {
                // If the text was not found, try OCR
                return tryOCR(document, expectedText);
            }
        } catch (IOException e) {
            System.err.println("Error reading or parsing the PDF: " + e.getMessage());
            return false;
        }
    }

    private static boolean tryOCR(PDDocument document, String expectedText) {
        PDFRenderer renderer = new PDFRenderer(document);
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Users\\Suhail_Shehzad\\Desktop\\FrequentlyUsed\\TCC\\Baxter\\project\\tessdata");
        tesseract.setLanguage("eng");

        try {
            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                BufferedImage image = renderer.renderImageWithDPI(page, 300);
                String result = tesseract.doOCR(image);
                if (result.contains(expectedText)) {
                    System.out.println("Validation Passed for text (OCR): " + expectedText);
                    return true;
                }
            }
        } catch (TesseractException | IOException e) {
            System.err.println("Error during OCR: " + e.getMessage());
        }

        System.out.println("Validation Failed for text (OCR): " + expectedText);
        return false;
    }
}