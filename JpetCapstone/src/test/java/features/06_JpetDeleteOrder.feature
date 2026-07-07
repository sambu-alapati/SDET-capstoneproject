Feature: Order Delete Functionality
 
 
@orderDelete
Scenario: Delete Existing Order Successfully
   Given the user is on the login page
   When the user is logged in with valid username "Nagasai" and valid password "sai123"
   And clicks the login button
   When user clicks on My Orders
   And user selects an existing order
   And user clicks on the Delete Order button
   Then order should be deleted successfully
   
@noOrders
Scenario: Verify No Orders Message
   Given the user is on the login page
   When the user is logged in with valid username "zeroorders" and valid password "sai123"
   And clicks the login button
   When user clicks on My Orders
   Then user should see no orders message "You have placed no orders."