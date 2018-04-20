package com.github.uitests.stepDefinitions;

import com.github.base.driver.DriverManager;
import com.github.entities.User;
import com.github.uitests.base.BaseStep;
import com.github.website.GithubSite;
import com.google.inject.Inject;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

@CucumberOptions(features = "features/CreateNewOrganization.feature")
public class CreateNewOrganizationStepDef extends BaseStep {
    private GithubSite github;
    private String currentDriverUrl;
    private String organizationName;
    private String organizationBillingEmail;
    private boolean organizationFreePlan;

    @Inject
    public CreateNewOrganizationStepDef(User user, GithubSite github, DriverManager driverManager) {
        this.github = github;
        this.currentDriverUrl = driverManager.getDriver().getCurrentUrl();
        this.organizationName = user.getUserOrganization().getOrganizationName();
        this.organizationBillingEmail = user.getUserOrganization().getOrganizationBillingEmail();
        this.organizationFreePlan = user.getUserOrganization().getOrganizationFreePlan();
    }

    @Given("^organization with required name is not created$")
    public void checkNewOrganizationIsNotCreated() {
        step(1, "Open organization menu");
        github.homePage().expandAccountSwitcher();
        github.homePage().waitForExpandedAccountSwitcher();

        step(2, "Open organizations manage base");
        github.homePage().openOrganizations();
        github.organizationsPage().waitForOrganizationsList();

        step(3, "Check if organization is exist with required name");
        WebElement checkOrganization = github.organizationsPage().createdOrganizationAlreadyExists(organizationName);
        if (checkOrganization != null) {
            subStep("3.1", "Enter existing organization");
            github.organizationsPage().enterExistingOrganization(checkOrganization);
            github.organizationsPage().waitForOrganizationNavigationMenu();
            github.organizationsPage().openOrganizationSettings();
            github.organizationsPage().waitForOrganizationSettings();
            subStep("3.2", "Delete existing organization");
            github.organizationsPage().deleteExistingOrganization(organizationName);
        }
        Assert.assertNull(github.homePage().createdRepositoryAlreadyExists(organizationName));
    }

    @When("^user create new organization via menu \"Create new\"$")
    public void createNewOrganization() {
        step(4, "Open menu for creation new entity");
        github.creationNewEntityMenu().waitForCreationNewEntityMenuLink();
        github.creationNewEntityMenu().openCreationNewEntityMenu();

        step(5, "Open new organization base");
        github.creationNewEntityMenu().waitForCreationNewEntityMenu();
        github.creationNewEntityMenu().openNewOrganizationPage();

        step(6, "Save new organization");
        github.newOrganizationPage().waitForNewOrganizationForm();
        github.newOrganizationPage().saveNewOrganization(organizationName, organizationBillingEmail, organizationFreePlan);

        github.newOrganizationPage().waitForFinishButton();
        github.newOrganizationPage().clickFinishButton();
    }

    @Then("^user can see opened page of created organization$")
    public void checkCreatedNewOrganizationPage() {
        check("Check if  base of new created organization is opened");
        github.organizationsPage().waitForOrganizationContent();

    }

    @And("^url contains the name of created organization$")
    public void checkRepositoryPageUrl() {
        check("Check if url contains the name of created organization");
        Assert.assertTrue(currentDriverUrl.contains(organizationName));
    }
}
