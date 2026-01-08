//package stepDefinitions.apisteps;
//
//import io.cucumber.java.After;
//import io.cucumber.java.Scenario;
//import io.cucumber.java.en.*;
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Assert;
//
//import java.io.File;
//import java.io.IOException;
//
//public class UserSteps {
//    private RequestSpecification request;
//    private Response response;
//    private JsonNode testData;
//
//    // Constructor to load JSON
//    public UserSteps() throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        File jsonFile = new File("src/test/resources/testdata/Test_Data.json");
//        testData = mapper.readTree(jsonFile);
//    }
//
//    @After
//    public void tearDown(Scenario scenario) {
//        if (scenario.isFailed()) {
//            scenario.log("Scenario FAILED: " + scenario.getName());
//        } else {
//            scenario.log("Scenario PASSED: " + scenario.getName());
//        }
//    }
//
//    @Given("the API base URI is {string}")
//    public void the_api_base_uri_is(String testCaseId) {
//        String baseUri = testData.get(testCaseId).get("url").asText();
//        RestAssured.baseURI = baseUri;
//        request = RestAssured.given().log().all(); // Logs request details to console
//    }
//
//    @When("I send a GET request to {string}")
//    public void i_send_a_get_request_to(String testCaseId) {
//        String endpoint = testData.get(testCaseId).get("endpoint").asText();
//        response = request.when().get(endpoint);
//    }
//
//    @Then("the response status code should be {int}")
//    public void the_response_status_code_should_be(Integer code) {
//        // Logging the response body so it shows in the HTML report
//        response.then().log().all();
//        response.then().statusCode(code);
//        // 3. Perform a hard assertion with a custom message
//        Assert.assertEquals(
//                "FAILED: The API returned " + actualCode + " but we expected " + expectedCode,
//                expectedCode.intValue(),
//                actualCode
//        );
//
//        // 4. Specific handling for Server Errors (500)
//        if (actualCode >= 500) {
//            System.out.println("ALERT: Server-side crash detected (500 series).");
//        }
//    }
//
//}


package stepDefinitions.apisteps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;

public class UserSteps {
    private RequestSpecification request;
    private Response response;
    private JsonNode testData;

    // Constructor with local error handling
    public UserSteps() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File jsonFile = new File("src/test/resources/testdata/Test_Data.json");
            testData = mapper.readTree(jsonFile);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Failed to load Test_Data.json: " + e.getMessage());
        }
    }


    @Given("the API base URI is {string}")
    public void the_api_base_uri_is(String testCaseId) {
        // Logic to fetch URL from JSON based on the TestCase ID provided in Gherkin
        JsonNode data = testData.get(testCaseId);
        Assert.assertNotNull("Test Case ID " + testCaseId + " not found in JSON!", data);

        String baseUri = data.get("url").asText();
        RestAssured.baseURI = baseUri;
        request = RestAssured.given().log().all();
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String testCaseId) {
        String endpoint = testData.get(testCaseId).get("endpoint").asText();
        response = request.when().get(endpoint);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer expectedCode) {
        // 1. Log the response details
        response.then().log().all();

        // 2. Define actualCode
        int actualCode = response.getStatusCode();

        // 3. Perform assertion with a custom message
        Assert.assertEquals(
                "The API returned an unexpected status code!",
                expectedCode.intValue(),
                actualCode
        );

        // 4. Alerting for Server Crashes
        if (actualCode >= 500) {
            System.err.println("ALERT: Server-side crash detected (500 series).");
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            scenario.log("Scenario FAILED: " + scenario.getName());
            if (response != null) {
                scenario.log("Response Body: " + response.getBody().asPrettyString());
            }
        } else {
            scenario.log("Scenario PASSED: " + scenario.getName());
        }
    }
}