package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinResetPasswordPage extends LinkedinBasePage{
    @FindBy(xpath = "//input[@name='userName']")
    private WebElement emailTestField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement submitButton;

    @FindBy(xpath = "//h2[@class='form__subtitle']")
    private WebElement instructionsMessage;


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

    public LinkedinResetPasswordSubmitPage submitUserEmail(String userEmail){
        emailTestField.sendKeys(userEmail);
        submitButton.click();
        return PageFactory.initElements(webDriver, LinkedinResetPasswordSubmitPage.class);
    }

    public String getInstructionsMessage(){
        return instructionsMessage.getText();
    }
}
