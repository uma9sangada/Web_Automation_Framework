package uma.org.web.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.uma.web.base.Web;
import org.uma.web.pageobjects.CartPage;
import org.uma.web.pageobjects.CheckoutPage;
import org.uma.web.pageobjects.ConfirmationPage;
import org.uma.web.pageobjects.OrderPage;
import org.uma.web.pageobjects.ProductCatalog;

public class E2eFowTest extends Web {

    private ProductCatalog productCatalog;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private ConfirmationPage confirmationPage;
    private OrderPage orderPage;

    public E2eFowTest() {
        this.productCatalog = new ProductCatalog();
        this.cartPage = new CartPage();
        this.checkoutPage = new CheckoutPage();
        this.confirmationPage = new ConfirmationPage();
        this.orderPage = new OrderPage();
    }

    @Test(dataProvider = "getdata", groups = { "purchase" })
    public void submitOrder(String mail, String password, String product) throws IOException, InterruptedException {

        landingpage.enterCredentials(mail, password);

        List<WebElement> products = productCatalog.getProductsList();
        productCatalog.addProductToCart(product);

        boolean match = cartPage.verifyProductDisplayed(product);
        Assert.assertTrue(match);

        checkoutPage.selectCountry("ind");

        String message = confirmationPage.orderMessage();
        Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dataProvider = "getdata", dependsOnMethods = { "submitOrder" })
    public void orderHistory(String mail, String password, String product) throws InterruptedException {
        landingpage.enterCredentials(mail, password);
        Assert.assertTrue(orderPage.verifyOrderDisplayed(product));
    }

    @DataProvider
    public Object[][] getdata() throws IOException {
        List<List<String>> data = getJsonStringData(
                System.getProperty("user.dir") + "\\src\\test\\java\\UmaSangada\\data\\Purchaseorder.json");

        Object[][] testData = new Object[data.size()][3];
        for (int i = 0; i < data.size(); i++) {
            List<String> row = data.get(i);
            testData[i][0] = row.get(0);
            testData[i][1] = row.get(1);
            testData[i][2] = row.get(2);
        }
        return testData;
    }

    private List<List<String>> getJsonStringData(String filePath) throws IOException {
        List<List<String>> result = new java.util.ArrayList<>();
        List<java.util.HashMap<String, String>> maps = getJsonDataToMap(filePath);

        for (java.util.HashMap<String, String> map : maps) {
            List<String> row = new java.util.ArrayList<>();
            row.add(map.get("mail"));
            row.add(map.get("password"));
            row.add(map.get("product"));
            result.add(row);
        }
        return result;
    }
}