package UmaSangada.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import UmaSangada.PageObjects.CartPage;
import UmaSangada.PageObjects.CheckoutPage;
import UmaSangada.PageObjects.ConfirmationPage;
import UmaSangada.PageObjects.OrderPage;
import UmaSangada.PageObjects.ProductCatalog;
import UmaSangada.TestComponents.BaseTest;

public class ModifiedE2e extends BaseTest


{
	//String ProductName = "ZARA COAT 3";
	@Test(dataProvider="getdata",groups={"purchase"})
	public void submitorder(HashMap<String,String> input) throws IOException, InterruptedException {
		//public void submitorder(String mail,String Password,String ProductName) throws IOException, InterruptedException {	
		

		// LandingPage PageObject
		
		ProductCatalog ProductCatalog = landingpage.EnterCredentials(input.get("mail"),input.get("password"));
		// ProductCatalog Page object
		// ProductCatalog collect = new ProductCatalog(driver);
		List<WebElement> products = ProductCatalog.getProductsList();
		ProductCatalog.addProductToCart(input.get("product"));
		CartPage cartpage = ProductCatalog.goToCartPage();
		// CartPage cartpage = new CartPage(driver);
		boolean match = cartpage.verifyProductDisplayed(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.gotocheckout();
		checkoutpage.selectcountry("ind");
		ConfirmationPage confirmationpage = checkoutpage.Submitbutton();
		// methods in cnfirmation page
		String message = confirmationpage.odermessage();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		

	}
	
	@Test(dataProvider="getdata",dependsOnMethods= {"submitorder"})
	public void orderhistory(HashMap<String,String> input) throws InterruptedException
	{
		ProductCatalog ProductCatalog = landingpage.EnterCredentials(input.get("mail"),input.get("password"));
		OrderPage orderpage =ProductCatalog.goToOrdersPage();
		Assert.assertTrue(orderpage.verifyOrderDisplayed(input.get("product")));
	
	}
	
/*	@DataProvider
	public Object[][] getdata()
	{
		return new Object [][]{ {"umasangada98@gmail.com", "T@bby23519","ZARA COAT 3"},{"umasangada98@gmail.com", "T@bby23519","ADIDAS ORIGINAL"}};
	}*/
	
	/*@DataProvider
	public Object [][] getdata()
	
	{
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("mail","umasangada98@gmail.com");
		map.put("password","T@bby23519");
		map.put("product","ZARA COAT 3");
		
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("mail","umasangada98@gmail.com");
		map1.put("password","T@bby23519");
		map1.put("product","ADIDAS ORIGINAL");
		
		
		
		return new Object  [][] {{map},{map1}};
	}*/
	
	@DataProvider
	public Object [][] getdata() throws IOException
	
	{
		List<HashMap<String, String>>  data= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\UmaSangada\\data\\Purchaseorder.json");

		return new Object  [][] {{data.get(0)},{data.get(1)}};
	}
	
	//System.getProperty("user.dir")+"\\src\\test\\java\\UmaSangada\\data\\Purchaseorder.json"
	
}
