@smokeTest
Feature: Create new repository

  As user signed into github site
  I want to be able to create new repository

  Background:
    Given user is signed into github home page

  Scenario: User creates new repository (happy pass)
    Given repository with required name is not created
    When user create new repository via menu "Create new"
    Then user can see the opened page of created repository
    And url contains the name of created repository
