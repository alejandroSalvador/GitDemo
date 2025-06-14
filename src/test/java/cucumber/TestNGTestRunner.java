package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber", glue = "stepDefinitions", monochrome = true,
        plugin = {"html:src/test/java/cucumberReport/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {


}
