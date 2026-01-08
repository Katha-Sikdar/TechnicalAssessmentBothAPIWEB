package stepDefinitions.webSteps;

import businessLogic.FlightBusiness;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class StepsForWeb {
    WebDriver driver;
    JsonNode testData;

    public StepsForWeb() throws IOException {
        // Load the same JSON file used for API
        ObjectMapper mapper = new ObjectMapper();
        testData = mapper.readTree(new File("src/test/resources/testdata/Test_Data.json"));
    }

    @Given("Navigate to the base URI is {string}")
    public void navigate_to_the_base_uri_is(String testCaseId) {
        // 1. Setup Chrome Driver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        // 2. Get URL from JSON
        String webUrl = testData.get(testCaseId).get("url").asText();

        // 3. Navigate
        driver.manage().window().maximize();
        driver.get(webUrl);

        System.out.println("Navigated to: " + driver.getTitle());

    }

    @Then("Click One way radio button")
    public void click_one_way_radio_button() throws InterruptedException {
        FlightBusiness.clickOneWayRadioButton(driver);
        Thread.sleep(20000);
    }
    @Then("Click Departure Date Field and date as tomorrow")
    public void select_departure_date_as_tomorrow() {
        FlightBusiness.clickDepartureDateFieldAndSelectTomorrow(driver);
    }
    @Then("Click search button")
    public void click_search_button() {
       FlightBusiness.clickSearchButton(driver);
    }

    @And("Assert The Search Results Page is displayed")
    public void assertTheSearchResultsPageIsDisplayed() {
        FlightBusiness.AssertSearchResultsPageDisplayed(driver);

    }
    @Then("In the search results:Select the first flight")
    public void in_the_search_results_select_the_first_flight() {
    }
    @Then("Verify that the Sign In page appears.")
    public void verify_that_the_sign_in_page_appears() {
    }


    @Then("Close the Sign In modal\\/browser window.")
    public void closeTheSignInModalBrowserWindow() {
    }
    @Then("Capture the price of the currently listed flights into an array.")
    public void capture_the_price_of_the_currently_listed_flights_into_an_array() {
    }

}