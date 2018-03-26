package com.github.testcases.RepositoryTests;

import com.github.testcases.Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class CreateNewRepositoryTest extends BaseTest {

    @Test
    void createNewRepository(ITestContext testName) {
        testLogger.info(testName.getCurrentXmlTest().getName(),"started");

        String repositoryName = user.getUserRepository().getRepositoryName();
        String repositoryDescription = user.getUserRepository().getRepositoryName();
        boolean repositoryPublicAccess = user.getUserRepository().getRepositoryPublicAccess();

        step(1, "Check if repository is exist with required name");
        WebElement checkRepository = website.homePage().createdRepositoryAlreadyExists(repositoryName);
        if (checkRepository != null) {
            step("1.1", "Enter existing repository");
            website.homePage().enterExistingRepository(checkRepository);
            website.repositoryPage().openRepositorySettings();
            website.repositoryPage().waitForRepositorySettings();
            step("1.2", "Delete existing repository");
            website.repositoryPage().deleteExistingRepository(repositoryName);
        }

        step(2, "Open menu for creation new entity");
        website.creationNewEntityMenu().waitForCreationNewEntityMenuLink();
        website.creationNewEntityMenu().openCreationNewEntityMenu();

        step(3, "Open new organization page");
        website.creationNewEntityMenu().waitForCreationNewEntityMenu();
        website.creationNewEntityMenu().openNewRepositoryPage();

        website.newRepositoryPage().waitForNewRepositoryForm();

        step(4, "Save new organization");
        website.newRepositoryPage().saveNewRepository(repositoryName, repositoryDescription, repositoryPublicAccess);

        check("Check if created organization is exist");
        Assert.assertTrue(webDriver.getCurrentUrl().contains(repositoryName));

        testLogger.info(testName.getCurrentXmlTest().getName(),"completed");
    }
}
