package steps;

import io.cucumber.java.*;

public class Hooks {
    @Before
    public void setupBeforeScenario() {
        // Initialize the WebDriver before each scenario
        DriverFactory.getDriver().manage().window().maximize();
        System.out.println("WebDriver open.");
    }

    @After
    public void cleanupAfterScenario() {
        // Close the WebDriver after each scenario
        DriverFactory.quitDriver();
        System.out.println("WebDriver closed.");
    }
}
