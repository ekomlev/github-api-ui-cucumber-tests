package com.github.uitests.runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        plugin = {"pretty", "com.epam.reportportal.cucumber.StepReporter"},
        features = "classpath:features",
        glue = "com.github.uitests.stepDefinitions",
        tags = "@thread-second"
)
public class Smoke2RunnerTest extends AbstractTestNGCucumberTests {
}