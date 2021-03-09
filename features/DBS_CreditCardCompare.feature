Feature: DBS Demo project
  I want to use this feature file for DBS demo testing purpose

  Scenario: DBS Demo project Scenario
    Given user has launched the url
    When user clicks on Cards link
    When user clicks on Credit Cards link
    And user selects the two cards
    And user clicks on compare button
    Then user verifies the following details
      | FieldName                       | Value1   | Value2   |
      | CardName                        | VISA     | VISA     |
      | Min Income Per Annum            | S$30,000 | S$30,000 |
      | Min Income Per Annum Foreigners | S$45,000 | S$45,000 |
      | Annual Fee Waiver               | 1 Year   | 1 Year   |
