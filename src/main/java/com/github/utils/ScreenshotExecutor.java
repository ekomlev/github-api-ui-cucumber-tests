package com.github.utils;

import com.github.base.browser.BrowserFactory;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;

public class ScreenshotExecutor extends TestListenerAdapter{
    private static Logger logger = LogManager.getRootLogger();

    @Override
    public void onTestFailure(ITestResult testResult){
        if(testResult.FAILURE == testResult.getStatus()){
            String error = testResult.getName() + "\n" + testResult.getThrowable().getLocalizedMessage();
            logger.error(testResult.getName() + " screenshot saved : " + takeScreenshot(error));
        }

    }

    public  String takeScreenshot(String error){
        String path;

        try {
            File source = ((TakesScreenshot) BrowserFactory.getInstance()).getScreenshotAs(OutputType.FILE);

            path = "./output/screenshots/" + getTimeMark() + ".png";
            FileUtils.copyFile(source, new File(path));
            logger.info("RP_MESSAGE#FILE#{}#{}", path, "screenshot on error");
        } catch (IOException e) {
            path = "Failed to capture screenshot" + e.getMessage();
        }

        return path;
    }

    public static String getTimeMark(){
        DateTime dateTime = new DateTime();
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy_dd_MM_HH_mm_sss");
        return dateTime.toString(dateTimeFormatter);
    }
}
