Feature: Complete Purchase
  Scenario: User completes a purchase
    Given the user is on the checkout page
    When user asked to enters their first name, last name, and zip code
    And the user reviews and confirms the order details
    Then a successful order submission should show a confirmation page