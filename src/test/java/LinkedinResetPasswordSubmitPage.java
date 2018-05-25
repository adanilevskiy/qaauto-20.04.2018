import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinResetPasswordSubmitPage extends LinkedinBasePage{

    private WebElement instructionsMessage;
    private WebElement tryDifferentEmailButton;
    private WebElement resendLinkButton;

    public LinkedinResetPasswordSubmitPage(WebDriver webDriver) {
        super(webDriver);
        initElements();
    }
    private void initElements(){
        tryDifferentEmailButton = webDriver.findElement(By.xpath("//a[@class='different__email different__email--desktop']"));
        resendLinkButton = webDriver.findElement(By.xpath("//button[@class='resend__link']"));
        instructionsMessage = webDriver.findElement(By.xpath("//h2[@class='form__subtitle']"));
    }

    @Override
    boolean isPageLoaded() {
        return instructionsMessage.isDisplayed();
    }
    public boolean isTryDifferentEmailButtonDisplayed(){
        return tryDifferentEmailButton.isDisplayed();
    }
    public boolean isResendLinkButtonDisplayed(){
        return resendLinkButton.isDisplayed();
    }
    public void clickOnTryDifferentEmailButton(){
        tryDifferentEmailButton.click();
    }
    public String getInstructionsMessage(){
        return instructionsMessage.getText();
    }
    public String getLinkedinResetPasswordLink(){
        return null;
    }

    public void navigateToResetPasswordLink(){

    }
}
