package UITests.StepDefinitions;

import io.cucumber.java.en.And;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class googleStepDef {

    private static final Logger logger = LogManager.getLogger(googleStepDef.class);
    WebDriver driver = null;

    String parentWindow;

    @Given("I am on {string}")
    public void initialized_browser_lands_on(String url) {
        System.setProperty("webdriver.chrome.driver", "/Users/tapeshnagarwal/Documents/Projects/Spiral/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(2));
        driver.navigate().to(url);
        logger.info("Current URL: " + driver.getCurrentUrl());
    }

    @When("I search for {string}")
    public void automation_inputs_into_the_search_field(String string) {
        WebElement searchBar = driver.findElement(By.xpath("//*[@id='APjFqb']"));
        searchBar.sendKeys(string);
        searchBar.sendKeys(Keys.RETURN);
        parentWindow = driver.getWindowHandle();
        logger.info("Test");
    }

    @Then("I should see results related to {string}")
    public void automation_validates_results_are_related_to(String string) {


//        Assert Title of Google Tab contains the inputted search value
        Assert.assertTrue(driver.getTitle().contains(string));

//        create two lists to collect all results and one to be used later to present valid ones
        List<WebElement> resultLinks;
        List<String> validResultLinks = new ArrayList<>();

//        This is the xpath for all the result divs
        String xpath = "//*[@id='rso']//div[@jscontroller='SC7lYd']//./a";
        resultLinks = driver.findElements(By.xpath(xpath));
        for (WebElement link : resultLinks) {
            String url = link.getAttribute("href").toString();
            WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);
            String newWindow = newTab.getWindowHandle();
            newTab.navigate().to(url);
            int occurrence = StringUtils.countMatches(newTab.getPageSource(),string);
            if(occurrence >= 5) {
                validResultLinks.add(url);
            }
            newTab.close();
            driver.switchTo().window(parentWindow);
        }
//            if (link.getText().contains(string))
//                validResultLinks.add(link.getText());
//        }
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
