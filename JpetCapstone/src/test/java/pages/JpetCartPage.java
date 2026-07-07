package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JpetCartPage {

	
	private WebDriver driver;
	 
    public JpetCartPage(WebDriver driver) {
 
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
 
    @FindBy(xpath = "//a[contains(@href,'newOrderForm')]")
    private WebElement checkoutButton;
 
    @FindBy(linkText = "Return to Main Menu")
    private WebElement returnToMainMenu;
 
    public void updateQuantity(String quantity) {
 
        WebElement quantityField =
                driver.findElement(
                        By.xpath("//input[@type='number']"));
 
        quantityField.clear();
        quantityField.sendKeys(quantity);
 
        WebElement updateButton =
                driver.findElement(
                        By.xpath("//button[text()='Update Cart']"));
 
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", updateButton);
    }
 
    public String getQuantity() {
 
        return driver.findElement(
                By.xpath("//input[@type='number']"))
                .getAttribute("value");
    }
 
    public void removeItem() {
 
        int beforeCount = getCartItemCount();
 
        List<WebElement> items =
                driver.findElements(
                        By.xpath("//a[contains(@hx-post,'removeItemFromCart')]"));
 
        if (!items.isEmpty()) {
 
            ((JavascriptExecutor) driver)
                    .executeScript(
                            "arguments[0].click();",
                            items.get(0));
 
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(d -> getCartItemCount() < beforeCount);
 
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
 
    public void removeAllItems() {
 
        WebElement removeAll =
                driver.findElement(
                        By.xpath("//a[contains(@hx-post,'removeAllItemsFromCart')]"));
 
        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        removeAll);
 
        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        removeAll);
 
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
 
    public int getCartItemCount() {
 
        return driver.findElements(
                By.xpath("//a[contains(@hx-post,'removeItemFromCart')]"))
                .size();
    }
 
    public boolean isCartEmpty() {
 
        return getCartItemCount() == 0;
    }
 
    public boolean isCheckoutButtonDisplayed() {
 
        return driver.findElement(
                By.xpath("//a[contains(@href,'newOrderForm')]"))
                .isDisplayed();
    }
 
    public void clickProceedToCheckout() {
 
        driver.findElement(
                By.xpath("//a[contains(@href,'newOrderForm')]"))
                .click();
    }
 
    public void clickReturnToMainMenu() {
 
        driver.findElement(
                By.linkText("Return to Main Menu"))
                .click();
    }
}
