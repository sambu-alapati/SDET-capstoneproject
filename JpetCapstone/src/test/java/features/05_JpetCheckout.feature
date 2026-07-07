@checkout
Feature: Checkout Functionality
 
  Background:
    Given user is logged in
    And user has 1 item in cart
 
  @smoke
  Scenario: Complete checkout using default details
    When user proceeds to checkout
    And user continues with default checkout details
    And user confirms the order
    Then order should be placed successfully
 
  @regression
  Scenario: Complete checkout with modified billing details
    When user proceeds to checkout
    And user updates checkout details
    And user continues checkout process
    And user confirms the order
    Then order should be placed successfully
 
  @regression
  Scenario: Verify order number generation
    When user proceeds to checkout
    And user continues with default checkout details
    And user confirms the order
    Then order number should be generated
 
  @regression
  Scenario: Verify order details page
    When user proceeds to checkout
    And user continues with default checkout details
    And user confirms the order
    Then ordered item details should be displayed
 
  @regression 
  Scenario: Delete newly created order
    When user proceeds to checkout
    And user continues with default checkout details
    And user confirms the order
    And user captures generated order id
    And user deletes the order
    Then deleted order should not be available
    
    
    
    
 