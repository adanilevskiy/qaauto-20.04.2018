package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinConfirmResetPasswordPage extends LinkedinBasePage{

    @FindBy(xpath = "//header[@class='content__header']")
    private WebElement pageHeader;

    @FindBy(xpath = "//button[@class='form__cancel']")
    private WebElement goToHomepageButton;


    public LinkedinConfirmResetPasswordPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Check if current page is loaded.
     * @return true/false if loaded or not.
     */
    @Override
    public boolean isPageLoaded() {
        waitUntilElementIsClickable(pageHeader,10);
        return pageHeader.isDisplayed();
    }

    public boolean isGoToHomepageButtonDisplayed(){
        return goToHomepageButton.isDisplayed();
    }

    public LinkedinHomePage clickOnGoToHomepageButton(){
        goToHomepageButton.click();
        return PageFactory.initElements(webDriver, LinkedinHomePage.class);
    }
}
