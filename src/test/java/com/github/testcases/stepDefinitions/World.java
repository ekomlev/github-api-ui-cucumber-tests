package com.github.testcases.stepDefinitions;

import com.github.base.browser.Browser;
import com.github.base.browser.DriverModule;
import com.github.entities.User;
import com.github.logging.LoggerInstanceProvider;
import com.github.utils.PropertyProvider;
import com.github.utils.UserCreator;
import com.github.website.GithubSite;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class World {
    //GithubSite website = new GithubSite();
    User user = UserCreator.getInstance();
    String commentText = user.getUserComment().getCommentText();
    String gistFile = user.getUserGist().getGistFile();
    String gistDescription = user.getUserGist().getGistDescription();
    String gistContent = user.getUserGist().getGistContent();
    boolean gistPublicAccess = user.getUserGist().getGistPublicAccess();

    String organizationName = user.getUserOrganization().getOrganizationName();
    String organizationBillingEmail = user.getUserOrganization().getOrganizationBillingEmail();
    boolean organizationFreePlan = user.getUserOrganization().getOrganizationFreePlan();
    String repositoryName = user.getUserRepository().getRepositoryName();
    String repositoryDescription = user.getUserRepository().getRepositoryName();
    boolean repositoryPublicAccess = user.getUserRepository().getRepositoryPublicAccess();
    String expectedGithubUrl = PropertyProvider.getProperty("environment.variables.base_url") + "/" + user.getUserName();


    Injector guice = Guice.createInjector(new DriverModule());
    GithubSite website = guice.getInstance(GithubSite.class);
    Browser browser = new Browser();
    WebDriver webDriver = browser.getDriver();

    private Logger logger = LoggerInstanceProvider.getLogger(World.class);

    public GithubSite setUp() {
        return website;
    }

    public void tearDown() {
        website.reset();
    }

    void step(int i, String msg) {
        logger.info(String.format("[Step %1$d]: %2$s", i, msg));
    }

    void subStep(String customStepNumber, String msg) {
        logger.info(String.format("[Step %1$s]: %2$s", customStepNumber, msg));
    }

    void test(String testName, String msg) {
        logger.info(String.format("==================Test '%1$s' %2$s =====================", testName, msg));
    }

    void error (String msg) {
        logger.error(msg);
    }
    private void info (String msg, Object p0, Object p1) {
        logger.info(msg, p0, p1);
    }

    void check(String msg) {
        logger.info(msg);
    }

    String takeScreenshot(String screenshotName) {
        String path;
        try {
            File source = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            path = "./output/screenshots/" + getTimeMark() + ".png";
            FileUtils.copyFile(source, new File(path));
            info("RP_MESSAGE#FILE#{}#{}", path, "screenshot on error");
        } catch (IOException e) {
            path = "Failed to capture screenshot" + e.getMessage();
        }
        return path;
    }

    private static String getTimeMark(){
        DateTime dateTime = new DateTime();
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy_dd_MM_HH_mm_sss");
        return dateTime.toString(dateTimeFormatter);
    }
}
