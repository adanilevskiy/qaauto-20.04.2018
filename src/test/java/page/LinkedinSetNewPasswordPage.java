package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page object with methods and webElements for 'Linkedin Set New Password' page.
 */
public class LinkedinSetNewPasswordPage extends LinkedinBasePage{

    @FindBy(xpath = "//input[@name='newPassword']")
    private WebElement newPasswordField;

    @FindBy(xpath = "//input[@name='confirmPassword']")
    private WebElement confirmNewPasswordField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement submitButton;

    @FindBy(xpath = "//h2[@class='form__subtitle']")
    private WebElement instructionsMessage;

    /**
     * Get current webDriver value.
     * @param webDriver
     */
    public LinkedinSetNewPasswordPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Check if current page is loaded.
     * @return true/false if loaded or not.
     */
    @Override
    public boolean isPageLoaded() {
        waitUntilElementIsClickable(instructionsMessage, 10);
        return instructionsMessage.isDisplayed();
    }

    /**
     * Add New user Password value to 'new password' text field
     * Add New user Password value to 'confirm new password' text field
     * Click Submit button
     * @param newPassword
     * @return new Linkedin Confirm reset password page
     */
    public LinkedinConfirmResetPasswordPage setNewPassword(String newPassword){
        newPasswordField.sendKeys(newPassword);
        confirmNewPasswordField.sendKeys(newPassword);
        submitButton.click();
        return PageFactory.initElements(webDriver, LinkedinConfirmResetPasswordPage.class);
    }
}
