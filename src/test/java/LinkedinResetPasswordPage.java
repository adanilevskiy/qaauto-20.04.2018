import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinResetPasswordPage extends LinkedinBasePage{

    private WebElement emailTestField;
    private WebElement submitButton;
    private WebElement instructionsMessage;


    public LinkedinResetPasswordPage(WebDriver webDriver) {
        super(webDriver);
        initElements();
    }

    @Override
    boolean isPageLoaded() {
        return instructionsMessage.isDisplayed();
    }

    private void initElements(){
        emailTestField = webDriver.findElement(By.xpath("//input[@name='userName']"));
        submitButton = webDriver.findElement(By.xpath("//button[@id='reset-password-submit-button']"));
        instructionsMessage = webDriver.findElement(By.xpath("//h2[@class='form__subtitle']"));
    }

    public boolean isEmailTextFieldDisplayed(){
        return emailTestField.isDisplayed();
    }

    public boolean isSubmitButtonDisplayed(){
        return submitButton.isDisplayed();
    }

    public void submitUserEmail(String userEmail){
        emailTestField.sendKeys(userEmail);
        submitButton.click();
    }

    public String getInstructionsMessage(){
        return instructionsMessage.getText();
    }
}
