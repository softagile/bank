Feature: A customer can apply for loan if it has an approved age by bank and he 
         has enogh money in all his accounts (checking, saving money market and etc...). 
         As of now, the minimum age is 28 and minimum monetory asset is $10,000

Scenario: david is valid to apply for loan
    Given first customer with id 10 exists
    When age is 28
    And total monetary asset is 233888.90
    Then david can apply for loan
    
  
Scenario: john does not have enogh money to apply for loan
    Given second customer with id 20 exists
    When second valid age is 38
    And second monetary asset is 12.45
    Then customer john cannnot apply for loan
    
    
Scenario: bob does not have enogh age to apply for loan
    Given third customer with id 30 exists
    When thrird valid age is 12
    And  thrird monetary asset is 233888.90
    Then customer bob cannnot apply for loan
    
    
Scenario: joe does not have enogh age and money to apply for loan
    Given forth customer with id 30 exists
    When forth valid age is 12
    And  forth monetary asset is 450.90
    Then customer joe cannnot apply for loan         