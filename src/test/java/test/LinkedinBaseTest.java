package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.LinkedinLoginPage;

/**
 * Base class contains necessary methods for each subclass
 */
public class LinkedinBaseTest {
    WebDriver webDriver;
    LinkedinLoginPage linkedinLoginPage;

    /**
     * Method runs before each test
     *
     * Lunch Firefox Browser
     * Navigate to 'https://www.linkedin.com'
     * Initialize new LinkedinLoginPage
     */
    @BeforeMethod
    public void before() {
        webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");
        linkedinLoginPage = new LinkedinLoginPage(webDriver);
    }
    /**
     * Method runs after each test
     * Close browser
     */
    @AfterMethod
    public void after() {
        webDriver.close();
    }
}