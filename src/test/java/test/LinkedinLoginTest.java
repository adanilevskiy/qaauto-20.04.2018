package test;

import org.testng.Assert;
import org.testng.annotations.*;
import page.*;

import static java.lang.Thread.sleep;

public class LinkedinLoginTest extends LinkedinBaseTest {

    /**
     * Positive cases:
     * - Successful Login Test Valid user email(lowerCase) + Valid Password
     * - Successful Login Test Valid user email(camelCase) + Valid Password
     *
     * Negative Cases:
     * - Empty email field
     * - Empty pass field
     * - Space in password
     * - Wrong email + Valid Pass
     * - Valid email + Wrong Pass
     * - Incorrect email + Valid Pass
     * - Verify Login With Too Short Password
     */

    @DataProvider
    public Object[][] ValidUserDataProvider() {
        return new Object[][]{
                {"smithbod58@gmail.com","Test123Test123"},
          //      {"sMiThBod58@gmail.com","Test123Test123"},
        };
    }
    @Test(dataProvider = "ValidUserDataProvider")
    public void successfulLoginTest(String validUserEmail, String validUserPassword) throws InterruptedException {

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),
                "Login Page is not loaded");

        LinkedinHomePage linkedinHomePage = linkedinLoginPage.login(validUserEmail, validUserPassword);
        Assert.assertTrue(linkedinHomePage.isPageLoaded(),
                "Home Page is not loaded");

    }
    @DataProvider
    public Object[][] incorrectPasswordValues() {
        return new Object[][]{
                {"smithbod58@gmail.com","Test 1234!"},
                {"smithbod58@gmail.com","Test12345!"}
        };
    }
    @Test (dataProvider = "incorrectPasswordValues")
    public void loginWithInvalidPasswordTest(String validUserEmail, String invalidUserPassword) throws InterruptedException {
        Assert.assertEquals(linkedinLoginPage.getCurrentTitle(),
                "LinkedIn: Log In or Sign Up",
                "Login Page title is wrong");

        linkedinLoginPage.login(validUserEmail, invalidUserPassword);
        sleep(5000);
        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(webDriver);

        Assert.assertEquals(loginSubmitPage.getCurrentTitle(),
                "Sign In to LinkedIn",
                "User is not redirected to Login page");
        Assert.assertEquals(loginSubmitPage.getGlobalErrorMessageText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "The error message is not displayed");
        Assert.assertEquals(loginSubmitPage.getWrongPasswordErrorMessageText(),
                "Hmm, that's not the right password. Please try again or request a new one.",
                "The error message is not displayed");
    }

    @DataProvider
    public Object[][] emptyLoginOrPasswordFieldProvider() {
        return new Object[][]{
                {"","Test123"},
                {"smithbod58@gmail.com",""}
        };
    }
    @Test (dataProvider = "emptyLoginOrPasswordFieldProvider")
    public void verifyLoginWithEmptyUserNameOrUserPassField(String userName, String userPassword) throws InterruptedException {
        Assert.assertEquals(linkedinLoginPage.getCurrentTitle(),
                "LinkedIn: Log In or Sign Up",
                "Login Page title is wrong");
        linkedinLoginPage.login(userName, userPassword);
        sleep(5000);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),
                "Linkedin Login Page is loaded");
    }

    @Test
    public void verifyLoginWithWrongEmail() throws InterruptedException {
        Assert.assertEquals(linkedinLoginPage.getCurrentTitle(),
                "LinkedIn: Log In or Sign Up",
                "Login Page title is wrong");

        linkedinLoginPage.login("XXXsmithbod58@gmail.com", "Test1234!");
        sleep(5000);
        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(webDriver);

        Assert.assertEquals(loginSubmitPage.getCurrentTitle(),
                "Sign In to LinkedIn",
                "User is not redirected to Login-Submit page");
        Assert.assertEquals(loginSubmitPage.getGlobalErrorMessageText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "The error message is not displayed");
        Assert.assertEquals(loginSubmitPage.getWrongEmailErrorMessageText(),
                "Hmm, we don't recognize that email. Please try again.",
                "The error message is not displayed");
    }

    @Test
    public void verifyLoginWithIncorrectEmail() throws InterruptedException {
        Assert.assertEquals(linkedinLoginPage.getCurrentTitle(),
                "LinkedIn: Log In or Sign Up",
                "Page title is wrong");
        linkedinLoginPage.login("smithbod58gmail.com", "Test1234!");
        sleep(5000);
        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(webDriver);

        Assert.assertEquals(loginSubmitPage.getCurrentTitle(),
                "Sign In to LinkedIn",
                "User is not redirected to Login page");
        Assert.assertEquals(loginSubmitPage.getGlobalErrorMessageText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "The error message is not displayed");
        Assert.assertEquals(loginSubmitPage.getIncorrectEmailErrorMessageText(),
                "Please enter a valid email address.",
                "The error message is not displayed");
    }

    @Test
    public void verifyLoginWithTooShortPassword() throws InterruptedException {
        Assert.assertEquals(linkedinLoginPage.getCurrentTitle(),
                "LinkedIn: Log In or Sign Up",
                "Page title is wrong");
        linkedinLoginPage.login("smithbod58@gmail.com", "T");

        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(webDriver);
        sleep(5000);
        Assert.assertEquals(loginSubmitPage.getCurrentTitle(),
                "Sign In to LinkedIn", "User is not redirected to Login page");

        Assert.assertEquals(loginSubmitPage.getGlobalErrorMessageText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "The error message is not displayed");
        Assert.assertEquals(loginSubmitPage.getToShortPassErrorMessageText(),
                "The password you provided must have at least 6 characters.",
                "The error message is not displayed");
    }

    @Test
    public void LinkedinSuccessfulResetPasswordTest() throws InterruptedException {
        String userEmail = "smithbod58@gmail.com";
        String UserPassword = "Test123Test123";

        Assert.assertEquals(linkedinLoginPage.getCurrentTitle(),
                "LinkedIn: Log In or Sign Up",
                "Page title is wrong");
        linkedinLoginPage.clickOnForgotPasswordLink();

        sleep(3000);

        LinkedinResetPasswordPage linkedinResetPasswordPage = new LinkedinResetPasswordPage(webDriver);

        Assert.assertTrue(linkedinResetPasswordPage.isPageLoaded(),"Reset Password Page is not loaded");
        Assert.assertEquals(linkedinResetPasswordPage.getCurrentTitle(),
                "Reset Password | LinkedIn",
                "Page title is wrong");

        Assert.assertTrue(linkedinResetPasswordPage.isEmailTextFieldDisplayed(),"Email text field is missing");
        Assert.assertTrue(linkedinResetPasswordPage.isSubmitButtonDisplayed(),"Submit button is missing");
        Assert.assertEquals(linkedinResetPasswordPage.getInstructionsMessage(),
                "Please enter your email or phone", "Wrong instructions message is displayed");

        linkedinResetPasswordPage.submitUserEmail(userEmail);

        sleep(3000);

        LinkedinResetPasswordSubmitPage linkedinResetPasswordSubmitPage = new LinkedinResetPasswordSubmitPage(webDriver);

        Assert.assertTrue(linkedinResetPasswordSubmitPage.isPageLoaded(),"Reset Password Submit Page is not loaded");
        Assert.assertEquals(linkedinResetPasswordSubmitPage.getCurrentTitle(),
                "Please check your mail for reset password link. | LinkedIn",
                "Page title is wrong");

        Assert.assertEquals(linkedinResetPasswordSubmitPage.getInstructionsMessage(),
                "Please check your email and click the secure link.", "Wrong instructions message is displayed");
        linkedinResetPasswordSubmitPage.getLinkedinResetPasswordLink();
        linkedinResetPasswordSubmitPage.navigateToResetPasswordLink();

        sleep(3000);

        LinkedinSetNewPasswordPage linkedinSetNewPasswordPage = new LinkedinSetNewPasswordPage(webDriver);
        Assert.assertTrue(linkedinSetNewPasswordPage.isPageLoaded(),"Set new Password Page is not loaded");
        Assert.assertEquals(linkedinSetNewPasswordPage.getCurrentTitle(),
                "Reset Your Password | LinkedIn",
                "Page title is wrong");

        Assert.assertTrue(linkedinSetNewPasswordPage.isNewPasswordFielsDisplayed(),"New password field is missing");
        Assert.assertTrue(linkedinSetNewPasswordPage.isConfirmNewPasswordFieldDisplayed(),"Confirm password field is missing");
        Assert.assertTrue(linkedinSetNewPasswordPage.isSubmitButtonDisplayed(),"Submit button is missing");
        Assert.assertTrue(linkedinSetNewPasswordPage.requireAllDevicesCheckbox(),"'Require all devices' checkbox is missing");
        Assert.assertEquals(linkedinSetNewPasswordPage.getInstructionsMessage(),
                "Password must include at least 8 characters including at least 1 number or 1 special character.",
                "Wrong instructions message is displayed");

        linkedinSetNewPasswordPage.setNewPassword(UserPassword);

        sleep(3000);

        ConfirmResetPasswordPage confirmResetPasswordPage = new ConfirmResetPasswordPage(webDriver);
        Assert.assertTrue(confirmResetPasswordPage.isPageLoaded(),"Confirm reset password Page is not loaded");
        Assert.assertEquals(confirmResetPasswordPage.getCurrentTitle(),
                "LinkedIn: Log In or Sign Up",
                "Page title is wrong");
        Assert.assertEquals(confirmResetPasswordPage.getSuccssesfulResetPasswordMessageText(),
                "LinkedIn: Log In or Sign Up",
                "Successful Reset Password Message is Wrong");

        Assert.assertTrue(confirmResetPasswordPage.isGoToHomepageButtonDisplayed(),"'Go to Homepage' button is missing");

        confirmResetPasswordPage.clickOnGoToHomepageButton();

        sleep(3000);

        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(webDriver);
        Assert.assertTrue(linkedinHomePage.isPageLoaded(),"Homepage is not loaded");
        Assert.assertEquals(linkedinHomePage.getCurrentTitle(),
                "LinkedIn",
                "Homepage title is wrong");
    }
}