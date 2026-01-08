package stepDefinitions.apisteps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class UserSteps {
    private RequestSpecification request;
    private Response response;
    private JsonNode testData;

    // Constructor to load JSON
    public UserSteps() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File("src/test/resources/testdata/Test_Data.json");
        testData = mapper.readTree(jsonFile);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            scenario.log("Scenario FAILED: " + scenario.getName());
        } else {
            scenario.log("Scenario PASSED: " + scenario.getName());
        }
    }

    @Given("the API base URI is {string}")
    public void the_api_base_uri_is(String testCaseId) {
        String baseUri = testData.get(testCaseId).get("url").asText();
        RestAssured.baseURI = baseUri;
        request = RestAssured.given().log().all(); // Logs request details to console
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String testCaseId) {
        String endpoint = testData.get(testCaseId).get("endpoint").asText();
        response = request.when().get(endpoint);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer code) {
        // Logging the response body so it shows in the HTML report
        response.then().log().all();
        response.then().statusCode(code);
    }


    @When("I send a POST request to {string}")
    public void i_send_a_post_request_to(String testCaseId) {
        // 1. Get the endpoint from the JSON file
        String endpoint = testData.get(testCaseId).get("endpoint").asText();

        // 2. Get the request body from the JSON file
        // We convert the JSON node to a String to send it as the API body
        String bodyContent = testData.get(testCaseId).get("requestBody").toString();

        // 3. Execute the POST request
        response = request
                .header("Content-Type", "application/json")
                .body(bodyContent)
                .when()
                .post(endpoint); // Use .post() for POST requests

        // Log the response to the console so you can see what happened
        response.then().log().all();
    }
}