package main_PageObjects;

import abstractComponents.AbastractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbastractComponents {
    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[class='hero-primary']")
    WebElement confirmationMessage;

    public String getConfirmationMessage(){
         return confirmationMessage.getText();
    }

}
