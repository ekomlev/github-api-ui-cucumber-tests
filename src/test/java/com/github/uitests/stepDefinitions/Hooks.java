
package com.github.uitests.stepDefinitions;

import com.github.base.driver.TestContextManager;
import com.github.uitests.base.BaseStep;
import com.github.utils.ScreenshotExecutor;
import com.google.inject.Inject;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends BaseStep {
    private TestContextManager driverManager;
    private ScreenshotExecutor screenshotExecutor;

    @Inject
    public Hooks(TestContextManager driverManager, ScreenshotExecutor screenshotExecutor) {
        this.driverManager = driverManager;
        this.screenshotExecutor = screenshotExecutor;
    }

    @Before(order = 0)
    public void beforeScenarioStart(Scenario scenario) {
        driverManager.open();
        test(scenario.getName(), "started");
    }

    @After(order = 0)
    public void afterScenarioFinish(Scenario scenario) {
        test(scenario.getName(), "completed");
        driverManager.reset();
    }

    @After(order = 1)
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            error(screenshotName + " screenshot saved : " + screenshotExecutor.takeScreenshot(screenshotName));
        }
    }

}


