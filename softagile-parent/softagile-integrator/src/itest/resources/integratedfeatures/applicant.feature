Feature: Applicant Service

Scenario: customer1 can apply for loan
    Given customer with id 1 exists
    Then one can apply for loan
    
  
Scenario: customer2 cannot apply for loan
    Given customer with id 2 exists
    Then one cannnot apply for loan
