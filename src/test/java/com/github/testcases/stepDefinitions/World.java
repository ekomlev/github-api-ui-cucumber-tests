package com.github.testcases.stepDefinitions;

import com.github.base.browser.BrowserFactory;
import com.github.entities.User;
import com.github.utils.PropertyProvider;
import com.github.utils.UserCreator;
import com.github.website.GithubSite;
import org.openqa.selenium.WebDriver;

public class World {
    GithubSite website = GithubSite.getInstance();
    User user = UserCreator.getInstance();
    String commentText = user.getUserComment().getCommentText();
    String gistFile = user.getUserGist().getGistFile();
    String gistDescription = user.getUserGist().getGistDescription();
    String gistContent = user.getUserGist().getGistContent();
    boolean gistPublicAccess = user.getUserGist().getGistPublicAccess();
    WebDriver webDriver = BrowserFactory.getInstance();
    String organizationName = user.getUserOrganization().getOrganizationName();
    String organizationBillingEmail = user.getUserOrganization().getOrganizationBillingEmail();
    boolean organizationFreePlan = user.getUserOrganization().getOrganizationFreePlan();
    String repositoryName = user.getUserRepository().getRepositoryName();
    String repositoryDescription = user.getUserRepository().getRepositoryName();
    boolean repositoryPublicAccess = user.getUserRepository().getRepositoryPublicAccess();
    String expectedGithubUrl = PropertyProvider.getProperty("environment.variables.base_url") + "/" + user.getUserName();
}
