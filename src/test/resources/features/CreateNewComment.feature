@smokeTest @thread-first
Feature: Create new comment

  As user signed into github site
  I want to be able to create new comment for gist

  Background:
    Given user is signed into github home page

  Scenario: User creates new comment (happy pass)
    Given "All your gists" page is opened
    When user enters to existing gist
    And user creates new comment
    Then user can see attached comment to gist