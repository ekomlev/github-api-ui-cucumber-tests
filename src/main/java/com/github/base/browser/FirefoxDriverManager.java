package com.github.base.browser;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager extends DriverManager {
    private final String GECKODRIVER = "webdriver.gecko.DRIVER";
    private final String GECKODRIVER_PATH = System.getProperty("wdr.pr");

    @Override
    protected FirefoxDriver createDriver() {
        FirefoxOptions ffOptions = new FirefoxOptions();

        ffOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream")
                .addPreference("browser.download.folderList", 2)
                .addPreference("browser.privatebrowsing.autostart", true);

        System.setProperty(GECKODRIVER,GECKODRIVER_PATH);
        return new FirefoxDriver(ffOptions);
    }
}
