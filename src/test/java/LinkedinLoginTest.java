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

    @BeforeMethod
    public void before() {
        webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");
    }

    @AfterMethod
    public void after() {
        webDriver.close();
    }


    @Test
    public void getLocalTime() throws InterruptedException {
        // Create object of SimpleDateFormat class and decide the format
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss ");
        Date date = new Date();
        System.out.println(dateFormat.format(date));

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

    @Test
    public void successfulLoginTest(){
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);

        Assert.assertEquals(linkedinLoginPage.getCurrentPageTitle(), "LinkedIn: Log In or Sign Up",
                "Login Page title is wrong");

        linkedinLoginPage.login("toshnot.1@gmail.com", "Test123");
        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(webDriver);
        Assert.assertTrue(linkedinHomePage.isFrofileMenuDisplayed(), "Profile menu is not displayed after login");

        Assert.assertEquals(linkedinLoginPage.getCurrentPageTitle(), "LinkedIn", "User is not logged in");
    }

    @Test
    public void successfulLoginWithCamelCaseEmailTest() throws InterruptedException {

        Assert.assertEquals(webDriver.getTitle(), "LinkedIn: Log In or Sign Up", "Page title is wrong");

        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));

        userEmailField.sendKeys("ToShnOt.1@gmail.com");
        userPasswordField.sendKeys("Test123");
        signInButton.click();
        sleep(3000);

        Assert.assertEquals(webDriver.getTitle(), "LinkedIn", "User is not logged in");
    }

    @Test
    public void spaceInPasswordFieldTest() throws InterruptedException {



        Assert.assertEquals(webDriver.getTitle(), "LinkedIn: Log In or Sign Up", "Page title is wrong");

        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));

        userEmailField.sendKeys("toshnot.1@gmail.com");
        userPasswordField.sendKeys("Test 123");
        signInButton.click();
        sleep(3000);

        Assert.assertEquals(webDriver.getTitle(), "LinkedIn", "User is not logged in");
    }

    @Test
    public void verifyLoginWithEmptyUserNameField(){

        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));
        Assert.assertTrue(signInButton.isDisplayed(), "Sign In button is missing");

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        linkedinLoginPage.login("", "Test123");
        Assert.assertTrue(linkedinLoginPage.isSignInButtomDisplayed(), "Sign In button is missing");
    }

    @Test
    public void verifyLoginWithEmptyPasswordField() throws InterruptedException {

        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));
        Assert.assertTrue(signInButton.isDisplayed(), "Sign In button is missing");

        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        userEmailField.sendKeys("toshnot.1@gmail.com");

        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        userPasswordField.sendKeys("");
        signInButton.click();
        sleep(3000);

        Assert.assertFalse(signInButton.isDisplayed(), "Sign In button is missing");
    }

    @Test
    public void verifyLoginWithWrongEmail() throws InterruptedException {

        Assert.assertEquals(webDriver.getTitle(), "LinkedIn: Log In or Sign Up", "Page title is wrong");

        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));

        userEmailField.sendKeys("X123toshnot.1@gmail.com");
        userPasswordField.sendKeys("Test123");
        signInButton.click();
        sleep(3000);


        Assert.assertEquals(webDriver.getTitle(), "Sign In to LinkedIn", "User is not redirected to Login page");
        Assert.assertEquals(webDriver.findElement(By.xpath("//div[@class='alert error']//strong")).getText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "The error message is not displayed");
        Assert.assertEquals(webDriver.findElement(By.xpath("//ul[@class='form-fields']//span[@id='session_key-login-error']")).getText(),
                "Hmm, we don't recognize that email. Please try again.",
                "The error message is not displayed");
    }

    @Test
    public void verifyLoginWithWrongPassword() throws InterruptedException {

        Assert.assertEquals(webDriver.getTitle(), "LinkedIn: Log In or Sign Up", "Page title is wrong");

        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));

        userEmailField.sendKeys("toshnot.1@gmail.com");
        userPasswordField.sendKeys("Test1234");
        signInButton.click();
        sleep(3000);


        Assert.assertEquals(webDriver.getTitle(), "Sign In to LinkedIn", "User is not redirected to Login page");
        Assert.assertEquals(webDriver.findElement(By.xpath("//div[@class='alert error']//strong")).getText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "The error message is not displayed");
        Assert.assertEquals(webDriver.findElement(By.xpath("//ul[@class='form-fields']//span[@id='session_password-login-error']")).getText(),
                "Hmm, we don't recognize that email. Please try again.",
                "The error message is not displayed");
    }

    @Test
    public void verifyLoginWithIncorrectEmail() throws InterruptedException {

        Assert.assertEquals(webDriver.getTitle(), "LinkedIn: Log In or Sign Up", "Page title is wrong");

        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));

        userEmailField.sendKeys("toshnot.1gmail.com");
        userPasswordField.sendKeys("Test123");
        signInButton.click();
        sleep(3000);


        Assert.assertEquals(webDriver.getTitle(), "Sign In to LinkedIn", "User is not redirected to Login page");
        Assert.assertEquals(webDriver.findElement(By.xpath("//div[@class='alert error']//strong")).getText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "The error message is not displayed");
        Assert.assertEquals(webDriver.findElement(By.xpath("//ul[@class='form-fields']//span[@id='session_key-login-error']")).getText(),
                "Please enter a valid email address.",
                "The error message is not displayed");
    }

    @Test
    public void verifyLoginWithTooShortPassword() throws InterruptedException {

        Assert.assertEquals(webDriver.getTitle(), "LinkedIn: Log In or Sign Up", "Page title is wrong");

        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));

        userEmailField.sendKeys("toshnot.1@gmail.com");
        userPasswordField.sendKeys("T");
        signInButton.click();
        sleep(3000);


        Assert.assertEquals(webDriver.getTitle(), "Sign In to LinkedIn", "User is not redirected to Login page");
        Assert.assertEquals(webDriver.findElement(By.xpath("//div[@class='alert error']//strong")).getText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "The error message is not displayed");
        Assert.assertEquals(webDriver.findElement(By.xpath("//ul[@class='form-fields']//span[@id='session_password-login-error']")).getText(),
                "The password you provided must have at least 6 characters.",
                "The error message is not displayed");
    }
}