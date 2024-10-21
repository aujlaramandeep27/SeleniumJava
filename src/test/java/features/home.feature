Feature: Homepage Feature
  This feature tests components on the home page.

  Background:
    Given I navigate to application homepage

  Scenario: Verify homepage title
    Then I should see homepage title

#  Scenario: Get articles on homepage
#    Then the homepage should contain articles
#
#  Scenario: Print unique icons and their occurrences
#    Then I should print unique icons and their occurrences
#
#  Scenario Outline: Add two numbers <num1> & <num2>
#    When I add <num1> and <num2>
#    Then the result should be <total>
#
#    Examples:
#      | num1 | num2 | total |
#      | -2   | 3    | 1     |
#      | 10   | 15   | 25    |
#      | 99   | -99  | 0     |
#      | -1   | -10  | -11   |
