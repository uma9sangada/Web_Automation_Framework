package UmaSangada.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import UmaSangada.AbstractComponents.AbstractComponents;

/**
 * Hello world!
 *
 */
public class CartPage extends AbstractComponents
{
    
	WebDriver driver ;
	public CartPage(WebDriver driver)//by default default constructor is called    
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	
	}
	
	@FindBy(css = ".cartWrap .cartSection  h3")
	List<WebElement> ProductTitles;
	
	@FindBy(css = ".subtotal  .btn")
	WebElement checkout;
	
	public  CheckoutPage gotocheckout()
	{
		checkout.click();
		CheckoutPage checkoutpage= new CheckoutPage(driver);
		return checkoutpage;
		
	}
	By textlabels=By.cssSelector(".cartWrap .cartSection  h3");
	
	
	/*public List<WebElement> getProductTitles() 
	{
		waitForElementToAppear(textlabels);
		List<WebElement> ProductTitles = driver.findElements(By.cssSelector(".cartWrap .cartSection  h3"));
		return ProductTitles;
	}*/
	
	public Boolean verifyProductDisplayed(String ProductName)
	{
	Boolean match = ProductTitles.stream().anyMatch(pname -> pname.getText().equals(ProductName));
	return match;
	}
	
	
}
