package com.github.testcases.stepDefinitions;

import com.github.base.page.PageFactory;
import com.google.inject.Inject;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

@CucumberOptions(features = "features/UpdateProfileSettings.feature")
public class UpdateProfileSettingsStepDef {
    private World world;
    PageFactory pageFactory;

    @Inject
    public UpdateProfileSettingsStepDef (World world) {
        this.world = world;
    }

    @Given("^profile settings page is opened$")
    public void openUserProfileMenu(){
        world.step(1, "Open user profile menu");
        world.github.userProfileMenu().waitForUserProfileMenuLink();
        world.github.userProfileMenu().openUserProfileMenu();
        world.github.userProfileMenu().waitForUserProfileMenu();

        world.step(2, "Open settings page");
        world.github.userProfileMenu().openProfileSettingsPage();
        world.github.profileSettingsPage().waitForPublicProfileForm();
    }

    @When("^user changes Name$")
    public void changeUserNameField() {
        world.step(3, "Change user Name");
        String publicProfileName = world.user.getUserPublicProfile().getPublicProfileName();
        world.github.profileSettingsPage().changeProfileName(publicProfileName);
    }

    @And("^user changes Bio$")
    public void changeUserBioField() {
        world.step(4, "Change user Bio");
        String publicProfileBio = world.user.getUserPublicProfile().getPublicProfileBio();
        world.github.profileSettingsPage().changeProfileBio(publicProfileBio);
    }

    @And("^user changes Url$")
    public void changeUserUrlField() {
        world.step(5, "Change user Url");
        String publicProfileUrl = world.user.getUserPublicProfile().getPublicProfileUrl();
        world.github.profileSettingsPage().changeProfileUrl(publicProfileUrl);
    }

    @When("^user changes Company$")
    public void changeUserCompanyField() {
        world.step(6, "Change user Company");
        String publicProfileCompany = world.user.getUserPublicProfile().getPublicProfileCompany();
        world.github.profileSettingsPage().changeProfileCompany(publicProfileCompany);
    }

    @And("^user changes Location$")
    public void changeUserLocationField() {
        world.step(7, "Change user Location");
        String publicProfileLocation = world.user.getUserPublicProfile().getPublicProfileLocation();
        world.github.profileSettingsPage().changeProfileLocation(publicProfileLocation);
    }

    @And("^user saves profile settings$")
    public void saveProfileSettings() {
        world.step(8, "Save user settings");
        world.github.profileSettingsPage().savePublicProfileSettings();
        world.github.profileSettingsPage().waitForValidationMessage();
    }

    @Then("^user can see new Name on the Profile page$")
    public void checkProfileUserName() {
        String currentUrl = world.driverManager.getDriver().getCurrentUrl();

        if(!(currentUrl.equals(world.expectedGithubUrl)))  {
            world.github.profileSettingsPage().openProfilePage();
            world.github.profilePage().waitForUserProfileDataColumn();
        }

        world.check("Check new User Name on the Profile page");
        Assert.assertEquals(world.user.getUserPublicProfile().getPublicProfileName(), world.github.profilePage().readActualPublicProfileNameField());
    }

    @And("^user can see new Bio on the Profile page$")
    public void checkProfileUserBio() {
        String currentUrl = world.driverManager.getDriver().getCurrentUrl();

        if(!(currentUrl.equals(world.expectedGithubUrl))) {
            world.github.profileSettingsPage().openProfilePage();
            world.github.profilePage().waitForUserProfileDataColumn();
        }

        world.check("Check new User Bio on the Profile page");
        Assert.assertEquals(world.user.getUserPublicProfile().getPublicProfileBio(), world.github.profilePage().readActualPublicProfileBioField());
    }

    @And("^user can see new Url on the Profile page$")
    public void checkProfileUserUrl() {
        String currentUrl = world.driverManager.getDriver().getCurrentUrl();

        if(!(currentUrl.equals(world.expectedGithubUrl))) {
            world.github.profileSettingsPage().openProfilePage();
            world.github.profilePage().waitForUserProfileDataColumn();
        }

        world.check("Check new User Url on the Profile page");
        Assert.assertEquals("http://" + world.user.getUserPublicProfile().getPublicProfileUrl(), world.github.profilePage().readActualPublicProfileUrlField());
    }

    @And("^user can see new Company on the Profile page$")
    public void checkProfileUserCompany() {
        String currentUrl = world.driverManager.getDriver().getCurrentUrl();

        if(!(currentUrl.equals(world.expectedGithubUrl)))  {
            world.github.profileSettingsPage().openProfilePage();
            world.github.profilePage().waitForUserProfileDataColumn();
        }

        world.check("Check new User Company on the Profile page");
        Assert.assertEquals(world.user.getUserPublicProfile().getPublicProfileCompany(), world.github.profilePage().readActualPublicProfileCompanyField());
    }

    @And("^user can see new Location on the Profile page$")
    public void checkProfileUserLocation() {
        String currentUrl = world.driverManager.getDriver().getCurrentUrl();

        if(!(currentUrl.equals(world.expectedGithubUrl)))  {
            world.github.profileSettingsPage().openProfilePage();
            world.github.profilePage().waitForUserProfileDataColumn();
        }

        world.check("Check new User Location on the Profile page");
        Assert.assertEquals (world.user.getUserPublicProfile().getPublicProfileLocation(), world.github.profilePage().readActualPublicProfileLocationField());
    }
}
