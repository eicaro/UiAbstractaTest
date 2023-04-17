package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;
    private By searchInputLocator = By.id("cb1-edit");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchForItem(String text) {
        WebElement searchInput = driver.findElement(searchInputLocator);
        searchInput.click();
        searchInput.sendKeys(text);
        searchInput.sendKeys(Keys.RETURN);
    }
}
