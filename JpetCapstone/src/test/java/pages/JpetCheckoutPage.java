package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JpetCheckoutPage {
	
	
	private WebDriver driver;
	 
    public JpetCheckoutPage(WebDriver driver) {
        this.driver = driver;
    }
 
    public void proceedToCheckout() {
 
        WebElement checkoutButton =
                driver.findElement(
                        By.xpath("//a[contains(@href,'newOrderForm')]"));
 
        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        checkoutButton);
 
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
 
        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        checkoutButton);
 
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
 
    public void continueCheckout() {
 
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
 
        WebElement continueButton;
 
        try {
 
            continueButton =
                    driver.findElement(
                            By.xpath("//button[contains(text(),'Continue')]"));
 
        } catch (Exception e) {
 
            continueButton =
                    driver.findElement(
                            By.xpath("//input[@value='Continue']"));
        }
 
        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        continueButton);
 
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
 
        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        continueButton);
 
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
 
    public void updateCheckoutDetails() {
 
        driver.findElement(By.name("billToFirstName"))
                .clear();
 
        driver.findElement(By.name("billToFirstName"))
                .sendKeys("Shabber");
 
        driver.findElement(By.name("billToLastName"))
                .clear();
 
        driver.findElement(By.name("billToLastName"))
                .sendKeys("Shaik");
 
        driver.findElement(By.name("billAddress1"))
                .clear();
 
        driver.findElement(By.name("billAddress1"))
                .sendKeys("MG Road");
 
        driver.findElement(By.name("billAddress2"))
                .clear();
 
        driver.findElement(By.name("billAddress2"))
                .sendKeys("Near Metro Station");
 
        driver.findElement(By.name("billCity"))
                .clear();
 
        driver.findElement(By.name("billCity"))
                .sendKeys("Bengaluru");
 
        driver.findElement(By.name("billState"))
                .clear();
 
        driver.findElement(By.name("billState"))
                .sendKeys("Karnataka");
 
        driver.findElement(By.name("billZip"))
                .clear();
 
        driver.findElement(By.name("billZip"))
                .sendKeys("560001");
 
        driver.findElement(By.name("billCountry"))
                .clear();
 
        driver.findElement(By.name("billCountry"))
                .sendKeys("India");
    }
    public void confirmOrder() {
 
        WebElement confirmButton;
 
        try {
 
            confirmButton =
                    driver.findElement(
                            By.xpath("//button[contains(text(),'Confirm')]"));
 
        } catch (Exception e) {
 
            confirmButton =
                    driver.findElement(
                            By.xpath("//input[@value='Confirm']"));
        }
 
        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        confirmButton);
 
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
 
        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        confirmButton);
 
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
 
    public boolean isCheckoutPageDisplayed() {
 
        return driver.getCurrentUrl()
                .contains("newOrderForm");
    }
 
    public boolean isOrderPlacedSuccessfully() {
 
        return driver.getPageSource()
                .contains("Thank you")
                || driver.getPageSource().contains("Order")
                || driver.getCurrentUrl().contains("viewOrder");
    }
 
    public String getOrderNumber() {
 
        try {
 
            return driver.findElement(
                    By.xpath("//td[contains(text(),'#')]"))
                    .getText()
                    .replace("#", "")
                    .trim();
 
        } catch (Exception e) {
 
            return driver.findElement(
                    By.xpath("//tr[th[contains(text(),'Order')]]/td"))
                    .getText()
                    .trim();
        }
    }
 
    public boolean isOrderDetailsDisplayed() {
 
        return driver.getPageSource().contains("Billing Address")
                && driver.getPageSource().contains("Shipping Address");
    }
 
    public void openMyOrders() {
 
        WebElement myOrders =
                driver.findElement(
                        By.xpath("//a[contains(text(),'My Orders')]"));
 
        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        myOrders);
    }
 
    public boolean isOrderPresent(String orderId) {
 
        return driver.getPageSource()
                .contains(orderId);
    }
 
    public void deleteOrder() {
 
        WebElement deleteButton =
                driver.findElement(
                        By.xpath("//button[contains(text(),'Delete Order')]"));
 
        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        deleteButton);
 
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
 
        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        deleteButton);
 
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
