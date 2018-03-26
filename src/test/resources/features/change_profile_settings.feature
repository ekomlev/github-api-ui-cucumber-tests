Feature: Change Profile Settings

@smokeTest
    Background:
        Given user navigates to github initial page
        And user signes in

    Scenario: User change profile settings
        Given profile settings page is opened
        When user change Name
        And user change Bio
        And user change Url
        And user change Company
        And user change Location
        And user save profile settings
        Then user can see new data on the Profile page