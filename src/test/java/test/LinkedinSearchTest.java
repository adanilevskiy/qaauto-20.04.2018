package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.LinkedinHomePage;
import page.LinkedinSearchPage;

import java.util.List;


/**
 * Tests for 'Search'.
 */
public class LinkedinSearchTest extends LinkedinBaseTest {

    @Test
    public void basicSearchTest(){
        String searhTerm = "HR";
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),
                "Login Page is not loaded");

        LinkedinHomePage linkedinHomePage = linkedinLoginPage.loginWithValidUserData("smithbod58@gmail.com","Test123Test123");
        Assert.assertTrue(linkedinHomePage.isPageLoaded(),
                "Home Page is not loaded");

        LinkedinSearchPage linkedinSearchPage = linkedinHomePage.search(searhTerm);
        Assert.assertTrue(linkedinSearchPage.isPageLoaded(),
                "Search Results Page is not loaded");
        Assert.assertEquals(linkedinSearchPage.getResultsCount(),
                10, "Search results count is not 10");

        List<String> resultsList = linkedinSearchPage.getResultList();
        for (String result:resultsList){
            Assert.assertTrue(result.contains(searhTerm.toLowerCase()),
                    "SearchTerm "+searhTerm+ " is missing in following result:\n"+result );
        }
    }
}
