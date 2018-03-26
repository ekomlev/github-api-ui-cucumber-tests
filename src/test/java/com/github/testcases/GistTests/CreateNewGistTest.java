package com.github.testcases.GistTests;

import com.github.testcases.Base.BaseTest;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class CreateNewGistTest extends BaseTest {

    @Test
    void createNewGist(ITestContext testName) {
        testLogger.info(testName.getCurrentXmlTest().getName(),"started");

        String gistFile = user.getUserGist().getGistFile();
        String gistDescription = user.getUserGist().getGistDescription();
        String gistContent = user.getUserGist().getGistContent();
        boolean gistPublicAccess = user.getUserGist().getGistPublicAccess();

        step(1, "Open menu for creation new entity");
        website.creationNewEntityMenu().waitForCreationNewEntityMenuLink();
        website.creationNewEntityMenu().openCreationNewEntityMenu();

        step(2, "Create new gist");
        website.creationNewEntityMenu().waitForCreationNewEntityMenu();
        website.creationNewEntityMenu().openNewGistPage();

        step(3, "Save new gist");
        website.newGistPage().waitForNewGistForm();
        website.newGistPage().saveNewGist(gistFile, gistDescription, gistContent, gistPublicAccess);

        check("Check if new gist is appeared");
        website.gistPage().waitForGistHeadOfGistPage();
        Assert.assertTrue(website.gistPage().getNameOfOpenedGist().contains(gistFile));

        testLogger.info(testName.getCurrentXmlTest().getName(),"completed");
    }
}
