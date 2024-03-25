Feature: Login Action
  Description: This feature will test a LogIn and LogOut functionality

  Scenario Outline: Login with valid credentials
    Given User is on Home Page
    When User navigates to Login Page
    Then User enters "<username>" and "<password>"
    And User should get logged in
    And Message displayed Login Successfully

    Examples:
      | username                | password      |
      | lakshyakumarsirohi@gmail.com | zfmUj2c@U@8p2r |
      | anotheruser@example.com | anotherpassword |
      | yetanotheruser@example.com | yetanotherpassword |
