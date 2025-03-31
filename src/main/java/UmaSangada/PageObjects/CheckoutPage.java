package UmaSangada.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import UmaSangada.AbstractComponents.AbstractComponents;

/**
 * Hello world!
 *
 */
public class CheckoutPage extends AbstractComponents {

	WebDriver driver;

	public CheckoutPage(WebDriver driver)// by default default constructor is called
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	WebElement selectcountry;

	@FindBy(css = ".action__submit")
	WebElement submitbutton;

	;

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;
	By popdown = By.cssSelector(".ta-results ");

	public void selectcountry(String val) 
	{
		Actions my = new Actions(driver);
		my.sendKeys(country, val).build().perform();
		waitForElementToAppear(popdown);
		selectcountry.click();
	}

	public ConfirmationPage Submitbutton() 
	{
		submitbutton.click();
		ConfirmationPage confirmationpage = new ConfirmationPage(driver);
		
		return confirmationpage;
	}

}
