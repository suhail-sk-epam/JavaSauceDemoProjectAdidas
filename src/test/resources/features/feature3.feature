Feature: Validate Product Information in PDF

  Scenario: Verify PDF Generation Functionality
    Given the user is logged in and on the Products page
    When the user clicks on the 'Print PDF' button
    Then a PDF of the product page should be generated and downloaded to the user's system

  Scenario: Validate Product Names in PDF
    Given a PDF has been generated from the Products page
    When the user opens the downloaded PDF
    Then all product names in the PDF should match exactly with those on the website

  Scenario: Validate Product Prices in PDF
    Given a PDF has been generated from the Products page
    When the user opens the downloaded PDF
    Then all product prices in the PDF should match exactly with those on the website

  Scenario: Check Consistency of Product Details
    Given a PDF has been generated and product details are visible both in the PDF and on the website
    When the user compares each product's details in the PDF against the corresponding details on the website
    Then all details (like description, availability) of products in the PDF should be consistent with the website