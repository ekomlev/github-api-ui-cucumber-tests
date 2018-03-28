package com.github.testcases.stepsDefinition;

import com.github.base.browser.BrowserFactory;
import com.github.entities.User;
import com.github.testcases.base.BaseTest;
import com.github.utils.UserCreator;
import com.github.website.GithubSite;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

@CucumberOptions(features = "features/create_new_comment.feature")
public class CreateNewCommentStepDef extends BaseTest {
    private User user = UserCreator.getInstance();
    private WebDriver webDriver = BrowserFactory.getInstance();
    private GithubSite website = GithubSite.getInstance(webDriver);
    private String commentText = user.getUserComment().getCommentText();
    private String gistFile = user.getUserGist().getGistFile();

    @When("^user create new gist via menu \"Create new\"$")
    public void createNewComment() {

    }

    @Then("^user can see opened page of created gist$")
    public void checkCreatedNewComment() {


    }

    @And("^header path contains the name of created gist$")
    public void checkRepositoryPageUrl() {

    }
}
