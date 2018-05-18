import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static java.lang.Thread.sleep;

public class BadCodeExample {

public static void main(String args[]) throws InterruptedException {

    String searchTerm = "Selenium";
    System.out.println("Hello World!!!");
    WebDriver webDriver = new FirefoxDriver();
    webDriver.get("https://www.google.com");
    WebElement searchField = webDriver.findElement(By.name("q"));
    searchField.sendKeys(searchTerm);
    searchField.sendKeys(Keys.ENTER);
    sleep (3000);
    List <WebElement> searchResults = webDriver.findElements(By.xpath("//div[@class='srg']/*[@class='g']"));
    System.out.println(searchResults.size()+ " search results are displayed on the page");
    for(WebElement searchResult : searchResults){
        String searchResultText = searchResult.getText();
        if(searchResultText.contains(searchTerm)) {
            System.out.println("Sea rch term '"+ searchTerm +"' found in result");
            System.out.println("--------------------------------------------");
        }
    }

        webDriver.close();
}

}
