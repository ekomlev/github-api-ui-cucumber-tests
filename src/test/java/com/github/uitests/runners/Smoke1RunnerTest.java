

package com.github.uitests.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

@CucumberOptions(
//      plugin = {"html:target/cucumber-report/smoketest", "json:target/cucumber.json"},
        features = "classpath:features",
//        features = "classpath:features/CreateNewComment.feature",
        glue = "com.github.uitests.stepDefinitions",
        tags = {"@thread-first"}
)
@Test
public class Smoke1RunnerTest extends AbstractTestNGCucumberTests {
}


