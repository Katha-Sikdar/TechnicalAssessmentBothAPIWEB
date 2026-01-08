package stepDefinitions.webSteps;

import io.cucumber.java.en.Given;
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

        // Optional: Close browser after 3 seconds for testing
        // driver.quit();
    }
}