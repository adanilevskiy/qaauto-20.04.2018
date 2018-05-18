import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginSubmitPage {
    private WebDriver webDriver;
    private WebElement globalErrorMessage;
    private WebElement toShortPassErrorMessage;
    private WebElement wrongPasswordErrorMessage;
    private WebElement wrongEmailErrorMessage;
    private WebElement incorrectEmailErrorMessage;

    public LoginSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        initElements();
    }

    private void initElements() {
        globalErrorMessage = webDriver.findElement(By.xpath("//div[@class='alert error']//strong"));
        toShortPassErrorMessage = webDriver.findElement(By.xpath("//ul[@class='form-fields']//span[@id='session_password-login-error']"));
        wrongPasswordErrorMessage = webDriver.findElement(By.xpath("//ul[@class='form-fields']//span[@id='session_password-login-error']"));
        wrongEmailErrorMessage = webDriver.findElement(By.xpath("//ul[@class='form-fields']//span[@id='session_key-login-error']"));
        incorrectEmailErrorMessage = webDriver.findElement(By.xpath("//ul[@class='form-fields']//span[@id='session_key-login-error']"));
    }

    public String getGlobalErrorMessageText() {
        return globalErrorMessage.getText();
    }
    public String getToShortPassErrorMessageText(){
        return   toShortPassErrorMessage.getText();
    }
    public String getWrongEmailErrorMessageText(){
        return   wrongEmailErrorMessage.getText();
    }
    public String getWrongPasswordErrorMessageText(){
        return   wrongPasswordErrorMessage.getText();
    }
    public String getIncorrectEmailErrorMessageText(){
        return   incorrectEmailErrorMessage.getText();
    }
    public String getLoginSubmitPageTitle(){
        return webDriver.getTitle();
    }
}
