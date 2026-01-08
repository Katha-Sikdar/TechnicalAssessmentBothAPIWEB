Feature: User API Validation for both Positive and Negative Cases

  @api
  Scenario Outline: Verify user details can be retrieved via API
    Given the API base URI is "<TestCase>"
    When I send a GET request to "<TestCase>"
    Then the response status code should be <ExpectedCode>

    Examples:
      | TestCase | ExpectedCode | Description           |
      | API_TC01 | 200          | Success Case          |
#      | API_TC01 | 404          | Not Found Case        |
#      | API_TC01 | 500          | Internal Server Error |
