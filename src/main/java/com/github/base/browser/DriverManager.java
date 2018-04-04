package com.github.base.browser;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
    private final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public WebDriver getDriver() {
        createDriver();
        return DRIVER.get();
    }

    protected abstract void createDriver();

    public void closeDriver(){
        DRIVER.get().close();
        DRIVER.remove();
    }


}
