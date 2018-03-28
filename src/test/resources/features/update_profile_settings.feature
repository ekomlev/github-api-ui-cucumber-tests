@smokeTest
Feature: Update profile settings

  As signed in user of github site
  I want to be able to update my profile settings

  Background:
    Given user is signed to github home page

  Scenario: User updates profile settings (happy pass)
    Given profile settings page is opened
    When user changes Name
    And user changes Bio
    And user changes Url
    And user changes Company
    And user changes Location
    And user saves profile settings
    Then user can see new Name on the Profile page
    And user can see new Bio on the Profile page
    And user can see new Url on the Profile page
    And user can see new Company on the Profile page
    And user can see new Location on the Profile page

