package com.github.base.browser;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;

import javax.inject.Named;
import java.util.concurrent.TimeUnit;

public abstract class DriverManager {
    private final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    @Inject
    @Named("test.variables.default.pageLoadTimeout")
    private String pageLoadTimeout;

    @Named("test.variables.default.implicitlyWaitTime")
    private String implicitlyWaitTime;
    
    public WebDriver getDriver() {
        DRIVER.set(createDriver());
        setTimeProperties();
        return DRIVER.get();
    }

    private void setTimeProperties() {
        DRIVER.get().manage().timeouts().implicitlyWait(Long.parseLong(implicitlyWaitTime), TimeUnit.SECONDS);
        DRIVER.get().manage().timeouts().pageLoadTimeout(Long.parseLong(pageLoadTimeout), TimeUnit.SECONDS);
    }

    protected abstract WebDriver createDriver();

    public void closeDriver() {
        DRIVER.get().close();
        DRIVER.remove();
    }


}
