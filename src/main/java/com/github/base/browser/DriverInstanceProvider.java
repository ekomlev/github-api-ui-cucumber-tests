package com.github.base.browser;

import com.github.utils.PropertyProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DriverInstanceProvider {
    private static BrowserType browserType;
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private static final String CHROMEDRIVER = "webdriver.chrome.DRIVER";
    private static final String CHROMEDRIVER_PATH = System.getProperty("wdr.pr");
    private static final String GECKODRIVER = "webdriver.gecko.DRIVER";
    private static final String GECKODRIVER_PATH = "./webdrivers/geckodriver.exe";
    private static PropertyProvider props;
    private static final String PROPERTIES_FILE = System.getProperty("tst.pr");
    private static final String BROWSER_PROP = "environment.variables.browser";
    private static final String DEFAULT_CONDITION_TIMEOUT = "defaultConditionTimeout"; //TODO: decide if this field necessary
    private static final String DEFAULT_LOAD_TIMEOUT = "test.variables.default.pageLoadTimeout";
    private static final String DEFAULT_ELEMENT_LOAD_TIMEOUT = "defaultElementNotDisplayedTimeout"; //TODO: decide if this field necessary
    private static String timeoutForLoad;
    private static String timeoutForCondition; //TODO: decide if this field necessary
    private static String timeoutForNotDisplayed; //TODO: decide if this field necessary

    private static void getDriver(final BrowserType browserType) {
        if (DRIVER.get() == null) {
            switch (browserType) {
                case CHROME:
                    DRIVER.set(createChromeDriver());
                    break;
                case FIREFOX:
                    DRIVER.set(createFirefoxDriver());
                    break;
                default:
                    DRIVER.set(createChromeDriver());
            }
        }
        DRIVER.get().manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS); //TODO: specify time of implicitly wait in .property file
        DRIVER.get().manage().timeouts().pageLoadTimeout(Long.parseLong(timeoutForLoad), TimeUnit.SECONDS);
    }

    public static WebDriver getDriver() {
        initProperties();
        getDriver(browserType);
        return DRIVER.get();
    }

    public static void closeDriver(){
        DRIVER.get().close();
        DRIVER.remove();
    }

    private static void initProperties() {
        props = new PropertyProvider(PROPERTIES_FILE);
        timeoutForLoad = PropertyProvider.getProperty(DEFAULT_LOAD_TIMEOUT);
        timeoutForCondition = PropertyProvider.getProperty(DEFAULT_CONDITION_TIMEOUT);
        timeoutForNotDisplayed = PropertyProvider.getProperty(DEFAULT_ELEMENT_LOAD_TIMEOUT);
        browserType = BrowserType.valueOf(PropertyProvider.getProperty(BROWSER_PROP).toUpperCase());
    }

    private static WebDriver createChromeDriver(){

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

        chromePrefs.put("download.directory_upgrade", true);
        chromePrefs.put("browser.helperApps.alwaysAsk.force", false);

        chromePrefs.put("safebrowsing.enabled", true);
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", "false");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("start-maximized", "--incognito");

        System.setProperty(CHROMEDRIVER, CHROMEDRIVER_PATH);



        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver(){

        FirefoxOptions ffOptions = new FirefoxOptions();

        ffOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream")
                .addPreference("browser.download.folderList", 2)
                .addPreference("browser.privatebrowsing.autostart", true);

        System.setProperty(GECKODRIVER,GECKODRIVER_PATH);
        return new FirefoxDriver(ffOptions);
    }

    private void highlightElement (By locator) {
        ((JavascriptExecutor) DRIVER).executeScript("arguments[0].style.border='5px solid green'", DRIVER.get().findElement(locator));
    }

    private void unHighlightElement (By locator) {
        ((JavascriptExecutor) DRIVER).executeScript("arguments[0].style.border='0px'", DRIVER.get().findElement(locator));
    }

}
