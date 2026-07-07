Feature: Login functionality

  @smoke
  Scenario: SMOKE-01 Successful login with valid credentials
    Given I am on the OrangeHRM login page
    When I enter valid username and password
    And I click the login button
    Then I should be redirected to the Dashboard page

  @smoke
  Scenario: SMOKE-02 Failed login with invalid credentials
    Given I am on the OrangeHRM login page
    When I enter invalid username and password
    And I click the login button
    Then I should see an error message
