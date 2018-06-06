package test;

import org.testng.Assert;
import org.testng.annotations.*;
import page.*;


/**
 * Tests for Login
 */
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
     * - Successful reset password
     */

    @DataProvider
    public Object[][] ValidUserDataProvider(){
        return new Object[][]{
                {"smithbod58@gmail.com","Test123Test123"},
                {"sMiThBod58@gmail.com","Test123Test123"},
        };
    }
    @Test(dataProvider = "ValidUserDataProvider")
    public void successfulLoginTest(String validUserEmail, String validUserPassword){
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),
                "Login Page is not loaded");

        LinkedinHomePage linkedinHomePage = linkedinLoginPage.loginWithValidUserData(validUserEmail, validUserPassword);
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
    public void loginWithInvalidPasswordTest(String userEmail, String userPassword){
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),
                "Login Page is not loaded");

        LinkedinLoginSubmitPage linkedinLoginSubmitPage = linkedinLoginPage.loginWithInvalidUserData(userEmail, userPassword);
        Assert.assertTrue(linkedinLoginSubmitPage.isPageLoaded(), "Page is not loaded");

        Assert.assertEquals(linkedinLoginSubmitPage.getGlobalErrorMessageText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "The error message is not displayed");
        Assert.assertEquals(linkedinLoginSubmitPage.getPasswordErrorMessageText(),
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
    public void verifyLoginWithEmptyUserNameOrUserPassField(String userName, String userPassword){
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),
                "Login Page is not loaded");

        linkedinLoginPage.loginWithEmptyFields(userName, userPassword);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),
                "Linkedin Login Page is loaded");
    }

    @Test
    public void verifyLoginWithWrongEmail(){
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),
                "Login Page is not loaded");

        LinkedinLoginSubmitPage linkedinLoginSubmitPage = linkedinLoginPage.loginWithInvalidUserData("XXXsmithbod58@gmail.com", "Test1234!");
        Assert.assertTrue(linkedinLoginSubmitPage.isPageLoaded(), "Page is not loaded");

        Assert.assertEquals(linkedinLoginSubmitPage.getGlobalErrorMessageText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "The error message is not displayed");
        Assert.assertEquals(linkedinLoginSubmitPage.getEmailErrorMessageText(),
                "Hmm, we don't recognize that email. Please try again.",
                "The error message is not displayed");
    }

    @Test
    public void verifyLoginWithIncorrectEmail(){
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),
                "Login Page is not loaded");

        LinkedinLoginSubmitPage linkedinLoginSubmitPage = linkedinLoginPage.loginWithInvalidUserData("smithbod58gmail.com", "Test1234!");
        Assert.assertTrue(linkedinLoginSubmitPage.isPageLoaded(), "Page is not loaded");

        Assert.assertEquals(linkedinLoginSubmitPage.getGlobalErrorMessageText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "The error message is not displayed");
        Assert.assertEquals(linkedinLoginSubmitPage.getEmailErrorMessageText(),
                "Please enter a valid email address.",
                "The error message is not displayed");
    }

    @Test
    public void verifyLoginWithTooShortPassword(){
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),
                "Login Page is not loaded");

        LinkedinLoginSubmitPage linkedinLoginSubmitPage = linkedinLoginPage.loginWithInvalidUserData("smithbod58@gmail.com", "T");
        Assert.assertTrue(linkedinLoginSubmitPage.isPageLoaded(), "Page is not loaded");

        Assert.assertEquals(linkedinLoginSubmitPage.getGlobalErrorMessageText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "The error message is not displayed");
        Assert.assertEquals(linkedinLoginSubmitPage.getPasswordErrorMessageText(),
                "The password you provided must have at least 6 characters.",
                "The error message is not displayed");
    }

    @Test
    public void LinkedinSuccessfulResetPasswordTest(){
        String userEmail = "smithbod58@gmail.com";
        String newPassword = "Test123Test123";

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),
                "Login Page is not loaded");

        LinkedinResetPasswordPage linkedinResetPasswordPage = linkedinLoginPage.clickOnForgotPasswordLink();
        Assert.assertTrue(linkedinResetPasswordPage.isPageLoaded(),
                "Reset Password Page is not loaded");

        LinkedinResetPasswordSubmitPage linkedinResetPasswordSubmitPage = linkedinResetPasswordPage.submitUserEmail(userEmail);
        Assert.assertTrue(linkedinResetPasswordSubmitPage.isPageLoaded(),"Reset Password Submit Page is not loaded");

        String resetPasswordLink = linkedinResetPasswordSubmitPage.getResetPasswordLinkFromEmail(userEmail);

        LinkedinSetNewPasswordPage linkedinSetNewPasswordPage = linkedinResetPasswordSubmitPage.navigateToResetPasswordLink(resetPasswordLink);
        Assert.assertTrue(linkedinSetNewPasswordPage.isPageLoaded(),"Set new Password Page is not loaded");

        LinkedinConfirmResetPasswordPage linkedinConfirmResetPasswordPage = linkedinSetNewPasswordPage.setNewPassword(newPassword);
        Assert.assertTrue(linkedinConfirmResetPasswordPage.isPageLoaded(),"Confirm reset password Page is not loaded");

        LinkedinHomePage linkedinHomePage = linkedinConfirmResetPasswordPage.clickOnGoToHomepageButton();
        Assert.assertTrue(linkedinHomePage.isPageLoaded(),"Homepage is not loaded");
    }
}