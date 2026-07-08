package stepDef;

import org.openqa.selenium.WebDriver;

import hooks.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pages.JpetDeleteOrderPage;

public class DeleteOrderStepDef {
	
	
	private WebDriver driver;
	private JpetDeleteOrderPage deleteOrderPage;
	
	
	private String deletedOrderId;
	  
	  
	  
	public void initializePages() {
		driver=Hooks.getDriver();
		deleteOrderPage=new JpetDeleteOrderPage(driver);
	}
	
	
	


@When("user clicks on My Orders")
public void user_clicks_on_my_orders() {

	initializePages();
	Hooks.getTest().info("Navigating to the My Orders management page.");
	deleteOrderPage.clickMyOrders();
	Hooks.getTest().pass("Successfully clicked on My Orders link.");
}

@When("user selects an existing order")
public void user_selects_an_existing_order() {
	initializePages();
	Hooks.getTest().info("Selecting an active existing order from the history summary table.");
	deleteOrderPage.selectExistingOrder();

    deletedOrderId = deleteOrderPage.getSelectedOrderId();
    Hooks.getTest().pass("Captured Target Order ID for deletion tracking: " + deletedOrderId);
}

@When("user clicks on the Delete Order button")
public void user_clicks_on_the_delete_order_button() {
	initializePages();
	Hooks.getTest().info("Clicking the final Delete Order processing button.");
	deleteOrderPage.clickDeleteOrder();
	Hooks.getTest().pass("Delete order request dispatched.");
}

@Then("order should be deleted successfully")
public void order_should_be_deleted_successfully() {
	initializePages();
	Hooks.getTest().info("Validating order history records to confirm deletion status of ID: " + deletedOrderId);
	boolean isDeleted = deleteOrderPage.isOrderDeleted(deletedOrderId);
	
	if(isDeleted) {
		Hooks.getTest().pass("Order records cleanup confirmed. Order ID " + deletedOrderId + " no longer exists.");
	} else {
		Hooks.getTest().fail("Order deletion verification failed. Order ID " + deletedOrderId + " still present in tables.");
	}

    Assert.assertTrue(
            "Order ID " + deletedOrderId + " still exists after deletion",
            isDeleted);

    System.out.println("Order ID " + deletedOrderId + " deleted successfully");
}

@Then("user should see no orders message {string}")
public void user_should_see_no_orders_message(String expectedMessage) {
	initializePages();
	Hooks.getTest().info("Checking layout states to ensure zero orders exist for the profile context.");
    if (Hooks.getDriver().getPageSource().contains("Order ID")
            && Hooks.getDriver().getPageSource().contains("viewOrder")) {

    	Hooks.getTest().fail("Pre-condition assertion breakdown: Orders are still visible on screen. 'No Orders' message cannot show.");
        Assert.fail("Orders are present for this user. No Orders message cannot be displayed.");
    }

    Hooks.getTest().info("Reading text element message values. Expected message string value: [" + expectedMessage + "]");
    String actualMessage = deleteOrderPage.getNoOrdersMessage();

    if(expectedMessage.equals(actualMessage)) {
    	Hooks.getTest().pass("Empty grid message placeholder verified successfully: " + actualMessage);
    } else {
    	Hooks.getTest().fail("Empty history text mismatch. Expected: [" + expectedMessage + "] but found: [" + actualMessage + "]");
    }

    Assert.assertEquals(expectedMessage, actualMessage);

    System.out.println("No Orders Message Verified Successfully");
}


}
