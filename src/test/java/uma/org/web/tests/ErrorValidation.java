package uma.org.web.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.uma.web.base.Web;
import org.uma.web.pages.ProductCatalog;

import org.uma.web.listners.*;

public class ErrorValidation extends Web
{
	@Test(groups= {"ErrorHandling"},retryAnalyzer=retry.class)
	public void loginErrorValidation() 
	{
		ProductCatalog ProductCatalog = landingpage.EnterCredentials("umasangada@gmail.com", "T@bby23519");
		landingpage.errormessage();
		Assert.assertEquals("Incorrect email o password.", landingpage.errormessage());
		
	}
}
