package com.github.testcases.base;

import com.github.base.browser.BrowserFactory;
import com.github.logging.TestLogger;
import com.github.utils.PropertyProvider;
import com.github.website.GithubSite;
import org.openqa.selenium.WebDriver;

public class BaseTest  {
    protected WebDriver webDriver;
    private GithubSite website; //TODO: Check "Field can be converted to local variable"
    protected TestLogger testLogger = TestLogger.getInstance();

    public GithubSite setUp() {
        webDriver = BrowserFactory.getInstance(); //TODO: To realise BrowserFactory.getInstance() via GithubSite.getInstance(webDriver)
        webDriver.get(PropertyProvider.getProperty("environment.variables.base_url"));
        website = GithubSite.getInstance(webDriver);
        return website;
    }

    public void tearDown() {
        BrowserFactory.closeDriver(); //TODO: To realise BrowserFactory.closeDriver() via GithubSite.reset
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
