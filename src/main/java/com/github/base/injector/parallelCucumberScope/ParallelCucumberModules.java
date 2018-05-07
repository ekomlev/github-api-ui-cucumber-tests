package com.github.base.injector.parallelCucumberScope;

import com.google.inject.Module;
import cucumber.runtime.java.guice.impl.ScenarioModule;

public class ParallelCucumberModules {
    public static final Module SCENARIO = new ScenarioModule(ParallelCucumberScopes.SCENARIO);
}
