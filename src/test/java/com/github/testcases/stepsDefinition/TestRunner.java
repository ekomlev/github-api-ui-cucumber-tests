
package com.github.testcases.stepsDefinition;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features",
       //glue = "src/test/java/com/github/testcases/stepsDefinition",
        tags = "@smokeTest"
)
@Test
public class TestRunner extends AbstractTestNGCucumberTests {
}

