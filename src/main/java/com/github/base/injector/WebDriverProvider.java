package com.github.base.injector;

import com.github.base.driver.BrowserType;
import com.github.base.driver.WebDriverFactory;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.Properties;

public class WebDriverProvider implements Provider<WebDriver> {

    private BrowserType browserType;
    private final String KEY_BROWSER_TYPE = "environment.variables.browser";
    private final Map<BrowserType, WebDriverFactory> driverManagerBinder;

    @Inject
    public WebDriverProvider(Map<BrowserType, WebDriverFactory> mapBinder, Properties props) {
        this.driverManagerBinder = mapBinder;
        this.browserType = BrowserType.valueOf(props.getProperty(KEY_BROWSER_TYPE).toUpperCase());
    }

    @Override
    public WebDriver get() {
        WebDriverFactory creator = driverManagerBinder.get(browserType);
        return creator.createDriver();
    }

  /*private void highlightElement (By locator) {
        ((JavascriptExecutor) DRIVER).executeScript("arguments[0].style.border='5px solid green'", DRIVER.get().findElement(locator));
    }

    private void unHighlightElement (By locator) {
        ((JavascriptExecutor) DRIVER).executeScript("arguments[0].style.border='0px'", DRIVER.get().findElement(locator));
    }*/
}
