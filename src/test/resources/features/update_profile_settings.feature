@smokeTest
Feature: Update Profile Settings

  As signed in user of github site
  I want to be able to update my profile settings

  Background:
    Given user is signed to github home page

  Scenario: #User update profile settings.
    Given profile settings page is opened
    When user change Name
    And user change Bio
    And user change Url
    And user change Company
    And user change Location
    And user save profile settings
    Then user can see new Name on the Profile page
    And user can see new Bio on the Profile page
    And user can see new Url on the Profile page
    And user can see new Company on the Profile page
    And user can see new Location on the Profile page

     #   - go to profile settings page
     #   - change user's data: name, bio, url, company, location
     #   - save profile settings page
     #   - go to profile page
     #   - check new user's data: name, bio, url, company, location