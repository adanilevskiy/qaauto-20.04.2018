package page;

import Utils.GMailService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Basic class with basic methods for 'Page' classes
 */
public abstract class LinkedinBasePage {
    protected WebDriver webDriver;
    protected static GMailService gMailService = new GMailService();

    /**
     *  Get current webDriver constructor
     * @param webDriver
     */
    public LinkedinBasePage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    /**
     * Basic 'Get page title' method
     * @return current page title
     */
    public String getCurrentTitle(){
        return webDriver.getTitle();
    }

    /**
     * Abstract method
     * Check if current page is loaded
     * @return true/false
     */
    abstract boolean isPageLoaded();


    public WebElement waitUntilElementIsClickable (WebElement webElement, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        return webElement;
    }
}
