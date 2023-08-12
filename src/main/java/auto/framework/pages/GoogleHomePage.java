package auto.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleHomePage {

    private WebDriver driver;

    private By searchBarLocator = By.xpath("//*[@id='APjFqb']");

    public GoogleHomePage(WebDriver driver){
        this.driver = driver;
    }

    public void search(String searchString) {
        WebElement searchbar = driver.findElement(searchBarLocator);
        searchbar.sendKeys(searchString);
        searchbar.sendKeys(Keys.RETURN);
    }


}
