package extendReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReportFunction {


    public  static ExtentReports getReportObject(){
        ExtentReports  extentReporter = new ExtentReports();
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

        return extentReporter;
    }
}
