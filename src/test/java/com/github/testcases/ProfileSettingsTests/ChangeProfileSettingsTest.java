package com.github.testcases.ProfileSettingsTests;

import com.github.testcases.Base.BaseTest;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ChangeProfileSettingsTest extends BaseTest {

    @Test
    void changeProfileSettings(ITestContext testName) {
        testLogger.info(testName.getCurrentXmlTest().getName(),"started");

        String publicProfileName = user.getUserPublicProfile().getPublicProfileName();
        String publicProfileBio = user.getUserPublicProfile().getPublicProfileBio();
        String publicProfileUrl = user.getUserPublicProfile().getPublicProfileUrl();
        String publicProfileCompany = user.getUserPublicProfile().getPublicProfileCompany();
        String publicProfileLocation = user.getUserPublicProfile().getPublicProfileLocation();

        step(1, "Open User menu");
        website.userProfileMenu().waitForUserProfileMenuLink();
        website.userProfileMenu().openUserProfileMenu();

        step(2, "Open settings page");
        website.userProfileMenu().waitForUserProfileMenu();
        website.userProfileMenu().openProfileSettingsPage();

        step(3, "Save profile settings with new data");
        website.profileSettingsPage().waitForPublicProfileForm();

        website.profileSettingsPage().saveNewPublicProfileSettings(publicProfileName, publicProfileBio, publicProfileUrl, publicProfileCompany, publicProfileLocation);

        website.profileSettingsPage().waitForValidationMessage();

        step(4, "Open profile page");
        website.profileSettingsPage().openProfilePage();

        check( "Check saved data");

        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertEquals(user.getUserPublicProfile().getPublicProfileName() + "1", website.profilePage().readActualPublicProfileNameField());
        softAssertion.assertEquals(user.getUserPublicProfile().getPublicProfileBio(), website.profilePage().readActualPublicProfileBioField());
        softAssertion.assertEquals("http://" + user.getUserPublicProfile().getPublicProfileUrl(), website.profilePage().readActualPublicProfileUrlField());
        softAssertion.assertEquals(user.getUserPublicProfile().getPublicProfileCompany(), website.profilePage().readActualPublicProfileCompanyField());
        softAssertion.assertEquals(user.getUserPublicProfile().getPublicProfileLocation(), website.profilePage().readActualPublicProfileLocationField());
        softAssertion.assertAll();

        testLogger.info(testName.getCurrentXmlTest().getName(),"completed");
    }
}
