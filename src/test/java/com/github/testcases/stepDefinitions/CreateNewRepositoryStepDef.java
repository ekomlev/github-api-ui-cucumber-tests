package com.github.testcases.stepDefinitions;

import com.google.inject.Inject;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

@CucumberOptions(features = "features/CreateNewRepository.feature")
public class CreateNewRepositoryStepDef {
    private World world;

    @Inject
    public CreateNewRepositoryStepDef (World world) {
        this.world = world;
    }


    @Given("^repository with required name is not created$")
    public void checkNewRepositoryIsNotCreated() {
        world.step(1, "Check if repository is exist with required name");
        WebElement checkRepository = world.github.homePage().createdRepositoryAlreadyExists(world.repositoryName);
        if (checkRepository != null) {
            world.subStep("1.1", "Enter existing repository");
            world.github.homePage().enterExistingRepository(checkRepository);
            world.github.repositoryPage().openRepositorySettings();
            world.github.repositoryPage().waitForRepositorySettings();
            world.subStep("1.2", "Delete existing repository");
            world.github.repositoryPage().deleteExistingRepository(world.repositoryName);
        }
        Assert.assertTrue(world.github.homePage().createdRepositoryAlreadyExists(world.repositoryName) == null);
    }

    @When("^user create new repository via menu \"Create new\"$")
    public void createNewRepository() {
        world.step(2, "Open menu for creation new entity");
        world.github.creationNewEntityMenu().waitForCreationNewEntityMenuLink();
        world.github.creationNewEntityMenu().openCreationNewEntityMenu();

        world.step(3, "Open new repository page");
        world.github.creationNewEntityMenu().waitForCreationNewEntityMenu();
        world.github.creationNewEntityMenu().openNewRepositoryPage();

        world.github.newRepositoryPage().waitForNewRepositoryForm();

        world.step(4, "Save new repository");
        world.github.newRepositoryPage().saveNewRepository(world.repositoryName, world.repositoryDescription, world.repositoryPublicAccess);
    }

    @Then("^user can see the opened page of created repository$")
    public void checkCreatedNewRepositoryPage() {
        world.check("Check if page of new created repository is opened");
        world.github.repositoryPage().waitForRepositoryContent();

    }

    @And("^url contains the name of created repository$")
    public void checkRepositoryPageUrl() {
        world.check("Check if url contains the name of created repository");
        Assert.assertTrue(world.driverManager.getDriver().getCurrentUrl().contains(world.repositoryName));
    }
}
