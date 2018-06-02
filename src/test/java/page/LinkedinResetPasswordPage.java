package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page object with methods and webElements for Linkedin Reset Password page.
 */
public class LinkedinResetPasswordPage extends LinkedinBasePage{
    @FindBy(xpath = "//input[@name='userName']")
    private WebElement emailTestField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement submitButton;

    @FindBy(xpath = "//h2[@class='form__subtitle']")
    private WebElement instructionsMessage;

    /**
     * Get current webDriver value.
     * @param webDriver
     */
    public LinkedinResetPasswordPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Check if current page is loaded.
     * @return true/false if loaded or not.
     */
    @Override
    public boolean isPageLoaded() {
        waitUntilElementIsClickable(instructionsMessage,5);
        return instructionsMessage.isDisplayed();
    }

    /**
     * Add user email to text field
     * Click 'Submit' button
     * @param userEmail
     * @return new Reset Password Submit Page
     */
    public LinkedinResetPasswordSubmitPage submitUserEmail(String userEmail){
        emailTestField.sendKeys(userEmail);
        submitButton.click();
        return PageFactory.initElements(webDriver, LinkedinResetPasswordSubmitPage.class);
    }
}
