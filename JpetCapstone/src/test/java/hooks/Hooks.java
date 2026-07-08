package hooks;

import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ExtentManager;
import utils.ScreenshotUtil;
import utils.allureScreenshotUtil;

public class Hooks {
	
	public static WebDriver driver;
	
	public static ExtentReports extent = ExtentManager.getInstance();
    public static ExtentTest test;
    
	  
    @Before
    public void setup(Scenario scenario) {
 
        System.out.println("Launching browser...");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        test = extent.createTest(scenario.getName());
    }
    
    @After
    public void tearDown(Scenario scenario) throws InterruptedException
    {
    	//capture the screen shot upon failure
    	
    	
    	
    	if (scenario.isFailed()) {
            try {
                // FIX: Give the browser 1.5 seconds to paint the screen layout before taking the picture
                Thread.sleep(1500);
                
                // Cast the driver to TakesScreenshot
                TakesScreenshot ts = (TakesScreenshot) driver;
                
                // Capture the screenshot as a byte array
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                
                // Embed/Attach the image to the Cucumber HTML report
                scenario.attach(screenshot, "image/png", scenario.getName());
                System.out.println("Screenshot embedded in cucumber report successfully.");
                
                allureScreenshotUtil.captureScreenshot(driver);
                
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot: " + e.getMessage());
            }
        }
    	
    	
    	
    	  try {
    		  
              if (scenario.isFailed()) {
   
                  String screenshotPath =
                          ScreenshotUtil.captureScreenshot(
                                  driver,
                                  scenario.getName());
   
                  test.fail("Scenario Failed " +scenario.getName());
   
                  test.addScreenCaptureFromPath(screenshotPath);
   
                  System.out.println("Screenshot captured for extent report successfully.");
   
              } else {
   
                  test.pass("Scenario Passed "+scenario.getName() );
   
                  System.out.println("Scenario Passed.");
              }
   
          } catch (Exception e) {
   
              System.out.println("Exception while capturing screenshot: "
                      + e.getMessage());
          }
   
        
   
          
      
    	
    	
    	System.out.println("Closing the browser..");
    	if (driver != null) {
            driver.quit();
        }
 
        extent.flush();
    	Thread.sleep(4000);
    	driver.quit();
    }
    

}
