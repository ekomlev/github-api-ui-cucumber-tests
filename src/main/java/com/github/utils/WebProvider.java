package com.github.utils;

import com.github.base.driver.BrowserType;
import com.github.base.driver.WebDriverCreator;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.Properties;

public class WebProvider implements Provider<WebDriver> {

    private BrowserType browserType;
    private final String KEY_BROWSER_TYPE = "environment.variables.browser";
    private final Map<BrowserType, WebDriverCreator> driverManagerBinder;

    @Inject
    public WebProvider(Map<BrowserType, WebDriverCreator> mapBinder, Properties props) {
        this.driverManagerBinder = mapBinder;
        this.browserType = BrowserType.valueOf(props.getProperty(KEY_BROWSER_TYPE).toUpperCase());
    }

    @Override
    public WebDriver get() {
        WebDriverCreator creator = driverManagerBinder.get(browserType);
        return creator.createDriver();
    }

  /*private void highlightElement (By locator) {
        ((JavascriptExecutor) DRIVER).executeScript("arguments[0].style.border='5px solid green'", DRIVER.get().findElement(locator));
    }

    private void unHighlightElement (By locator) {
        ((JavascriptExecutor) DRIVER).executeScript("arguments[0].style.border='0px'", DRIVER.get().findElement(locator));
    }*/
}
