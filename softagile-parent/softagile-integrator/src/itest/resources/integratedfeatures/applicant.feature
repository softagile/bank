Feature: Applicant Service

Scenario: customer1 can apply for loan
    Given customer1 with id 1 exists
    Then customer1 can apply for loan
    
  
Scenario: customer2 cannot apply for loan
    Given customer2 with id 2 exists
    Then customer2 cannnot apply for loan
