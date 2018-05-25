package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.LinkedinHomePage;
import page.LinkedinLoginPage;
import page.LinkedinSearchPage;

import java.util.List;

import static java.lang.Thread.sleep;

public class LinkedinSearchTest extends LinkedinBaseTest {

    @Test
    public void basicSearchTest() throws InterruptedException {
        String searhTerm = "HR";
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),
                "Login Page is not loaded");

        linkedinLoginPage.login("smithbod58@gmail.com","Test1234!");
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
