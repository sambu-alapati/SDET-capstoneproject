package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class JpetRegisterPage {
	
	WebDriver driver;
	
	public JpetRegisterPage(WebDriver driver) {
		this.driver=driver;
	}
	
	By signUpBtn = By.linkText("Sign Up");
	 
    By userId = By.name("username");
    By password = By.name("password");
    By confirmPassword = By.name("repeatedPassword");
 
    By firstName = By.name("firstName");
    By lastName = By.name("lastName");
    By email = By.name("email");
    By phone = By.name("phone");
 
    By address1 = By.name("address1");
    By address2 = By.name("address2");
    By city = By.name("city");
    By state = By.name("state");
    By zip = By.name("zip");
    By country = By.name("country");
 
    By languagePreference = By.name("languagePreference");
    By favouriteCategory = By.name("favouriteCategoryId");
 
    By saveAccountBtn = By.xpath("//button[contains(text(),'Save Account Information')]");
 
    public void clickSignUp() {
        driver.findElement(signUpBtn).click();
    }
 
    public void enterUserId(String value) {
        driver.findElement(userId).sendKeys(value);
    }
 
    public void enterPassword(String value) {
        driver.findElement(password).sendKeys(value);
    }
 
    public void enterConfirmPassword(String value) {
        driver.findElement(confirmPassword).sendKeys(value);
    }
 
    public void enterFirstName(String value) {
        driver.findElement(firstName).sendKeys(value);
    }
 
    public void enterLastName(String value) {
        driver.findElement(lastName).sendKeys(value);
    }
 
    public void enterEmail(String value) {
        driver.findElement(email).sendKeys(value);
    }
 
    public void enterPhone(String value) {
        driver.findElement(phone).sendKeys(value);
    }
 
    public void enterAddress1(String value) {
        driver.findElement(address1).sendKeys(value);
    }
 
    public void enterAddress2(String value) {
        driver.findElement(address2).sendKeys(value);
    }
 
    public void enterCity(String value) {
        driver.findElement(city).sendKeys(value);
    }
 
    public void enterState(String value) {
        driver.findElement(state).sendKeys(value);
    }
 
    public void enterZip(String value) {
        driver.findElement(zip).sendKeys(value);
    }
 
    public void enterCountry(String value) {
        driver.findElement(country).sendKeys(value);
    }
 
    public void selectLanguage(String value) {
        new Select(driver.findElement(languagePreference))
                .selectByValue(value);
    }
 
    public void selectCategory(String value) {
        new Select(driver.findElement(favouriteCategory))
                .selectByValue(value);
    }
 
    public void clickSaveAccountInformation() {
        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(saveAccountBtn)).click().perform();
    }
 
    public boolean isRegistrationSuccessful() {
        return driver.getPageSource().contains("Welcome");
    }
}
