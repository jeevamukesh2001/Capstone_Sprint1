Feature: E-commerce Login and Checkout Flow

  Scenario Outline: Login with credentials
    Given the user launches the browser
    And the user navigates to the homepage
    And the user is on the login page
    When the user enters "<username>" and "<password>"
    And clicks the login button
    Then the login result should be "<expectedResult>"

    Examples:
      | username               | password      | expectedResult |
      | jeeva@gmail.com        | jeeva_2001    | error          |
      | jeevamukesh2@gmail.com | Jeeva@2001    | success        |