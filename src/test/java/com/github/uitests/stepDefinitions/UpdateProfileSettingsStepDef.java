package com.github.uitests.stepDefinitions;

import com.github.base.driver.TestContextManager;
import com.github.entities.User;
import com.github.uitests.base.BaseStep;
import com.github.website.GithubSite;
import com.google.inject.Inject;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

@CucumberOptions(features = "features/UpdateProfileSettings.feature")
public class UpdateProfileSettingsStepDef extends BaseStep {
    private GithubSite github;
    private String expectedPublicProfileName;
    private String expectedGithubUrl;
    private String currentDriverUrl;
    private String expectedPublicProfileBio;
    private String expectedPublicProfileUrl;
    private String expectedPublicProfileCompany;
    private String expectedPublicProfileLocation;

    @Inject
    public UpdateProfileSettingsStepDef(User user, GithubSite github, TestContextManager driverManager) {
        this.github = github;
        this.expectedPublicProfileName = user.getUserPublicProfile().getPublicProfileName();
        this.expectedGithubUrl = driverManager.getGithubUrl() + "/" + user.getUserName();
        this.currentDriverUrl = driverManager.getDriver().getCurrentUrl();
        this.expectedPublicProfileBio = user.getUserPublicProfile().getPublicProfileBio();
        this.expectedPublicProfileUrl = user.getUserPublicProfile().getPublicProfileBlogUrl();
        this.expectedPublicProfileCompany = user.getUserPublicProfile().getPublicProfileCompany();
        this.expectedPublicProfileLocation = user.getUserPublicProfile().getPublicProfileLocation();

    }

    @Given("^profile settings page is opened$")
    public void openUserProfileMenu() {
        step(1, "Open user profile menu");
        github.userProfileMenu().waitForUserProfileMenuBtn();
        github.userProfileMenu().openUserProfileMenu();
        github.userProfileMenu().waitForUserProfileMenu();

        step(2, "Open settings base");
        github.userProfileMenu().openProfileSettingsPage();
        github.profileSettingsPage().waitForPublicProfileForm();
    }

    @When("^user changes Name$")
    public void changeUserNameField() {
        step(3, "Change user Name");
        github.profileSettingsPage().changeProfileName(expectedPublicProfileName);
    }

    @And("^user changes Bio$")
    public void changeUserBioField() {
        step(4, "Change user Bio");
        github.profileSettingsPage().changeProfileBio(expectedPublicProfileBio);
    }

    @And("^user changes Url$")
    public void changeUserUrlField() {
        step(5, "Change user Url");
        github.profileSettingsPage().changeProfileUrl(expectedPublicProfileUrl);
    }

    @When("^user changes Company$")
    public void changeUserCompanyField() {
        step(6, "Change user Company");
        github.profileSettingsPage().changeProfileCompany(expectedPublicProfileCompany);
    }

    @And("^user changes Location$")
    public void changeUserLocationField() {
        step(7, "Change user Location");
        github.profileSettingsPage().changeProfileLocation(expectedPublicProfileLocation);
    }

    @And("^user saves profile settings$")
    public void saveProfileSettings() {
        step(8, "Save user settings");
        github.profileSettingsPage().savePublicProfileSettings();
        github.profileSettingsPage().waitForValidationMessage();
    }

    @Then("^user can see new Name on the Profile page$")
    public void checkProfileUserName() {
        if (!(currentDriverUrl.equals(expectedGithubUrl))) {
            github.profileSettingsPage().openProfilePage();
            github.profilePage().waitForUserProfileDataColumn();
        }

        String actualPublicProfileName = github.profilePage().readActualPublicProfileNameField();

        check("Check new User Name on the Profile base");
        Assert.assertEquals(actualPublicProfileName, expectedPublicProfileName);
    }

    @And("^user can see new Bio on the Profile page$")
    public void checkProfileUserBio() {
        if (!(currentDriverUrl.equals(expectedGithubUrl))) {
            github.profileSettingsPage().openProfilePage();
            github.profilePage().waitForUserProfileDataColumn();
        }

        String actualPublicProfileBio = github.profilePage().readActualPublicProfileBioField();

        check("Check new User Bio on the Profile base");
        Assert.assertEquals(actualPublicProfileBio, expectedPublicProfileBio);
    }

    @And("^user can see new Url on the Profile page$")
    public void checkProfileUserUrl() {
        if (!(currentDriverUrl.equals(expectedGithubUrl))) {
            github.profileSettingsPage().openProfilePage();
            github.profilePage().waitForUserProfileDataColumn();
        }

        String actualPublicProfileUrl = github.profilePage().readActualPublicProfileUrlField();

        check("Check new User Url on the Profile base");
        Assert.assertEquals(actualPublicProfileUrl, expectedPublicProfileUrl);
    }

    @And("^user can see new Company on the Profile page$")
    public void checkProfileUserCompany() {
        if (!(currentDriverUrl.equals(expectedGithubUrl))) {
            github.profileSettingsPage().openProfilePage();
            github.profilePage().waitForUserProfileDataColumn();
        }

        String actualPublicProfileCompany = github.profilePage().readActualPublicProfileCompanyField();

        check("Check new User Company on the Profile base");
        Assert.assertEquals(actualPublicProfileCompany, expectedPublicProfileCompany);
    }

    @And("^user can see new Location on the Profile page$")
    public void checkProfileUserLocation() {
        if (!(currentDriverUrl.equals(expectedGithubUrl))) {
            github.profileSettingsPage().openProfilePage();
            github.profilePage().waitForUserProfileDataColumn();
        }

        String actualPublicProfileLocation = github.profilePage().readActualPublicProfileLocationField();

        check("Check new User Location on the Profile base");
        Assert.assertEquals(actualPublicProfileLocation, expectedPublicProfileLocation);
    }
}