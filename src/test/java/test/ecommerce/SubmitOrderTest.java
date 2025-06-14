package test.ecommerce;

import junit.framework.Assert;
import main_PageObjects.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import testComponents.BaseTest;
import java.io.IOException;
import java.util.List;
import org.testng.Assert.*;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";
    @Test
    public void submitOrder() throws IOException {

        String country = "India";
        ProductCatalogue productCatalogue = landingPage.loginAplication("acastromoraga@gmail.com","1234");
        List<WebElement> productos = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage =   productCatalogue.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(productName);
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
        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));

    }

    @DataProvider
    public Object[][] getData(){
        return new Object[][]{{"acastromoraga@gmail.com","1234","ZARA COAT 3"},{"alejandro.castromoraga@outlook.com","A12345678!a","ADIDAS ORIGINAL"}};
    }


}
