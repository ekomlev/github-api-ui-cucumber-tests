package com.github.testcases.stepDefinitions;

import cucumber.api.CucumberOptions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

@CucumberOptions(features = "features/CreateNewGist.feature")
public class CreateNewGistStepDef {
    private World world;

    public CreateNewGistStepDef (World world) {
        this.world = world;
    }

    @When("^user create new gist via menu \"Create new\"$")
    public void createNewGist() {
        world.step(1, "Open menu for creation new entity");
        world.website.creationNewEntityMenu().waitForCreationNewEntityMenuLink();
        world.website.creationNewEntityMenu().openCreationNewEntityMenu();

        world.step(2, "Create new gist");
        world.website.creationNewEntityMenu().waitForCreationNewEntityMenu();
        world.website.creationNewEntityMenu().openNewGistPage();

        world.step(3, "Save new gist");
        world.website.newGistPage().waitForNewGistForm();
        world.website.newGistPage().saveNewGist(world.gistFile, world.gistDescription, world.gistContent, world.gistPublicAccess);
    }

    @Then("^user can see opened page of created gist$")
    public void checkCreatedNewGist() {
        world.check("Check if  page of new created gist is opened");
        world.website.gistPage().waitForGistContent();

    }

    @And("^header path contains the name of created gist$")
    public void checkGistHeaderPath() {
        world.check("Check if header path contains the name of created gist");
        world.website.gistPage().waitForGistHeadOfGistPage();
        Assert.assertTrue(world.website.gistPage().getNameOfOpenedGist().contains(world.gistFile));
    }
}
