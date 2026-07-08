@regression @cart-feature @cart
Feature: Shopping Cart Functionality
 
  Background:
    Given user is logged in
 
  @smoke @sanity @add-to-cart @functional
  Scenario: Add single item to cart
    When user selects "Fish" category
    And user selects first product
    And user adds first item to cart
    Then cart should contain 1 item
 
  @add-to-cart @functional
  Scenario: Add multiple items to cart
    When user adds "Fish" product to cart
    And user returns to main menu
    And user adds "Dogs" product to cart
    Then cart should contain 2 items
 
  @remove-from-cart @functional
  Scenario: Remove single item from cart
    When user selects "Fish" category
    And user selects first product
    And user adds first item to cart
    Then cart should contain 1 item
    When user removes item from cart
    Then cart should be empty
 
  @remove-from-cart @functional
  Scenario: Remove all items from cart
    When user adds "Fish" product to cart
    And user returns to main menu
    And user adds "Dogs" product to cart
    Then cart should contain 2 items
    When user removes all items from cart
    Then cart should be empty
 
  @remove-from-cart @functional
  Scenario: Remove specific item from multiple items
    When user adds "Fish" product to cart
    And user returns to main menu
    And user adds "Dogs" product to cart
    Then cart should contain 2 items
    When user removes first item from cart
    Then cart should contain 1 item
 
  @cart-modification @functional
  Scenario: Update quantity for single item
    When user selects "Fish" category
    And user selects first product
    And user adds first item to cart
    Then cart should contain 1 item
    When user updates quantity to "3"
    Then quantity should be updated to "3"
 
  @cart-modification @functional
  Scenario: Update cart after multiple modifications
    When user adds "Fish" product to cart
    And user returns to main menu
    And user adds "Dogs" product to cart
    Then cart should contain 2 items
    When user removes first item from cart
    And user updates quantity to "4"
    Then quantity should be updated to "4"
    And cart should contain 1 item
 
  @checkout-validation @positive @ui-verification
  Scenario: Verify checkout button is displayed
    When user selects "Fish" category
    And user selects first product
    And user adds first item to cart
    Then proceed to checkout button should be displayed
