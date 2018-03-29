package com.github.testcases.stepDefinitions;

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

@CucumberOptions(features = "features/CreateNewRepository.feature")
public class CreateNewRepositoryStepDef extends BaseTest {
    private User user = UserCreator.getInstance();
    private WebDriver webDriver = BrowserFactory.getInstance();
    private GithubSite website = GithubSite.getInstance();
    private String repositoryName = user.getUserRepository().getRepositoryName();
    private String repositoryDescription = user.getUserRepository().getRepositoryName();
    private boolean repositoryPublicAccess = user.getUserRepository().getRepositoryPublicAccess();

    @Given("^repository with required name is not created$")
    public void checkNewRepositoryIsNotCreated() {
        step(1, "Check if repository is exist with required name");
        WebElement checkRepository = website.homePage().createdRepositoryAlreadyExists(repositoryName);
        if (checkRepository != null) {
            step("1.1", "Enter existing repository");
            website.homePage().enterExistingRepository(checkRepository);
            website.repositoryPage().openRepositorySettings();
            website.repositoryPage().waitForRepositorySettings();
            step("1.2", "Delete existing repository");
            website.repositoryPage().deleteExistingRepository(repositoryName);
        }
        Assert.assertTrue(website.homePage().createdRepositoryAlreadyExists(repositoryName) == null);
    }

    @When("^user create new repository via menu \"Create new\"$")
    public void createNewRepository() {
        step(2, "Open menu for creation new entity");
        website.creationNewEntityMenu().waitForCreationNewEntityMenuLink();
        website.creationNewEntityMenu().openCreationNewEntityMenu();

        step(3, "Open new repository page");
        website.creationNewEntityMenu().waitForCreationNewEntityMenu();
        website.creationNewEntityMenu().openNewRepositoryPage();

        website.newRepositoryPage().waitForNewRepositoryForm();

        step(4, "Save new repository");
        website.newRepositoryPage().saveNewRepository(repositoryName, repositoryDescription, repositoryPublicAccess);
    }

    @Then("^user can see the opened page of created repository$")
    public void checkCreatedNewRepositoryPage() {
        check("Check if page of new created repository is opened");
        website.repositoryPage().waitForRepositoryContent();

    }

    @And("^url contains the name of created repository$")
    public void checkRepositoryPageUrl() {
        check("Check if url contains the name of created repository");
        Assert.assertTrue(webDriver.getCurrentUrl().contains(repositoryName));
    }
}
