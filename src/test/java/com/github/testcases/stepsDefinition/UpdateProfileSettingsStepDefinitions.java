package com.github.testcases.stepsDefinition;

import com.github.testcases.Base.BaseTest;
import com.github.utils.PropertyProvider;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

@CucumberOptions(features = {"src/test/resources/features/update_profile_settings.feature"} )
public class UpdateProfileSettingsStepDefinitions extends BaseTest {


    @Given("^profile settings page is opened$")
    public void openUserProfileMenu(){
        System.out.println("step startedddd");
        step(1, "Open User menu");
        website.userProfileMenu().waitForUserProfileMenuLink();
        website.userProfileMenu().openUserProfileMenu();
        website.profileSettingsPage().waitForPublicProfileForm();

        step(2, "Open settings page");
        website.userProfileMenu().waitForUserProfileMenu();
        website.userProfileMenu().openProfileSettingsPage();
    }

    @When("^user change Name$")
    public void changeUserNameField() {
        step(3, "Change user Name");
        String publicProfileName = user.getUserPublicProfile().getPublicProfileName();
        website.profileSettingsPage().changeProfileName(publicProfileName);
    }

    @And("^user change Bio$")
    public void changeUserBioField() {
        step(4, "Change user Bio");
        String publicProfileBio = user.getUserPublicProfile().getPublicProfileBio();
        website.profileSettingsPage().changeProfileName(publicProfileBio);
    }

    @And("^user change Url$")
    public void changeUserUrlField() {
        step(5, "Change user Url");
        String publicProfileUrl = user.getUserPublicProfile().getPublicProfileUrl();
        website.profileSettingsPage().changeProfileName(publicProfileUrl);
    }

    @When("^user change Company$")
    public void changeUserCompanyField() {
        step(6, "Change user Company");
        String publicProfileCompany = user.getUserPublicProfile().getPublicProfileCompany();
        website.profileSettingsPage().changeProfileName(publicProfileCompany);
    }

    @And("^user change Location$")
    public void changeUserLocationField() {
        step(7, "Change user Company");
        String publicProfileLocation = user.getUserPublicProfile().getPublicProfileLocation();
        website.profileSettingsPage().changeProfileName(publicProfileLocation);
    }

    @And("^user save profile settings$")
    public void saveProfileSettings() {
        step(8, "Change user Company");
        website.profileSettingsPage().savePublicProfileSettings();
        website.profileSettingsPage().waitForValidationMessage();
    }

    @Then("^user can see new Name on the Profile page$")
    public void checkProfileUserName() {
        if(webDriver.getCurrentUrl() != PropertyProvider.getProperty("environment.variables.base_url") + "/" + user.getUserName()) {
            website.profileSettingsPage().openProfilePage();
        }
        check("Check new User Name on the Profile page");
        Assert.assertEquals(user.getUserPublicProfile().getPublicProfileName() + "1", website.profilePage().readActualPublicProfileNameField());
    }

    @And("^user can see new Bio on the Profile page$")
    public void checkProfileUserBio() {
        if(webDriver.getCurrentUrl() != PropertyProvider.getProperty("environment.variables.base_url") + "/" + user.getUserName()) {
            website.profileSettingsPage().openProfilePage();
        }
        check("Check new User Bio on the Profile page");
        Assert.assertEquals(user.getUserPublicProfile().getPublicProfileBio(), website.profilePage().readActualPublicProfileBioField());
    }

    @And("^user can see new Url on the Profile page$")
    public void checkProfileUserUrl() {
        if(webDriver.getCurrentUrl() != PropertyProvider.getProperty("environment.variables.base_url") + "/" + user.getUserName()) {
            website.profileSettingsPage().openProfilePage();
        }
        check("Check new User Url on the Profile page");
        Assert.assertEquals("http://" + user.getUserPublicProfile().getPublicProfileUrl(), website.profilePage().readActualPublicProfileUrlField());
    }

    @And("^user can see new Company on the Profile page$")
    public void checkProfileUserCompany() {
        if(webDriver.getCurrentUrl() != PropertyProvider.getProperty("environment.variables.base_url") + "/" + user.getUserName()) {
            website.profileSettingsPage().openProfilePage();
        }
        check("Check new User Company on the Profile page");
        Assert.assertEquals(user.getUserPublicProfile().getPublicProfileCompany(), website.profilePage().readActualPublicProfileCompanyField());
    }

    @And("^user can see new Location on the Profile page$")
    public void checkProfileUserLocation() {
        if(webDriver.getCurrentUrl() != PropertyProvider.getProperty("environment.variables.base_url") + "/" + user.getUserName()) {
            website.profileSettingsPage().openProfilePage();
        }
        check("Check new User Location on the Profile page");
        Assert.assertEquals (user.getUserPublicProfile().getPublicProfileLocation(), website.profilePage().readActualPublicProfileLocationField());
    }
}
