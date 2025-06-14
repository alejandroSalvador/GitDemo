@tag
  Feature: Error validation Website

    Background:
    Given I landed on Ecommerce Page

    @tag
    Scenario Outline: Negative Test
      Given I landed on Ecommerce Page
      When Logged in with usarName <name> and Password <password>
      Then "Incorrect email or password." message is displayed

      Examples:
        | name                               | password       |
        | acastromoraga@gmail.com            | 1234qq         |
        | alejandro.castromoraga@outlook.com | A12345678!aqqq |


