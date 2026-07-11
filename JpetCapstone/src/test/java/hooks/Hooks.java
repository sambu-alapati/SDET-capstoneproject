package hooks;

import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ExtentManager;
import utils.ScreenshotUtil;
import utils.allureScreenshotUtil;

public class Hooks {
    
    // 1. ThreadLocal declarations for parallel execution safety
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> tlTest = new ThreadLocal<>();
    
    public static ExtentReports extent = ExtentManager.getInstance();
    
    // 2. Public getter methods so step definitions can fetch the correct instance
    public static WebDriver getDriver() {
        return tlDriver.get();
    }
    
    public static ExtentTest getTest() {
        return tlTest.get();
    }
      
    @Before
    public void setup(Scenario scenario) {
        System.out.println("Launching browser on thread: " + Thread.currentThread().getId());
        
        
       
        WebDriver driver = new EdgeDriver(); 
        
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Store instances securely inside the current thread container
        tlDriver.set(driver);
        tlTest.set(extent.createTest(scenario.getName()));
    }
    
    @After
    public void tearDown(Scenario scenario) throws InterruptedException {
        WebDriver driver = getDriver();
        ExtentTest test = getTest();
        
        if (driver != null) {
            if (scenario.isFailed()) {
                try {
                    Thread.sleep(1500);
                    
                    TakesScreenshot ts = (TakesScreenshot) driver;
                    byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                    
                    scenario.attach(screenshot, "image/png", scenario.getName());
                    System.out.println("Screenshot embedded in cucumber report successfully.");
                    
                    allureScreenshotUtil.captureScreenshot(driver);
                    
                } catch (Exception e) {
                    System.out.println("Exception while taking screenshot: " + e.getMessage());
                }
            }
            
            try {
                if (scenario.isFailed()) {
                    String screenshotPath = ScreenshotUtil.captureScreenshot(driver, scenario.getName());
                    test.fail("Scenario Failed " + scenario.getName());
                    test.addScreenCaptureFromPath(screenshotPath);
                    System.out.println("Screenshot captured for extent report successfully.");
                } else {
                    test.pass("Scenario Passed " + scenario.getName());
                    System.out.println("Scenario Passed.");
                }
            } catch (Exception e) {
                System.out.println("Exception while capturing screenshot: " + e.getMessage());
            }
            
            System.out.println("Closing the browser on thread: " + Thread.currentThread().getId());
            driver.quit();
        }
        
        // 3. Clear ThreadLocal copies to prevent memory leaks [2]
        tlDriver.remove();
        tlTest.remove();
        
        // Flush synchronized data safely
        synchronized (extent) {
            extent.flush();
        }
    }
}
