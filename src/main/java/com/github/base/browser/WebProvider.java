package com.github.base.browser;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WebProvider implements Provider<WebDriver> {
    private WebDriver webDriver;
    private BrowserType browserType;
    private String pageLoadTimeout;
    private String implicitlyWaitTime;
    private final String CHROMEDRIVER = "webdriver.chrome.DRIVER";
    private final String GECKODRIVER = "webdriver.gecko.DRIVER";
    private final String KEY_DRIVER_PATH = System.getProperty("wdr.pr");
    private final String KEY_BROWSER_TYPE = "environment.variables.browser";
    private final String KEY_PAGE_LOAD_TIMEOUT = "test.variables.default.pageLoadTimeout";
    private final String KEY_IMPLICITLY_WAIT_TIME = "test.variables.default.implicitlyWaitTime";

    @Inject
    WebProvider(Properties props) {
       this.browserType = BrowserType.valueOf(props.getProperty(KEY_BROWSER_TYPE).toUpperCase());
       this.pageLoadTimeout = props.getProperty(KEY_PAGE_LOAD_TIMEOUT);
       this.implicitlyWaitTime = props.getProperty(KEY_IMPLICITLY_WAIT_TIME);
       createDriver(browserType);
    }

    @Override
    public WebDriver get() {
        return webDriver;
    }

    private WebDriver createDriver(BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                webDriver = createChromeDriver();
                break;
            case FIREFOX:
                webDriver = createFirefoxDriver();
                break;
            default:
                webDriver = createChromeDriver();
        }
        setTimeProperties();
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

        System.setProperty(CHROMEDRIVER, KEY_DRIVER_PATH);

        return new ChromeDriver(options);
    }

    private FirefoxDriver createFirefoxDriver() {
        FirefoxOptions ffOptions = new FirefoxOptions();

        ffOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream")
                .addPreference("browser.download.folderList", 2)
                .addPreference("browser.privatebrowsing.autostart", true);

        System.setProperty(GECKODRIVER, KEY_DRIVER_PATH);
        return new FirefoxDriver(ffOptions);
    }

    private void setTimeProperties() {
        webDriver.manage().timeouts().implicitlyWait(Long.parseLong(implicitlyWaitTime), TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(Long.parseLong(pageLoadTimeout), TimeUnit.SECONDS);
    }

    public void closeDriver() {
        webDriver.close();
    }

    /*private void highlightElement (By locator) {
        ((JavascriptExecutor) DRIVER).executeScript("arguments[0].style.border='5px solid green'", DRIVER.get().findElement(locator));
    }

    private void unHighlightElement (By locator) {
        ((JavascriptExecutor) DRIVER).executeScript("arguments[0].style.border='0px'", DRIVER.get().findElement(locator));
    }*/

}

