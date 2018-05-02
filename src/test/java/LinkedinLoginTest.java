import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LinkedinLoginTest {

    @Test
    public void successfulLoginTest() {
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");

        Assert.assertEquals(webDriver.getTitle(), "Expected", "Page title is wrong");

        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));


    }

}
