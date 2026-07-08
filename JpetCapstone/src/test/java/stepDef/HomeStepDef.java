package stepDef;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pages.JpetHomePage;
import pages.JpetRegisterPage;

public class HomeStepDef {
    
    private WebDriver driver;
    private JpetHomePage homePage;
      
    // JpetHome feature for scenario for user search bar functionality 
    
    // Core Shared Locator Map - Kept localized to guarantee dynamic runtime relocation
    private final By searchFieldLocator = By.xpath("//form[contains(@action,'searchProducts')]//input[@name='keyword']");
    private final By searchButtonLocator = By.xpath("//form[contains(@action,'searchProducts')]//button|//input[@type='submit']|//form[contains(@action,'searchProducts')]//input[@type='image']");
      
    /**
     * Lazy-initialization utility method. Ensures that we always pull a live, 
     * synchronized session driver instance across fragmented test blocks.
     */
    private JpetHomePage getHomePage() {
        this.driver = Hooks.getDriver();
        if (this.homePage == null) {
            this.homePage = new JpetHomePage(this.driver);
        }
        return this.homePage;
    }

    // =========================================================================
    // JpetHome feature for scenario for user search bar functionality 
    // =========================================================================

    @When("User enter {string} bird in searchbar")
    public void user_enter_bird_in_searchbar(String text) {
         getHomePage(); // Synced driver reference allocation
         
         Hooks.getTest().info("Entering text into searchbar: " + text);
         
         // Explicit wait shield to guarantee form input readiness
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         WebElement inputElement = wait.until(ExpectedConditions.elementToBeClickable(searchFieldLocator));
         
         inputElement.clear();
         inputElement.sendKeys(text);
         
         Hooks.getTest().pass("Search text entered successfully.");
    }

