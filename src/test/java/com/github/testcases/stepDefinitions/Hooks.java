
package com.github.testcases.stepDefinitions;

import com.github.base.driver.DriverManager;
import com.github.testcases.reporter.TestReporter;
import com.github.utils.ScreenshotExecutor;
import com.google.inject.Inject;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends TestReporter {
    private DriverManager driverManager;
    private ScreenshotExecutor screenshotExecutor;

    @Inject
    public Hooks(DriverManager driverManager, ScreenshotExecutor screenshotExecutor) {
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


