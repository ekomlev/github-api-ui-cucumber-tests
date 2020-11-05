package com.github.uitests.stepDefinitions;

import com.github.entities.User;
import com.github.uitests.base.BaseStep;
import com.github.website.GithubSite;
import com.google.inject.Inject;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
        github.userProfileMenu().waitForUserProfileMenuBtn();
        github.userProfileMenu().openUserProfileMenu();
        github.userProfileMenu().waitForUserProfileMenu();

        step(2, "Open gist page");
        github.userProfileMenu().openYourGistPage();
        github.newGistPage().waitForGistPageBody();
        github.allYourGistsPage().waitForAllYourGistsList();
    }

    @When("^user enters to existing gist$")
    public void createNewComment() {
        step(3, "Enter to gist if it exists");
        WebElement checkGist = github.allYourGistsPage().gistIsExist(gistFileName);
        if (checkGist != null) {
            github.allYourGistsPage().openGist(checkGist);
        }
    }

    @And("^user creates new comment$")
    public void checkCreatedNewComment() {
        step(4, "Create new comment if gist file is exist");
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
