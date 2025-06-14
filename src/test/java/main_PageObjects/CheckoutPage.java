package main_PageObjects;

import abstractComponents.AbastractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CheckoutPage extends AbastractComponents {

    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy (css = ".action__submit")
    WebElement submit;

    @FindBy(css = "[placeholder='Select Country']")
    WebElement campocountry;

    @FindBy(css = ".list-group button")
    List<WebElement> counstries;

    public void selectOriginCountry(String country){

        Actions a = new Actions(driver);
        a.sendKeys(campocountry,country).build().perform();
        //waitForElementToDisappear((WebElement) By.cssSelector(".list-group button"));

        for (int i = 0; i < counstries.size(); i++) {
            if(counstries.get(i).getText().equalsIgnoreCase(country)){
                counstries.get(i).click();
                break;
            }

        }

    }
    public ConfirmationPage submitOrder(){
        submit.click();
        return new ConfirmationPage(driver);
    }




}
