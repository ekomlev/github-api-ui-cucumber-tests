package com.github.base.injector;

import com.github.base.driver.*;
import com.github.base.injector.parallelCucumberScope.ParallelCucumberModules;
import com.github.entities.User;
import com.github.website.GithubSite;
import com.github.website.base.PageFactory;
import com.google.inject.*;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.name.Names;
import cucumber.runtime.java.guice.InjectorSource;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class ManagerModule extends AbstractModule implements InjectorSource {
    private final String PROPERTIES_FILE = System.getProperty("tst.pr");

    @Override
    public void configure() {
        Properties props = new PropertyProvider(PROPERTIES_FILE).getProperties();
        bind(Properties.class).toInstance(props);
        Names.bindProperties(binder(), props);

        bind(User.class).toProvider(UserProvider.class).in(Scopes.SINGLETON);
        bind(GithubSite.class).in(Scopes.SINGLETON);
        bind(TestContextManager.class).in(Scopes.SINGLETON);
        bind(PageFactory.class);

        MapBinder<BrowserType, WebDriverFactory> mapBinder
                = MapBinder.newMapBinder(binder(), BrowserType.class, WebDriverFactory.class);

        mapBinder.addBinding(BrowserType.CHROME).to(ChromeDriverFactory.class);
        mapBinder.addBinding(BrowserType.FIREFOX).to(FirefoxDriverFactory.class);

        bind(WebDriver.class).toProvider(WebDriverProvider.class).in(Scopes.SINGLETON);
    }

    @Override
    public Injector getInjector() {
        return Guice.createInjector(Stage.PRODUCTION, ParallelCucumberModules.SCENARIO, new ManagerModule());
    }
}
