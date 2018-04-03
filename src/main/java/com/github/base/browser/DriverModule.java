package com.github.base.browser;

import com.google.inject.AbstractModule;

public class DriverModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(DriverManager.class)
                .to(ChromeDriverManager.class)
                .in(Scopes.SINGLETON);

    }
    @Provides
    public WebDriver getDriver(DriverManager driverManager) {
        return driverManager.getDriver();
    }
}
