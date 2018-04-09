
package com.github.testcases.stepDefinitions;

import com.google.inject.Inject;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
    private World world;
    private static boolean dunit = false;

    @Inject
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

        world.test(scenario.getName(),"completed");
        if(!dunit) {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    world.tearDown();
                }
                });
            dunit = true;
        }
    }

    @After(order = 1)
    public void afterScenario(Scenario scenario) throws InterruptedException {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            world.error(screenshotName + " screenshot saved : " +  world.takeScreenshot(screenshotName));
        }
    }

}


