package utils;
 
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
 
public class ScreenshotUtil {
	//captures screen shot
 
    public static String captureScreenshot(WebDriver driver,
                                           String screenshotName) {
 
        String timestamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
 
        String destination =
                "target/screenshots/"
                        + screenshotName
                        + "_"
                        + timestamp
                        + ".png";
 
        File srcFile =
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
 
        File destFile = new File(destination);
 
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return destFile.getAbsolutePath();
    }
}