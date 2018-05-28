package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.LinkedinHomePage;
import page.LinkedinSearchPage;

import java.util.List;

import static java.lang.Thread.sleep;

public class LinkedinSearchTest extends LinkedinBaseTest {

    @Test
    public void basicSearchTest() throws InterruptedException {
        String searhTerm = "HR";
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),
                "Login Page is not loaded");

        LinkedinHomePage linkedinHomePage = linkedinLoginPage.loginWithValidUserData("smithbod58@gmail.com","Test123Test123");
        sleep(3000);
        Assert.assertTrue(linkedinHomePage.isPageLoaded(), "Home Page is not loaded");

        Assert.assertTrue(linkedinHomePage.isSearchFieldDisplayed(), "Search field is missing on page");

        LinkedinSearchPage linkedinSearchPage = linkedinHomePage.search(searhTerm);
        sleep(3000);
        Assert.assertTrue(linkedinSearchPage.isPageLoaded(), "Search Results Page is not loaded");

        Assert.assertEquals(linkedinSearchPage.getResultsCount(),
                10, "Search results count is not 10");

        List<String> resultsList = linkedinSearchPage.getResultList();
        for (String result:resultsList){
            Assert.assertTrue(result.contains(searhTerm.toLowerCase()),
                    "SearchTerm "+searhTerm+ " is missing in following result:\n"+result );
        }
    }
}
