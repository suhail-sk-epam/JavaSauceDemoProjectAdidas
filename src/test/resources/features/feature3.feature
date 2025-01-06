Feature: Validate Product Information in PDF

  Scenario: Validate Product Names and Prices in PDF
    Given the user is on the product page
    When a PDF has been generated from the Products page
    Then the PDF should contain the expected product names and prices