package util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extent;

    // Method to get the ExtentReports instance (Singleton pattern)
    public static ExtentReports getInstance() {
        if (extent == null) {
            // Create a new Spark reporter to generate the report at the specified location
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/ExtentReport.html");

            // Configure the report title and name
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Test Execution Report");

            // Create the ExtentReports instance and attach the Spark reporter
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

    // Method to flush the report
    public static void flushReport() {
        if (extent != null) {
            extent.flush();  // Flush the report to write all data
        }
    }
}
