package com.github.utils;

import com.github.base.driver.DriverManager;
import com.github.logging.BaseLogger;
import com.google.inject.Inject;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotExecutor extends BaseLogger {
    private WebDriver webDriver;
//    private Logger logger = LoggerInstanceProvider.getLogger(ScreenshotExecutor.class);

    @Inject
    public ScreenshotExecutor(DriverManager driverManager) {
        this.webDriver = driverManager.getDriver();
    }

    public String takeScreenshot(String screenshotName) {
        String path;
        try {
            File source = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            path = "./output/screenshots/" + screenshotName + "_" + getTimeMark() + ".png";
            FileUtils.copyFile(source, new File(path));
            info("RP_MESSAGE#FILE#{}#{}", path, "screenshot on error");
        } catch (IOException e) {
            path = "Failed to capture screenshot" + e.getMessage();
        }
        return path;
    }

    private static String getTimeMark() {
        DateTime dateTime = new DateTime();
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy_dd_MM_HH_mm_sss");
        return dateTime.toString(dateTimeFormatter);
    }
}
