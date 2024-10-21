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

    private By emailId = By.id("inputEmail");
    private By passwordId = By.id("inputPassword");
    private By signInButtonCss = By.cssSelector(".form-signin button.btn-primary");
    private By testDivHeaderTag = By.tagName("h1");
    private By listGroupClass = By.className("list-group");
    private By listGroupItemClass = By.className("list-group-item");
    private By listGroupItemBadgeClass = By.className("badge");
    private By dropDownButtonClass = By.id("dropdownMenuButton");
    private By dropDownOptionCss = By.cssSelector(".dropdown-item:contains('text')");
    private By buttonTag = By.tagName("button");
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

    // Test Div

    public WebElement getTestDiv(int number) {
        return driver.findElement(By.id(String.format("test-%s-div", number)));
    }

    public String getTestDivLabel(int number) {
        return getTestDiv(number).findElement(testDivHeaderTag).getText();
    }

    // Test 1

    public WebElement getTestDivLogin() {
        return getTestDiv(1);
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

    public void setPasswordId(String value) {
        clearAndSetFieldValue(getPasswordField(), value);
    }

    public WebElement getSignInButton() {
        return getTestDivLogin().findElement(signInButtonCss);
    }

    public void clickSignIn() {
        getSignInButton().click();
    }

    // Test 2

    // '#test-2-div ul li:nth-of-type(2) .badge'
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

    public WebElement getTestDivDropdown() {
        WebElement testDiv = getTestDiv(3);
        return testDiv.findElement(dropDownButtonClass);
    }

    public String getTestDivDropdownValue() {
        return getTestDivDropdown().getText().trim();
    }

    public void clickTestDivDropdown() {
        getTestDivDropdown().click();
    }

    public void selectTestDivDropdownValue(String value) {
        getTestDivDropdown().findElement(By.cssSelector(String.format(".dropdown-item:contains('%s')", value))).click();
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
            wait.until(ExpectedConditions.alertIsPresent());

            Alert alert = driver.switchTo().alert();
            return alert.getText();
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
        WebElement cell = tableBody.findElement(By.xpath(String.format(".//tr[\"%s\"]/td[\"%s\"]", rowIndex+1, columnIndex+1)));
        return cell.getText();
    }

    // Common

    public void clearAndSetFieldValue(WebElement field, String value) {
        field.clear();
        field.sendKeys(value);
    }

    public WebElement getLabelForId(String id) {
         return driver.findElement(By.xpath("//label[@for='"+id+"']"));
    }

    public String getLabelText(WebElement element) {
        return element.getText();
    }

    public String getElementTextWithoutChild(WebElement element) {
        // Use JavaScript to get only the inner text of the element without its children's text
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].childNodes.length ? arguments[0].childNodes[0].nodeValue.trim() : '';", element);
    }
}
