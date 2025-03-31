package uma.org.web.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.uma.web.base.Web;
import org.uma.web.pages.ProductCatalog;

import org.uma.web.listners.retry; // Corrected import

public class ErrorValidation extends Web {

    @Test(groups = { "ErrorHandling" }, retryAnalyzer = retry.class)
    public void loginErrorValidation() {
        landingpage.enterCredentials("umasangada@gmail.com", "T@bby23519"); // Removed ProductCatalog assignment
        String errorMessage = landingpage.errorMessage();
        Assert.assertEquals("Incorrect email or password.", errorMessage);
    }
}