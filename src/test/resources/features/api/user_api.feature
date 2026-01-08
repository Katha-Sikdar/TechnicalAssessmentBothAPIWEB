Feature: User API Validation
@api
  Scenario Outline: Verify user details can be retrieved via API
    Given the API base URI is "<TestCase>"
    When I send a GET request to "<TestCase>"
    Then the response status code should be 200

    Examples:
             |TestCase|
              |  API_TC01      |
  @api
  Scenario Outline: Verify user can be created via POST API
    Given the API base URI is "<TestCase>"
    When I send a POST request to "<TestCase>"
    Then the response status code should be 201

    Examples:
      | TestCase |
      | API_TC02 |