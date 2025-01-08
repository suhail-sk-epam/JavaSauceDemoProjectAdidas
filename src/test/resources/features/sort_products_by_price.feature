Feature: Sort Products by Price

  Scenario: User sorts products by price
    Given the user is on the product page
    When the user selects to sort products by price
    Then the products should be displayed in ascending order of price