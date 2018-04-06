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
        world.website.userProfileMenu().waitForUserProfileMenuLink();
        world.website.userProfileMenu().openUserProfileMenu();
        world.website.userProfileMenu().waitForUserProfileMenu();

        world.step(2, "Open gist page");
        world.website.userProfileMenu().openYourGistPage();
        world.website.newGistPage().waitForGistHeadOfNewGistPage();

        world.step(3, "Open all gists");
        world.website.newGistPage().openAllYourGistsPage();
        world.website.allYourGistsPage().waitForAllYourGistsList();
    }

    @When("^user enters to existing gist$")
    public void createNewComment() {
        world.step(4, "Enter to gist if it exists");
        WebElement checkGist = world.website.allYourGistsPage().gistIsExist(world.gistFile);
        if (checkGist != null) {
            world.website.allYourGistsPage().openGist(checkGist);
        }
    }

    @And("^user creates new comment$")
    public void checkCreatedNewComment() {
        world.step(5, "Create new comment if gist file is exist");
        world.website.gistPage().waitForGistCommentForm();
        world.website.gistPage().saveNewGistComment(world.commentText);
        world.website.gistPage().waitForGistComment();
    }

    @Then("^user can see attached comment to gist$")
    public void checkRepositoryPageUrl() {
        world. check("Check if new comment is appeared");
        Assert.assertTrue(world.website.gistPage().getLastCommentText().contains(world.commentText+"12123"));
    }
}
