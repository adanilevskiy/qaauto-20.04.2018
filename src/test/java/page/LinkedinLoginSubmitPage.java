package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object with methods and webElements for Linkedin Login Submit page.
 */
public class LinkedinLoginSubmitPage extends LinkedinBasePage{

    @FindBy(xpath = "//div[@class='alert error']//strong")
    private WebElement globalErrorMessage;

    @FindBy(xpath = "//ul[@class='form-fields']//span[@id='session_password-login-error']")
    private WebElement passErrorMessage;

    @FindBy(xpath = "//ul[@class='form-fields']//span[@id='session_key-login-error']")
    private WebElement emailErrorMessage;


    /**
     * Get current webDriver value.
     * @param webDriver
     */
    public LinkedinLoginSubmitPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Check if current page is loaded.
     * @return true/false if loaded or not.
     */
    @Override
    public boolean isPageLoaded() {
        waitUntilElementIsClickable(globalErrorMessage,10);
        return globalErrorMessage.isDisplayed();
    }

    /**
     * Get Global Error Message Test
     * @return String - Global error Message Test
     */
    public String getGlobalErrorMessageText() {
        return globalErrorMessage.getText();
    }

    /**
     * Get Password Error message text.
     * @return String - Password error message text.
     */
    public String getPasswordErrorMessageText(){
        return   passErrorMessage.getText();
    }

    /**
     * Get Email Error message text.
     * @return String - Email error message text.
     */
    public String getEmailErrorMessageText(){
        return   emailErrorMessage.getText();
    }
}
