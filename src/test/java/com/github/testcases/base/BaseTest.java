package com.github.testcases.base;

import com.github.logging.TestLogger;
import com.github.website.GithubSite;

public class BaseTest  {
    protected TestLogger testLogger = TestLogger.getInstance();

    public void setUp() {
         GithubSite.getInstance();
    }

    public void tearDown() {
        GithubSite.reset();
    }

    protected void step(int i, String msg) {
        testLogger.step(i, msg);
    }

    protected void step(String customStepNumber, String msg) {
        testLogger.step(customStepNumber, msg);
    }

    protected void check(String msg) {
        testLogger.check(msg);
    }

}