import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

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
        String searhTerm = "HR";
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),
                "Login Page is not loaded");

        linkedinLoginPage.login("toshnot.1@gmail.com","Test123");
        sleep(5000);

        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(webDriver);
        Assert.assertTrue(linkedinHomePage.isPageLoaded(), "Home Page is not loaded");

        Assert.assertTrue(linkedinHomePage.isSearchFieldDisplayed(), "Search field is missing on page");

        linkedinHomePage.search("HR");

        sleep(10000);

        LinkedinSearchPage linkedinSearchPage = new LinkedinSearchPage(webDriver);
        Assert.assertTrue(linkedinSearchPage.isPageLoaded(), "Search Results Page is not loaded");

        Assert.assertEquals(linkedinSearchPage.getResultsCount(),
                10, "Search results count is not 10");

        List<String> resultsList = linkedinSearchPage.getResultList();
        for (String result:resultsList){
            Assert.assertTrue(result.contains(searhTerm),
                    "SearchTerm "+searhTerm+ " is missing in following result:\n"+result );
        }
    }
}
