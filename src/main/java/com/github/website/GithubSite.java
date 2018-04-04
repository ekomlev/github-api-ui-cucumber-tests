package com.github.website;

import com.github.base.browser.DriverManager;
import com.github.website.components.CreationNewEntityMenu;
import com.github.website.components.UserProfileMenu;
import com.github.website.pages.*;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import java.net.URL;

@Singleton
public class GithubSite {
    private DriverManager driverManager;

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
    @Named("environment.variables.base_url")
    private URL githubUrl;

    @Inject
    public GithubSite (DriverManager driverManager) {
        this.driverManager = driverManager;
        driverManager.getDriver().navigate().to(githubUrl);
    }

    public void reset() {
        driverManager.closeDriver();
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