    @And("Clicks the search button")
    public void Clicks_the_search_button() {
        getHomePage();
        Hooks.getTest().info("Clicking the search button layout element.");
        
        // Dynamic wait check to clear timing mismatches
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchButtonLocator));
        
        searchBtn.click();
        Hooks.getTest().pass("Search button clicked and navigation dispatched.");
    }

    @Then("User Should able to see data related to it")
    public void User_should_able_to_see_data_releated_to_it() {
        getHomePage();
        String expectedText = "Amazon Parrot"; 
        Hooks.getTest().info("Verifying search results contain expected text: " + expectedText);
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            boolean isTextPresent = wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), expectedText));
            
            if (isTextPresent) {
                Hooks.getTest().pass("Search text validation successful. Found: " + expectedText);
            } else {
                Hooks.getTest().fail("Search text validation failed. Missing: " + expectedText);
            }
            
            Assert.assertTrue("BUG DETECTED: The page text did not contain the expected text: " + expectedText, isTextPresent);
        } catch (Exception e) {
            Hooks.getTest().fail("Search validation step encountered an exception: " + e.getMessage());
            throw e;
        }
    }
    
    // =========================================================================
    // jpethome feature for scenario for all animal search bar function using keys
    // =========================================================================

    @Then("User Should able to see data related to it with expected text {string}")
    public void user_should_able_to_see_data_related_to_it_with_expected_text(String expectedBreed) {
        getHomePage();
        Hooks.getTest().info("Scanning page layout table for expected data string: [" + expectedBreed + "]");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            boolean isTextPresent = wait.until(
                ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), expectedBreed)
            );
            
            if (isTextPresent) {
                Hooks.getTest().pass("Search results table verified successfully. Found target item: " + expectedBreed);
            } else {
                Hooks.getTest().fail("Search results layout verification failed. Missing text marker: " + expectedBreed);
            }
            
            Assert.assertTrue("BUG DETECTED: The search results table did not contain the expected text: " + expectedBreed, isTextPresent);
            
        } catch (Exception e) {
            Hooks.getTest().fail("Assertion step encountered an unexpected runtime exception: " + e.getMessage());
            throw e;
        }
    }

    // =========================================================================
    // jpethome feature for scenario search bar function using enter keys 
    // =========================================================================

    @And("Presses the Enter key on the keyboard")
    public void Presses_the_Enter_key_on_the_keyboard() throws InterruptedException {
        getHomePage();
        Hooks.getTest().info("Executing search bar interaction using keyboard Enter key.");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(searchFieldLocator));

        // Submit the search query by executing native Selenium keyboard inputs directly on the active input element
        searchBox.sendKeys(org.openqa.selenium.Keys.ENTER);
        Hooks.getTest().pass("Search action via Enter key processed.");
    }


    // jpethome feature for scenario Navbar elements navigation

    @When("user clicks the Bird navbar element and clicks return to main menu")
    public void user_clicks_the_bird_navbar_element_and_clicks_return_to_main_menu() throws InterruptedException {
        driver = Hooks.getDriver();
        homePage = new JpetHomePage(driver);
        
        Hooks.getTest().info("Clicking Bird navbar element and returning to main menu.");
        homePage.birdnavelement();
        homePage.returntomainmenu();
        Hooks.getTest().pass("Bird navbar navigation sequence completed.");
    }

    @When("user clicks the Cat navbar element and clicks return to main menu")
    public void user_clicks_the_cat_navbar_element_and_clicks_return_to_main_menu() {
        driver = Hooks.getDriver();
        homePage = new JpetHomePage(driver);
        
        Hooks.getTest().info("Clicking Cat navbar element and returning to main menu.");
        homePage.catnavelement();
        homePage.returntomainmenu();
        Hooks.getTest().pass("Cat navbar navigation sequence completed.");
    }

    @When("user clicks the Dog navbar element and clicks return to main menu")
    public void user_clicks_the_dog_navbar_element_and_clicks_return_to_main_menu() {
        driver = Hooks.getDriver();
        homePage = new JpetHomePage(driver);
        
        Hooks.getTest().info("Clicking Dog navbar element and returning to main menu.");
        homePage.dognavelement();
        homePage.returntomainmenu();
        Hooks.getTest().pass("Dog navbar navigation sequence completed.");
    }

    @When("user clicks the Reptile navbar element and clicks return to main menu")
    public void user_clicks_the_reptile_navbar_element_and_clicks_return_to_main_menu() {
        driver = Hooks.getDriver();
        homePage = new JpetHomePage(driver);
        
        Hooks.getTest().info("Clicking Reptile navbar element and returning to main menu.");
        homePage.reptilenavelement();
        homePage.returntomainmenu();
        Hooks.getTest().pass("Reptile navbar navigation sequence completed.");
    }

    @When("user clicks the Fish navbar element and clicks return to main menu")
    public void user_clicks_the_fish_navbar_element_and_clicks_return_to_main_menu() {
        driver = Hooks.getDriver();
        homePage = new JpetHomePage(driver);
        
        Hooks.getTest().info("Clicking Fish navbar element and returning to main menu.");
        homePage.fishnavelement();
        homePage.returntomainmenu();
        Hooks.getTest().pass("Fish navbar navigation sequence completed.");
    }

    @When("user clicks the Cart navbar element and clicks return to main menu")
    public void user_clicks_the_cart_navbar_element_and_clicks_return_to_main_menu() {
        driver = Hooks.getDriver();
        homePage = new JpetHomePage(driver);
        
        Hooks.getTest().info("Clicking Cart navbar element and returning to main menu.");
        homePage.cartnavelement();
        homePage.returntomainmenu();
        Hooks.getTest().pass("Cart navbar navigation sequence completed.");
    }

    @When("user clicks the My Orders navbar element")
    public void user_clicks_the_my_orders_navbar_element() {
        driver = Hooks.getDriver();
        homePage = new JpetHomePage(driver);
        
        Hooks.getTest().info("Clicking My Orders navbar element.");
        homePage.myordersnavelement();
        Hooks.getTest().pass("My Orders navbar navigation sequence completed.");
    }

    // JpetHome feature for scenario imageNavigation

    @When("user clicks on the Bird image and clicks return to main menu")
    public void user_clicks_on_the_bird_image_and_clicks_return_to_main_menu() {
        driver = Hooks.getDriver();
        homePage = new JpetHomePage(driver);
        
        Hooks.getTest().info("Clicking Bird image link and returning to main menu.");
        homePage.birdimage();
        homePage.returntomainmenu();
        Hooks.getTest().pass("Bird image navigation sequence completed.");
    }

    @When("user clicks on the Fish image and clicks return to main menu")
    public void user_clicks_on_the_fish_image_and_clicks_return_to_main_menu() {
        driver = Hooks.getDriver(); 
        homePage = new JpetHomePage(driver);
        
        Hooks.getTest().info("Clicking Fish image link and returning to main menu.");
        homePage.fishimage();
        homePage.returntomainmenu();
        Hooks.getTest().pass("Fish image navigation sequence completed.");
    }

    @When("user clicks on the Dog image and clicks return to main menu")
    public void user_clicks_on_the_dog_image_and_clicks_return_to_main_menu() {
        driver = Hooks.getDriver(); 
        homePage = new JpetHomePage(driver);
        
        Hooks.getTest().info("Clicking Dog image link and returning to main menu.");
        homePage.dogimage();
        homePage.returntomainmenu();
        Hooks.getTest().pass("Dog image navigation sequence completed.");
    }

    @When("user clicks on the Reptile image and clicks return to main menu")
    public void user_clicks_on_the_reptile_image_and_clicks_return_to_main_menu() {
        driver = Hooks.getDriver(); 
        homePage = new JpetHomePage(driver);
        
        Hooks.getTest().info("Clicking Reptile image link and returning to main menu.");
        homePage.reptileimage();
        homePage.returntomainmenu();
        Hooks.getTest().pass("Reptile image navigation sequence completed.");
    }

    @When("user clicks on the Cat image and clicks return to main menu")
    public void user_clicks_on_the_cat_image_and_clicks_return_to_main_menu() {
        driver = Hooks.getDriver(); 
        homePage = new JpetHomePage(driver);
        
        Hooks.getTest().info("Clicking Cat image link and returning to main menu.");
        homePage.catimage();
        homePage.returntomainmenu();
        Hooks.getTest().pass("Cat image navigation sequence completed.");
    }

    // JpetHome feature for Account management scenario 

    @When("user clicks on the account icon and goes to my Account")
    public void user_clicks_on_the_account_icon_and_goes_to_my_account() {
        driver = Hooks.getDriver();
        homePage = new JpetHomePage(driver);
        
        Hooks.getTest().info("Navigating to My Account via profile icon utility link.");
        homePage.accountIcon();
        homePage.myAccount();
        Hooks.getTest().pass("My Account context layout opened.");
    }

    @When("user updates the password {string} and confirmpassword {string}")
    public void user_updates_the_password_and_confirmpassword(String pass, String confirmpass) {
        Hooks.getTest().info("Updating profile credentials.");
        homePage.password(pass);
        homePage.confirmPassword(confirmpass);
        homePage.saveAccountBtn();
        Hooks.getTest().pass("Password values applied and save action dispatched.");
    }

    @Then("user should see the message {string}")
    public void user_should_see_the_message(String verificationtext) {
    	Hooks.getTest().info("Waiting for alert notification panel displaying: " + verificationtext);
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	By alertParagraphLocator = By.xpath("//div[contains(@class, 'alert-info')]/p");
    	try {
    		WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(alertParagraphLocator));
    		String actualText = messageElement.getText().trim();
    		if (actualText.equals(verificationtext)) {
    			Hooks.getTest().pass("Profile update message verified successfully. Banner text matches expectation.");
    			
    		}
    		else 
    		{
    			Hooks.getTest().fail("Profile update verification failed. Expected: [" + verificationtext + "], but got: [" + actualText + "]");
    			}
    		Assert.assertEquals(actualText, verificationtext);
    		}
    	catch (Exception e) 
    	{
    		Hooks.getTest().fail("Account alert validation step threw exception: " + e.getMessage());
    		throw e;
    		}
    	}
    
    
    


    
    }