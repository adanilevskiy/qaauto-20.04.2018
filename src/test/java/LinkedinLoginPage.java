import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinLoginPage extends LinkedinBasePage{

    private WebElement userEmailField;
    private WebElement userPasswordField;
    private WebElement signInButton;
    private WebElement forgotPasswordLink;

    public LinkedinLoginPage(WebDriver webDriver) {
        super(webDriver);
        initElements();
    }

    @Override
    boolean isPageLoaded() {
        return signInButton.isDisplayed();
    }

    private void initElements(){
        userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));
        forgotPasswordLink = webDriver.findElement(By.xpath("//a[@class='link-forgot-password']"));
    }

    public void login(String userEmail, String userPassword) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInButton.click();
    }
    public void clickOnForgotPasswordLink(){
        signInButton.click();
    }

    public boolean isForgotPasswordLinkDisplayed(){
        return forgotPasswordLink.isDisplayed();
    }

}
