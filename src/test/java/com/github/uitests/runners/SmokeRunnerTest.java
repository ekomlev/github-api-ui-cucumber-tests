
package com.github.uitests.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

@CucumberOptions(
//        plugin = {"html:target/cucumber-report/smoketest", "json:target/cucumber.json"},
        features = "classpath:features",
        glue = "com.github.uitests.stepDefinitions",
        tags = {"@smokeTest"}
)
@Test
public class SmokeRunnerTest extends AbstractTestNGCucumberTests {
}

