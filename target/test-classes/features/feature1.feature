Feature: View Available Products

  Scenario: User views the product list
    Given the user is on the product page
    When the user scrolls through the product list
    Then the products should be displayed with names, prices, and images