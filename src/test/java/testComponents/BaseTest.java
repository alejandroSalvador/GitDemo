package testComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import main_PageObjects.LandingPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;
    public WebDriver initializeDriver() throws IOException {

        //properties clases
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"//src//test//java//resources//GlobalData.properties");
        properties.load(fileInputStream);
        String browserName=  System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");
        //properties.getProperty("browser");
        //comando para ejecutar desde la linea de comaando
        //mvn test -PerrorValidationListeners -Dbrowser=edge


        if (browserName.equalsIgnoreCase("chrome")){

            if (browserName.toLowerCase().contains("chrome")){
                ChromeOptions chromeOptions = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                if (browserName.toLowerCase().contains("headless")){
                    chromeOptions.addArguments("headless");
                }
                driver = new ChromeDriver(chromeOptions);
                // Ajuste adicional de dimensiones (para asegurar compatibilidad)
                if (browserName.toLowerCase().contains("headless")) {
                    driver.manage().window().setSize(new Dimension(1440, 900)); // Redundante pero segura
                } else {
                    driver.manage().window().maximize(); // O usa setSize() para modo con UI
                }
            }


        }

        if (browserName.equalsIgnoreCase("firexfox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        if (browserName.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        return driver;

    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage lunchAplication() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }

    public  List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException{
        //Read json to string
        String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);

        //Stringt to HashMap jackson DataBind
        ObjectMapper mapper = new JsonMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });

        return data;  //

    }

    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);


        File file = new File(System.getProperty("user.dir") + "//reportFrameworks//"+ testCaseName+".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "//reportFrameworks//"+ testCaseName+".png";

    }

}
