package UITests.StepDefinitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features/google.feature",
        glue={"UITests/StepDefinitions"},
        plugin = {"pretty", "html:target/Reports/HtmlReports.html"})
public class TestRunner {

}
