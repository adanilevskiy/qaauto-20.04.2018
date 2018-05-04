import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LinkedinLoginTest {

    @Test
    public void successfulLoginTest() throws InterruptedException {
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");

        Assert.assertEquals(webDriver.getTitle(), "LinkedIn: Log In or Sign Up", "Page title is wrong");

        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));

        userEmailField.sendKeys("toshnot.1@gmail.com");
        userPasswordField.sendKeys("Test123");
        signInButton.click();
        sleep (5000);

        Assert.assertEquals(webDriver.getTitle(), "LinkedIn", "User is not logged in");

        webDriver.close();
    }

}
