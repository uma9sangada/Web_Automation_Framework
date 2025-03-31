package UmaSangada.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import UmaSangada.PageObjects.CartPage;
import UmaSangada.PageObjects.OrderPage;

public class AbstractComponents 
{
	WebDriver driver;
	public AbstractComponents(WebDriver driver) {
	this.driver = driver;
	}

	@FindBy(css="[routerlink*='myorders']")
	WebElement myorders;
	
	
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	public  OrderPage goToOrdersPage() throws InterruptedException
	{
		
		driver.findElement(By.cssSelector("[routerlink*='myorders']")).click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
		
		
	}
	
	public  CartPage goToCartPage()
	{
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		CartPage x = new CartPage(driver);
		return x;
	}
	public  void waitForElementToAppear(By findby)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	
	}
	public  void waitForElementToAppear(WebElement findby)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(findby));
	
	}
	
	public  void waitForElementToDisappear() throws InterruptedException
	{
	Thread.sleep(1000);
	}

}
