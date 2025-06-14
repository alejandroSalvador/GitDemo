package test.ecommerce;

import junit.framework.Assert;
import main_PageObjects.LandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StandAloneTest01 {
    public static void main(String[] args) throws InterruptedException {
        String productName = "ZARA COAT 3";
        String country = "India";
        WebDriver driver = new ChromeDriver();
        LandingPage landingPage = new LandingPage(driver);


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/client/");




        String titulo = driver.getTitle();
        String url =  driver.getCurrentUrl();
        System.out.println(titulo);


        //lOGIN
        WebElement email = driver.findElement(By.id("userEmail"));
        email.sendKeys("acastromoraga@gmail.com");
        WebElement passWord = driver.findElement(By.id("userPassword"));
        passWord.sendKeys("1234");
        driver.findElement(By.id("login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
            List<WebElement> productos = driver.findElements(By.cssSelector(".mb-3"));

        WebElement pro = productos.stream().filter(p->
                p.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
                pro.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        List<WebElement> myCarts = driver.findElements(By.cssSelector(".cartSection h3"));
        int countMyCart = myCarts.size();
        System.out.println(countMyCart);

       boolean match =  myCarts.stream().anyMatch(myCart-> myCart.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);

        driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions a = new Actions(driver);

        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india")
                .build().perform();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".list-group button"))));

       List<WebElement> counstries = driver.findElements(By.cssSelector(".list-group button"));

        for (int i = 0; i < counstries.size(); i++) {
            if(counstries.get(i).getText().equalsIgnoreCase(country)){
                counstries.get(i).click();
                break;
            }

        }

        driver.findElement(By.cssSelector(".action__submit")).click();
        WebElement message = driver.findElement(By.cssSelector("[class='hero-primary']"));
        String msg = message.getText();

        Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));










    }
}
