package com.github.testcases.OrganizationTests;

import com.github.testcases.Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class CreateNewOrganizationTest extends BaseTest {

    @Test
    void createNewOrganization(ITestContext testName) {
        testLogger.info(testName.getCurrentXmlTest().getName(),"started");

        String organizationName = user.getUserOrganization().getOrganizationName();
        String organizationBillingEmail = user.getUserOrganization().getOrganizationBillingEmail();
        boolean organizationFreePlan = user.getUserOrganization().getOrganizationFreePlan();

        step(1, "Open organization menu");
        website.homePage().expandAccountSwitcher();
        website.homePage().waitForExpandedAccountSwitcher();

        step(2, "Open organizations manage page");
        website.homePage().openOrganizations();
        website.organizationsPage().waitForOrganizationsList();

        step(3, "Check if organization is exist with required name");
        WebElement checkOrganization = website.organizationsPage().createdOrganizationAlreadyExists(organizationName);
        if (checkOrganization != null) {
            step("3.1", "Enter existing organization");
            website.organizationsPage().enterExistingOrganization(checkOrganization);
            website.organizationsPage().waitForOrganizationNavigationMenu();
            website.organizationsPage().openOrganizationSettings();
            website.organizationsPage().waitForOrganizationSettings();
            step("3.2", "Delete existing organization");
            website.organizationsPage().deleteExistingOrganization(organizationName);
        }

        step(4, "Open menu for creation new entity");
        website.creationNewEntityMenu().waitForCreationNewEntityMenuLink();
        website.creationNewEntityMenu().openCreationNewEntityMenu();

        step(5, "Open new organization page");
        website.creationNewEntityMenu().waitForCreationNewEntityMenu();
        website.creationNewEntityMenu().openNewOrganizationPage();

        step(6, "Save new organization");
        website.newOrganizationPage().waitForNewOrganizationForm();
        website.newOrganizationPage().saveNewOrganization(organizationName, organizationBillingEmail, organizationFreePlan);

        website.newOrganizationPage().waitForFinishButton();
        website.newOrganizationPage().clickFinishButton();

        check("Check if created organization is exist");
        Assert.assertTrue(webDriver.getCurrentUrl().contains(organizationName));

        testLogger.info(testName.getCurrentXmlTest().getName(),"completed");
    }
}
