import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinHomePage extends LinkedinBasePage{

    private WebElement profileMenu;

    public LinkedinHomePage(WebDriver webDriver){
        super(webDriver);
        initElements();
    }

    @Override
    boolean isPageLoaded() {
        return profileMenu.isDisplayed();
    }

    private void initElements(){
        profileMenu = webDriver.findElement(By.xpath("//li[@id='profile-nav-item']"));
    }
}