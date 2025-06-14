package test.ecommerce;

import junit.framework.Assert;
import main_PageObjects.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testComponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTestDataProvider extends BaseTest {

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
    public Object[][] getData(){
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("email","acastromoraga@gmail.com" );
        map.put("passWord","1234");
        map.put("productNameDataPro","ZARA COAT 3");

        HashMap<String,String> map2 = new HashMap<String,String>();
        map2.put("email","alejandro.castromoraga@outlook.com" );
        map2.put("passWord","A12345678!a");
        map2.put("productNameDataPro","ADIDAS ORIGINAL");

        return new Object[][]{{map},{map2}};
    }


}
