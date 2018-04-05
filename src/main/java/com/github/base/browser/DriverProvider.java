package com.github.base.browser;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DriverProvider implements Provider<WebDriver> {
    private final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private WebDriver webDriver;
    private final String CHROMEDRIVER = "webdriver.chrome.DRIVER";
    private final String GECKODRIVER = "webdriver.gecko.DRIVER";
    private final String DRIVER_PATH = System.getProperty("wdr.pr");

    @Inject
    @javax.inject.Named("test.variables.default.pageLoadTimeout")
    private String pageLoadTimeout;

    @javax.inject.Named("test.variables.default.implicitlyWaitTime")
    private String implicitlyWaitTime;

    @Inject
    public DriverProvider(BrowserType browserType) {
        DRIVER.set(createDriver(browserType));
        setTimeProperties();
    }

    @Override
    public WebDriver get() {
        return DRIVER.get();
    }

    private WebDriver createDriver(final BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                webDriver = createChromeDriver();
            case FIREFOX:
                webDriver = createFirefoxDriver();
            default:
                webDriver = createChromeDriver();
        }
        return webDriver;
    }

    private WebDriver createChromeDriver() {
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

        chromePrefs.put("download.directory_upgrade", true);
        chromePrefs.put("browser.helperApps.alwaysAsk.force", false);

        chromePrefs.put("safebrowsing.enabled", true);
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", "false");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("start-maximized", "--incognito");

        System.setProperty(CHROMEDRIVER, DRIVER_PATH);

        return new ChromeDriver(options);
    }

    private FirefoxDriver createFirefoxDriver() {
        FirefoxOptions ffOptions = new FirefoxOptions();

        ffOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream")
                .addPreference("browser.download.folderList", 2)
                .addPreference("browser.privatebrowsing.autostart", true);

        System.setProperty(GECKODRIVER, DRIVER_PATH);
        return new FirefoxDriver(ffOptions);
    }

    private void setTimeProperties() {
        DRIVER.get().manage().timeouts().implicitlyWait(Long.parseLong(implicitlyWaitTime), TimeUnit.SECONDS);
        DRIVER.get().manage().timeouts().pageLoadTimeout(Long.parseLong(pageLoadTimeout), TimeUnit.SECONDS);
    }

    public void closeDriver() {
        DRIVER.get().close();
        DRIVER.remove();
    }

    /*private void highlightElement (By locator) {
        ((JavascriptExecutor) DRIVER).executeScript("arguments[0].style.border='5px solid green'", DRIVER.get().findElement(locator));
    }

    private void unHighlightElement (By locator) {
        ((JavascriptExecutor) DRIVER).executeScript("arguments[0].style.border='0px'", DRIVER.get().findElement(locator));
    }*/

}

