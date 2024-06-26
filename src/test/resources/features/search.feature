Feature: The application should be running

  @smoke @test1
  Scenario: simple search
    Given I am on the home page
    When I search for "flower"
    Then I should see the results

  @regression @test2
  Scenario: another search
    Given I am on the home page
    When I search for "jewelry"
    Then I should see more results