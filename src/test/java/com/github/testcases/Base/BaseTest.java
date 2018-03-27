package com.github.testcases.Base;

import com.github.base.browser.BrowserFactory;
import com.github.entities.User;
import com.github.logging.TestLogger;
import com.github.utils.PropertyProvider;
import com.github.website.GithubSite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest  {
    protected WebDriver webDriver;
    protected GithubSite website;
    protected WebDriverWait wait;
    private static BrowserFactory browserFactory = new BrowserFactory();
    protected User user;
    protected TestLogger testLogger = TestLogger.getInstance();


    public GithubSite setUp() {
        webDriver = browserFactory.getInstance();
        webDriver.get(PropertyProvider.getProperty("environment.variables.base_url"));
        website = new GithubSite(webDriver);
        return website;
    }


    public void tearDown() {
        if (webDriver != null) {
            browserFactory.closeDriver();
        }
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
