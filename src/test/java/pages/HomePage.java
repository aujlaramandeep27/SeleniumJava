package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.*;

public class HomePage {
    private WebDriver driver;

    private By loginTitleCss = By.cssSelector(".form-signin h2");
    private By emailId = By.id("inputEmail");
    private By passwordId = By.id("inputPassword");
    private By loginButtonCss = By.cssSelector(".form-signin button.btn-primary");
    private By testDivHeaderTag = By.tagName("h1");
    private By listGroupClass = By.className("list-group");
    private By listGroupItemClass = By.className("list-group-item");
    private By listGroupItemBadgeClass = By.className("badge");
    private By dropDownButtonId = By.id("dropdownMenuButton");
    private By dropDownMenuClass = By.className("dropdown-menu");
    private By buttonTag = By.tagName("button");
    private By alertId = By.id("test5-alert");
    private By dispalyButtonId = By.id("test5-button");
    private By tableBodyCss = By.cssSelector(".table tbody");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        String webPageFile = "QE-index.html";
        try {
            // Load the HTML file from resources
            InputStream inputStream = HomePage.class.getClassLoader().getResourceAsStream(webPageFile);

            // Get the URL of the HTML file
            URL fileUrl = HomePage.class.getClassLoader().getResource(webPageFile);

            if (fileUrl != null) {
                // Navigate to the local HTML file
                driver.get(fileUrl.toString());

                // Print the page title
                System.out.println("Page title is: " + driver.getTitle());
            } else {
                System.out.println("File not found!");
            }
        } catch (Exception e){
            System.out.println("Caught Exception: " + e);
        }
    }

    public String getTitle() {
        return driver.getTitle();
    }

    // Test Case

    public WebElement getTestDiv(int number) {
        return driver.findElement(By.id(String.format("test-%d-div", number)));
    }

    public String getTestDivLabel(int number) {
        return getTestDiv(number).findElement(testDivHeaderTag).getText();
    }

    // Test 1

    public WebElement getTestDivLogin() {
        return getTestDiv(1);
    }

    public String getLoginTitle() {
        return getTestDivLogin().findElement(loginTitleCss).getText();
    }

    public WebElement getEmailField() {
        return getTestDivLogin().findElement(emailId);
    }

    public void setEmail(String value) {
        clearAndSetFieldValue(getEmailField(), value);
    }

    public WebElement getPasswordField() {
        return getTestDivLogin().findElement(passwordId);
    }

    public void setPassword(String value) {
        clearAndSetFieldValue(getPasswordField(), value);
    }

    public WebElement getLoginButton() {
        return getTestDivLogin().findElement(loginButtonCss);
    }

    // Test 2

    public WebElement getTestDivList() {
        WebElement testDiv = getTestDiv(2);
        return testDiv.findElement(listGroupClass);
    }

    public List<WebElement> getTestDivListItems() {
        WebElement list = getTestDivList();
        return list.findElements(listGroupItemClass);
    }

    public WebElement getTestDivListItem(int number) {
        List<WebElement> items = getTestDivListItems();
        return items.get(number);
    }

    public String getTestDivListItemValue(int number) {
        WebElement item = getTestDivListItem(number);
        return getElementTextWithoutChild(item);
    }

    public String getTestDivListItemBadge(int number) {
        WebElement item = getTestDivListItem(number);
        return item.findElement(listGroupItemBadgeClass).getText();
    }

    // Test 3

    public WebElement getTestDivDropdownButton() {
        WebElement testDiv = getTestDiv(3);
        return testDiv.findElement(dropDownButtonId);
    }

    public void clickTestDivDropdownButton() {
        getTestDivDropdownButton().click();
    }

    public WebElement getTestDivDropdownMenu() {
        WebElement testDiv = getTestDiv(3);
        return testDiv.findElement(dropDownMenuClass);
    }

    public void selectTestDivDropdownValue(String value) {
        WebElement dropdownMenu = getTestDivDropdownMenu();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // wait for dropdown menu to appear
            wait.until(ExpectedConditions.visibilityOf(dropdownMenu));
            // select option from dropdown menu
            WebElement option = dropdownMenu.findElement(By.xpath(String.format(".//a[contains(text(), '%s')]", value)));
            option.click();
        } catch (TimeoutException e){
            System.out.println("Timed out while waiting for dropdown menu to be displayed in Test Div 3.");
        }
    }

    // Test 4

    public List<WebElement> getTestDivButtons() {
        WebElement testDiv = getTestDiv(4);
        return testDiv.findElements(buttonTag);
    }

    // Test 5

    public WebElement getTestDivDisplayButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(dispalyButtonId));
        } catch (TimeoutException e){
            System.out.println("Timed out while waiting for button to be displayed in Test Div 5.");
        }
        return null;
    }

    public void clickTestDivDisplayButton() {
        WebElement button = getTestDivDisplayButton();
        if (button != null && button.isEnabled()) {
            button.click();
        }
    }

    public String getAlertText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(alertId));
            return alert.getText().trim();
        } catch (TimeoutException e){
            System.out.println("Timed out while waiting for alert on button click in Test Div 5.");
        }
        return null;
    }

    // Test 6

    public WebElement getTestDivTableBody() {
        WebElement testDiv = getTestDiv(6);
        return testDiv.findElement(tableBodyCss);
    }

    public String getTestDivTableCellValue(int rowIndex, int columnIndex) {
        WebElement tableBody = getTestDivTableBody();
        WebElement cell = tableBody.findElement(By.xpath(String.format(".//tr[%d]/td[%d]", rowIndex + 1, columnIndex + 1)));
        return cell.getText();
    }

    // Common

    public void clearAndSetFieldValue(WebElement field, String value) {
        field.clear();
        field.sendKeys(value);
    }

    public WebElement getLabelFor(WebElement element) {
        String id = element.getAttribute("id");
        String selector = String.format("//label[@for='%s']", id);
        return driver.findElement(By.xpath(selector));
    }

    public String getElementTextWithoutChild(WebElement element) {
        // Use JavaScript to get only the inner text of the element without its children's text
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].childNodes.length ? arguments[0].childNodes[0].nodeValue.trim() : '';", element);
    }
}
