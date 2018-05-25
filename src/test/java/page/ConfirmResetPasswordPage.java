package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConfirmResetPasswordPage extends LinkedinBasePage{

    private WebElement pageHeader;
    private WebElement goToHomepageButton;


    public ConfirmResetPasswordPage(WebDriver webDriver) {
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
    public void clickOnGoToHomepageButton(){
        goToHomepageButton.click();
    }
    public String getSuccssesfulResetPasswordMessageText(){
        return pageHeader.getText();
    }
}
