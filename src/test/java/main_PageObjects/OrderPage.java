package main_PageObjects;

import abstractComponents.AbastractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbastractComponents {
    WebDriver driver;

    @FindBy(css = ".totalRow button")
    WebElement checkoutEle;

    @FindBy(xpath = "//tr/td[2]")
    private List<WebElement> productNameOrder;


    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean verifyOrderDisplay(String productName){
        Boolean match = productNameOrder.stream().anyMatch(myCart-> myCart.getText().equalsIgnoreCase(productName));
        return match;
    }


}
