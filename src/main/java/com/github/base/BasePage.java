package com.github.base;

import com.github.logging.LoggerInstanceProvider;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver webDriver;
    protected WebDriverWait wait;
    private Logger logger = LoggerInstanceProvider.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        webDriver = driver;
        wait = new WebDriverWait(webDriver, 30, 500);
        PageFactory.initElements(webDriver, this);
    }

    protected void pageInfo(String msg) {
        logger.info(msg);
    }
}
