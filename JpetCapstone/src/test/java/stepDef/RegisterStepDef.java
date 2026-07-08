package stepDef;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pages.JpetLoginPage;
import pages.JpetRegisterPage;

public class RegisterStepDef {
	
	
	    private WebDriver driver;
	    private JpetRegisterPage registerPage;
	    
	    public void initializePages() {
	        driver = Hooks.getDriver();
	        registerPage = new JpetRegisterPage(driver);
	    }

    //JpetRegister feature for scenario Register new user with valid details
    
    @Given("User is on JPetStore Registration page")
    public void user_is_on_jpetstore_registration_page() {
    	 initializePages();
        
        Hooks.getTest().info("Navigating to JPetStore home page: https://jpetstore.aspectran.com/");
        driver.get("https://jpetstore.aspectran.com/");
        
        
        Hooks.getTest().info("Navigating to Registration Page by clicking Sign Up.");
        registerPage.clickSignUp();
        
        Hooks.getTest().pass("Successfully landed on JPetStore Registration page.");
    }

    @When("User enters User ID {string}")
    public void user_enters_user_id(String userid) {
    	initializePages();
        String finalUserId = userid;

        // ONLY randomize if the scenario targets your new dynamic user profile marker
        if (userid.equals("newdemo123")) {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String randomSuffix = timestamp.substring(timestamp.length() - 5);
            finalUserId = userid + randomSuffix;
            Hooks.getTest().info("Generating a brand new unique account ID: " + finalUserId);
        } else {
            // Leave it static (e.g., "jpetuser123") so the app catches the existing record conflict
            Hooks.getTest().info("Using static existing account ID to trigger warning: " + finalUserId);
        }

        registerPage.enterUserId(finalUserId);
        Hooks.getTest().pass("User ID field populated successfully.");
    }

    @And("User enters Password {string}")
    public void user_enters_password(String password) {
    	initializePages();
        Hooks.getTest().info("Typing Password field.");
        registerPage.enterPassword(password);
        Hooks.getTest().pass("Password field filled.");
    }

    @And("User enters Confirm Password {string}")
    public void user_enters_confirm_password(String confirmPassword) {
    	initializePages();
        Hooks.getTest().info("Typing Confirm Password field.");
        registerPage.enterConfirmPassword(confirmPassword);
        Hooks.getTest().pass("Confirm Password field filled.");
    }

    @And("User enters First Name {string}")
    public void user_enters_first_name(String firstName) {
    	initializePages();
        Hooks.getTest().info("Typing First Name: " + firstName);
        registerPage.enterFirstName(firstName);
        Hooks.getTest().pass("First Name field filled.");
    }

    @And("User enters Last Name {string}")
    public void user_enters_last_name(String lastName) {
    	initializePages();
        Hooks.getTest().info("Typing Last Name: " + lastName);
        registerPage.enterLastName(lastName);
        Hooks.getTest().pass("Last Name field filled.");
    }

    @And("User enters Email {string}")
    public void user_enters_email(String email) {
    	initializePages();
        Hooks.getTest().info("Typing Email address: " + email);
        registerPage.enterEmail(email);
        Hooks.getTest().pass("Email field filled.");
    }

    @And("User enters Phone {string}")
    public void user_enters_phone(String phone) {
    	initializePages();
        Hooks.getTest().info("Typing Phone Number: " + phone);
        registerPage.enterPhone(phone);
        Hooks.getTest().pass("Phone field filled.");
    }

    @And("User enters Address1 {string}")
    public void user_enters_address1(String address1) {
    	initializePages();
        Hooks.getTest().info("Typing Address line 1: " + address1);
        registerPage.enterAddress1(address1);
        Hooks.getTest().pass("Address1 field filled.");
    }

    @And("User enters Address2 {string}")
    public void user_enters_address2(String address2) {
    	initializePages();
        Hooks.getTest().info("Typing Address line 2: " + address2);
        registerPage.enterAddress2(address2);
        Hooks.getTest().pass("Address2 field filled.");
    }

    @And("User enters City {string}")
    public void user_enters_city(String city) {
    	initializePages();
        Hooks.getTest().info("Typing City: " + city);
        registerPage.enterCity(city);
        Hooks.getTest().pass("City field filled.");
    }

    @And("User enters State {string}")
    public void user_enters_state(String state) {
    	initializePages();
        Hooks.getTest().info("Typing State: " + state);
        registerPage.enterState(state);
        Hooks.getTest().pass("State field filled.");
    }

    @And("User enters Zip {string}")
    public void user_enters_zip(String zip) {
    	initializePages();
        Hooks.getTest().info("Typing Zip Code: " + zip);
        registerPage.enterZip(zip);
        Hooks.getTest().pass("Zip field filled.");
    }

    @And("User enters Country {string}")
    public void user_enters_country(String country) {
    	initializePages();
        Hooks.getTest().info("Typing Country: " + country);
        registerPage.enterCountry(country);
        Hooks.getTest().pass("Country field filled.");
    }

    @And("User selects Language Preference {string}")
    public void user_selects_language_preference(String language) {
    	initializePages();
        Hooks.getTest().info("Selecting Language Dropdown Option: " + language);
        registerPage.selectLanguage(language);
        Hooks.getTest().pass("Language preference configured.");
    }

