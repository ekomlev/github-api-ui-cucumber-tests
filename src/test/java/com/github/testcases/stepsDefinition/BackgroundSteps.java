package com.github.testcases.stepsDefinition;

import com.github.entities.User;
import com.github.testcases.base.BaseTest;
import com.github.utils.UserCreator;
import com.github.website.GithubSite;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;

@CucumberOptions(features = {"src/test/resources/features/"} )
public class BackgroundSteps extends BaseTest {

    GithubSite website = GithubSite.getInstance(webDriver);
    User user = UserCreator.getInstance(); //TODO: Check singleton of User entity

    @Given("^user is signed to github home page$")
    public void sign_in() {
        website.initialPage().clickSignInLink();
        website.loginPage().waitForAuthorizationForm();

        String userName = user.getUserName();
        String userPassword = user.getUserPassword();

        website.loginPage().fillInAuthorizationForm(userName, userPassword);

        website.loginPage().clickSignInButton();
    }
}
