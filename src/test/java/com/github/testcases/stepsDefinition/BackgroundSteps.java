package com.github.testcases.stepsDefinition;

import com.github.testcases.Base.BaseTest;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;

@CucumberOptions(features = {"src/test/resources/features/"} )
public class BackgroundSteps extends BaseTest {


    @Given("^user is signed to github home page$")
    public void sign_in() {
        System.out.println("21323123121");

        website.initialPage().clickSignInLink();
        website.loginPage().waitForAuthorizationForm();

        user = website.loginPage().createUserFromJson();

        String userName = user.getUserName();
        String userPassword = user.getUserPassword();

        website.loginPage().fillInAuthorizationForm(userName, userPassword);

        website.loginPage().clickSignInButton();
    }
}
