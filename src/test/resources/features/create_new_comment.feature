@smokeTest
Feature: Create new comment

  As user signed in to github site
  I want to be able to create new comment for gist

  Background:
    Given user is signed in to github home page

  Scenario: User creates new gist (happy pass)
    When user create new gist via menu "Create new"
    Then user can see opened page of created gist
    And header path contains the name of created gist