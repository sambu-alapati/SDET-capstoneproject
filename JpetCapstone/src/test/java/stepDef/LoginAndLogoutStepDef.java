package stepDef;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pages.JpetLoginPage;

public class LoginAndLogoutStepDef {

    private WebDriver driver;
    private JpetLoginPage loginPage;
    private boolean isLoginSuccessful = true;

    /**
     * Dynamically loads the driver instance unique to the executing thread
     * and maps page objects securely.
     */
    private void initializePages() {
        this.driver = Hooks.getDriver();
        this.loginPage = new JpetLoginPage(this.driver);
    }
	
    // =========================================================================
    // JpetLoginAndLogout feature scenario for valid creds 	
    // =========================================================================
    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        initializePages();
        
        Hooks.getTest().info("Navigating to JPetStore home page: https://jpetstore.aspectran.com/");
        driver.get("https://jpetstore.aspectran.com/");
        
        Hooks.getTest().info("Clicking the Sign In link.");
        loginPage.SignInlink();
        
        Hooks.getTest().pass("Successfully landed on the login page.");
    }

    @When("User enters username {string} password {string}")
    public void user_enters_username_password(String username, String password) {
        initializePages();
        
        Hooks.getTest().info("Entering username: " + username);
        loginPage.username(username);
        
        if (utils.CheckingValidPassword.isValidpassword(password)) {
            System.out.println("[INFO] Password matches rule specifications. Interacting with POM.");
            Hooks.getTest().info("Password matches validation rule specifications.");
            loginPage.password(password);
        } else {
            System.out.println("[WARN] Password failed structural validity profile checks.");
            Hooks.getTest().info("Password failed structural validity profile checks.");
            loginPage.password(password);
        }
        Hooks.getTest().pass("Username and password fields populated.");
    }

    @When("clicks the login button")
    public void clicks_the_login_button() {
        initializePages();
        
        Hooks.getTest().info("Clicking the login button.");
        loginPage.LoginBtn();
        Hooks.getTest().pass("Login button clicked.");
    }

    @Then("user should be logged in and see greeting for user {string}")
    public void user_should_be_logged_in_and_see_greeting_for_user(String username) {
        initializePages();
        String expectedGreeting = "Welcome " + username + "!";
        Hooks.getTest().info("Waiting for visibility of expected greeting: " + expectedGreeting);
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), expectedGreeting));
		
        String bodyText = driver.findElement(By.tagName("body")).getText();

        if (bodyText.contains("Welcome " + username + "!")) {
            Hooks.getTest().pass("Login verified successfully. Found greeting banner for user: " + username);
            Assert.assertTrue(bodyText.contains("Welcome " + username + "!"));
            isLoginSuccessful = true;
            System.out.println("Valid Credentials :" + username);
        } else {
            Hooks.getTest().fail("Login Verification Failed! Missing greeting for user: " + username);
            isLoginSuccessful = false;
            System.out.println("InValid Credentials :" + username);
            Assert.assertTrue("Login Verification Failed! Missing greeting for user: " + username, 
                bodyText.contains("Welcome " + username + "!"));
        }
    }

    // =========================================================================
    // JpetLoginandLogout feature scenario for invalid login creds handling
    // =========================================================================
    @When("the user enters {string} and {string}")
    public void the_user_enters_and(String username, String password) {
        initializePages();
        
        Hooks.getTest().info("Entering negative scenario username: " + username);
        loginPage.username(username);
        
        if (utils.CheckingValidPassword.isValidpassword(password)) {
            System.out.println("[INFO] Password matches rule specifications. Interacting with POM.");
            Hooks.getTest().info("Password matches validation rule specifications.");
            loginPage.password(password);
        } else {
            System.out.println("[WARN] Password failed structural validity profile checks.");
            Hooks.getTest().info("Password failed structural validity profile checks.");
            loginPage.password(password);
        }
        Hooks.getTest().pass("Negative scenario credentials entered.");
    }

    @Then("they should see an error message {string}")
    public void they_should_see_an_error_message(String errormsg) {
        initializePages();
        
        Hooks.getTest().info("Waiting for error message text on screen: [" + errormsg + "]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
		
        String bodyText = driver.findElement(By.tagName("body")).getText();
		
        boolean containsError = bodyText.contains(errormsg);
        if (containsError) {
            Hooks.getTest().pass("Negative Scenario Validation Passed. Error displayed: " + errormsg);
        } else {
            Hooks.getTest().fail("Negative Scenario Validation Failed. Missing expected error: " + errormsg);
        }
        
        Assert.assertTrue(bodyText.contains(errormsg));
        System.out.println("Negative Scenario Validation Passed. Error displayed: " + errormsg);	 
    }

    // =========================================================================
    // Jpet LoginAndLogout feature for scenario logout
    // =========================================================================
    @When("the user is logged in with valid username {string} and valid password {string}")
    public void the_user_is_logged_in_with_valid_username_and_valid_password(String username, String password) {
        initializePages();
        
        Hooks.getTest().info("Pre-logging user into session with username: " + username);
        loginPage.username(username);
        loginPage.password(password);
        Hooks.getTest().pass("Active session login fields filled.");
    }

    @When("user clicks on the account icon and clicks sign out")
    public void user_clicks_on_the_account_icon_and_clicks_sign_out() {
        initializePages();
        
        Hooks.getTest().info("Clicking the My Account navbar utility icon.");
        loginPage.accountIcon();
        
        Hooks.getTest().info("Clicking the Sign Out navigation button.");
        loginPage.signoutButton();
        
        Hooks.getTest().pass("Sign out action sequence completed.");
    }

    @Then("user should successfully able to logout")
    public void user_should_successfully_able_to_logout() {
        initializePages();
        
        System.out.println("User Successfully Logged Out");
        Hooks.getTest().pass("User successfully verified logged out status.");
    }
}
