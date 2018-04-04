package com.github.base.browser;

import com.github.utils.PropertyProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.name.Names;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class DriverModule extends AbstractModule {
    private final String PROPERTIES_FILE = System.getProperty("tst.pr");

    @Override
    protected void configure() {
        MapBinder<BrowserType, DriverManager> mapBinder
                = MapBinder.newMapBinder(binder(), BrowserType.class, DriverManager.class);

        mapBinder.addBinding(BrowserType.CHROME).to(ChromeDriverManager.class);
        mapBinder.addBinding(BrowserType.FIREFOX).to(FirefoxDriverManager.class);

        Properties props = new PropertyProvider(PROPERTIES_FILE).getProperties();
        Names.bindProperties(binder(), props);

    }

    @Provides
    public WebDriver getDriver(DriverManager driverManager) {
        return driverManager.getDriver();
    }
}
