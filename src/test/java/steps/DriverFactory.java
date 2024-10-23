package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    private static WebDriver driver;

    // Private constructor to prevent instantiation
    private DriverFactory() {}

    public static WebDriver getDriver() {
        if (driver == null) {
//            If you don't have the chrome driver in your system's PATH,
//            Update path_to_chrome_driver below to point to your chrome driver location
//            System.setProperty("webdriver.chrome.driver", "path_to_chrome_driver");
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static void quitDriver() {
        if (isDriverOpen()) {
            driver.quit();
            driver = null;
        }
    }

    public static boolean isDriverOpen() {
        try {
            return driver != null && driver.getTitle() != null;
        } catch (Exception e) {
            return false; // If any exception occurs, the driver is likely not usable
        }
    }
}
