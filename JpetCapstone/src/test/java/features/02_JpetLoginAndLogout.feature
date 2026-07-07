Feature: User Login Functionality
  As a registered user
  I want to log into my account

  Background:
    Given the user is on the login page

  @smoke @positive @signin
  Scenario: Successful login with valid credentials
    When User enters username "Sambu9" password "Sambu@123"
    And clicks the login button
    Then user should be logged in and see greeting for user "Sambu"
    
    
   @negative
  Scenario Outline: Failed login with invalid or missing credentials
    When the user enters "<username>" and "<password>"
    And clicks the login button
    Then they should see an error message "<error_message>"
    

    Examples:
      
| username            | password       | error_message                                |
| user                | user123       | Invalid username or password. Signon failed. |
| Sambu9              |                | Enter password                               |
|                     | Sambu@123      | Enter username                               |




@regression @signout
Scenario: Succesful user Logout 
  When the user is logged in with valid username "Sambu9" and valid password "Sambu@123"
  And clicks the login button 
  When user clicks on the account icon and clicks sign out 
  Then user should successfully able to logout 

