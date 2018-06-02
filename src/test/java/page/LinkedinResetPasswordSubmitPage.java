package page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page object with methods and webElements for Linkedin Reset Password Submit page.
 */
public class LinkedinResetPasswordSubmitPage extends LinkedinBasePage{

    @FindBy(xpath = "//h2[@class='form__subtitle']")
    private WebElement instructionsMessage;

    /**
     * Get current webDriver value.
     * @param webDriver
     */
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

    /**
     * Navigate to 'Reset Password' link from user email.
     * @param resetPasswordLink
     * @return new 'Linkedin Set new Password' page
     */
    public LinkedinSetNewPasswordPage navigateToResetPasswordLink(String resetPasswordLink) {
        webDriver.get(resetPasswordLink);
        return PageFactory.initElements(webDriver, LinkedinSetNewPasswordPage.class);
    }


    /**
     * Get 'Linkedin Reset Password' Link from user email via Gmail service
     * @param messageToPartial
     * @return 'Linkedin Reset Password' Link from user email
     */
    public String getResetPasswordLinkFromEmail(String messageToPartial) {
        String messageSubjectPartial = "here's the link to reset your password";
        String messageFromPartial = "security-noreply@linkedin.com";
        String message = gMailService.waitMessage(messageSubjectPartial, messageToPartial, messageFromPartial, 60);
        String resetPasswordLink = StringUtils.substringBetween(message, "To change your LinkedIn password, click <a href=\"", "\" style")
                .replaceAll("amp;", "");
        return resetPasswordLink;
    }
}
