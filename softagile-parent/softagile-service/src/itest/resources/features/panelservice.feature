Feature: PanelExcellenceService

  Scenario: Retriving panel list by calling getPanelistsForCountry method API
    Given A country code USA exists
    Then The panel list has size of 2
    Then The following panels exist:
      | id|
      | 1 |
      | 2 |
