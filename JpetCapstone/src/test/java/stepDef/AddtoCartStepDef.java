package stepDef;

import org.openqa.selenium.WebDriver;

import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pages.JpetCartPage;
import pages.JpetHomePage;
import pages.JpetLoginPage;
import pages.JpetProductPage;

public class AddtoCartStepDef {
	
	
	private WebDriver driver;
	private JpetLoginPage loginPage;
	private JpetHomePage homePage;
	private JpetCartPage cartPage;
	private JpetProductPage productPage;
	
	
	
	public void initializePages() {
		 driver = Hooks.driver;
		 loginPage = new JpetLoginPage(driver);
		 cartPage = new JpetCartPage(driver);
		 productPage = new JpetProductPage(driver);
		 homePage = new JpetHomePage(driver);
	}
	

@Given("user is logged in")
public void user_is_logged_in() {
   
	initializePages();
	 
	Hooks.test.info("Navigating to JPetStore home page: https://jpetstore.aspectran.com/");
	Hooks.driver.get("https://jpetstore.aspectran.com/");
	
	Hooks.test.info("Clicking the Sign In link.");
    loginPage.SignInlink();
    
    Hooks.test.info("Entering credentials for user: j2ee");
    loginPage.username("j2ee");
    loginPage.password("j2ee");
    
    Hooks.test.info("Clicking the login button.");
    loginPage.LoginBtn();
   
    Hooks.test.pass("User successfully logged into the application session.");
}

@When("user selects {string} category")
public void user_selects_category(String category) {
	
	initializePages();
	homePage=new JpetHomePage(driver);
	
	Hooks.test.info("Selecting category link option: " + category);
    homePage.selectCategory(category);
    Hooks.test.pass("Category selection executed.");
}

@And("user selects first product")
public void user_selects_first_product() {
	initializePages();

	Hooks.test.info("Clicking the first available product link on the layout grid.");
    productPage.selectFirstProduct();
    Hooks.test.pass("First product selected.");
}

@And("user adds first item to cart")
public void user_adds_first_item_to_cart() {

	initializePages();

	Hooks.test.info("Clicking Add to Cart button for the selected item.");
    productPage.addFirstItemToCart();
    Hooks.test.pass("Add to Cart operation submitted.");
}

@Then("cart should contain {int} item")
public void cart_should_contain_item(Integer count) {
	initializePages();

	Hooks.test.info("Verifying cart contents count. Expected count: " + count);
	int actualCount = cartPage.getCartItemCount();
	
	if (actualCount == count.intValue()) {
		Hooks.test.pass("Cart quantity verified successfully. Found items: " + actualCount);
	} else {
		Hooks.test.fail("Cart validation failed. Expected: " + count + ", but found: " + actualCount);
	}

    Assert.assertEquals(
            count.intValue(),
            actualCount);
}

@Then("cart should contain {int} items")
public void cart_should_contain_items(Integer count) {

	initializePages();

	Hooks.test.info("Verifying cart contents total items. Expected count: " + count);
	int actualCount = cartPage.getCartItemCount();
	
	if (actualCount == count.intValue()) {
		Hooks.test.pass("Cart total items verified successfully. Found items: " + actualCount);
	} else {
		Hooks.test.fail("Cart items validation failed. Expected: " + count + ", but found: " + actualCount);
	}

    Assert.assertEquals(
            count.intValue(),
            actualCount);
}

@When("user adds {string} product to cart")
public void user_adds_product_to_cart(String category) {

	initializePages();

	Hooks.test.info("Automating end-to-end add sequence for category: " + category);
    homePage.selectCategory(category);

    Hooks.test.info("Selecting target item inside category view.");
    productPage.selectFirstProduct();

    Hooks.test.info("Dispatching add to cart click.");
    productPage.addFirstItemToCart();
    Hooks.test.pass("Successfully added product from category [" + category + "] into the cart.");
}

@When("user returns to main menu")
public void user_returns_to_main_menu() {
 
	initializePages();

	Hooks.test.info("Clicking the Return to Main Menu link navigation button.");
    cartPage.clickReturnToMainMenu();
    Hooks.test.pass("Main menu navigation sequence completed.");
}

@When("user removes item from cart")
public void user_removes_item_from_cart() {

	initializePages();

	Hooks.test.info("Clicking the Remove button element next to the item row.");
    cartPage.removeItem();
    Hooks.test.pass("Item removal request completed.");
}

@When("user removes first item from cart")
public void user_removes_first_item_from_cart() {

	initializePages();

	Hooks.test.info("Clicking the Remove button for the first item in the shopping basket rows.");
    cartPage.removeItem();
    Hooks.test.pass("First item removed sequence finished.");
}

@When("user removes all items from cart")
public void user_removes_all_items_from_cart() {

	initializePages();

	Hooks.test.info("Clearing the basket layout by triggering the global remove operations.");
    cartPage.removeAllItems();
    Hooks.test.pass("All shopping cart records removed.");
}

@Then("cart should be empty")
public void cart_should_be_empty() {

	initializePages();

	Hooks.test.info("Validating empty table constraints inside the shopping cart screen.");
	boolean isEmpty = cartPage.isCartEmpty();
	
	if (isEmpty) {
		Hooks.test.pass("Cart status checkpoint confirmed. Shopping container table records are empty.");
	} else {
		Hooks.test.fail("Cart status assertion exception. Items still present inside layout list.");
	}

    Assert.assertTrue(
            "Cart should be empty",
            isEmpty);
}

@When("user updates quantity to {string}")
public void user_updates_quantity_to(String quantity) {

	initializePages();

	Hooks.test.info("Updating shopping basket input row count field value to: " + quantity);
    cartPage.updateQuantity(quantity);
    Hooks.test.pass("Quantity field updated values submitted.");
}

@Then("quantity should be updated to {string}")
public void quantity_should_be_updated_to(String quantity) {

 
	initializePages();
	Hooks.test.info("Checking item table row field data. Expecting field text value: " + quantity);
	String actualQuantity = cartPage.getQuantity();
	
	if (actualQuantity.equals(quantity)) {
		Hooks.test.pass("Row field text verified successfully. Match state value: " + actualQuantity);
	} else {
		Hooks.test.fail("Row field text discrepancy found. Expected field value: " + quantity + ", but got: " + actualQuantity);
	}
	
    Assert.assertEquals(
            quantity,
            actualQuantity);
}

@Then("proceed to checkout button should be displayed")
public void proceed_to_checkout_button_should_be_displayed() {
	initializePages();
    
	Hooks.test.info("Scanning table summaries container layout for Proceed to Checkout link UI element visibility.");
	boolean isDisplayed = cartPage.isCheckoutButtonDisplayed();
	
	if (isDisplayed) {
		Hooks.test.pass("Proceed to Checkout button tracking confirmed. Element visible on screen.");
	} else {
		Hooks.test.fail("Proceed to Checkout button layout evaluation error. Element is missing or inaccessible.");
	}

    Assert.assertTrue(
            "Checkout button not displayed",
            isDisplayed);
}

}
