Feature: Google Search Feature

  Background:
    Given initialized browser lands on 'www.google.com'

    Scenario: Google Search 'Ducks'
      When automation inputs 'Ducks' into the search field
      Then automation validates results are related to 'Ducks'
