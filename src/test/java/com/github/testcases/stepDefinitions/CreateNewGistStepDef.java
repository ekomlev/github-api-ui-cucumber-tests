package com.github.testcases.stepDefinitions;

import com.github.entities.User;
import com.github.testcases.base.BaseTest;
import com.github.utils.UserCreator;
import com.github.website.GithubSite;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

@CucumberOptions(features = "features/CreateNewGist.feature")
public class CreateNewGistStepDef extends BaseTest {
    public CreateNewGistStepDef(World world) {
        super(world);
    }
    /*private User user = UserCreator.getInstance();
    private GithubSite website = GithubSite.getInstance();
    private String gistFile = user.getUserGist().getGistFile();
    private String gistDescription = user.getUserGist().getGistDescription();
    private String gistContent = user.getUserGist().getGistContent();
    private boolean gistPublicAccess = user.getUserGist().getGistPublicAccess();*/

    @When("^user create new gist via menu \"Create new\"$")
    public void createNewGist() {
        step(1, "Open menu for creation new entity");
        world.website.creationNewEntityMenu().waitForCreationNewEntityMenuLink();
        world.website.creationNewEntityMenu().openCreationNewEntityMenu();

        step(2, "Create new gist");
        world.website.creationNewEntityMenu().waitForCreationNewEntityMenu();
        world.website.creationNewEntityMenu().openNewGistPage();

        step(3, "Save new gist");
        world.website.newGistPage().waitForNewGistForm();
        world.website.newGistPage().saveNewGist(world.gistFile, world.gistDescription, world.gistContent, world.gistPublicAccess);
    }

    @Then("^user can see opened page of created gist$")
    public void checkCreatedNewGist() {
        check("Check if  page of new created gist is opened");
        world.website.gistPage().waitForGistContent();

    }

    @And("^header path contains the name of created gist$")
    public void checkGistHeaderPath() {
        check("Check if header path contains the name of created gist");
        world.website.gistPage().waitForGistHeadOfGistPage();
        Assert.assertTrue(world.website.gistPage().getNameOfOpenedGist().contains(world.gistFile));
    }
}
