package steps;

import io.cucumber.java.en.*;
import pages.HomePage;

import java.util.*;

import static org.junit.Assert.*;

public class HomeSteps {
    private HomePage homePage = new HomePage(DriverFactory.getDriver());

    @When("I navigate to application homepage")
    public void iNavigateToApplicationHomepage() {
        homePage.open();
    }

    @Then("I should see homepage title")
    public void iShouldSeeHomepageTitle() {
        assertEquals("Slashdot: News for nerds, stuff that matters", homePage.getTitle());
    }

    @Then("the homepage should contain articles")
    public void theHomepageShouldContainArticles() {
        int articlesCount = homePage.getNumberOfArticles();
        System.out.println("Number of articles on the page: " + articlesCount);
        assertTrue(articlesCount > 0);
    }

    @Then("I should print unique icons and their occurrences")
    public void iShouldPrintUniqueIconsAndTheirOccurrences() {
        List<String> iconsTitles = homePage.getIconsTitlesList();

        Set<String> uniqueIconsTitles = new HashSet<>(iconsTitles);
        for (String iconTitle: uniqueIconsTitles) {
            System.out.println("Icon '" + iconTitle + "' occurs " + Collections.frequency(iconsTitles, iconTitle) + " times.");
        }
    }

    @And("I click polls button in menu bar")
    public void iClickPollsButtonInMenuBar() {
        homePage.clickPollsNavButton();
    }
}
