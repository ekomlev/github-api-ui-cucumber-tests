package com.github.testcases.stepsDefinition;

import com.github.base.browser.BrowserFactory;
import com.github.entities.User;
import com.github.testcases.base.BaseTest;
import com.github.utils.PropertyProvider;
import com.github.utils.UserCreator;
import com.github.website.GithubSite;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

@CucumberOptions(features = "features/update_profile_settings.feature")
public class UpdateProfileSettingsStepDef extends BaseTest {
    private User user = UserCreator.getInstance();
    private WebDriver webDriver = BrowserFactory.getInstance();
    private GithubSite website = GithubSite.getInstance(webDriver);
    private String expectedUrl = PropertyProvider.getProperty("environment.variables.base_url") + "/" + user.getUserName();

    @Given("^profile settings page is opened$")
    public void openUserProfileMenu(){
        step(1, "Open User menu");
        website.userProfileMenu().waitForUserProfileMenuLink();
        website.userProfileMenu().openUserProfileMenu();
        website.userProfileMenu().waitForUserProfileMenu();

        step(2, "Open settings page");
        website.userProfileMenu().openProfileSettingsPage();
        website.profileSettingsPage().waitForPublicProfileForm();
    }

    @When("^user changes Name$")
    public void changeUserNameField() {
        step(3, "Change user Name");
        String publicProfileName = user.getUserPublicProfile().getPublicProfileName();
        website.profileSettingsPage().changeProfileName(publicProfileName);
    }

    @And("^user changes Bio$")
    public void changeUserBioField() {
        step(4, "Change user Bio");
        String publicProfileBio = user.getUserPublicProfile().getPublicProfileBio();
        website.profileSettingsPage().changeProfileBio(publicProfileBio);
    }

    @And("^user changes Url$")
    public void changeUserUrlField() {
        step(5, "Change user Url");
        String publicProfileUrl = user.getUserPublicProfile().getPublicProfileUrl();
        website.profileSettingsPage().changeProfileUrl(publicProfileUrl);
    }

    @When("^user changes Company$")
    public void changeUserCompanyField() {
        step(6, "Change user Company");
        String publicProfileCompany = user.getUserPublicProfile().getPublicProfileCompany();
        website.profileSettingsPage().changeProfileCompany(publicProfileCompany);
    }

    @And("^user changes Location$")
    public void changeUserLocationField() {
        step(7, "Change user Location");
        String publicProfileLocation = user.getUserPublicProfile().getPublicProfileLocation();
        website.profileSettingsPage().changeProfileLocation(publicProfileLocation);
    }

    @And("^user saves profile settings$")
    public void saveProfileSettings() {
        step(8, "Save user settings");
        website.profileSettingsPage().savePublicProfileSettings();
        website.profileSettingsPage().waitForValidationMessage();
    }

    @Then("^user can see new Name on the Profile page$")
    public void checkProfileUserName() {
        String currentUrl = webDriver.getCurrentUrl();

        if(!(currentUrl.equals(expectedUrl)))  {
            website.profileSettingsPage().openProfilePage();
            website.profilePage().waitForUserProfileDataColumn();
        }

        check("Check new User Name on the Profile page");
        Assert.assertEquals(user.getUserPublicProfile().getPublicProfileName(), website.profilePage().readActualPublicProfileNameField());
    }

    @And("^user can see new Bio on the Profile page$")
    public void checkProfileUserBio() {
        String currentUrl = webDriver.getCurrentUrl();

        if(!(currentUrl.equals(expectedUrl))) {
            website.profileSettingsPage().openProfilePage();
            website.profilePage().waitForUserProfileDataColumn();
        }

        check("Check new User Bio on the Profile page");
        Assert.assertEquals(user.getUserPublicProfile().getPublicProfileBio(), website.profilePage().readActualPublicProfileBioField());
    }

    @And("^user can see new Url on the Profile page$")
    public void checkProfileUserUrl() {
        String currentUrl = webDriver.getCurrentUrl();

        if(!(currentUrl.equals(expectedUrl))) {
            website.profileSettingsPage().openProfilePage();
            website.profilePage().waitForUserProfileDataColumn();
        }

        check("Check new User Url on the Profile page");
        Assert.assertEquals("http://" + user.getUserPublicProfile().getPublicProfileUrl(), website.profilePage().readActualPublicProfileUrlField());
    }

    @And("^user can see new Company on the Profile page$")
    public void checkProfileUserCompany() {
        String currentUrl = webDriver.getCurrentUrl();

        if(!(currentUrl.equals(expectedUrl)))  {
            website.profileSettingsPage().openProfilePage();
            website.profilePage().waitForUserProfileDataColumn();
        }

        check("Check new User Company on the Profile page");
        Assert.assertEquals(user.getUserPublicProfile().getPublicProfileCompany(), website.profilePage().readActualPublicProfileCompanyField());
    }

    @And("^user can see new Location on the Profile page$")
    public void checkProfileUserLocation() {
        String currentUrl = webDriver.getCurrentUrl();

        if(!(currentUrl.equals(expectedUrl)))  {
            website.profileSettingsPage().openProfilePage();
            website.profilePage().waitForUserProfileDataColumn();
        }

        check("Check new User Location on the Profile page");
        Assert.assertEquals (user.getUserPublicProfile().getPublicProfileLocation(), website.profilePage().readActualPublicProfileLocationField());
    }
}
