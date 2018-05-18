import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;


import static java.lang.Thread.sleep;

public class LinkedinLoginTest {
    WebDriver webDriver;

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
                {"toshnot.1@gmail.com","Test123"},
                {"tOsHnOt.1@gmail.com","Test123"},
        };
    }
    @Test(dataProvider = "ValidUserDataProvider")
    public void successfulLoginTest(String validUserEmail, String validUserPassword) throws InterruptedException {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),
                "Login Page is not loaded");

        linkedinLoginPage.login(validUserEmail, validUserPassword);
        sleep(5000);
        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(webDriver);
        Assert.assertTrue(linkedinHomePage.isPageLoaded(),
                "Home Page is not loaded");

    }
    @DataProvider
    public Object[][] incorrectPasswordValues() {
        return new Object[][]{
                {"toshnot.1@gmail.com","Test 123"},
                {"toshnot.1@gmail.com","Test1234"}
        };
    }
    @Test (dataProvider = "incorrectPasswordValues")
    public void loginWithInvalidPasswordTest(String validUserEmail, String invalidUserPassword) throws InterruptedException {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
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
                {"toshnot.1@gmail.com",""}
        };
    }
    @Test (dataProvider = "emptyLoginOrPasswordFieldProvider")
    public void verifyLoginWithEmptyUserNameOrUserPassField(String userName, String userPassword) throws InterruptedException {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
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
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getCurrentTitle(),
                "LinkedIn: Log In or Sign Up",
                "Login Page title is wrong");

        linkedinLoginPage.login("XXXtoshnot.1@gmail.com", "Test123");
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
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getCurrentTitle(),
                "LinkedIn: Log In or Sign Up",
                "Page title is wrong");
        linkedinLoginPage.login("toshnot.1gmail.com", "Test123");
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
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getCurrentTitle(),
                "LinkedIn: Log In or Sign Up",
                "Page title is wrong");
        linkedinLoginPage.login("toshnot.1@gmail.com", "T");

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
}