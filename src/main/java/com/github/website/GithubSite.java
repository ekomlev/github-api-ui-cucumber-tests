package com.github.website;

import com.github.base.page.PageFactory;
import com.github.website.components.CreationNewEntityMenu;
import com.github.website.components.UserProfileMenu;
import com.github.website.pages.*;
import com.google.inject.Inject;


public class GithubSite {
    @Inject
    PageFactory pageFactory;


    public GithubSite() {}

    public InitialPage initialPage() {
        return pageFactory.initPage(InitialPage.class);
    }

    public LoginPage loginPage() {
        return pageFactory.initPage(LoginPage.class);
    }

    public CreationNewEntityMenu creationNewEntityMenu() {
        return pageFactory.initPage(CreationNewEntityMenu.class);
    }

    public UserProfileMenu userProfileMenu() {
        return pageFactory.initPage(UserProfileMenu.class);
    }

    public HomePage homePage() {
        return pageFactory.initPage(HomePage.class);
    }

    public ProfilePage profilePage() {
        return pageFactory.initPage(ProfilePage.class);
    }

    public NewRepositoryPage newRepositoryPage() {
        return pageFactory.initPage(NewRepositoryPage.class);
    }

    public RepositoryPage repositoryPage() {
        return pageFactory.initPage(RepositoryPage.class);
    }

    public NewOrganizationPage newOrganizationPage() {
        return pageFactory.initPage(NewOrganizationPage.class);
    }

    public OrganizationsPage organizationsPage() {
        return pageFactory.initPage(OrganizationsPage.class);
    }

    public NewGistPage newGistPage() {
        return pageFactory.initPage(NewGistPage.class);
    }

    public AllYourGistsPage allYourGistsPage() {
        return pageFactory.initPage(AllYourGistsPage.class);
    }

    public GistPage gistPage() {
        return pageFactory.initPage(GistPage.class);
    }

    public ProfileSettingsPage profileSettingsPage() {
        return pageFactory.initPage(ProfileSettingsPage.class);
    }

}

