package com.github.testcases.stepsDefinition;

import com.github.entities.User;
import com.github.testcases.base.BaseTest;
import com.github.utils.UserCreator;
import com.github.website.GithubSite;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;

@CucumberOptions(features = "features/")
public class BackgroundStepDef extends BaseTest {

    GithubSite website = GithubSite.getInstance();
    User user = UserCreator.getInstance(); //TODO: Check singleton of User entity

    @Given("^user is signed into github home page$")
    public void sign_in() {
        website.initialPage().clickSignInLink();
        website.loginPage().waitForAuthorizationForm();

        String userName = user.getUserName();
        String userPassword = user.getUserPassword();

        website.loginPage().fillInAuthorizationForm(userName, userPassword);

        website.loginPage().clickSignInButton();
    }
}
