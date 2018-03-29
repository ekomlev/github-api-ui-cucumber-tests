
package com.github.testcases.stepDefinitions;

import com.github.testcases.base.BaseTest;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends BaseTest{


    @Before(order=0)
    public void beforeScenarioStart(Scenario scenario){
        setUp();
        testLogger.info(scenario.getName(),"started"); //TODO: Define var testName
    }

    @After(order=0)
    public void afterScenarioFinish(Scenario scenario){
        testLogger.info(scenario.getName(),"completed");
        tearDown();
    }
}


