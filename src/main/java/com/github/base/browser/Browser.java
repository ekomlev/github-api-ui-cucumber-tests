package com.github.base.browser;

import com.github.utils.PropertyProvider;
import com.google.inject.Singleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Singleton
public class Browser {
    private BrowserType browserType;
    private final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private final String CHROMEDRIVER = "webdriver.chrome.DRIVER";
    private final String CHROMEDRIVER_PATH = System.getProperty("wdr.pr");
    private final String GECKODRIVER = "webdriver.gecko.DRIVER";
    private final String GECKODRIVER_PATH = "./webdrivers/geckodriver.exe";
    private PropertyProvider props;
    private final String PROPERTIES_FILE = System.getProperty("tst.pr");
    private final String BROWSER_PROP = "environment.variables.browser";
    private final String DEFAULT_CONDITION_TIMEOUT = "defaultConditionTimeout"; //TODO: decide if this field necessary
    private final String DEFAULT_LOAD_TIMEOUT = "test.variables.default.pageLoadTimeout";
    private final String DEFAULT_ELEMENT_LOAD_TIMEOUT = "defaultElementNotDisplayedTimeout"; //TODO: decide if this field necessary
    private String timeoutForLoad;
    private String timeoutForCondition; //TODO: decide if this field necessary
    private String timeoutForNotDisplayed; //TODO: decide if this field necessary

    public WebDriver getDriver() {
        initProperties();
        createDriver(browserType);
        return DRIVER.get();
    }

    public void closeDriver(){
        DRIVER.get().close();
        DRIVER.remove();
    }

    private void createDriver(final BrowserType browserType) {
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



    private void initProperties() {
        props = new PropertyProvider(PROPERTIES_FILE);
        timeoutForLoad = PropertyProvider.getProperty(DEFAULT_LOAD_TIMEOUT);
        timeoutForCondition = PropertyProvider.getProperty(DEFAULT_CONDITION_TIMEOUT);
        timeoutForNotDisplayed = PropertyProvider.getProperty(DEFAULT_ELEMENT_LOAD_TIMEOUT);
        browserType = BrowserType.valueOf(PropertyProvider.getProperty(BROWSER_PROP).toUpperCase());
    }

    private WebDriver createChromeDriver(){

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

    private WebDriver createFirefoxDriver(){

        FirefoxOptions ffOptions = new FirefoxOptions();

        ffOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream")
                .addPreference("browser.download.folderList", 2)
                .addPreference("browser.privatebrowsing.autostart", true);

        System.setProperty(GECKODRIVER,GECKODRIVER_PATH);
        return new FirefoxDriver(ffOptions);
    }



}
