package page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinResetPasswordSubmitPage extends LinkedinBasePage{

    @FindBy(xpath = "//h2[@class='form__subtitle']")
    private WebElement instructionsMessage;

    @FindBy(xpath = "//a[@class='different__email different__email--desktop']")
    private WebElement tryDifferentEmailButton;

    @FindBy(xpath = "//button[@class='resend__link']")
    private WebElement resendLinkButton;

    public LinkedinResetPasswordSubmitPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Check if current page is loaded.
     * @return true/false if loaded or not.
     */
    @Override
    public boolean isPageLoaded() {
        waitUntilElementIsClickable(instructionsMessage, 120);
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

    public LinkedinSetNewPasswordPage navigateToResetPasswordLink(String resetPasswordLink) {
        webDriver.get(resetPasswordLink);
        return PageFactory.initElements(webDriver, LinkedinSetNewPasswordPage.class);
    }


    public String getResetPasswordLinkFromEmail(String messageToPartial) {
        String messageSubjectPartial = "here's the link to reset your password";
        String messageFromPartial = "security-noreply@linkedin.com";
        String message = gMailService.waitMessage(messageSubjectPartial, messageToPartial, messageFromPartial, 60);
        String resetPasswordLink = StringUtils.substringBetween(message, "To change your LinkedIn password, click <a href=\"", "\" style")
                .replaceAll("amp;", "");
        System.out.println(resetPasswordLink);
        return resetPasswordLink;
    }
}
