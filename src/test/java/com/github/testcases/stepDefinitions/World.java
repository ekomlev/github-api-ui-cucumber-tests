package com.github.testcases.stepDefinitions;

import com.github.base.browser.WebProvider;
import com.github.entities.User;
import com.github.logging.LoggerInstanceProvider;
import com.github.website.GithubSite;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class World {
    GithubSite website;
    WebProvider webDriver;
    User user;

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

    @Inject
    @Named("environment.variables.base_url")
    private URL githubUrl;
    String expectedGithubUrl = githubUrl + "/" + user.getUserName();

    @Inject
    public World (User user, GithubSite website, WebProvider webDriver) {
        this.user = user;
        this.website = website;
        this.webDriver = webDriver;
    }

    private Logger logger = LoggerInstanceProvider.getLogger(World.class);

    void setUp() {
        website.open(githubUrl);
    }

    void tearDown() {
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
