package com.github.base.browser;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.name.Names;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DriverModule extends AbstractModule {

    @Override
    protected void configure() {
        MapBinder<BrowserType, DriverManager> mapBinder
                = MapBinder.newMapBinder(binder(), BrowserType.class, DriverManager.class);

        mapBinder.addBinding(BrowserType.CHROME).to(ChromeDriverManager.class);
        mapBinder.addBinding(BrowserType.FIREFOX).to(FirefoxDriverManager.class);

        try {
            Properties props = new Properties();
            props.load(new FileInputStream("test.properties"));
            Names.bindProperties(binder(), props);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Provides
    public WebDriver getDriver(DriverManager driverManager) {
        return driverManager.getDriver();
    }
}
