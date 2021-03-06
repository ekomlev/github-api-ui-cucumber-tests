package com.github.uitests.stepDefinitions;

import com.github.entities.User;
import com.github.uitests.base.BaseStep;
import com.github.website.GithubSite;
import com.google.inject.Inject;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

@CucumberOptions(features = "features/CreateNewGist.feature")
public class CreateNewGistStepDef extends BaseStep {
    private GithubSite github;
    private String gistFileName;
    private String gistDescription;
    private String gistContent;
    private boolean gistPublicAccess;

    @Inject
    public CreateNewGistStepDef(User user, GithubSite github) {
        this.github = github;
        this.gistFileName = user.getUserGist().getGistFile().entrySet().iterator().next().getKey();
        this.gistDescription = user.getUserGist().getGistDescription();
        this.gistContent = user.getUserGist().getGistFile().entrySet().iterator().next().getValue().entrySet().iterator().next().getValue();
        this.gistPublicAccess = user.getUserGist().getGistPublicAccess();
    }

    @When("^user create new gist via menu \"Create new\"$")
    public void createNewGist() {
        step(1, "Open menu for creation new entity");
        github.creationNewEntityMenu().waitForCreationNewEntityMenuBtn();
        github.creationNewEntityMenu().openCreationNewEntityMenu();

        step(2, "Open new gist page");
        github.creationNewEntityMenu().waitForCreationNewEntityMenu();
        github.creationNewEntityMenu().openNewGistPage();

        step(3, "Save new gist");
        github.newGistPage().waitForNewGistForm();
        github.newGistPage().saveNewGist(gistFileName, gistDescription, gistContent, gistPublicAccess);
    }

    @Then("^user can see opened page of created gist$")
    public void checkCreatedNewGist() {
        check("Check if base of new created gist is opened");
        github.gistPage().waitForGistContent();
    }

    @And("^header path contains the name of created gist$")
    public void checkGistHeaderPath() {
        check("Check if header path contains the name of created gist");
        github.gistPage().waitForGistHeadOfGistPage();
        Assert.assertTrue(github.gistPage().getNameOfOpenedGist().contains(gistFileName));
    }
}