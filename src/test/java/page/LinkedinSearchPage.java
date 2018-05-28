package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class LinkedinSearchPage extends LinkedinBasePage{

    @FindBy(xpath = "//div[@class='search-results-page core-rail']")
    private WebElement searchResultsContainer;

    @FindBy(xpath = "//li[contains(@class,'search-result search-result__occluded-item')]")
    private List<WebElement> searchResults;

    public LinkedinSearchPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean isPageLoaded() {
        return searchResultsContainer.isDisplayed();
    }

    public int getResultsCount(){
        return searchResults.size();
    }

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
