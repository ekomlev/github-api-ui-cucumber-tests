package com.github.uitests.stepDefinitions;

import com.github.base.driver.TestContextManager;
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

@CucumberOptions(features = "features/CreateNewRepository.feature")
public class CreateNewRepositoryStepDef extends BaseStep {
    private GithubSite github;
    private String currentDriverUrl;
    private String repositoryName;
    private String repositoryDescription;
    private boolean repositoryPublicAccess;

    @Inject
    public CreateNewRepositoryStepDef(User user, GithubSite github, TestContextManager driverManager) {
        this.github = github;
        this.currentDriverUrl = driverManager.getDriver().getCurrentUrl();
        this.repositoryName = user.getUserRepository().getRepositoryName();
        this.repositoryDescription = user.getUserRepository().getRepositoryName();
        this.repositoryPublicAccess = user.getUserRepository().isRepositoryPrivateAccess();
    }


    @Given("^repository with required name is not created$")
    public void checkNewRepositoryIsNotCreated() {
        step(1, "Check if repository is exist with required name");
        WebElement checkRepository = github.homePage().createdRepositoryAlreadyExists(repositoryName);
        if (checkRepository != null) {
            subStep("1.1", "Enter existing repository");
            github.homePage().enterExistingRepository(checkRepository);
            github.repositoryPage().openRepositorySettings();
            github.repositoryPage().waitForRepositorySettings();
            subStep("1.2", "Delete existing repository");
            github.repositoryPage().deleteExistingRepository(repositoryName);
        }
        Assert.assertNull(github.homePage().createdRepositoryAlreadyExists(repositoryName));
    }

    @When("^user create new repository via menu \"Create new\"$")
    public void createNewRepository() {
        step(2, "Open menu for creation new entity");
        github.creationNewEntityMenu().waitForCreationNewEntityMenuLink();
        github.creationNewEntityMenu().openCreationNewEntityMenu();

        step(3, "Open new repository base");
        github.creationNewEntityMenu().waitForCreationNewEntityMenu();
        github.creationNewEntityMenu().openNewRepositoryPage();

        github.newRepositoryPage().waitForNewRepositoryForm();

        step(4, "Save new repository");
        github.newRepositoryPage().saveNewRepository(repositoryName, repositoryDescription, repositoryPublicAccess);
    }

    @Then("^user can see the opened page of created repository$")
    public void checkCreatedNewRepositoryPage() {
        check("Check if base of new created repository is opened");
        github.repositoryPage().waitForRepositoryContent();

    }

    @And("^url contains the name of created repository$")
    public void checkRepositoryPageUrl() {
        check("Check if url contains the name of created repository");
        Assert.assertTrue(currentDriverUrl.contains(repositoryName));
    }
}
