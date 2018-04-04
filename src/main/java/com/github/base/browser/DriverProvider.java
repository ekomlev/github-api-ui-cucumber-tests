package com.github.base.browser;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.util.Map;

public class DriverProvider {

    @Inject
    @Named("environment.variables.browser")
    private BrowserType browserType;

    final Map<BrowserType, DriverManager> driverManagerBinder;

    @Inject
    public DriverProvider(Map<BrowserType, DriverManager> mapBinder) {
        this.driverManagerBinder = mapBinder;
    }

    private DriverManager driverBinder() {
        switch (browserType) {
            case CHROME:
                return driverManagerBinder.get(BrowserType.CHROME);
            case FIREFOX:
                return driverManagerBinder.get(BrowserType.CHROME);
            default:
                return driverManagerBinder.get(BrowserType.CHROME);
        }
    }




    /*private void highlightElement (By locator) {
        ((JavascriptExecutor) DRIVER).executeScript("arguments[0].style.border='5px solid green'", DRIVER.get().findElement(locator));
    }

    private void unHighlightElement (By locator) {
        ((JavascriptExecutor) DRIVER).executeScript("arguments[0].style.border='0px'", DRIVER.get().findElement(locator));
    }*/

}

