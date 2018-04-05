package com.github.base.browser;

import com.github.utils.PropertyProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class DriverModule extends AbstractModule {
    private final String PROPERTIES_FILE = System.getProperty("tst.pr");

    @Override
    protected void configure() {
        Properties props = new PropertyProvider(PROPERTIES_FILE).getProperties();
        Names.bindProperties(binder(), props);
        bind(WebDriver.class).toProvider(DriverProvider.class).in(Scopes.SINGLETON);

    }

    /*@Provides
    public WebDriver getDriver(DriverManager driverManager) {
        return driverManager.getDriver();
    }*/
}
