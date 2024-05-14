Feature: ClickUp feature

  Scenario: Create a new Folder in ClickUp Space and check the name
    Given Space is setup and has correct info
    When I add a new folder to space named "API Folder"
    Then I have a folder named API Folder
    When I add a new List named "API List"
    Then I have a list named API List
    When I randomize a name for the task
    And I add a task with a random name to the list
    And I remove the task