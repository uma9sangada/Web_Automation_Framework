package UmaSangada.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import UmaSangada.PageObjects.ProductCatalog;
import UmaSangada.TestComponents.BaseTest;
import UmaSangada.TestComponents.retry;

public class ErrorValidation extends BaseTest
{
	@Test(groups= {"ErrorHandling"},retryAnalyzer=retry.class)
	public void loginErrorValidation() 
	{
		ProductCatalog ProductCatalog = landingpage.EnterCredentials("umasangada@gmail.com", "T@bby23519");
		landingpage.errormessage();
		Assert.assertEquals("Incorrect email o password.", landingpage.errormessage());
		
	}
}
