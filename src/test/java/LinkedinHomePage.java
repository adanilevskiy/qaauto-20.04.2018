import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinHomePage extends LinkedinBasePage{

    private WebElement profileMenu;
    private WebElement searchField;
    private WebElement searchButton;

    public LinkedinHomePage(WebDriver webDriver){
        super(webDriver);
        initElements();
    }
    private void initElements(){
        profileMenu = webDriver.findElement(By.xpath("//li[@id='profile-nav-item']"));
        searchField = webDriver.findElement(By.xpath("//div[@class='nav-search-typeahead']//input[@placeholder='Search']"));
        searchButton = webDriver.findElement(By.xpath("//button[@class='search-typeahead-v2__button typeahead-icon']"));
    }
    @Override
    boolean isPageLoaded() {
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