Feature: View Available Products
  As a user, I want to view a list of available products so that I can browse the items I may want to purchase.

  Scenario: User views the product list
    Given the user is on the product page
    When the user scrolls through the product list
    Then the user is presented with a list of products including images, names, descriptions, prices, and 'Add to Cart' buttons
    And the products should be sorted according to the default sorting option (e.g., price low to high)

  Scenario: User views the product list
    Given the user is on the product page
    When the user scrolls through the product list
    Then the products should be displayed with names, prices, and images