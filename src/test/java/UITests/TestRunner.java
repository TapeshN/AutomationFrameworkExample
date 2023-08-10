package UITests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features/",
        glue={"UITests/StepDefinitions"},
        plugin = {"pretty", "html:target/Reports/HtmlReports.html"})
public class TestRunner {

}
