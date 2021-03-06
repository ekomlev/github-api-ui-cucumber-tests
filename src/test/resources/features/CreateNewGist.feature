@smokeTest @thread-first
Feature: Create new gist

  As user signed into github site
  I want to be able to create new gist

  Background:
    Given user is signed into github home page

  Scenario: User creates new gist (happy pass)
    When user create new gist via menu "Create new"
    Then user can see opened page of created gist
    And header path contains the name of created gist