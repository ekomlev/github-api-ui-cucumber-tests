package com.github.uitests.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "classpath:features",
//        features = "classpath:features/CreateNewRepository.feature",
        glue = "com.github.uitests.stepDefinitions",
        tags = {"@thread-second"}
)
@Test
public class Smoke2RunnerTest extends AbstractTestNGCucumberTests {
}

