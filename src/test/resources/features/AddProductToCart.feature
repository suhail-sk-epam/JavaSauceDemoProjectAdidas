Feature: Add Product to Shopping Cart

  Scenario: Add a single product to the cart
    Given the user is on the product page
    When the user clicks on the Add to Cart button for a product
    Then the product should be added to the shopping cart
    And the cart icon should reflect the updated cart count