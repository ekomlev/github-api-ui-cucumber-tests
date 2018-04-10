package com.github.testcases.stepDefinitions;

import com.google.inject.Inject;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

@CucumberOptions(features = "features/CreateNewComment.feature")
public class CreateNewCommentStepDef {
    private World world;

    @Inject
    public CreateNewCommentStepDef (World world) {
        this.world = world;
    }

    @Given("^\"All your gists\" page is opened$")
    public void openAllYourGistsPage() {
        world.step(1, "Open user profile menu");
        world.github.userProfileMenu().waitForUserProfileMenuLink();
        world.github.userProfileMenu().openUserProfileMenu();
        world.github.userProfileMenu().waitForUserProfileMenu();

        world.step(2, "Open gist page");
        world.github.userProfileMenu().openYourGistPage();
        world.github.newGistPage().waitForGistHeadOfNewGistPage();

        world.step(3, "Open all gists");
        world.github.newGistPage().openAllYourGistsPage();
        world.github.allYourGistsPage().waitForAllYourGistsList();
    }

    @When("^user enters to existing gist$")
    public void createNewComment() {
        world.step(4, "Enter to gist if it exists");
        WebElement checkGist = world.github.allYourGistsPage().gistIsExist(world.gistFile);
        if (checkGist != null) {
            world.github.allYourGistsPage().openGist(checkGist);
        }
    }

    @And("^user creates new comment$")
    public void checkCreatedNewComment() {
        world.step(5, "Create new comment if gist file is exist");
        world.github.gistPage().waitForGistCommentForm();
        world.github.gistPage().saveNewGistComment(world.commentText);
        world.github.gistPage().waitForGistComment();
    }

    @Then("^user can see attached comment to gist$")
    public void checkRepositoryPageUrl() {
        world. check("Check if new comment is appeared");
        Assert.assertTrue(world.github.gistPage().getLastCommentText().contains(world.commentText));
    }
}
