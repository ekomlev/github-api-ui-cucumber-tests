package com.github.testcases.CommentTests;

import com.github.testcases.Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class CreateNewCommentTests extends BaseTest {

    @Test
    public void createNewComment(ITestContext testName) {
        testLogger.info(testName.getCurrentXmlTest().getName(),"started");

        String commentText = user.getUserComment().getCommentText();
        String gistFile = user.getUserGist().getGistFile();

        step(1, "Open user profile menu");
        website.userProfileMenu().waitForUserProfileMenuLink();
        website.userProfileMenu().openUserProfileMenu();

        step(2, "Open gist page");
        website.userProfileMenu().waitForUserProfileMenu();
        website.userProfileMenu().openYourGistPage();

        step(3, "Open all gists");
        website.newGistPage().waitForGistHeadOfNewGistPage();
        website.newGistPage().openAllYourGistsPage();

        website.allYourGistsPage().waitForAllYourGistsList();

        step(4, "Create new comment if gist file is exist");
        WebElement checkGist = website.allYourGistsPage().gistIsExist(gistFile);
        if (checkGist != null) {
            website.allYourGistsPage().openGist(checkGist);
            website.gistPage().waitForGistCommentForm();
            website.gistPage().saveNewGistComment(commentText);
            website.gistPage().waitForGistComment();
        }

        check("Check if new comment is appeared");
        Assert.assertTrue(website.gistPage().getLastCommentText().contains(commentText));

        testLogger.info(testName.getCurrentXmlTest().getName(),"completed");
    }
}
