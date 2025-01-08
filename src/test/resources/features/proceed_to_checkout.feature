Feature: Proceed to Checkout
  Scenario: Navigate to checkout from cart page
    Given the user is on the cart page
    When the user clicks on the Checkout button
    Then the user should be navigated to the checkout page
    And user asked to enters their first name, last name, and zip code