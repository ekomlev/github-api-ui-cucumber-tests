package com.github.base;

import com.github.logging.PageLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver webDriver;
    protected WebDriverWait wait;
    protected PageLogger pagelogger;

    public BasePage(WebDriver driver) {
        webDriver = driver;
        wait = new WebDriverWait(webDriver, 30, 500);
        this.pagelogger = PageLogger.getInstance();
        PageFactory.initElements(webDriver, this);
    }
}
