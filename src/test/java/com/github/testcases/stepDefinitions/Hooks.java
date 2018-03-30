
package com.github.testcases.stepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
    private World world;

    public Hooks(World world) {
        this.world = world;
    }

    @Before(order=0)
    public void beforeScenarioStart(Scenario scenario){
        world.setUp();
        world.test(scenario.getName(),"started");
    }

    @After(order=0)
    public void afterScenarioFinish(Scenario scenario){
        world.tearDown();
        world.test(scenario.getName(),"completed");
    }

    @After(order = 1)
    public void afterScenario(Scenario scenario) throws InterruptedException {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            world.error(screenshotName + " screenshot saved : " +  world.takeScreenshot(screenshotName));
        }
    }

}


