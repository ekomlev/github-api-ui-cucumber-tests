package com.github.testcases.stepDefinitions;

import com.github.entities.User;
import com.github.testcases.base.BaseStep;
import com.github.website.GithubSite;
import com.google.inject.Inject;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;

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
