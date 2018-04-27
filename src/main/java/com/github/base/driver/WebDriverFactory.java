package com.github.base.driver;

import com.github.logging.BaseLogger;
import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public abstract class WebDriverFactory {
    private String pageLoadTimeout;
    private String implicitlyWaitTime;
    private final String KEY_PAGE_LOAD_TIMEOUT = "test.variables.default.pageLoadTimeout";
    private final String KEY_IMPLICITLY_WAIT_TIME = "test.variables.default.implicitlyWaitTime";
    String KEY_DRIVER_PATH = System.getProperty("wdr.pr");
    BaseLogger logger = new BaseLogger();

    @Inject
    WebDriverFactory(Properties props) {
        this.pageLoadTimeout = props.getProperty(KEY_PAGE_LOAD_TIMEOUT);
        this.implicitlyWaitTime = props.getProperty(KEY_IMPLICITLY_WAIT_TIME);
    }

    public abstract WebDriver createDriver();

    void setTimeProperties(WebDriver webDriver) {
        webDriver.manage().timeouts().implicitlyWait(Long.parseLong(implicitlyWaitTime), TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(Long.parseLong(pageLoadTimeout), TimeUnit.SECONDS);
    }
}
