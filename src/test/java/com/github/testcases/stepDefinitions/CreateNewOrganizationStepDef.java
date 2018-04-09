package com.github.testcases.stepDefinitions;

import com.google.inject.Inject;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

@CucumberOptions(features = "features/CreateNewOrganization.feature")
public class CreateNewOrganizationStepDef {
    private World world;

    @Inject
    public CreateNewOrganizationStepDef (World world) {
        this.world = world;
    }

    @Given("^organization with required name is not created$")
    public void checkNewOrganizationIsNotCreated() {
        world.step(1, "Open organization menu");
        world.website.homePage().expandAccountSwitcher();
        world.website.homePage().waitForExpandedAccountSwitcher();

        world.step(2, "Open organizations manage page");
        world.website.homePage().openOrganizations();
        world.website.organizationsPage().waitForOrganizationsList();

        world.step(3, "Check if organization is exist with required name");
        WebElement checkOrganization = world.website.organizationsPage().createdOrganizationAlreadyExists(world.organizationName);
        if (checkOrganization != null) {
            world.subStep("3.1", "Enter existing organization");
            world.website.organizationsPage().enterExistingOrganization(checkOrganization);
            world.website.organizationsPage().waitForOrganizationNavigationMenu();
            world.website.organizationsPage().openOrganizationSettings();
            world.website.organizationsPage().waitForOrganizationSettings();
            world.subStep("3.2", "Delete existing organization");
            world.website.organizationsPage().deleteExistingOrganization(world.organizationName);
        }
        Assert.assertTrue(world.website.homePage().createdRepositoryAlreadyExists(world.organizationName) == null);
    }

    @When("^user create new organization via menu \"Create new\"$")
    public void createNewOrganization() {
        world.step(4, "Open menu for creation new entity");
        world.website.creationNewEntityMenu().waitForCreationNewEntityMenuLink();
        world.website.creationNewEntityMenu().openCreationNewEntityMenu();

        world.step(5, "Open new organization page");
        world.website.creationNewEntityMenu().waitForCreationNewEntityMenu();
        world.website.creationNewEntityMenu().openNewOrganizationPage();

        world.step(6, "Save new organization");
        world.website.newOrganizationPage().waitForNewOrganizationForm();
        world.website.newOrganizationPage().saveNewOrganization(world.organizationName, world.organizationBillingEmail, world.organizationFreePlan);

        world.website.newOrganizationPage().waitForFinishButton();
        world.website.newOrganizationPage().clickFinishButton();
    }

    @Then("^user can see opened page of created organization$")
    public void checkCreatedNewOrganizationPage() {
        world.check("Check if  page of new created organization is opened");
        world.website.organizationsPage().waitForOrganizationContent();

    }

    @And("^url contains the name of created organization$")
    public void checkRepositoryPageUrl() {
        world.check("Check if url contains the name of created organization");
        Assert.assertTrue(world.webDriver.getCurrentUrl().contains(world.organizationName));
    }
}
