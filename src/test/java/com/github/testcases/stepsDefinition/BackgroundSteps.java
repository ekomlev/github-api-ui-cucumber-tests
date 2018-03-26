package com.github.testcases.stepsDefinition;

import com.github.testcases.Base.BaseTest;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;

@CucumberOptions(features = {"src/test/java/resources/features/"} )
public class BackgroundSteps extends BaseTest{

    @Given("^user navigates to initial page$")
    public void open_initial_page() {

    }

    @Given("^And User signes in$")
    public void sign_in() {

    }
}
