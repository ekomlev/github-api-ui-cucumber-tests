package com.github.uitests.stepDefinitions;

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

@CucumberOptions(features = "features/CreateNewComment.feature")
public class CreateNewCommentStepDef extends BaseStep {
    private GithubSite github;
    private String gistFileName;
    private String commentText;

    @Inject
    public CreateNewCommentStepDef(User user, GithubSite github) {
        this.github = github;
        this.gistFileName = user.getUserGist().getGistFile().entrySet().iterator().next().getKey();
        this.commentText = user.getUserComment().getCommentText();
    }

    @Given("^\"All your gists\" page is opened$")
    public void openAllYourGistsPage() {
        step(1, "Open user profile menu");
        github.userProfileMenu().waitForUserProfileMenuLink();
        github.userProfileMenu().openUserProfileMenu();
        github.userProfileMenu().waitForUserProfileMenu();

        step(2, "Open gist page");
        github.userProfileMenu().openYourGistPage();
        github.newGistPage().waitForGistHeadOfNewGistPage();

        step(3, "Open all gists");
        github.newGistPage().openAllYourGistsPage();
        github.allYourGistsPage().waitForAllYourGistsList();
    }

    @When("^user enters to existing gist$")
    public void createNewComment() {
        step(4, "Enter to gist if it exists");
        WebElement checkGist = github.allYourGistsPage().gistIsExist(gistFileName);
        if (checkGist != null) {
            github.allYourGistsPage().openGist(checkGist);
        }
    }

    @And("^user creates new comment$")
    public void checkCreatedNewComment() {
        step(5, "Create new comment if gist file is exist");
        github.gistPage().waitForGistCommentForm();
        github.gistPage().saveNewGistComment(commentText);
        github.gistPage().waitForGistComment();
    }

    @Then("^user can see attached comment to gist$")
    public void checkRepositoryPageUrl() {
        check("Check if new comment is appeared");
        Assert.assertTrue(github.gistPage().getLastCommentText().contains(commentText));
    }
}
