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

public class BrowserFactory {
    private static BrowserType browserType;
    private static WebDriver driver;
    private static final String CHROMEDRIVER = "webdriver.chrome.driver";
    private static final String CHROMEDRIVER_PATH = System.getProperty("chrdr.ps");
    private static final String GECKODRIVER = "webdriver.gecko.driver";
    private static final String GECKODRIVER_PATH = "./webdrivers/geckodriver.exe";
    private static PropertyProvider props;
    private static String PROPERTIES_FILE = System.getProperty("tst.pr");
    private static final String BROWSER_PROP = "environment.variables.browser";
    private static final String DEFAULT_CONDITION_TIMEOUT = "defaultConditionTimeout"; //TODO: decide if this field necessary
    private static final String DEFAULT_LOAD_TIMEOUT = "test.variables.default.pageLoadTimeout";
    private static final String DEFAULT_ELEMENT_LOAD_TIMEOUT = "defaultElementNotDisplayedTimeout"; //TODO: decide if this field necessary
    private static String timeoutForLoad;
    private static String timeoutForCondition; //TODO: decide if this field necessary
    private static String timeoutForNotDisplayed; //TODO: decide if this field necessary

    private static WebDriver getDriver(final BrowserType browserType) {

        switch (browserType) {
            case CHROME:
                driver = createChromeDriver();
                break;
            case FIREFOX:
                driver = createFirefoxDriver();
                break;
            default:
                driver = createChromeDriver();
        }

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS); //TODO: specify time of implicitly wait in .property file
        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(timeoutForLoad), TimeUnit.SECONDS);
        return driver;
    }

    public static WebDriver getInstance() {
        if(driver == null){
            initProperties();
            getDriver(browserType);
        }
        return driver;
    }

    public  void closeDriver(){
        driver.quit();
        driver = null;
    }

    public static void initProperties() {
        props = new PropertyProvider(PROPERTIES_FILE);
        timeoutForLoad = props.getProperty(DEFAULT_LOAD_TIMEOUT);
        timeoutForCondition = props.getProperty(DEFAULT_CONDITION_TIMEOUT);
        timeoutForNotDisplayed = props.getProperty(DEFAULT_ELEMENT_LOAD_TIMEOUT);
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

        FirefoxOptions ffoptions = new FirefoxOptions();

        ffoptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream")
                .addPreference("browser.download.folderList", 2)
                .addPreference("browser.privatebrowsing.autostart", true);

        System.setProperty(GECKODRIVER,GECKODRIVER_PATH);
        return new FirefoxDriver(ffoptions);
    }

    private void highlightElement (By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='5px solid green'", driver.findElement(locator));
    }

    private void unHighlightElement (By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", driver.findElement(locator));
    }

}
