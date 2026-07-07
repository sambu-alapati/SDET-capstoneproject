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

    //JpetRegister feature for scenario Register new user with valid details
    
    @Given("User is on JPetStore Registration page")
    public void user_is_on_jpetstore_registration_page() {
    	driver=Hooks.driver;
    	registerPage=new JpetRegisterPage(driver);
        
        Hooks.test.info("Navigating to JPetStore home page: https://jpetstore.aspectran.com/");
        driver.get("https://jpetstore.aspectran.com/");
        
        registerPage = new JpetRegisterPage(Hooks.driver);
        Hooks.test.info("Navigating to Registration Page by clicking Sign Up.");
        registerPage.clickSignUp();
        
        Hooks.test.pass("Successfully landed on JPetStore Registration page.");
    }

    @When("User enters User ID {string}")
    public void user_enters_user_id(String userid) {
        Hooks.test.info("Typing User ID: " + userid);
        registerPage.enterUserId(userid);
        Hooks.test.pass("User ID field filled.");
    }

    @And("User enters Password {string}")
    public void user_enters_password(String password) {
        Hooks.test.info("Typing Password field.");
        registerPage.enterPassword(password);
        Hooks.test.pass("Password field filled.");
    }

    @And("User enters Confirm Password {string}")
    public void user_enters_confirm_password(String confirmPassword) {
        Hooks.test.info("Typing Confirm Password field.");
        registerPage.enterConfirmPassword(confirmPassword);
        Hooks.test.pass("Confirm Password field filled.");
    }

    @And("User enters First Name {string}")
    public void user_enters_first_name(String firstName) {
        Hooks.test.info("Typing First Name: " + firstName);
        registerPage.enterFirstName(firstName);
        Hooks.test.pass("First Name field filled.");
    }

    @And("User enters Last Name {string}")
    public void user_enters_last_name(String lastName) {
        Hooks.test.info("Typing Last Name: " + lastName);
        registerPage.enterLastName(lastName);
        Hooks.test.pass("Last Name field filled.");
    }

    @And("User enters Email {string}")
    public void user_enters_email(String email) {
        Hooks.test.info("Typing Email address: " + email);
        registerPage.enterEmail(email);
        Hooks.test.pass("Email field filled.");
    }

    @And("User enters Phone {string}")
    public void user_enters_phone(String phone) {
        Hooks.test.info("Typing Phone Number: " + phone);
        registerPage.enterPhone(phone);
        Hooks.test.pass("Phone field filled.");
    }

    @And("User enters Address1 {string}")
    public void user_enters_address1(String address1) {
        Hooks.test.info("Typing Address line 1: " + address1);
        registerPage.enterAddress1(address1);
        Hooks.test.pass("Address1 field filled.");
    }

    @And("User enters Address2 {string}")
    public void user_enters_address2(String address2) {
        Hooks.test.info("Typing Address line 2: " + address2);
        registerPage.enterAddress2(address2);
        Hooks.test.pass("Address2 field filled.");
    }

    @And("User enters City {string}")
    public void user_enters_city(String city) {
        Hooks.test.info("Typing City: " + city);
        registerPage.enterCity(city);
        Hooks.test.pass("City field filled.");
    }

    @And("User enters State {string}")
    public void user_enters_state(String state) {
        Hooks.test.info("Typing State: " + state);
        registerPage.enterState(state);
        Hooks.test.pass("State field filled.");
    }

    @And("User enters Zip {string}")
    public void user_enters_zip(String zip) {
        Hooks.test.info("Typing Zip Code: " + zip);
        registerPage.enterZip(zip);
        Hooks.test.pass("Zip field filled.");
    }

    @And("User enters Country {string}")
    public void user_enters_country(String country) {
        Hooks.test.info("Typing Country: " + country);
        registerPage.enterCountry(country);
        Hooks.test.pass("Country field filled.");
    }

    @And("User selects Language Preference {string}")
    public void user_selects_language_preference(String language) {
        Hooks.test.info("Selecting Language Dropdown Option: " + language);
        registerPage.selectLanguage(language);
        Hooks.test.pass("Language preference configured.");
    }

    @And("User selects Favourite Category {string}")
    public void user_selects_favourite_category(String category) {
        Hooks.test.info("Selecting Category Preference Dropdown Option: " + category);
        registerPage.selectCategory(category);
        Hooks.test.pass("Favourite category option configured.");
    }

    @And("User clicks Save Account Information button")
    public void user_clicks_save_account_information_button() {
        Hooks.test.info("Submitting registration details by clicking Save button.");
        registerPage.clickSaveAccountInformation();
        Hooks.test.pass("Save Account Information action executed.");
    }

    @Then("User account should be created successfully")
    public void user_account_should_be_created_successfully() {
        Hooks.test.info("Verifying application landing url context and view text layout.");
        String visibleText = Hooks.driver.findElement(By.tagName("body")).getText();
        
        boolean matchState = Hooks.driver.getCurrentUrl().contains("signin") || visibleText.contains("Sign In");
        
        if (matchState) {
            Hooks.test.pass("Account verified! UI redirected successfully to Sign-In page view state.");
        } else {
            Hooks.test.fail("Account verification checkpoint broken! UI did not render required Redirection details.");
        }
        
        Assert.assertTrue(
                "Registration failed",
                matchState
        );
    }

    
    
    
    
    
    
    
    
    
    //JpetRegister feature for scenario Verify mandatory field error messages
    
    
    
    // =========================================================================
    // CRITICAL SHIELD: Shared explicit wait method to stop StaleElement Exceptions
    // =========================================================================
    private void verifyErrorMessageOnScreen(String msg) {
        Hooks.test.info("Scanning application page layout content for explicit message text: [" + msg + "]");
        // Sets up a 5-second explicit wait to allow layout shifts to settle
        WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(5));
        
        try {
            // Relocates the body element dynamically on every poll loop to avoid going stale
            boolean isTextPresent = wait.until(
                ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), msg.trim())
            );
            
            if (isTextPresent) {
                Hooks.test.pass("Validation match confirmed. Target text found: [" + msg + "]");
            } else {
                Hooks.test.fail("Validation check completed with empty results. Target text missing: [" + msg + "]");
            }
            
            Assert.assertTrue("Validation message absent! Expected text: [" + msg + "]", isTextPresent);
        } catch (Exception e) {
            Hooks.test.fail("Validation validation step threw exception: " + e.getMessage());
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
    
    

}
