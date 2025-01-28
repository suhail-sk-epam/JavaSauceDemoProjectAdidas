Feature: User Authentication

  Scenario: Successful Login
    Given a registered user with valid credentials
    When the user enters their username and password
    And the user clicks the Login button
    Then the user should be logged in and redirected to their dashboard

  Scenario: Failed Login
    Given a registered user with invalid credentials
    When the user attempts to log in
    Then the system should display an error message and remain on the login page