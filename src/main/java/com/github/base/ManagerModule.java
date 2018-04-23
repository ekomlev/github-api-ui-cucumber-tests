package com.github.base;

import com.github.base.driver.*;
import com.github.entities.User;
import com.github.utils.PropertyProvider;
import com.github.utils.UserProvider;
import com.github.utils.WebProvider;
import com.github.website.GithubSite;
import com.github.website.base.PageFactory;
import com.google.inject.*;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.name.Names;
import cucumber.api.guice.CucumberModules;
import cucumber.runtime.java.guice.InjectorSource;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class ManagerModule extends AbstractModule implements InjectorSource {
    private final String PROPERTIES_FILE = System.getProperty("tst.pr");

    @Override
    protected void configure() {
        Properties props = new PropertyProvider(PROPERTIES_FILE).getProperties();
        bind(Properties.class).toInstance(props);
        Names.bindProperties(binder(), props);

        bind(User.class).toProvider(UserProvider.class).in(Scopes.SINGLETON);
        bind(GithubSite.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).in(Scopes.SINGLETON);
        bind(PageFactory.class);

        MapBinder<BrowserType, WebDriverCreator> mapBinder
                = MapBinder.newMapBinder(binder(), BrowserType.class, WebDriverCreator.class);

        mapBinder.addBinding(BrowserType.CHROME).to(ChromeDriverCreator.class);
        mapBinder.addBinding(BrowserType.FIREFOX).to(FirefoxDriverCreator.class);

        bind(WebDriver.class).toProvider(WebProvider.class).in(Scopes.SINGLETON);
    }

    @Override
    public Injector getInjector() {
        return Guice.createInjector(Stage.PRODUCTION, CucumberModules.SCENARIO, new ManagerModule());
    }
}
