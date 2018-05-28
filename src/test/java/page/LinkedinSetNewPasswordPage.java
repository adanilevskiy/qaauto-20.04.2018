package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinSetNewPasswordPage extends LinkedinBasePage{

    @FindBy(xpath = "//input[@name='newPassword']")
    private WebElement newPasswordField;

    @FindBy(xpath = "//input[@name='confirmPassword']")
    private WebElement confirmNewPasswordField;

    @FindBy(xpath = "//input[@name='deleteAllSession']")
    private WebElement requireAllDevicesCheckbox;

    @FindBy(xpath = "reset-password-submit-button")
    private WebElement submitButton;

    @FindBy(xpath = "//h2[@class='form__subtitle']")
    private WebElement instructionsMessage;

    public LinkedinSetNewPasswordPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean isPageLoaded() {
        return instructionsMessage.isDisplayed();
    }

    public boolean isNewPasswordFielsDisplayed(){
        return newPasswordField.isDisplayed();
    }
    public boolean isConfirmNewPasswordFieldDisplayed(){
        return confirmNewPasswordField.isDisplayed();
    }
    public boolean requireAllDevicesCheckbox(){
        return requireAllDevicesCheckbox.isDisplayed();
    }
    public boolean isSubmitButtonDisplayed(){
        return submitButton.isDisplayed();
    }

    public LinkedinConfirmResetPasswordPage setNewPassword(String newPassword){
        newPasswordField.sendKeys(newPassword);
        confirmNewPasswordField.sendKeys(newPassword);
        requireAllDevicesCheckbox.click();
        submitButton.click();
        return PageFactory.initElements(webDriver, LinkedinConfirmResetPasswordPage.class);
    }
    public String getInstructionsMessage(){
        return instructionsMessage.getText();
    }
}
