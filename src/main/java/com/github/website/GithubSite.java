package com.github.website;

import com.github.website.components.CreationNewEntityMenu;
import com.github.website.components.UserProfileMenu;
import com.github.website.pages.*;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.openqa.selenium.WebDriver;

import java.net.URL;

@Singleton
public class GithubSite {
    private WebDriver webDriver;

    @Inject
    private CreationNewEntityMenu creationNewEntityMenu;

    @Inject
    private UserProfileMenu userProfileMenu;

    @Inject
    private InitialPage initialPage;

    @Inject
    private LoginPage loginPage;

    @Inject
    private HomePage homePage;

    @Inject
    private ProfilePage profilePage;

    @Inject
    private NewRepositoryPage newRepositoryPage;

    @Inject
    private RepositoryPage repositoryPage;

    @Inject
    private NewOrganizationPage newOrganizationPage;

    @Inject
    private OrganizationsPage organizationsPage;

    @Inject
    private NewGistPage newGistPage;

    @Inject
    private AllYourGistsPage allYourGistsPage;

    @Inject
    private GistPage gistPage;

    @Inject
    private ProfileSettingsPage profileSettingsPage;

    @Inject
    public GithubSite (WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public void open(URL githubUrl) {
        webDriver.navigate().to(githubUrl);
    }

    public void reset() {
        webDriver.close();
    }

    public InitialPage initialPage() {
        return initialPage;
    }

    public LoginPage loginPage() {
        return loginPage;
    }

    public CreationNewEntityMenu creationNewEntityMenu() {
        return creationNewEntityMenu;
    }

    public UserProfileMenu userProfileMenu() {
        return userProfileMenu;
    }

    public HomePage homePage() {
        return homePage;
    }

    public ProfilePage profilePage() {
        return profilePage;
    }

    public NewRepositoryPage newRepositoryPage() {
        return newRepositoryPage;
    }

    public RepositoryPage repositoryPage() {
        return repositoryPage;
    }

    public NewOrganizationPage newOrganizationPage() {
        return newOrganizationPage;
    }

    public OrganizationsPage organizationsPage() {
        return organizationsPage;
    }

    public NewGistPage newGistPage() {
        return newGistPage;
    }

    public AllYourGistsPage allYourGistsPage() {
        return allYourGistsPage;
    }

    public GistPage gistPage() {
        return gistPage;
    }

    public ProfileSettingsPage profileSettingsPage() {
        return profileSettingsPage;
    }

}
