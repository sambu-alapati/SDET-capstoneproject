package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JpetHomePage {
	
	WebDriver driver;
	
	public JpetHomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//search bar and search button 
	By searchbar=By.xpath("//form[contains(@action, 'searchProducts')]//input[@name='keyword']");
	By searchBtn=By.xpath("//button[@id='jpetstore-search-btn']");
	
	
	// Account management for changing password 
	By accountIcon=By.xpath("//a[@title='My Account']");
	By myAccount=By.linkText("My Account");
	By password = By.name("password");
	By confirmPassword = By.name("repeatedPassword");
	By saveAccountBtn = By.xpath("//button[contains(text(),'Save Account Information')]");
	
	
	
	
	//image Navigation
	By returntomain=By.linkText("Return to Main Menu");
	By birdimage = By.xpath("//map[@name='estoremap']/area[@alt='Birds']");
	By catimage=By.xpath("//map[@name='estoremap']/area[@alt='Cats']");
	By dogimage=By.xpath("//map[@name='estoremap']/area[@alt='Dogs']");
	By reptileimage=By.xpath("//map[@name='estoremap']/area[@alt='Reptiles']");
	By fishimage=By.xpath("//map[@name='estoremap']/area[@alt='Fish']");
	
	
	
	//navbar elements 
	By birdnavelement=By.linkText("Birds");
	By catnavelement=By.linkText("Cats");
	By fishavelement=By.linkText("Fish");
	By reptilenavelement=By.linkText("Reptiles");
	By dognavelement=By.linkText("Dogs");
	By cartnavelement=By.xpath("//a[@href='/cart/viewCart']");
	By myordersnavelement=By.partialLinkText("My Orders");
	
	
	
	// Reusable private helper method to execute Javascript clicks safely
    private void jsClick(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
    
    
	
	public void searchbar(String text) {
		driver.findElement(searchbar).sendKeys(text);
	}
	
	public void searchBtn() {
		driver.findElement(searchBtn).click();
	}
	
	
	public void accountIcon() {
		driver.findElement(accountIcon).click();
	}
	
	public void myAccount() {
		driver.findElement(myAccount).click();
	}
	
	public void password(String pass) {
		driver.findElement(password).sendKeys(pass);
	}
	
	
	public void confirmPassword(String confirmpass) {
		driver.findElement(confirmPassword).sendKeys(confirmpass);
	}
	
	
	public void saveAccountBtn() {
		Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(saveAccountBtn)).click().perform();
	}
	
	
	
	// Updated methods to use Javascript execution
	public void birdimage() {
		jsClick(birdimage);
	}
	
    public void fishimage() {
        jsClick(fishimage);
    }
    
    public void dogimage() {
        jsClick(dogimage);
    }
    
    public void reptileimage() {
        jsClick(reptileimage);
    }
    
    public void catimage() {
        jsClick(catimage);
    }
	
	
	public void returntomainmenu() {
		driver.findElement(returntomain).click();
	}
	
	
	
	
	
	
	public void searchbarusingkeys() {
		  driver.findElement(searchbar).sendKeys(Keys.ENTER);
	}
	
	
	
	public void birdnavelement() {
		driver.findElement(birdnavelement).click();
	}
	
	
	public void catnavelement() {
		driver.findElement(catnavelement).click();
	}
	
	
	public void dognavelement() {
		driver.findElement(dognavelement).click();
	}
	
	
	public void reptilenavelement() {
		driver.findElement(reptilenavelement).click();
	}
	
	
	public void fishnavelement() {
		driver.findElement(fishavelement).click();
	}
	
	
	public void cartnavelement() {
		driver.findElement(cartnavelement).click();
	}
	
	public void myordersnavelement() {
		driver.findElement(myordersnavelement).click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@FindBy(name = "keyword")
    private WebElement searchBox;
 
    @FindBy(id = "jpetstore-search-btn")
    private WebElement searchButton;
 
    @FindBy(xpath = "//button[contains(@class,'dropdown-toggle')]")
    private WebElement themeDropdown;
 
    @FindBy(xpath = "//a[contains(text(),'Light')]")
    private WebElement lightTheme;
 
    @FindBy(xpath = "//a[contains(text(),'Dark')]")
    private WebElement darkTheme;
 
    @FindBy(xpath = "//a[contains(text(),'Auto')]")
    private WebElement autoTheme;
 
    @FindBy(xpath = "//a[contains(@href,'cart')]")
    private WebElement cartIcon;
 
//    public void selectCategory(String category) {
//
//        driver.findElement(
//                By.linkText(category))
//                .click();
//    }
    public void selectCategory(String category) {
 
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
 
        driver.findElement(
                By.linkText(category))
                .click();
    }
 
    public void searchProduct(String product) {
 
        searchBox.clear();
        searchBox.sendKeys(product);
        searchButton.click();
    }
 
    public void selectTheme(String theme) {
 
        themeDropdown.click();
 
        switch (theme.toLowerCase()) {
 
        case "light":
            lightTheme.click();
            break;
 
        case "dark":
            darkTheme.click();
            break;
 
        case "auto":
            autoTheme.click();
            break;
 
        default:
            throw new IllegalArgumentException(
                    "Invalid Theme : " + theme);
        }
    }
 
    public void openCart() {
 
        cartIcon.click();
    }
	
	
	

}
