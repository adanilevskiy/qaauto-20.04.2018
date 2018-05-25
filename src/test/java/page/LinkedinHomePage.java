package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinHomePage extends LinkedinBasePage{

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileMenu;

    @FindBy(xpath = "//div[@class='nav-search-typeahead']//input[@placeholder='Search']")
    private WebElement searchField;

    @FindBy(xpath = "//button[@class='search-typeahead-v2__button typeahead-icon']")
    private WebElement searchButton;

    public LinkedinHomePage(WebDriver webDriver){
        super(webDriver);
        //PageFactory.initElements(webDriver,this);
    }

    @Override
    public boolean isPageLoaded() {
        return profileMenu.isDisplayed();
    }
    public void search(String searchTerm){
        searchField.sendKeys(searchTerm);
        searchButton.click();
    }
    public boolean isSearchFieldDisplayed(){
        return searchField.isDisplayed();
    }
}