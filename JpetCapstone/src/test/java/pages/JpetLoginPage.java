package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

public class JpetLoginPage {
    WebDriver driver;

    public JpetLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By SignInlink = By.linkText("Sign In");
    By username = By.name("username");
    By password = By.name("password");
    By LoginBtn = By.xpath("//button[text()='Login']");
    By accountIcon = By.xpath("//a[@title='My Account']");
    By signoutButton = By.linkText("Sign Out");

    // Action Methods
    public void SignInlink() {
        driver.findElement(SignInlink).click();
    }

    public void username(String uname) {
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(uname);
    }

    public void password(String pass) {
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
    }

    public void LoginBtn() {

        WebElement LoginButton = driver.findElement(LoginBtn);
        
        Actions actions = new Actions(driver);
        actions.moveToElement(LoginButton).click().perform();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // 4. NOW scroll down 250 pixels to reveal the newly appeared message
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 250);");
        
    }

    public void accountIcon() {
        driver.findElement(accountIcon).click();
    }

    public void signoutButton() {
        driver.findElement(signoutButton).click();
    }
}
