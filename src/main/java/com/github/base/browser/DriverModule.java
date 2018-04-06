package com.github.base.browser;

import com.github.entities.User;
import com.github.utils.PropertyProvider;
import com.google.inject.*;
import com.google.inject.name.Names;
import cucumber.api.guice.CucumberModules;
import cucumber.runtime.java.guice.InjectorSource;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class DriverModule extends AbstractModule implements InjectorSource {
    private final String PROPERTIES_FILE = System.getProperty("tst.pr");

    //Properties props = new Properties();

    @Override
    protected void configure() {
        /*try {
            props.load(new FileInputStream(PROPERTIES_FILE));
            Names.bindProperties(binder(), props);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Properties props = new PropertyProvider(PROPERTIES_FILE).getProperties();
        Names.bindProperties(binder(), props);

        bind(WebDriver.class).toProvider(new WebProvider(props)).in(Scopes.SINGLETON);
        bind(User.class).toProvider(UserProvider.class).in(Scopes.SINGLETON);
    }

    @Override
    public Injector getInjector() {
        return Guice.createInjector(Stage.PRODUCTION, CucumberModules.SCENARIO, new DriverModule());
    }
}
