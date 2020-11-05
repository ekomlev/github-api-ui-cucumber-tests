package com.github.uitests.stepDefinitions;

import com.github.entities.User;
import com.github.uitests.base.BaseStep;
import com.github.website.GithubSite;
import com.google.inject.Inject;
import io.cucumber.java.en.Given;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "features")
public class BackgroundStepDef extends BaseStep {
    private GithubSite github;
    private String userName;
    private String userPassword;

    @Inject
    public BackgroundStepDef(User user, GithubSite github) {
        this.github = github;
        this.userName = user.getUserName();
        this.userPassword = user.getUserPassword();
    }

    @Given("^user is signed into github home page$")
    public void sign_in() {
        github.initialPage().clickSignInLink();
        github.loginPage().waitForAuthorizationForm();

        github.loginPage().fillInAuthorizationForm(userName, userPassword);

        github.loginPage().clickSignInButton();
    }
}