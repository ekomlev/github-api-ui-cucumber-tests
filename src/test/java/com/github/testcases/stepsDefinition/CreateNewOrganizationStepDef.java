package com.github.testcases.stepsDefinition;

import com.github.base.browser.BrowserFactory;
import com.github.entities.User;
import com.github.testcases.base.BaseTest;
import com.github.utils.UserCreator;
import com.github.website.GithubSite;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

@CucumberOptions(features = "features/create_new_organization.feature")
public class CreateNewOrganizationStepDef extends BaseTest {
    private User user = UserCreator.getInstance();
    private WebDriver webDriver = BrowserFactory.getInstance();
    private GithubSite website = GithubSite.getInstance(webDriver);
    private String organizationName = user.getUserOrganization().getOrganizationName();
    private String organizationBillingEmail = user.getUserOrganization().getOrganizationBillingEmail();
    private boolean organizationFreePlan = user.getUserOrganization().getOrganizationFreePlan();

    @Given("^organization with required name is not created$")
    public void checkNewOrganizationIsNotCreated() {
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
        Assert.assertTrue(website.homePage().createdRepositoryAlreadyExists(organizationName) == null);
    }

    @When("^user create new organization via menu \"Create new\"$")
    public void createNewOrganization() {
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
    }

    @Then("^user can see opened page of created organization$")
    public void checkCreatedNewOrganizationPage() {
        check("Check if  page of new created organization is opened");
        website.organizationsPage().waitForOrganizationContent();

    }

    @And("^url contains the name of created organization$")
    public void checkRepositoryPageUrl() {
        check("Check if url contains the name of created organization");
        Assert.assertTrue(webDriver.getCurrentUrl().contains(organizationName));
    }
}
