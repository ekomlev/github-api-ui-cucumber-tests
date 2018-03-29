package com.github.testcases.stepsDefinition;

import com.github.entities.User;
import com.github.testcases.base.BaseTest;
import com.github.utils.UserCreator;
import com.github.website.GithubSite;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

@CucumberOptions(features = "features/create_new_comment.feature")
public class CreateNewCommentStepDef extends BaseTest {
    private User user = UserCreator.getInstance();
    private GithubSite website = GithubSite.getInstance();
    private String commentText = user.getUserComment().getCommentText();
    private String gistFile = user.getUserGist().getGistFile();

    @Given("^\"All your gists\" page is opened$")
    public void openAllYourGistsPage() {
        step(1, "Open user profile menu");
        website.userProfileMenu().waitForUserProfileMenuLink();
        website.userProfileMenu().openUserProfileMenu();
        website.userProfileMenu().waitForUserProfileMenu();

        step(2, "Open gist page");
        website.userProfileMenu().openYourGistPage();
        website.newGistPage().waitForGistHeadOfNewGistPage();

        step(3, "Open all gists");
        website.newGistPage().openAllYourGistsPage();
        website.allYourGistsPage().waitForAllYourGistsList();
    }

    @When("^user enters to existing gist$")
    public void createNewComment() {
        step(4, "Enter to gist if it exists");
        WebElement checkGist = website.allYourGistsPage().gistIsExist(gistFile);
        if (checkGist != null) {
            website.allYourGistsPage().openGist(checkGist);
        }
    }

    @And("^user creates new comment$")
    public void checkCreatedNewComment() {
        step(5, "Create new comment if gist file is exist");
        website.gistPage().waitForGistCommentForm();
        website.gistPage().saveNewGistComment(commentText);
        website.gistPage().waitForGistComment();
    }

    @Then("^user can see attached comment to gist$")
    public void checkRepositoryPageUrl() {
        check("Check if new comment is appeared");
        Assert.assertTrue(website.gistPage().getLastCommentText().contains(commentText));
    }
}
