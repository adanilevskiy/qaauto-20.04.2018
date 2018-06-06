package page;

import Utils.GMailService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page object class with methods and webElements for Linkedin Login page.
 */
public class LinkedinLoginPage extends LinkedinBasePage{

    @FindBy(xpath = "//input[@id='login-email']")
    private WebElement userEmailField;

    @FindBy(xpath = "//input[@id='login-password']")
    private WebElement userPasswordField;

    @FindBy(xpath = "//input[@id='login-submit']")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement forgotPasswordLink;


    /**
     * Get current webDriver value.
     * Initialization webElements for Linkedin Login page.
     * @param webDriver
     */
    public LinkedinLoginPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver,this);
    }

    /**
     * Check if current page is loaded.
     * @return true/false if loaded or not.
     */
    @Override
    public boolean isPageLoaded() {
        waitUntilElementIsClickable(signInButton, 10);
        return signInButton.isDisplayed();
    }

    /**
     * Enter valid user email and valid user password to login fields.
     * Click 'Sign in' button.
     * @param userEmail
     * @param userPassword
     * @return new Linkedin Home page
     */
    public LinkedinHomePage loginWithValidUserData(String userEmail, String userPassword) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInButton.click();
        return PageFactory.initElements(webDriver, LinkedinHomePage.class);
    }

    /**
     * Enter invalid user email or invalid user password to login fields.
     * Click 'Sign in' button.
     * @param userEmail
     * @param userPassword
     * @return new Linkedin Login Submit page
     */
    public LinkedinLoginSubmitPage loginWithInvalidUserData(String userEmail, String userPassword) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInButton.click();
        return PageFactory.initElements(webDriver, LinkedinLoginSubmitPage.class);
    }

    /**
     * Enter creds with empty user email or invalid user password to login fields.
     * Click 'Sign in' button.
     * @param userEmail
     * @param userPassword
     */
    public void loginWithEmptyFields(String userEmail, String userPassword) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInButton.click();
    }

    /**
     * Click on 'Forgot Password' link
     * Connect to user mailbox via Gmail service.
     * @return new Linkedin Reset Password page.
     */
    public LinkedinResetPasswordPage clickOnForgotPasswordLink(){
        gMailService = new GMailService();
        gMailService.connect();
        forgotPasswordLink.click();
        return PageFactory.initElements(webDriver, LinkedinResetPasswordPage.class);
    }
}
