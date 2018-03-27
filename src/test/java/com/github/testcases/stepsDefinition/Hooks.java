
package com.github.testcases.stepsDefinition;

import com.github.testcases.Base.BaseTest;
import com.github.website.GithubSite;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends BaseTest{


    @Before(order=0)
    public GithubSite beforeScenarioStart(){
        setUp();
        testLogger.info("1111","started"); //TODO: Define var testName
        System.out.println("before starteddddd");
        return website;
    }

    @After(order=0)
    public void afterScenarioFinish(){
        testLogger.info("1111","completed");
    }
}


