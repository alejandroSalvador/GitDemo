package test.ecommerce;

import junit.framework.Assert;

import main_PageObjects.CartPage;
import main_PageObjects.CheckoutPage;
import main_PageObjects.ConfirmationPage;
import main_PageObjects.ProductCatalogue;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import testComponents.BaseTest;
import testComponents.Retry;

import java.io.IOException;
import java.util.List;


public class ErrorValidation extends BaseTest {

    @Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
    public void loginError() throws IOException {
        String productName = "ZARA COAT 3";
        String country = "India";
        landingPage.loginAplication("acastromoraga@gmail.com","1234333");
        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
        //SubmitOrderTest
    }

    @Test(groups = "ErrorHandling")
    public void productErrorValidaction(){
        String productName = "ZARA COAT 3";
        String country = "India";
        ProductCatalogue productCatalogue = landingPage.loginAplication("acastromoraga@gmail.com","1234");
        List<WebElement> productos = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage =   productCatalogue.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);

    }


}
