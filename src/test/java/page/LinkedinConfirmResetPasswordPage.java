package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LinkedinConfirmResetPasswordPage extends LinkedinBasePage{

    private WebElement pageHeader;
    private WebElement goToHomepageButton;


    public LinkedinConfirmResetPasswordPage(WebDriver webDriver) {
        super(webDriver);
    }
    private void initElements(){
        goToHomepageButton = webDriver.findElement(By.xpath("//button[@class='form__cancel']"));
        pageHeader = webDriver.findElement(By.xpath("//header[@class='content__header']"));
    }

    @Override
    public boolean isPageLoaded() {
        return pageHeader.isDisplayed();
    }
    public boolean isGoToHomepageButtonDisplayed(){
        return goToHomepageButton.isDisplayed();
    }

    public String getSuccssesfulResetPasswordMessageText(){
        return pageHeader.getText();
    }
    public LinkedinHomePage clickOnGoToHomepageButton(){
        goToHomepageButton.click();
        return PageFactory.initElements(webDriver, LinkedinHomePage.class);
    }
}
