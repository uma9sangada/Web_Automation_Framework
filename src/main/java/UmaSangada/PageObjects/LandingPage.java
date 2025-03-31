package UmaSangada.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UmaSangada.AbstractComponents.AbstractComponents;

/**
 * Hello world!
 *
 */
public class LandingPage extends AbstractComponents
{
    
	WebDriver driver ;
	public LandingPage(WebDriver driver)//by default default constructor is called    
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	
	}
	//creating page objects for login and screen
	//WebElement a= driver.findElement(By.id("userEmail"));
	@FindBy(id="userEmail")
	WebElement userEmail;
	@FindBy(id="userPassword")
	WebElement userPassword;
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement InvalidData;
	//.ng-tns-c4-2.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	//action methods for page factory web elements
	public ProductCatalog EnterCredentials(String mail,String password)
	{
		userEmail.sendKeys(mail);
		userPassword.sendKeys(password);
		login.click();
		ProductCatalog ProductCatalog = new ProductCatalog(driver);
		return ProductCatalog ;
	}
	
	public String errormessage()
	{
		waitForElementToAppear(InvalidData);
		
		return InvalidData.getText();
	}
	
	public void NavigateToWebsiteURL()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
	
}
