import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class LinkedinSearchPage extends LinkedinBasePage{

    private WebElement searchResultsContainer;
    private List<WebElement> searchResults;

    public LinkedinSearchPage(WebDriver webDriver) {
        super(webDriver);
        initElements();
    }

    private void initElements(){
        searchResultsContainer = webDriver.findElement(By.xpath("//div[@class='search-results-page core-rail']"));
        searchResults = webDriver.findElements(By.xpath("//li[contains(@class,'search-result search-result__occluded-item')]"));
    }
    @Override
    boolean isPageLoaded() {
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
            searchResultList.add(searchResultText);
        }
        return searchResultList;
    }
}
