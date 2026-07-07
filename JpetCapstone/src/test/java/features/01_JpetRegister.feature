Feature: User Registration
 
 @register
  Scenario Outline: Register new user with valid details
 
    Given User is on JPetStore Registration page
 
    When User enters User ID "<userid>"
    And User enters Password "<password>"
    And User enters Confirm Password "<confirmpassword>"
 
    And User enters First Name "<firstname>"
    And User enters Last Name "<lastname>"
    And User enters Email "<email>"
    And User enters Phone "<phone>"
 
    And User enters Address1 "<address1>"
    And User enters Address2 "<address2>"
    And User enters City "<city>"
    And User enters State "<state>"
    And User enters Zip "<zip>"
    And User enters Country "<country>"
 
    And User selects Language Preference "<language>"
    And User selects Favourite Category "<category>"
 
    And User clicks Save Account Information button
 
    Then User account should be created successfully
 
    Examples:
      | userid      | password | confirmpassword | firstname | lastname | email             | phone      | address1   | address2 | city      | state     | zip    | country | language | category |
      | jpetuser123 | Test@123 | Test@123        | Naga      | Sai      | nagasai@gmail.com | 9876543210 | Whitefield | ITPL     | Bangalore | Karnataka | 560066 | India   | english  | FISH     |
 
  Scenario: Verify mandatory field error messages
 
    Given User is on JPetStore Registration page
 
    When User clicks Save Account Information button
 
    Then User ID error message should be displayed as "must not be blank"
    And Password error message should be displayed as "must not be blank"
    And First Name error message should be displayed as "must not be blank"
    And Last Name error message should be displayed as "must not be blank"
    And Email error message should be displayed as "must not be blank"
    And Phone error message should be displayed as "must not be blank"
    And Address1 error message should be displayed as "must not be blank"
    And City error message should be displayed as "must not be blank"
    And State error message should be displayed as "must not be blank"
    And Zip error message should be displayed as "must not be blank"
    And Country error message should be displayed as "must not be blank"