package page;

import org.openqa.selenium.WebDriver;

public abstract class LinkedinBasePage {
    protected WebDriver webDriver;

    public LinkedinBasePage(WebDriver webDriver){
        this.webDriver = webDriver;
    }
    public String getCurrentTitle(){
        return webDriver.getTitle();
    }
    abstract boolean isPageLoaded();
}
