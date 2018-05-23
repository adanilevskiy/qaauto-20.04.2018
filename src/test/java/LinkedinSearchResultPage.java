import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LinkedinSearchResultPage extends LinkedinBasePage{

    private WebElement searchResultsPanel;
    private List<WebElement> searchResults;


    public LinkedinSearchResultPage(WebDriver webDriver) {
        super(webDriver);
        initElements();
    }

    private void initElements(){
        searchResultsPanel = webDriver.findElement(By.xpath("//div[@class='search-results-page core-rail']"));
        searchResults = webDriver.findElements(By.xpath("//li[contains(@class,'search-result search-result__occluded-item')]"));
    }
    @Override
    public String getCurrentTitle() {
        return super.getCurrentTitle();
    }
    @Override
    boolean isPageLoaded() {
        return searchResultsPanel.isDisplayed();
    }

    public int getCountOfSearchResultsOnPage(){
        return searchResults.size();
    }
    public void verifyEachSearchResultContainsSearchTerm(String searchTerm){
        int searchResultCounter = 0;
        for(WebElement searchResult : searchResults) {
            searchResult.click();
            String searchResultText = searchResult.getText();
            if (searchResultText.contains(searchTerm)) {
                searchResultCounter++;
                System.out.println("Search Term '" + searchTerm + "' found in result " + searchResultCounter);
            }else{
                searchResultCounter++;
                System.out.println("Search Term '" + searchTerm + "' NOT found in result " + searchResultCounter);
            }
        }
    }
}
