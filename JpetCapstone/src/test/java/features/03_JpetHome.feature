Feature: Home Page Functionalities 


Background:
   Given the user is on the login page
   When the user is logged in with valid username "jpetuser123" and valid password "Test@1234"
   And clicks the login button
 
 @click
  Scenario: User Searchbar Functionality 
    When User enter "Amazon Parrot" bird in searchbar
    And Clicks the search button 
    Then User Should able to see data related to it
    
 @enterkey   
 Scenario: User Searchbar Functionality using Enter Key
    When User enter "Amazon Parrot" bird in searchbar
    And Presses the Enter key on the keyboard
    Then User Should able to see data related to it


@navbarelementsNavigation 
Scenario: Navbar Elements Navigation 
When user clicks the Bird navbar element and clicks return to main menu 
When user clicks the Cat navbar element and clicks return to main menu
When user clicks the Dog navbar element and clicks return to main menu
When user clicks the Reptile navbar element and clicks return to main menu 
When user clicks the Fish navbar element and clicks return to main menu 
When user clicks the Cart navbar element and clicks return to main menu 
When user clicks the My Orders navbar element 



@imageNavigation
Scenario: Image Navigation Functionality 
When user clicks on the Bird image and clicks return to main menu 
When user clicks on the Fish image and clicks return to main menu
When user clicks on the Dog image and clicks return to main menu
When user clicks on the Reptile image and clicks return to main menu
When user clicks on the Cat image and clicks return to main menu



@accountManagement
Scenario: User Account Management 
When user clicks on the account icon and goes to my Account 
And user updates the password "Test@1234" and confirmpassword "Test@1234" 
Then user should see the message "Your account has been updated."
 



