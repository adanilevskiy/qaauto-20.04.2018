import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LinkedinSearchTest {
    WebDriver webDriver;

    @BeforeMethod
    public void before() {
        webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");
    }
    @AfterMethod
    public void after() {
        webDriver.close();
    }
    @Test
    public void basicSearchTest() throws InterruptedException {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),
                "Login Page is not loaded");

        linkedinLoginPage.login("toshnot.1@gmail.com","Test123");
        sleep(5000);

        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(webDriver);
        Assert.assertTrue(linkedinHomePage.isPageLoaded(), "Home Page is not loaded");

        Assert.assertTrue(linkedinHomePage.isSearchFieldDisplayed(), "Search field is missing on page");

        linkedinHomePage.search("HR");

        sleep(3000);

        LinkedinSearchResultPage linkedinSearchResultPage = new LinkedinSearchResultPage(webDriver);
        Assert.assertTrue(linkedinSearchResultPage.isPageLoaded(), "Search Results Page is not loaded");

        Assert.assertEquals(linkedinSearchResultPage.getCountOfSearchResultsOnPage(),
                10, "Number of search results on page is not 10");

        linkedinSearchResultPage.verifyEachSearchResultContainsSearchTerm("HR");
    }
}
