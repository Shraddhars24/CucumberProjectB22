Feature: Add employee with HRMS

  Background:
    #Given user is able to access HRMS application
    When user enters valid username and password
    And user clicks on login button
    Then user is able to see dashboard page
    When user clicks on PIM option
    And user clicks on Add employee option


    @withEmpID @addEmp
  Scenario: Adding an employee without providing a unique employee ID
    #Given user is able to access HRMS application
    #When user enters valid username and password
    #And user clicks on login button
    #Then user is able to see dashboard page
    #When user clicks on PIM option
    #And user clicks on Add employee option
    And user enters firstname and lastname
    And user clicks on save button
    Then the system should generate a unique employee ID
    And the employee should be added successfully

      @withoutEmpID @addEmp
    Scenario: Adding an employee by providing a unique employee ID
      #Given user is able to access HRMS application
      #When user enters valid username and password
      #And user clicks on login button
      #Then user is able to see dashboard page
      #When user clicks on PIM option
      #And user clicks on Add employee option
      And user enters firstname and lastname
        Then user providing a unique employee ID
        And user clicks on save button
      And the employee should be added successfully

        @errorMessage @addEmp
      Scenario: System provides appropriate error message
        When user enters incomplete or invalid information
        And user clicks on save button
        Then user can see the error message Required






