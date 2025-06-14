package extendReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import main_PageObjects.LandingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class ExtendReportTest {
    ExtentReports extentReporter;

    @BeforeTest
    public void config(){
        /*
        //ExtentReport, ExtentSparkReport
        String path = System.getProperty(("user.dir") + "/src/test/java/extendReport/reporte.html");
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Resultados de automatización");
        reporter.config().setDocumentTitle("Resultado Ejecución");

        extentReporter = new ExtentReports();
        extentReporter.attachReporter(reporter);
        extentReporter.setSystemInfo("Tester", "Alejandro Castro");

         */
        // Ruta donde se guardará el reporte (dentro de extendReport)
        extentReporter = new ExtentReports();
        String reportDir = System.getProperty("user.dir") + "/src/test/java/extendReport";
        String reportPath = reportDir + "/extent-report.html";

        // Asegúrate de que el directorio exista
        File dir = new File(reportDir);
        if (!dir.exists()) {
            dir.mkdirs(); // Crea el directorio si no existe
        }

        // Configura el ExtentSparkReporter
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Resultados de automatización");
        reporter.config().setDocumentTitle("Resultado Ejecución");


        // Inicializa ExtentReports y adjunta el reporter
        extentReporter = new ExtentReports();
        extentReporter.attachReporter(reporter);
        extentReporter.setSystemInfo("Tester", "Alejandro");
    }

    @Test
    public void innitialDemo(){

        ExtentTest test = extentReporter.createTest("Inicial Demo");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com");
        String titulo = driver.getTitle();
        System.out.println(titulo);
        driver.close();
        test.fail("Error en la ejecución");
        extentReporter.flush();
    }

}
