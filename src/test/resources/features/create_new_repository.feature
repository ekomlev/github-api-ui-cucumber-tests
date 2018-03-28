@smokeTest
Feature: Create new repository

  As signed in user of github site
  I want to be able to create new repostiory

  Background:
    Given user is signed to github home page

  Scenario: User creates new repostiory (happy pass)
    Given repository with required name is not created
    When user create new repository via menu "Create new"
    Then user can see the opened page of created repository
    And url contains the name of created repository
