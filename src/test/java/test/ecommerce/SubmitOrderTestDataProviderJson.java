package test.ecommerce;

import junit.framework.Assert;
import main_PageObjects.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testComponents.BaseTest;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTestDataProviderJson extends BaseTest {

    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"purchase"})
    public void submitOrder(HashMap<String, String> input) throws IOException {

        String country = "India";
        ProductCatalogue productCatalogue = landingPage.loginAplication(input.get("email"), input.get("passWord"));
        List<WebElement> productos = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("productNameDataPro"));
        CartPage cartPage =   productCatalogue.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(input.get("productNameDataPro"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectOriginCountry(country);
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String msg = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = "submitOrder")
    public void orderHistory(){
        ProductCatalogue productCatalogue = landingPage.loginAplication("acastromoraga@gmail.com","1234");
        OrderPage orderPage = productCatalogue.goToOrderPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay("ZARA COAT 3"));

    }

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String,String>> data = (List<HashMap<String, String>>) getJsonDataToMap(System.getProperty("user.dir")
                        + "//src//test//java//dataPrivider//purchaseOrder.json");

        return new Object[][]{{data.get(0)},{data.get(1)}};
    }

}
