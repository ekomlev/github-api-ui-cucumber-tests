package com.github.base.browser;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;

public class DriverManager {
    WebDriver webDriver; //instance for 1 scenario
    WebProvider webProvider;

    @Inject
    DriverManager (WebDriver webDriver, WebProvider webProvider) {
        this.webDriver = webDriver;
        this.webProvider = webProvider;
    }

    void getDriver(){}
    void resetdriver(){}
}
