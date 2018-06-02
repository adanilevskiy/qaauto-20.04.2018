package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page object with methods and webElements for Linkedin Home page.
 */
public class LinkedinHomePage extends LinkedinBasePage{

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileMenu;

    @FindBy(xpath = "//div[@class='nav-search-typeahead']//input[@placeholder='Search']")
    private WebElement searchField;

    @FindBy(xpath = "//button[@class='search-typeahead-v2__button typeahead-icon']")
    private WebElement searchButton;

    /**
     * Get current webDriver value.
     * @param webDriver
     */
    public LinkedinHomePage(WebDriver webDriver){
        super(webDriver);
    }

    /**
     * Check if current page is loaded.
     * @return true/false if loaded or not.
     */
    @Override
    public boolean isPageLoaded() {
        waitUntilElementIsClickable(profileMenu,10);
        return profileMenu.isDisplayed();
    }

    /**
     * Add search term to search field.
     * Click 'Search' btn.
     * @param searchTerm
     * @return New Search results page.
     */
    public LinkedinSearchPage search(String searchTerm){
        searchField.sendKeys(searchTerm);
        searchButton.click();
        return PageFactory.initElements(webDriver, LinkedinSearchPage.class);
    }

    /**
     * Check is 'Search' field is displayed.
     * @return true/false if displayed or not.
     */
    public boolean isSearchFieldDisplayed(){
        return searchField.isDisplayed();
    }

}