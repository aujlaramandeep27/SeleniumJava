package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class HomePage {
    private WebDriver driver;
    private String baseUrl = "https://slashdot.org/";

    private By articleTag = By.tagName("article");
    private By iconsXpath = By.xpath("//article//span[@class='topic']/a/img");
    private By pollsNavButtonXpath = By.xpath("//nav[@class='nav-primary']//ul[@class='nav-site']//span[contains(text(),'Polls')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(baseUrl);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public int getNumberOfArticles() {
        List<WebElement> articles = driver.findElements(articleTag);
        return articles.size();
    }

    public List<WebElement> getIconsList() {
        return driver.findElements(iconsXpath);
    }

    public List<String> getIconsTitlesList() {
        List<WebElement> icons = getIconsList();
        List<String> iconsTitles = new ArrayList<String>();

        for (WebElement icon : icons) {
            iconsTitles.add(icon.getAttribute("title"));
        }
        return iconsTitles;
    }

    public void clickPollsNavButton() {
        WebElement firstPoll = driver.findElement(pollsNavButtonXpath);
        firstPoll.click();
    }
}
