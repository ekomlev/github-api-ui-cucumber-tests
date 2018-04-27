@smokeTest @thread-first
Feature: Create new organization

  As user signed into github site
  I want to be able to create new organization

  Background:
    Given user is signed into github home page

  Scenario: User creates new organization (happy pass)
    Given organization with required name is not created
    When user create new organization via menu "Create new"
    Then user can see opened page of created organization
    And url contains the name of created organization