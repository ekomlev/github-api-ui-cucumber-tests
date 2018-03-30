package com.github.testcases.stepDefinitions;

import com.github.testcases.base.BaseTest;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

@CucumberOptions(features = "features/CreateNewComment.feature")
public class CreateNewCommentStepDef extends BaseTest {

    public CreateNewCommentStepDef(World world) {
        super(world);
    }
    /*private User user = UserCreator.getInstance();
    private GithubSite website = GithubSite.getInstance();
    private String commentText = user.getUserComment().getCommentText();
    private String gistFile = user.getUserGist().getGistFile();*/

    @Given("^\"All your gists\" page is opened$")
    public void openAllYourGistsPage() {
        step(1, "Open user profile menu");
        world.website.userProfileMenu().waitForUserProfileMenuLink();
        world.website.userProfileMenu().openUserProfileMenu();
        world.website.userProfileMenu().waitForUserProfileMenu();

        step(2, "Open gist page");
        world.website.userProfileMenu().openYourGistPage();
        world.website.newGistPage().waitForGistHeadOfNewGistPage();

        step(3, "Open all gists");
        world.website.newGistPage().openAllYourGistsPage();
        world.website.allYourGistsPage().waitForAllYourGistsList();
    }

    @When("^user enters to existing gist$")
    public void createNewComment() {
        step(4, "Enter to gist if it exists");
        WebElement checkGist = world.website.allYourGistsPage().gistIsExist(world.gistFile);
        if (checkGist != null) {
            world.website.allYourGistsPage().openGist(checkGist);
        }
    }

    @And("^user creates new comment$")
    public void checkCreatedNewComment() {
        step(5, "Create new comment if gist file is exist");
        world.website.gistPage().waitForGistCommentForm();
        world.website.gistPage().saveNewGistComment(world.commentText);
        world.website.gistPage().waitForGistComment();
    }

    @Then("^user can see attached comment to gist$")
    public void checkRepositoryPageUrl() {
        check("Check if new comment is appeared");
        Assert.assertTrue(world.website.gistPage().getLastCommentText().contains(world.commentText));
    }
}
