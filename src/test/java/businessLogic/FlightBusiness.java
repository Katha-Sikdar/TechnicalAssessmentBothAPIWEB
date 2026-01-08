package businessLogic;

import locators.pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FlightBusiness {


    public static void clickOneWayRadioButton(WebDriver driver) {
        WebElement element = driver.findElement(By.xpath(pageObjects.OneWayRadioButton));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }


    public static void clickDepartureDateFieldAndSelectTomorrow(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Click the Departure field to open the calendar
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pageObjects.DepartureDateField))).click();
        // 2. Wait for the calendar to be visible and click 'Tomorrow'
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pageObjects.TomorrowDate))).click();
        System.out.println("Successfully selected tomorrow's date.");
    }

    public static void clickSearchButton(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pageObjects.SearchBtn))).click();
        System.out.println("Search button clicked.");
    }


    public static void AssertSearchResultsPageDisplayed(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pageObjects.SearchRslt)));
            System.out.println("Success: Search Results Page is displayed.");
        } catch (Exception e) {
            System.out.println("Failure: Search Results Page was NOT displayed within 15 seconds.");
            throw e; // Rethrow to fail the test
        }
    }
}
