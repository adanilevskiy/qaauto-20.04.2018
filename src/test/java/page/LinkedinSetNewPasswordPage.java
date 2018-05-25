package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinSetNewPasswordPage extends LinkedinBasePage{

    private WebElement newPasswordField;
    private WebElement confirmNewPasswordField;
    private WebElement requireAllDevicesCheckbox;
    private WebElement submitButton;
    private WebElement instructionsMessage;

    public LinkedinSetNewPasswordPage(WebDriver webDriver) {
        super(webDriver);
        initElements();
    }

    private void initElements(){
        newPasswordField = webDriver.findElement(By.xpath("//input[@name='newPassword']"));
        confirmNewPasswordField = webDriver.findElement(By.xpath("//input[@name='confirmPassword']"));
        requireAllDevicesCheckbox = webDriver.findElement(By.xpath("//input[@name='deleteAllSession']"));
        submitButton = webDriver.findElement(By.xpath("reset-password-submit-button"));
        instructionsMessage = webDriver.findElement(By.xpath("//h2[@class='form__subtitle']"));
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

    public void setNewPassword(String newPassword){
        newPasswordField.sendKeys(newPassword);
        confirmNewPasswordField.sendKeys(newPassword);
        requireAllDevicesCheckbox.click();
        submitButton.click();
    }
    public String getInstructionsMessage(){
        return instructionsMessage.getText();
    }
}
