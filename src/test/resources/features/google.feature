@UI @Google @Search
Feature: Google Search Feature

  Background:
    Given I am on 'https://google.com'

  @TC1 @Ducks
  Scenario: Google Search 'Ducks'
    When I search for 'Ducks'
    Then I should see results related to 'Ducks'
    And browser shuts down
