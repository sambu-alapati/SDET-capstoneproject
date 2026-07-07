package stepDef;

import org.openqa.selenium.WebDriver;

import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pages.JpetCartPage;
import pages.JpetCheckoutPage;
import pages.JpetHomePage;
import pages.JpetProductPage;

public class CheckoutStepDef {
	
	private WebDriver driver;
	private JpetHomePage homePage;
	private JpetProductPage productPage;
	private JpetCartPage cartPage;
	private JpetCheckoutPage checkoutPage;
	
	
	
	
	  private  String orderId;
	    
	    
	    
	public void initializePages() {
		driver=Hooks.driver;
		homePage=new JpetHomePage(driver);
		productPage=new JpetProductPage(driver);
		cartPage=new JpetCartPage(driver);
		checkoutPage=new JpetCheckoutPage(driver);
		
	}
	


@Given("user has 1 item in cart")
public void user_has_1_item_in_cart() {

	initializePages();
	Hooks.test.info("Setting up cart with 1 item.");

    waitForPage();
    Hooks.test.info("Selecting category: Fish");
    homePage.selectCategory("Fish");

    waitForPage();
    Hooks.test.info("Selecting the first product under Fish.");
    productPage.selectFirstProduct();

    waitForPage();
    Hooks.test.info("Adding the selected item to the cart.");
    productPage.addFirstItemToCart();

    waitForPage();
    Hooks.test.pass("Successfully added 1 item to the cart.");
}

@Given("user has 2 items in cart")
public void user_has_2_items_in_cart() {

	initializePages();
	Hooks.test.info("Setting up cart with 2 items.");

    Hooks.test.info("Selecting first category: Fish");
    homePage.selectCategory("Fish");

    waitForPage();
    Hooks.test.info("Selecting the first product under Fish.");
    productPage.selectFirstProduct();

    waitForPage();
    Hooks.test.info("Adding the first item to the cart.");
    productPage.addFirstItemToCart();

    waitForPage();
    Hooks.test.info("Returning to the main menu to select the second item.");
    cartPage.clickReturnToMainMenu();

    waitForPage();
    Hooks.test.info("Selecting second category: Dogs");
    homePage.selectCategory("Dogs");

    waitForPage();
    Hooks.test.info("Selecting the first product under Dogs.");
    productPage.selectFirstProduct();

    waitForPage();
    Hooks.test.info("Adding the second item to the cart.");
    productPage.addFirstItemToCart();

    waitForPage();
    Hooks.test.pass("Successfully added 2 distinct items to the cart.");
}

@When("user proceeds to checkout")
public void user_proceeds_to_checkout() {

	initializePages();
	Hooks.test.info("Clicking the Proceed to Checkout button.");
    checkoutPage.proceedToCheckout();
    Hooks.test.pass("Proceeded to checkout screen.");
}

@And("user continues checkout process")
public void user_continues_checkout_process() {

	initializePages();
	Hooks.test.info("Continuing the checkout process.");
    checkoutPage.continueCheckout();
    Hooks.test.pass("Checkout navigation step continued.");
}

@And("user continues with default checkout details")
public void user_continues_with_default_checkout_details() {

	initializePages();
	Hooks.test.info("Continuing with default shipping and billing details.");
    checkoutPage.continueCheckout();
    Hooks.test.pass("Default checkout configurations submitted.");
}

@And("user updates checkout details")
public void user_updates_checkout_details() {

	initializePages();
	Hooks.test.info("Modifying shipping or payment details on checkout form.");
    checkoutPage.updateCheckoutDetails();
    Hooks.test.pass("Updated checkout details processed successfully.");
}

@And("user confirms the order")
public void user_confirms_the_order() {

	initializePages();
	Hooks.test.info("Clicking the final order confirmation button.");
    checkoutPage.confirmOrder();
    Hooks.test.pass("Order confirmation action dispatched.");
}

@Then("checkout page should be displayed")
public void checkout_page_should_be_displayed() {

	initializePages();
	Hooks.test.info("Verifying visibility of the checkout landing page.");
	
	boolean isDisplayed = checkoutPage.isCheckoutPageDisplayed();
	if(isDisplayed) {
		Hooks.test.pass("Checkout page verification passed. Target layout is visible.");
	} else {
		Hooks.test.fail("Checkout page verification failed. Component not visible.");
	}

    Assert.assertTrue(
            "Checkout page is not displayed",
            isDisplayed);
}

@Then("order should be placed successfully")
public void order_should_be_placed_successfully() {

	initializePages();
	Hooks.test.info("Verifying order confirmation landing state.");
	
	boolean isSuccess = checkoutPage.isOrderPlacedSuccessfully();
	if(isSuccess) {
		Hooks.test.pass("Order placement confirmation passed. Order success message displayed.");
	} else {
		Hooks.test.fail("Order placement confirmation failed. Success message layout is absent.");
	}

    Assert.assertTrue(
            "Order placement failed",
            isSuccess);
}

@Then("order number should be generated")
public void order_number_should_be_generated() {

	initializePages();
	Hooks.test.info("Fetching and validating generated order token.");

    String orderNumber = checkoutPage.getOrderNumber();

    if(orderNumber != null && !orderNumber.isEmpty()) {
    	Hooks.test.pass("Order number validation passed. Generated ID token: " + orderNumber);
    } else {
    	Hooks.test.fail("Order number validation failed. Returned reference was null or empty.");
    }

    Assert.assertNotNull(orderNumber);
    Assert.assertFalse(orderNumber.isEmpty());

    System.out.println(
            "Generated Order Number : "
                    + orderNumber);
}

@Then("ordered item details should be displayed")
public void ordered_item_details_should_be_displayed() {

	initializePages();
	Hooks.test.info("Verifying summary item grid layouts on order summary panel.");
	
	boolean isDisplayed = checkoutPage.isOrderDetailsDisplayed();
	if(isDisplayed) {
		Hooks.test.pass("Order item details rows are verified and displayed.");
	} else {
		Hooks.test.fail("Order item details rows missing from confirmation layout.");
	}

    Assert.assertTrue(
            "Order details are not displayed",
            isDisplayed);
}

@And("user captures generated order id")
public void user_captures_generated_order_id() {

	initializePages();
	Hooks.test.info("Capturing order number into local context variable for future lifecycle tracking steps.");

     orderId = checkoutPage.getOrderNumber();
     Hooks.test.pass("Captured Order ID from UI layout: " + orderId);

    System.out.println(
            "Captured Order ID : "
                    + orderId);
}

@And("user deletes the order")
public void user_deletes_the_order() {

	initializePages();
	Hooks.test.info("Triggering order deletion/cancellation routine workflow.");
    checkoutPage.deleteOrder();
    Hooks.test.pass("Order deletion action executed.");
}

@Then("deleted order should not be available")
public void deleted_order_should_not_be_available() {

	initializePages();
	Hooks.test.info("Navigating to My Orders grid dashboard to confirm deletion profile of target ID: " + orderId);
	
    checkoutPage.openMyOrders();
    boolean isOrderStillPresent = checkoutPage.isOrderPresent(orderId);
    
    if(!isOrderStillPresent) {
    	Hooks.test.pass("Order cleanup validation passed. Order ID [" + orderId + "] was not found inside account tables.");
    } else {
    	Hooks.test.fail("Order cleanup validation failed. Order ID [" + orderId + "] is still present in history lists.");
    }

    Assert.assertFalse(
            "Deleted order still exists",
            isOrderStillPresent);
}

private void waitForPage() {

    try {
        Thread.sleep(1500);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}


}
