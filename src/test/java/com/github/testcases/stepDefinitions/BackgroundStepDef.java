package com.github.testcases.stepDefinitions;

import com.github.testcases.base.BaseTest;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;

@CucumberOptions(features = "features")
public class BackgroundStepDef extends BaseTest {
    // GithubSite website = GithubSite.getInstance();
    // User user = UserCreator.getInstance(); //TODO: Check singleton of User entity

    public BackgroundStepDef(World world) {
        super(world);
    }


    @Given("^user is signed into github home page$")
    public void sign_in() {
        world.website.initialPage().clickSignInLink();
        world.website.loginPage().waitForAuthorizationForm();

        String userName = world.user.getUserName();
        String userPassword = world.user.getUserPassword();

        world.website.loginPage().fillInAuthorizationForm(userName, userPassword);

        world.website.loginPage().clickSignInButton();
    }
}
