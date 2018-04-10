package com.github.base.browser;

import com.github.entities.User;
import com.github.utils.PropertyProvider;
import com.github.website.GithubSite;
import com.google.inject.*;
import com.google.inject.name.Names;
import cucumber.api.guice.CucumberModules;
import cucumber.runtime.java.guice.InjectorSource;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class DriverModule extends AbstractModule implements InjectorSource {
    private final String PROPERTIES_FILE = System.getProperty("tst.pr");

    @Override
    protected void configure() {
        Properties props = new PropertyProvider(PROPERTIES_FILE).getProperties();
        bind(Properties.class).toInstance(props);
        Names.bindProperties(binder(), props);

        bind(WebDriver.class).toProvider(WebProvider.class).in(Scopes.SINGLETON);
        bind(User.class).toProvider(UserProvider.class).in(Scopes.SINGLETON);
        bind(GithubSite.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).in(Scopes.SINGLETON);
    }

    @Override
    public Injector getInjector() {
        return Guice.createInjector(Stage.PRODUCTION, CucumberModules.SCENARIO, new DriverModule());
    }
}
