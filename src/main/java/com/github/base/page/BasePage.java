package com.github.base.page;

import com.github.base.driver.DriverManager;
import com.github.logging.LoggerInstanceProvider;
import com.google.inject.Inject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    //protected WebDriver webDriver;
 protected  DriverManager driverManager;
    protected WebDriverWait wait;
    private Logger logger = LoggerInstanceProvider.getLogger(BasePage.class);

    @Inject
    public BasePage(DriverManager driverManager) {
      //  this.webDriver = driverManager.getDriver();
        this.driverManager = driverManager;
        wait = new WebDriverWait(driverManager.getDriver(), 30, 500);
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    public void init() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected void pageInfo(String msg) {
        logger.info(msg);
    }
}
