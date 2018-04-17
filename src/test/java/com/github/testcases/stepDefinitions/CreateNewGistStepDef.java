package com.github.testcases.stepDefinitions;

import com.github.entities.User;
import com.github.testcases.base.BaseStep;
import com.github.website.GithubSite;
import com.google.inject.Inject;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

@CucumberOptions(features = "features/CreateNewGist.feature")
public class CreateNewGistStepDef extends BaseStep {
    private GithubSite github;
    private String gistFile;
    private String gistDescription;
    private String gistContent;
    private boolean gistPublicAccess;


    @Inject
    public CreateNewGistStepDef(User user, GithubSite github) {
        this.github = github;
        this.gistFile = user.getUserGist().getGistFile();
        this.gistDescription = user.getUserGist().getGistDescription();
        this.gistContent = user.getUserGist().getGistContent();
        this.gistPublicAccess = user.getUserGist().getGistPublicAccess();
    }

    @When("^user create new gist via menu \"Create new\"$")
    public void createNewGist() {
        step(1, "Open menu for creation new entity");
        github.creationNewEntityMenu().waitForCreationNewEntityMenuLink();
        github.creationNewEntityMenu().openCreationNewEntityMenu();

        step(2, "Open new gist page");
        github.creationNewEntityMenu().waitForCreationNewEntityMenu();
        github.creationNewEntityMenu().openNewGistPage();

        step(3, "Save new gist");
        github.newGistPage().waitForNewGistForm();
        github.newGistPage().saveNewGist(gistFile, gistDescription, gistContent, gistPublicAccess);
    }

    @Then("^user can see opened page of created gist$")
    public void checkCreatedNewGist() {
        check("Check if  base of new created gist is opened");
        github.gistPage().waitForGistContent();

    }

    @And("^header path contains the name of created gist$")
    public void checkGistHeaderPath() {
        check("Check if header path contains the name of created gist");
        github.gistPage().waitForGistHeadOfGistPage();
        Assert.assertTrue(github.gistPage().getNameOfOpenedGist().contains(gistFile));
    }
}
