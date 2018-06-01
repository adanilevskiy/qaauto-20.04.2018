package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LinkedinLoginSubmitPage extends LinkedinBasePage{

    @FindBy(xpath = "//div[@class='alert error']//strong")
    private WebElement globalErrorMessage;

    @FindBy(xpath = "//ul[@class='form-fields']//span[@id='session_password-login-error']")
    private WebElement passErrorMessage;

    @FindBy(xpath = "//ul[@class='form-fields']//span[@id='session_key-login-error']")
    private WebElement emailErrorMessage;


    public LinkedinLoginSubmitPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Check if current page is loaded.
     * @return true/false if loaded or not.
     */
    @Override
    public boolean isPageLoaded() {
        return globalErrorMessage.isDisplayed();
    }

    public String getGlobalErrorMessageText() {
        return globalErrorMessage.getText();
    }
    public String getPasswordErrorMessageText(){
        return   passErrorMessage.getText();
    }
    public String getEmailErrorMessageText(){
        return   emailErrorMessage.getText();
    }

}
