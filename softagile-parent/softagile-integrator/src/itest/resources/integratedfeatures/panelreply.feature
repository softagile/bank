Feature: Panel Service

  Scenario: Retrieving panel list by country code
    Given A panel country code USA exists
    Then The panel list size is 2

  Scenario: Retrieving country details by country code
    Given A country code for USA exists
    Then The US is ISO country code
