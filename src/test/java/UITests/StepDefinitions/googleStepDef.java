package UITests.StepDefinitions;

import io.cucumber.java.en.And;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class googleStepDef {

    private static final Logger logger = LogManager.getLogger(googleStepDef.class);
    WebDriver driver = null;

    @Given("initialized browser lands on {string}")
    public void initialized_browser_lands_on(String url) {
        System.setProperty("webdriver.chrome.driver", "/Users/tapeshnagarwal/Documents/Projects/Spiral/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.navigate().to(url);
        logger.info("Current URL: " + driver.getCurrentUrl());
    }

    @When("automation inputs {string} into the search field")
    public void automation_inputs_into_the_search_field(String string) {
        WebElement searchBar = driver.findElement(By.xpath("//*[@id='APjFqb']"));
        searchBar.sendKeys(string);
        searchBar.sendKeys(Keys.RETURN);
        logger.info("Test");
    }

    @Then("automation validates results are related to {string}")
    public void automation_validates_results_are_related_to(String string) {
        Assert.assertTrue(driver.getTitle().contains(string));
        List<WebElement> resultLinks;
        List<String> validResultLinks = new ArrayList<>();
        resultLinks = driver.findElements(By.xpath("//*[@id='rso']/div[@class='MjjYud']//a"));
        for (WebElement link : resultLinks) {
            if (link.getText().contains(string))
                validResultLinks.add(link.getText());
        }
        logger.info("Valid Results related to " + string + "are as following: ");
        for (String result : validResultLinks) {
            logger.info(result);
        }
    }

    @And("browser shuts down")
    public void browser_shuts_down() {
        driver.close();
    }

}
