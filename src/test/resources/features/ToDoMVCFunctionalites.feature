Feature: Scenarios to validate ToDoMVC page

Background:
Given user launches todomvc page url

Scenario: Validate navigation to the todomvc list page
					Validate if placeHolder "What needs to be done?" is displayed in UI
Given user launches todomvc page url
Then user should be navigated to the home page
  And I should see "Vue.js • TodoMVC" as page title
  And I should see "What needs to be done?" displayed in the home page

Scenario: Validate creation of a todo list
					Validate whether alphanumeric characters are accepted while adding an item
* user should be navigated to the home page
When user creates a list with following items
	| Buy groceries |
	| Buy RayBan sunglasses |
	| Get dry cleaned clothes |
	| Pizza from Joes pizzaria |
	| send email to zzz@gmail.com |
Then I should see a list created with following items
	| Buy groceries |
	| Buy RayBan sunglasses |
	| Get dry cleaned clothes |
	| Pizza from Joes pizzaria |
	| send email to zzz@gmail.com |
  And I should see All, Active, Completed filters in footer
  
Scenario: Verify various filters from footer
* user should be navigated to the home page
When user creates a list with following items
	| Buy groceries |
	| Buy RayBan sunglasses |
	| Get dry cleaned clothes |
	| Pizza from Joes pizzaria |
	And I click on 2nd checkbox to mark the item as completed
Then I should see following items listed
	| Buy groceries |
	| Get dry cleaned clothes |
	| Pizza from Joes pizzaria |
When I click on "Active" filter from footer
Then I should not see any completed todo items in the list
  And I should see following items listed
		| Buy groceries |
		| Get dry cleaned clothes |
		| Pizza from Joes pizzaria |
  And I should see base url ends with "#/active"
When I click on "Completed" filter from footer
Then I should see only completed todo items in the list
  And I should see following completed items listed
		| Buy RayBan sunglasses |
  And I should see base url ends with "#/completed"
When I click on "All" filter from footer
Then I should see following completed items listed
	| Buy groceries |
	| Buy RayBan sunglasses |
	| Get dry cleaned clothes |
	| Pizza from Joes pizzaria |
  And I should see base url ends with "#/all"

Scenario: Verify Clear completed link from footer
* user should be navigated to the home page
When user creates a list with following items
	| Buy groceries |
	| Buy RayBan sunglasses |
	| Get dry cleaned clothes |
	| Pizza from Joes pizzaria |
	And I click on 2nd checkbox to mark the item as completed
Then I should see following items listed
	| Buy groceries |
	| Get dry cleaned clothes |
	| Pizza from Joes pizzaria |
When I click on "Clear completed" button from footer
Then I should see following items listed
		| Buy groceries |
		| Get dry cleaned clothes |
		| Pizza from Joes pizzaria |

Scenario: Validate marking all items as completed functionality
* user should be navigated to the home page
When user creates a list with following items
	| Buy groceries |
	| Buy RayBan sunglasses |
	| Get dry cleaned clothes |
	| Pizza from Joes pizzaria |
	And I click on down arrow to mark all items as completed
Then I should see only completed todo items in the list
  And I should see following completed items listed
		| Buy groceries |
		| Buy RayBan sunglasses |
		| Get dry cleaned clothes |
		| Pizza from Joes pizzaria |
  And I should see "0 items left" in the footer

Scenario: Validate removing active and completed items by clicking on destroy button 
* user should be navigated to the home page
When user creates a list with following items
	| Buy groceries |
	| Buy RayBan sunglasses |
	| Get dry cleaned clothes |
	| Pizza from Joes pizzaria |
	And I click on 2nd checkbox to mark the item as completed
	And I click on destory button next to "Buy RayBan sunglasses"
	And I click on destory button next to "Pizza from Joes pizzaria"
Then I should see following items listed
		| Buy groceries |
		| Get dry cleaned clothes |

Scenario: Validate editing a todo item
* user should be navigated to the home page
When user creates a list with following items
	| Buy groceries |
Then I should see a list created with following items
	| Buy groceries |
When I edit "Buy groceries" as " @ Whole Foods" in the list
Then I should see a list created with following items
	| Buy groceries @ Whole Foods |

Scenario: Validate whether user is defaulted to home page after clearing all completed items
* user should be navigated to the home page
When user creates a list with following items
	| Buy groceries |
	| Buy RayBan sunglasses |
	| Get dry cleaned clothes |
	| Pizza from Joes pizzaria |
	And I click on down arrow to mark all items as completed
Then I should see only completed todo items in the list
When I click on "Clear completed" button from footer
Then I should see "What needs to be done?" displayed in the home page
  And I should not see any to do item in the list
  And user should be navigated to the home page

@EndToEnd
Scenario: Verify whether list is maintained when user launches the application in same browser session
* user should be navigated to the home page
When user creates a list with following items
	| Buy groceries |
	| Buy RayBan sunglasses |
	| Get dry cleaned clothes |
	| Pizza from Joes pizzaria |
Then I should see a list created with following items
	| Buy groceries |
	| Buy RayBan sunglasses |
	| Get dry cleaned clothes |
	| Pizza from Joes pizzaria |
When user launches todomvc page url in new tab of same browser session
Then I should see a list created with following items
	| Buy groceries |
	| Buy RayBan sunglasses |
	| Get dry cleaned clothes |
	| Pizza from Joes pizzaria |

Scenario: Verify if user doesn't press enter key entered data is not added to the list
* user should be navigated to the home page
When user creates a list with following items
	| Buy groceries |
  And user creates a list with following items but do not press enter
		| Buy RayBan sunglasses |
  And user refreshes the page
Then I should see a list created with following items
	| Buy groceries |
	
	