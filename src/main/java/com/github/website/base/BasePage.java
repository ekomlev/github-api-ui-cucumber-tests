package com.github.website.base;

import com.github.base.driver.TestContextManager;
import com.github.logging.LoggerInstanceProvider;
import com.google.inject.Inject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected TestContextManager driverManager;
    protected WebDriverWait wait;
    private Logger logger = LoggerInstanceProvider.getLogger(BasePage.class);

    @Inject
    public BasePage(TestContextManager driverManager) {
        this.driverManager = driverManager;
        wait = new WebDriverWait(driverManager.getDriver(), 30, 500);
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected void pageInfo(String msg) {
        logger.info(msg);
    }
}
