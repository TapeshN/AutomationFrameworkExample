package StepDefinitions;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;


public class googleStepDef {

    private static final Logger logger = LogManager.getLogger(googleStepDef.class);

    @Given("initialized browser instance")
    public void initialized_browser_instance() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("browser directs to {string}")
    public void browser_directs_to(String string) {
        // Write code here that turns the phrase above into concrete actions
        logger.info("Test");
//        throw new io.cucumber.java.PendingException();
    }

    @Given("initialized browser lands on {string}")
    public void initialized_browser_lands_on(String string) {
        // Write code here that turns the phrase above into concrete actions
        logger.info("Test");
//        throw new io.cucumber.java.PendingException();
    }
    @When("automation inputs {string} into the search field")
    public void automation_inputs_into_the_search_field(String string) {
        // Write code here that turns the phrase above into concrete actions
        logger.info("Test");
//        throw new io.cucumber.java.PendingException();
    }
    @Then("automation validates results are related to {string}")
    public void automation_validates_results_are_related_to(String string) {
        // Write code here that turns the phrase above into concrete actions
        logger.info("Test");
//        throw new io.cucumber.java.PendingException();
    }


}
