package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class JpetDeleteOrderPage {
	
	WebDriver driver;
    String selectedOrderId;
 
    public JpetDeleteOrderPage(WebDriver driver) {
        this.driver = driver;
    }
 
    By myOrdersBtn = By.xpath("//a[contains(text(),'My Orders')]");
    By firstOrder = By.xpath("(//tbody[@class='table-group-divider']//a)[1]");
    By deleteOrderBtn = By.cssSelector("button.btn.btn-danger");
    By noOrdersMessage = By.xpath("//td[contains(text(),'You have placed no orders.')]");
 
    public void clickMyOrders() {
        driver.findElement(myOrdersBtn).click();
    }
 
    public void selectExistingOrder() {
 
        WebElement order = driver.findElement(firstOrder);
 
        selectedOrderId = order.getText().trim();
 
        System.out.println("Selected Order ID : " + selectedOrderId);
 
        order.click();
    }
 
    public String getSelectedOrderId() {
        return selectedOrderId;
    }
 
    public void clickDeleteOrder() {
 
        WebElement deleteBtn = driver.findElement(deleteOrderBtn);
 
        Actions actions = new Actions(driver);
 
        actions.moveToElement(deleteBtn).click().perform();
    }
 
    public boolean isOrderDeleted(String orderId) {
 
        driver.findElement(myOrdersBtn).click();
 
        List<WebElement> orders = driver.findElements(
                By.xpath("//tbody[@class='table-group-divider']//tr/td[1]/a"));
 
        for (WebElement order : orders) {
 
            if (order.getText().trim().equals(orderId)) {
                return false;
            }
        }
 
        return true;
    }
 
    public String getNoOrdersMessage() {
        return driver.findElement(noOrdersMessage).getText().trim();
    }

}