    @And("User selects Favourite Category {string}")
    public void user_selects_favourite_category(String category) {
    	initializePages();
        Hooks.getTest().info("Selecting Category Preference Dropdown Option: " + category);
        registerPage.selectCategory(category);
        Hooks.getTest().pass("Favourite category option configured.");
    }

    @And("User clicks Save Account Information button")
    public void user_clicks_save_account_information_button() {
    	initializePages();
        Hooks.getTest().info("Submitting registration details by clicking Save button.");
        registerPage.clickSaveAccountInformation();
        Hooks.getTest().pass("Save Account Information action executed.");
    }

    @Then("User account should be created successfully")
    public void user_account_should_be_created_successfully() {
    	initializePages();
        Hooks.getTest().info("Verifying application landing url context and view text layout.");
        String visibleText = Hooks.getDriver().findElement(By.tagName("body")).getText();
        
        boolean matchState = Hooks.getDriver().getCurrentUrl().contains("signin") || visibleText.contains("Sign In");
        
        if (matchState) {
            Hooks.getTest().pass("Account verified! UI redirected successfully to Sign-In page view state.");
        } else {
            Hooks.getTest().fail("Account verification checkpoint broken! UI did not render required Redirection details.");
        }
        
        Assert.assertTrue(
                "Registration failed",
                matchState
        );
    }

    
    
    
    
    
    
    
    
    // =========================================================================
    // JpetRegister feature for scenario: Verify mandatory field error messages
    // =========================================================================
    
    // =========================================================================
    // CRITICAL SHIELD: Shared explicit wait method to stop StaleElement Exceptions
    // =========================================================================
    private void verifyErrorMessageOnScreen(String msg) {
        initializePages();
        Hooks.getTest().info("Scanning application page layout content for explicit message text: [" + msg + "]");
        // Sets up a 5-second explicit wait to allow layout shifts to settle
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        
        try {
            // Relocates the body element dynamically on every poll loop to avoid going stale
            boolean isTextPresent = wait.until(
                ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), msg.trim())
            );
            
            if (isTextPresent) {
                Hooks.getTest().pass("Validation match confirmed. Target text found: [" + msg + "]");
            } else {
                Hooks.getTest().fail("Validation check completed with empty results. Target text missing: [" + msg + "]");
            }
            
            Assert.assertTrue("Validation message absent! Expected text: [" + msg + "]", isTextPresent);
        } catch (Exception e) {
            Hooks.getTest().fail("Validation validation step threw exception: " + e.getMessage());
            throw e;
        }
    }

    @Then("User ID error message should be displayed as {string}")
    public void user_id_error_message_should_be_displayed_as(String msg) {
        verifyErrorMessageOnScreen(msg);
    }

    @Then("Password error message should be displayed as {string}")
    public void password_error_message_should_be_displayed_as(String msg) {
        verifyErrorMessageOnScreen(msg);
    }

    @Then("First Name error message should be displayed as {string}")
    public void first_name_error_message_should_be_displayed_as(String msg) {
        verifyErrorMessageOnScreen(msg);
    }

    @Then("Last Name error message should be displayed as {string}")
    public void last_name_error_message_should_be_displayed_as(String msg) {
        verifyErrorMessageOnScreen(msg);
    }

    @Then("Email error message should be displayed as {string}")
    public void email_error_message_should_be_displayed_as(String msg) {
        verifyErrorMessageOnScreen(msg);
    }

    @Then("Phone error message should be displayed as {string}")
    public void phone_error_message_should_be_displayed_as(String msg) {
        verifyErrorMessageOnScreen(msg);
    }

    @Then("Address1 error message should be displayed as {string}")
    public void address1_error_message_should_be_displayed_as(String msg) {
        verifyErrorMessageOnScreen(msg);
    }

    @Then("City error message should be displayed as {string}")
    public void city_error_message_should_be_displayed_as(String msg) {
        verifyErrorMessageOnScreen(msg);
    }

    @Then("State error message should be displayed as {string}")
    public void state_error_message_should_be_displayed_as(String msg) {
        verifyErrorMessageOnScreen(msg);
    }

    @Then("Zip error message should be displayed as {string}")
    public void zip_error_message_should_be_displayed_as(String msg) {
        verifyErrorMessageOnScreen(msg);
    }

    @Then("Country error message should be displayed as {string}")
    public void country_error_message_should_be_displayed_as(String msg) {
        verifyErrorMessageOnScreen(msg);
    }

    
    
    
    @Then("User should see the existing user ID message {string}")
    public void user_should_see_the_existing_user_id_message(String expectedWarning) {
        // Synchronize and assign active driver context safely via your initialization helper
        initializePages();
        
        Hooks.getTest().info("Scanning layout for duplicate user profile warning message: [" + expectedWarning + "]");
        
        // Wait up to 10 seconds for the form submit to process and render the warning message
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        try {
            boolean isWarningDisplayed = wait.until(
                ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), expectedWarning.trim())
            );
            
            if (isWarningDisplayed) {
                Hooks.getTest().pass("Duplicate account validation verification successful. Message rendered: " + expectedWarning);
            } else {
                Hooks.getTest().fail("Duplicate validation failed! Missing expected message banner: " + expectedWarning);
            }
            
            Assert.assertTrue("BUG DETECTED: The expected validation warning was absent: " + expectedWarning, isWarningDisplayed);
            
        } catch (Exception e) {
            Hooks.getTest().fail("Duplicate verification step encountered a runtime error: " + e.getMessage());
            throw e;
        }
    }


    

}
