Feature: Homepage Feature
  This feature tests components on the home page.

  Background:
    Given I navigate to application homepage

  Scenario: Verify homepage title
    Then I should see homepage title

  Scenario: Test 1 - Validate sign in form and enter values
    Then the page should contain "Test 1"
    And the sign in form should contain title "Sample Login"
    And the email field should be present with label "Email address"
    And the password field should be present with label "Password"
    And the login button should be present with label "Sign in"
    And user enters email "test@user.com" and password "testUser" in the login form

  Scenario: Test 2 - Validate list group
    Then the page should contain "Test 2"
    And Test 2 should contain listgroup with 3 values
    And item 2 in the listgroup should contain value "List Item 2" and badge "6" in Test 2

  Scenario: Test 3 - Validate dropdown and select an option
    Then the page should contain "Test 3"
    And Test 3 should contain dropdown with default value "Option 1"
    And user selects "Option 3" in Test 3 dropdown

  Scenario: Test 4 - Validate buttons
    Then the page should contain "Test 4"
    And Test 4 should contain 2 buttons
    And button 1 should be enabled in Test 4
    And button 2 should be disabled in Test 4

  Scenario: Test 5 - Wait for button to click and validate behavior
    Then the page should contain "Test 5"
    And user waits to display button in Test 5
    And the button should be enabled in Test 5
    When user clicks the button in Test 5
    Then an alert should display with message "You clicked a button!"
    And the button should be disabled in Test 5

  Scenario: Test 6 - Find and validate value in table cell
    Then the page should contain "Test 6"
    And the table should contain "Ventosanzap" in cell with coordinates (2, 2)