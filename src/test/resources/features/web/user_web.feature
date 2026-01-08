Feature: User WEB Validation
  @web
  Scenario Outline: Verify URL Navigation to User Page
    Given Navigate to the base URI is "<TestCase>"
#    Then Click on the Username Field
#    When I send a GET request to "<TestCase>"
#    Then the response status code should be 200

    Examples:
      |TestCase|
      |  WEB_TC01    |
#  @api
#  Scenario Outline: Verify user can be created via POST API
#    Given the API base URI is "<TestCase>"
#    When I send a POST request to "<TestCase>"
#    Then the response status code should be 201
#
#    Examples:
#      | TestCase |
#      | WEB_TC04 |