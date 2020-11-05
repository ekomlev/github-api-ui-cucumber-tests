package com.github.base.driver;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Properties;

public class ChromeDriverFactory extends WebDriverFactory {
    private final String CHROMEDRIVER = "webdriver.chrome.driver";

    @Inject
    ChromeDriverFactory(Properties props) {
        super(props);
    }

    @Override
    public WebDriver createDriver() {
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

        chromePrefs.put("download.directory_upgrade", true);
        chromePrefs.put("driver.helperApps.alwaysAsk.force", false);

        chromePrefs.put("safebrowsing.enabled", true);
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", "false");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("start-maximized", "--incognito");

        System.setProperty(CHROMEDRIVER, KEY_DRIVER_PATH);
        WebDriver webDriver = new ChromeDriver(options);
        setTimeProperties(webDriver);
        logger.info("ChromeDriver is initiated");
        return webDriver;
    }
}