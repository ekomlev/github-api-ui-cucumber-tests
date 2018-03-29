package com.github.website;

import com.github.base.browser.BrowserFactory;
import com.github.utils.PropertyProvider;
import com.github.website.components.CreationNewEntityMenu;
import com.github.website.components.UserProfileMenu;
import com.github.website.pages.*;
import org.openqa.selenium.WebDriver;

public class GithubSite {
    private static GithubSite instance;
    private static WebDriver webDriver;

    private GithubSite(WebDriver driver) {
        webDriver = driver;
    }

    public static GithubSite getInstance() {
        if (instance == null) {
            webDriver = BrowserFactory.getInstance();
            webDriver.get(PropertyProvider.getProperty("environment.variables.base_url"));
            instance = new GithubSite(webDriver);
        }
        return instance;
    }

    public static void reset() {
        BrowserFactory.closeDriver();
        instance = null;
    }

    public InitialPage initialPage() {
        return new InitialPage(webDriver);
    }

    public LoginPage loginPage() {
        return new LoginPage(webDriver);
    }

    public CreationNewEntityMenu creationNewEntityMenu() {
        return new CreationNewEntityMenu(webDriver);
    }

    public UserProfileMenu userProfileMenu() {
        return new UserProfileMenu(webDriver);
    }

    public HomePage homePage() {
        return new HomePage(webDriver);
    }

    public ProfilePage profilePage() {
        return new ProfilePage(webDriver);
    }

    public NewRepositoryPage newRepositoryPage() {
        return new NewRepositoryPage(webDriver);
    }

    public RepositoryPage repositoryPage() {
        return new RepositoryPage(webDriver);
    }

    public NewOrganizationPage newOrganizationPage() {
        return new NewOrganizationPage(webDriver);
    }

    public OrganizationsPage organizationsPage() {
        return new OrganizationsPage(webDriver);
    }

    public NewGistPage newGistPage() {
        return new NewGistPage(webDriver);
    }

    public AllYourGistsPage allYourGistsPage() {
        return new AllYourGistsPage(webDriver);
    }

    public GistPage gistPage() {
        return new GistPage(webDriver);
    }

    public ProfileSettingsPage profileSettingsPage() {
        return new ProfileSettingsPage(webDriver);
    }

}
