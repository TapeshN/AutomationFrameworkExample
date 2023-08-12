package auto.framework.pages;

import org.apache.commons.lang3.StringUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class GoogleResultPage {

    private WebDriver driver;

    private By linksLocator = By.xpath("//*[@id='rso']//div[@jscontroller='SC7lYd']//./a");

    public GoogleResultPage(WebDriver driver) {
        this.driver = driver;
    }


    public List<String> collectAllResultLinks() {
        List<String> resultUrls = new ArrayList<>();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(2));
        List<WebElement> resultElements = driver.findElements(linksLocator);
        for (WebElement result : resultElements) {
            resultUrls.add(result.getAttribute("href").toString());
        }
        return resultUrls;
    }

    public List<String> extractRelevantResults(List<String> urlList, String searchSubject) {
        List<String> validResultLinks = new ArrayList<>();
        for (String url : urlList) {
            driver.navigate().to(url);
            int occurrence = StringUtils.countMatches(driver.getPageSource(), searchSubject);
            if (occurrence >= 5) {
                validResultLinks.add(url);
            }
        }
        return validResultLinks;
    }


}
