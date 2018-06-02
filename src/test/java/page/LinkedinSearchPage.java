package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * Page object with methods and webElements for Linkedin Search page.
 */
public class LinkedinSearchPage extends LinkedinBasePage{

    @FindBy(xpath = "//div[@class='search-results-page core-rail']")
    private WebElement searchResultsContainer;

    @FindBy(xpath = "//li[contains(@class,'search-result search-result__occluded-item')]")
    private List<WebElement> searchResults;

    /**
     * Get current webDriver value.
     * @param webDriver
     */
    public LinkedinSearchPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Check if current page is loaded.
     * @return true/false if loaded or not.
     */
    @Override
    public boolean isPageLoaded() {
        waitUntilElementIsClickable(searchResultsContainer,10);
        return searchResultsContainer.isDisplayed();
    }

    /**
     * Get Results Count on page
     * @return int Results Count
     */
    public int getResultsCount(){
        return searchResults.size();
    }

    /**
     * Get all search results from page then add them to 'Search Results List'
     * @return List<String> Search result List
     */
    public List<String> getResultList() {
        List<String> searchResultList = new ArrayList();
        for(WebElement searchResult : searchResults) {
            ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", searchResult);
            String searchResultText = searchResult.getText();
            searchResultList.add(searchResultText.toLowerCase());
        }
        return searchResultList;
    }
}
