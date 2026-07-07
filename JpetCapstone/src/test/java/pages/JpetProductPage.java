package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JpetProductPage {
	
	
	
	WebDriver driver;
	 
    public JpetProductPage(WebDriver driver) {
 
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
 
    @FindBy(xpath = "//table//tbody//tr//td[1]//a")
    private List<WebElement> productLinks;
 
    @FindBy(xpath = "//a[contains(text(),'Add to Cart')]")
    private List<WebElement> addToCartButtons;
 
    public void selectFirstProduct() {
 
        productLinks.get(0).click();
    }
 
    public void selectProductByIndex(int index) {
 
        productLinks.get(index).click();
    }
 
    public int getProductCount() {
 
        return productLinks.size();
    }
 
    public void addFirstItemToCart() {
 
        addToCartButtons.get(0).click();
    }
 
    public void addItemToCartByIndex(int index) {
 
        addToCartButtons.get(index).click();
    }
 
    public int getAvailableItemCount() {
 
        return addToCartButtons.size();
    }

}
