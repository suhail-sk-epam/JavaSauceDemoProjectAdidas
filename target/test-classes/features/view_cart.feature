Feature: View Shopping Cart
  Scenario:  View products in the cart
    Given the user is on the product page
    When the user clicks on the cart icon
    Then the user should be navigated to the cart page
    And each product should display its name, quantity, and price