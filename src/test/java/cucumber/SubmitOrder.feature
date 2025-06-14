@tag
  Feature: Purchase the order from ecommerce Website

    Background:
    Given I landed on Ecommerce Page

    @tag
    Scenario Outline: Positive Test Of Submit the order
      Given Logged in with usarName <name> and Password <password>
      When I add product <productName> to Cart
      And Checkout <productName> and submit the order
      Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage

      Examples:
        | name                               | password    | productName     |  |
        | acastromoraga@gmail.com            | 1234        | ZARA COAT 3     |  |
        | alejandro.castromoraga@outlook.com | A12345678!a | ADIDAS ORIGINAL |  |


