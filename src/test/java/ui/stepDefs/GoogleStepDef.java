package ui.stepDefs;

import auto.framework.pages.GoogleHomePage;
import auto.framework.pages.GoogleResultPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class GoogleStepDef {

    private static final Logger logger = LogManager.getLogger(GoogleStepDef.class);
    private WebDriver driver;

    private GoogleHomePage googleHomePage;
    private GoogleResultPage googleResultPage;

    @Before
    public void setup() {
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I am on {string}")
    public void initialized_browser_lands_on(String url) {
        driver.navigate().to(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(2));
        googleHomePage = new GoogleHomePage(driver);
        logger.info("Current URL: " + driver.getCurrentUrl());
    }

    @When("I search for {string}")
    public void automation_inputs_into_the_search_field(String string) {
        googleHomePage.search(string);
    }

    @Then("I should see results related to {string}")
    public void automation_validates_results_are_related_to(String searchSubject) {
        googleResultPage = new GoogleResultPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//        Assert Title of Google Tab contains the inputted search value
        Assert.assertTrue(driver.getTitle().contains(searchSubject));
        List<String> relatedResultUrls = googleResultPage.extractRelevantResults(
                googleResultPage.collectAllResultLinks(), searchSubject);
        logger.info("Valid URLs related to " + searchSubject + "are as following: ");
        for (String url : relatedResultUrls) {
            logger.info(url);
        }
    }

    @And("browser shuts down")
    public void browser_shuts_down() {
        driver.close();
    }

}
