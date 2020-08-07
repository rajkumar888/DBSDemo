Feature: DBS Demo project
  I want to use this feature file for dbs demo testing purpose

  Scenario: DBS Demo project Scenario
    Given user has launched the url
    When user clicks on Learn More button
    And user Scroll down and navigates to Singapore on the left panel
    And user Read and writes the table in excel sheet
    And user Navigates to About in the header tabs
    And user Navigates to Who we are tab
    And user Navigates to  Our Awards & Accolades
    Then Validates the total number of awards displayed on the page is 22
    Then Validates all the award name and caption of the awards mentioned in the below table
      | Award Name     | Caption of the award                                    |
      | A World First  | Euromoney                                               |
      | The Banker     | Bank of the Year 2018                                   |
      | Global Finance | Best Bank in the World 2018                             |
      | Euromoney      | Awards For Excellence                                   |
      | Global Finance | World's Best Banks                                      |
      | Global Finance | World's Best Investment Banks and Derivatives Providers |
    When user prints in the cucumber report in the form of a table
