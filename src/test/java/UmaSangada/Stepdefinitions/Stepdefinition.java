package UmaSangada.Stepdefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import UmaSangada.PageObjects.CartPage;
import UmaSangada.PageObjects.CheckoutPage;
import UmaSangada.PageObjects.ConfirmationPage;
import UmaSangada.PageObjects.LandingPage;
import UmaSangada.PageObjects.ProductCatalog;
import UmaSangada.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Stepdefinition extends BaseTest {
	public LandingPage landingpage;
	/*public ProductCatalog productCatalog;
	ConfirmationPage confirmationpage;*/

	@Given("I landed on ecommerce page")
	public void I_landed_on_ecommerce_page() throws IOException {
		landingpage = LaunchApplication();
	}

	/*@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String name, String Password) {

		productCatalog = landingpage.EnterCredentials(name,Password);
	}

	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String Protectname) throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		List<WebElement> products = productCatalog.getProductsList();
		productCatalog.addProductToCart(Protectname);
	}

	@And("^Checkout (.+) and submit the order$")
	public void check_out_adidas_original_and_submit_the_order(String Protectname) throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions

		CartPage cartpage = productCatalog.goToCartPage();
		// CartPage cartpage = new CartPage(driver);
		boolean match = cartpage.verifyProductDisplayed(Protectname);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.gotocheckout();
		checkoutpage.selectcountry("ind");
		confirmationpage = checkoutpage.Submitbutton();
	}

	@Then("{string} messages displayed on confirmation page")
	public void messages_displayed_on_confirmation_page(String a) {
		// Write code here that turns the phrase above into concrete actions
		String message = confirmationpage.odermessage();
		Assert.assertTrue(message.equalsIgnoreCase(a));
		driver.close();
	}*/

}
