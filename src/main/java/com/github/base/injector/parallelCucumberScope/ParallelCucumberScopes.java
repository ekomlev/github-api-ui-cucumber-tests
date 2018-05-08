package com.github.base.injector.parallelCucumberScope;

import cucumber.runtime.java.guice.ScenarioScope;

class ParallelCucumberScopes {
    static final ScenarioScope SCENARIO = new ParallelScenarioScope();
}
