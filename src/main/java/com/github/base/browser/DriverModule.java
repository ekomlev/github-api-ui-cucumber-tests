package com.github.base.browser;

import com.github.utils.PropertyProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Scopes;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class DriverModule extends AbstractModule {
    private final String PROPERTIES_FILE = System.getProperty("tst.pr");
    @Inject
    @Named("environment.variables.browser")
    private BrowserType browserType;

    @Override
    protected void configure() {
        Properties props = new PropertyProvider(PROPERTIES_FILE).getProperties();
        Names.bindProperties(binder(), props);
        bind(WebDriver.class).toInstance((WebDriver) new DriverProvider(browserType)); //.in(Scopes.SINGLETON);

    }

    /*@Provides
    public WebDriver getDriver(DriverManager driverManager) {
        return driverManager.getDriver();
    }*/
}
