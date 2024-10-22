package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import pages.HomePage;

import static org.junit.Assert.*;

public class HomeSteps {
    private HomePage homePage = new HomePage(DriverFactory.getDriver());

    @Given("I navigate to application homepage")
    public void i_navigate_to_application_homepage() {
        homePage.open();
    }
    @Then("I should see homepage title")
    public void i_should_see_homepage_title() {
        assertEquals("Home", homePage.getTitle());
    }

    @Then("the page should contain {string}")
    public void the_page_should_contain(String testCase) {
        char lastChar = testCase.charAt(testCase.length() - 1);
        int testNumber = lastChar - '0';
        assertEquals(testCase, homePage.getTestDivLabel(testNumber));
    }

    @Then("the sign in form should contain title {string}")
    public void the_sign_in_form_should_contain_title(String expectedTitle) {
        String actualTitle = homePage.getLoginTitle();
        assertEquals(expectedTitle, actualTitle);
    }

    @Then("the email field should be present with label {string}")
    public void the_email_field_should_be_present_with_label(String expectedLabel) {
        WebElement element = homePage.getEmailField();
        assertTrue("Email field is not displayed", element.isDisplayed());

        String actualLabel = homePage.getLabelFor(element).getText();
        assertEquals("Email label does not match expected", expectedLabel, actualLabel);
    }

    @Then("the password field should be present with label {string}")
    public void the_password_field_should_be_present_with_label(String expectedLabel) {
        WebElement element = homePage.getPasswordField();
        assertTrue("Password field is not displayed", element.isDisplayed());

        String actualLabel = homePage.getLabelFor(element).getText();
        assertEquals("Password label does not match expected", expectedLabel, actualLabel);
    }

    @Then("the login button should be present with label {string}")
    public void the_login_button_should_be_present_with_label(String expectedLabel) {
        WebElement element = homePage.getLoginButton();
        assertTrue("Login button is not displayed", element.isDisplayed());

        String actualLabel = element.getText();
        assertEquals("Login label does not match expected", expectedLabel, actualLabel);
    }

    @Then("user enters email {string} and password {string} in the login form")
    public void user_enters_email_and_password_in_the_login_form(String email, String password) {
        homePage.setEmail(email);
        homePage.setPassword(password);
    }

    @Then("Test 2 should contain listgroup with {int} values")
    public void test_2_should_contain_listgroup_with_values(int expCount) {
        assertTrue("Test 2 does not contain list group", homePage.getTestDivList().isDisplayed());
        assertEquals("Test 2 list group does not contain expected number of values", expCount, homePage.getTestDivListItems().size());
    }

    @Then("item {int} in the listgroup should contain value {string} and badge {string} in Test 2")
    public void item_in_the_listgroup_should_contain_value_and_badge_in_test_2(int itemNumber, String expValue, String expBadge) {
        // List is 0 based index
        int itemIndex = itemNumber - 1;
        assertEquals("List item does not contain expected value", expValue, homePage.getTestDivListItemValue(itemIndex));
        assertEquals("List item does not contain expected badge", expBadge, homePage.getTestDivListItemBadge(itemIndex));
    }

    @Then("Test 3 should contain dropdown with default value {string}")
    public void test_3_should_contain_dropdown_with_default_value(String expValue) {
        WebElement dropdownButton = homePage.getTestDivDropdownButton();
        assertTrue("Test 3 does not contain dropdown", dropdownButton.isDisplayed());
        assertEquals("Test 3 dropdown does not contain expected default value", expValue, dropdownButton.getText().trim());
    }

    @Then("user selects {string} in Test 3 dropdown")
    public void user_selects_in_test_3_dropdown(String option) {
        homePage.clickTestDivDropdownButton();
        homePage.selectTestDivDropdownValue(option);
    }

    @Then("Test 4 should contain {int} buttons")
    public void test_4_should_contain_buttons(int expButtonsCount) {
        assertEquals("Test 4 does not contain expected number of buttons", expButtonsCount, homePage.getTestDivButtons().size());
    }

    @Then("button {int} should be enabled in Test 4")
    public void button_should_be_enabled_in_test_4(int buttonNumber) {
        // List is 0 based index
        int buttonIndex = buttonNumber - 1;
        WebElement button = homePage.getTestDivButtons().get(buttonIndex);
        assertTrue("Button is not enabled Test 4", button.isEnabled());
    }

    @Then("button {int} should be disabled in Test 4")
    public void button_should_be_disabled_in_test_4(int buttonNumber) {
        // List is 0 based index
        int buttonIndex = buttonNumber - 1;
        WebElement button = homePage.getTestDivButtons().get(buttonIndex);
        assertFalse("Button is disabled Test 4", button.isEnabled());
    }

    @Then("user waits to display button in Test 5")
    public void user_waits_to_display_button_in_test_5() {
        WebElement button = homePage.getTestDivDisplayButton();
        assertNotNull("Button is not displayed Test 5", button);
    }

    @Then("the button should be enabled in Test 5")
    public void the_button_should_be_enabled_in_test() {
        WebElement button = homePage.getTestDivDisplayButton();
        assertTrue("Button is not enabled Test 5", button.isEnabled());
    }

    @Then("the button should be disabled in Test 5")
    public void the_button_should_be_disabled_in_test() {
        WebElement button = homePage.getTestDivDisplayButton();
        assertFalse("Button is not disabled Test 5", button.isEnabled());
    }

    @When("user clicks the button in Test 5")
    public void user_clicks_the_button_in_test() {
        homePage.clickTestDivDisplayButton();
    }

    @Then("an alert should display with message {string}")
    public void an_alert_should_display_with_message(String expMessage) {
        String actualMessage = homePage.getAlertText();
        assertEquals("Alert does not contain expected message", expMessage, actualMessage);
    }

    @Then("the table should contain {string} in cell with coordinates \\({int}, {int})")
    public void the_table_should_contain_in_cell_with_coordinates(String expValue, int row, int col) {
        String actualValue = homePage.getTestDivTableCellValue(row, col);
        assertEquals("Table in test 6 does not contain expected cell value", expValue, actualValue);
    }
}
