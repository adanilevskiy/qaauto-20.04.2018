import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;

public class LinkedinLoginTest {
   WebDriver webDriver;
    String validUserEmail = "toshnot.1@gmail.com";
    String validCamelCaseUserEmail = "tOsHnOt.1@gmail.com";
    String validUserPassword = "Test123";
    String incorrectUserEmail = "toshnot.1gmail.com";
    String wrongUserEmail = "XXXtoshnot.1@gmail.com";
    String wrongUserPass = "Test123!!";
    String emptyUserEmail = "";
    String emptyUserPassword = "";
    String tooShortPassword = "T";
    String validPasswordWithSpase = "Test 123";

    @BeforeMethod
    public void before() {
        webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");
    }

    @AfterMethod
    public void after() {
        webDriver.close();
    }

    /**
     * Positive cases:
     * - Successful Login Test Valid user email(lowerCase) + Valid Password
     * - Successful Login Test Valid user email(camelCase) + Valid Password
     * - Space in password
     * <p>
     * Negative Cases:
     * - Empty email field
     * - Empty pass field
     * - Wrong email + Valid Pass
     * - Valid email + Wrong Pass
     * - Incorrect email + Valid Pass
     * - Verify Login With Too Short Password
     */

    @DataProvider
    public Object[][] ValidUserDataProvider() {
        return new Object[][]{
                {"toshnot.1@gmail.com","Test123"},
                {"tOsHnOt.1@gmail.com","Test123"},
        };
    }
    @Test(dataProvider = "ValidUserDataProvider")
    public void successfulLoginTest(String validUserEmail, String validUserPassword) throws InterruptedException {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);

        Assert.assertEquals(linkedinLoginPage.getLoginPageTitle(),
                "LinkedIn: Log In or Sign Up",
                "Login Page title is wrong");

        linkedinLoginPage.login(validUserEmail, validUserPassword);
        sleep(5000);
        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(webDriver);

        Assert.assertTrue(linkedinHomePage.isProfileMenuDisplayed(),
                "Profile menu is not displayed after login");
        Assert.assertEquals(linkedinHomePage.getHomePageTitle(),
                "LinkedIn",
                "User is not logged in");
    }

    @Test (enabled = false)
    public void spaceInPasswordFieldTest() throws InterruptedException {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getLoginPageTitle(),
                "LinkedIn: Log In or Sign Up",
                "Login Page title is wrong");

        linkedinLoginPage.login(validUserEmail, validPasswordWithSpase);
        sleep(5000);
        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(webDriver);
        Assert.assertEquals(linkedinHomePage.getHomePageTitle(),
                "LinkedIn",
                "User is not logged in");
    }

    @DataProvider
    public Object[][] EmptyLoginOrPasswordFieldProvider() {
        return new Object[][]{
                {"","Test123"},
                {"toshnot.1@gmail.com",""}
        };
    }
    @Test (dataProvider = "EmptyLoginOrPasswordFieldProvider")
    public void verifyLoginWithEmptyUserNameField(String userName, String userPassword) throws InterruptedException {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getLoginPageTitle(),
                "LinkedIn: Log In or Sign Up",
                "Login Page title is wrong");
        linkedinLoginPage.login(userName, userPassword);
        sleep(5000);
        Assert.assertTrue(linkedinLoginPage.isSignInButtonDisplayed(),
                "Sign In button is missing");
    }

    @Test
    public void verifyLoginWithWrongEmail() throws InterruptedException {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getLoginPageTitle(),
                "LinkedIn: Log In or Sign Up",
                "Login Page title is wrong");

        linkedinLoginPage.login(wrongUserEmail, validUserPassword);
        sleep(5000);
        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(webDriver);

        Assert.assertEquals(loginSubmitPage.getLoginSubmitPageTitle(),
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
    public void verifyLoginWithWrongPassword() throws InterruptedException {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getLoginPageTitle(),
                "LinkedIn: Log In or Sign Up",
                "Page title is wrong");

        linkedinLoginPage.login(validUserEmail, wrongUserPass);
        sleep(5000);
        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(webDriver);

        Assert.assertEquals(loginSubmitPage.getLoginSubmitPageTitle(),
                "Sign In to LinkedIn",
                "User is not redirected to Login page");
        Assert.assertEquals(loginSubmitPage.getGlobalErrorMessageText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "The error message is not displayed");
        Assert.assertEquals(loginSubmitPage.getWrongPasswordErrorMessageText(),
                "Hmm, that's not the right password. Please try again or request a new one.",
                "The error message is not displayed");
    }

    @Test
    public void verifyLoginWithIncorrectEmail() throws InterruptedException {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getLoginPageTitle(),
                "LinkedIn: Log In or Sign Up",
                "Page title is wrong");
        linkedinLoginPage.login(incorrectUserEmail, validUserPassword);
        sleep(5000);
        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(webDriver);

        Assert.assertEquals(loginSubmitPage.getLoginSubmitPageTitle(),
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
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getLoginPageTitle(),
                "LinkedIn: Log In or Sign Up",
                "Page title is wrong");
        linkedinLoginPage.login(validUserEmail, tooShortPassword);

        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(webDriver);
        sleep(5000);
        Assert.assertEquals(loginSubmitPage.getLoginSubmitPageTitle(),
                "Sign In to LinkedIn", "User is not redirected to Login page");

        Assert.assertEquals(loginSubmitPage.getGlobalErrorMessageText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "The error message is not displayed");
        Assert.assertEquals(loginSubmitPage.getToShortPassErrorMessageText(),
                "The password you provided must have at least 6 characters.",
                "The error message is not displayed");
    }
}