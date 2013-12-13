Feature: Applicant Service

Scenario: customer is valid to apply for loan
    Given a customer with id 1 exists
    When age is 28
    And total monetary asset is 40000.90
    Then customer can apply for loan
    
  
Scenario: customer is not valid to apply for loan
    Given a non valid customer with id 2 exists
    When non valid age is 16
    And non total monetary asset is 12.45
    Then customer cannnot apply for loan