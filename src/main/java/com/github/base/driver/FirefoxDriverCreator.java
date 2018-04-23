package com.github.base.driver;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class FirefoxDriverCreator extends WebDriverCreator {
    private final String GECKODRIVER = "webdriver.gecko.DRIVER";

    @Inject
    FirefoxDriverCreator(Properties props) {
        super(props);
    }

    @Override
    public WebDriver createDriver() {
        FirefoxOptions ffOptions = new FirefoxOptions();

        ffOptions.addPreference("driver.helperApps.neverAsk.saveToDisk", "application/octet-stream")
                .addPreference("driver.download.folderList", 2)
                .addPreference("driver.privatebrowsing.autostart", true);

        System.setProperty(GECKODRIVER, KEY_DRIVER_PATH);
        WebDriver webDriver = new FirefoxDriver(ffOptions);
        setTimeProperties(webDriver);
        logger.info("FirefoxDriver is initiated");
        return webDriver;
    }
}
