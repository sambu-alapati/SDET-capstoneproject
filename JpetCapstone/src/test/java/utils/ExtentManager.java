package utils;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;
    public static ExtentReports getInstance() {
        if (extent == null) {
 
            ExtentSparkReporter spark =
                    new ExtentSparkReporter("target/ExtentReports/JPetStoreReport.html");
 
            spark.config().setDocumentTitle("JPetStore Automation Report");
            spark.config().setReportName("JPetStore Execution Report");
 
            extent = new ExtentReports();
            extent.attachReporter(spark);
 
            extent.setSystemInfo("Project", "JPetStore");
            extent.setSystemInfo("Tester", "Group D");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", "Edge");
        }
 
        return extent;
    }
}