package com.github.testcases.stepDefinitions;

import com.google.inject.Inject;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;

@CucumberOptions(features = "features")
public class BackgroundStepDef {
    private World world;

    @Inject
    public BackgroundStepDef(World world) {
        this.world = world;
    }

    @Given("^user is signed into github home page$")
    public void sign_in() {
        world.github.initialPage().clickSignInLink();
        world.github.loginPage().waitForAuthorizationForm();

        String userName = world.user.getUserName();
        String userPassword = world.user.getUserPassword();

        world.github.loginPage().fillInAuthorizationForm(userName, userPassword);

        world.github.loginPage().clickSignInButton();
    }
}
