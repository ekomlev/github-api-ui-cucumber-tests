
package com.github.testcases.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "features",
        glue = "com/github/testcases/stepsDefinition",
        tags = {"@smokeTest"}
)
@Test
public class SmokeTestRunner extends AbstractTestNGCucumberTests {
}

