Feature: User Authentication
  Scenario: Successful login with valid credentials
    Given the user is on the login API
    When the user submits valid credentials
    Then the API should return a 200 OK status
