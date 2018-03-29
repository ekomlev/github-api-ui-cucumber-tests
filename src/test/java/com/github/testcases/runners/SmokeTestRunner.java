
package com.github.testcases.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

@CucumberOptions(
       // plugin = {"html:target/cucumber-report/smoketest", "json:target/cucumber.json"},
        features = "src/test/resources/features",
        glue = "src/test/resources/com/github/testcases/stepDefinitions",
        tags = {"@smokeTest"}
)
@Test
public class SmokeTestRunner extends AbstractTestNGCucumberTests {
}

