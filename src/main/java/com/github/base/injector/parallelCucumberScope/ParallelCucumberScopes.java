package com.github.base.injector.parallelCucumberScope;

import cucumber.runtime.java.guice.ScenarioScope;

public class ParallelCucumberScopes {
    public static final ScenarioScope SCENARIO = new ParallelScenarioScope();
}
