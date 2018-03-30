package com.github.testcases.stepDefinitions;

import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;

@CucumberOptions(features = "features")
public class BackgroundStepDef {
    private World world;

    public BackgroundStepDef(World world) {
        this.world = world;
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
