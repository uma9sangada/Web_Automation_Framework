package UmaSangada.Tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import UmaSangada.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class E2Eproject {
	public static void main(String[] args) {
		String Itenname = "ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("umasangada98@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("T@bby23519");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(Itenname))
				.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();// regular expression
		List<WebElement> productname = driver.findElements(By.cssSelector(".cartWrap .cartSection  h3"));
		Boolean match = productname.stream().anyMatch(pname -> pname.getText().equalsIgnoreCase(Itenname));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".subtotal  .btn")).click();
		Actions my = new Actions(driver);
		// my.moveToElement(driver.findElement(By.xpath("//input[@placeholder='Select
		// Country']"))).click().sendKeys("india");
		my.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "ind").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results ")));
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String name = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(name.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.quit();

	}
}
