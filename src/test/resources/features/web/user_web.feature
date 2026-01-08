Feature: User WEB Validation

  @web
  Scenario Outline: Verify fields for firststrip.com web application
    Given Navigate to the base URI is "<TestCase>"
    Then Click One way radio button
    Then Click Departure Date Field and date as tomorrow
    Then Click search button
    And Assert The Search Results Page is displayed
    Then In the search results:Select the first flight
    And Verify that the Sign In page appears.
   #Then Close the Sign In modal/browser window.
    And Capture the price of the currently listed flights into an array.

    Examples:
      | TestCase |
      | WEB_TC01 |