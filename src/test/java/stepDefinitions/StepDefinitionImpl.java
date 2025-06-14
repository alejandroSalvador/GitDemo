package stepDefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import main_PageObjects.*;
import org.openqa.selenium.WebElement;
import testComponents.BaseTest;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public  CartPage cartPage;
    ConfirmationPage confirmationPage;
    String country = "India";

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        landingPage =  lunchAplication();

    }

    @Given("^Logged in with usarName (.+) and Password (.+)$")
    public void Logged_in_with_usarName_and_Password (String userName, String passWord){
        productCatalogue = landingPage.loginAplication(userName,passWord);
    }

    @When("^I add product (.+) to Cart$")
    public void I_add_product_to_Cart(String productName){
        List<WebElement> productos = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }

    @And("^Checkout (.+) and submit the order$")
    public void Checkout_and_submit_the_order(String productName){
        cartPage =   productCatalogue.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectOriginCountry(country);
        confirmationPage = checkoutPage.submitOrder();
    }


    @Then("{string} message is displayed on confirmationPage")
    public void message_is_displayed_on_confirmationPage(String string){
        String msg = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(msg.equalsIgnoreCase(string));
        driver.close();
    }

    @Then("^\"([^\"]*)\" message is displayed$")
    public void some_message_is_displayed(String string){
        Assert.assertEquals(string, landingPage.getErrorMessage());
        driver.close();
    }



}
