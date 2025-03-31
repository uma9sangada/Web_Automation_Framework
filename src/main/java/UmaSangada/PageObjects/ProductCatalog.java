package UmaSangada.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import UmaSangada.AbstractComponents.AbstractComponents;

/**
 * Hello world!
 *
 */
public class ProductCatalog extends AbstractComponents {
	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;


	
	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By productlocation = By.cssSelector(".mb-3");
	By toastmessage = By.id("toast-container");
	By Addtocart = By.cssSelector(".card-body button:last-of-type");
	// By animation = By.cssSelector(".ng-animating");

	public List<WebElement> getProductsList() 
	{
		waitForElementToAppear(productlocation);
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		return products;
	}

	public WebElement getProductByName(String ProductName) 
	{
		WebElement prod = getProductsList() .stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addProductToCart(String ProductName) throws InterruptedException 
	{
		WebElement prod = getProductByName(ProductName);
		prod.findElement(Addtocart).click();
		waitForElementToAppear(toastmessage);
		waitForElementToDisappear();
		
	}
}
