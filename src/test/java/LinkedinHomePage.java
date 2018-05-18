import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinHomePage {

    private WebDriver webDriver;
    private WebElement profileMenu;

    public LinkedinHomePage(WebDriver webDriver){
        this.webDriver = webDriver;
        initElements();
    }

    private void initElements(){
        profileMenu = webDriver.findElement(By.xpath("//li[@id='profile-nav-item']"));
    }

    public boolean isProfileMenuDisplayed(){
        return profileMenu.isDisplayed();
    }

    public String getHomePageTitle(){
        return webDriver.getTitle();
    }
}