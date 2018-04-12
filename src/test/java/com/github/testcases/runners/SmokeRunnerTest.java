
package com.github.testcases.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

@CucumberOptions(
       // plugin = {"html:target/cucumber-report/smoketest", "json:target/cucumber.json"},
        features = "classpath:features/UpdateProfileSettings.feature",
        glue = "com.github.testcases.stepDefinitions",
        tags = {"@smokeTest"}
)
@Test
public class SmokeRunnerTest extends AbstractTestNGCucumberTests {
}

